package org.cyk.system.datastructure.server.business.impl.collection.set.nested;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

import org.cyk.system.datastructure.server.business.api.collection.set.nested.NestedSetBusiness;
import org.cyk.system.datastructure.server.persistence.api.collection.set.nested.NestedSetPersistence;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.__kernel__.computation.ComparisonOperator;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.assertion.AssertionBuilderComparison;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessServiceProvider;
import org.cyk.utility.system.action.SystemAction;

@Singleton
public class NestedSetBusinessImpl extends AbstractBusinessEntityImpl<NestedSet,NestedSetPersistence> implements NestedSetBusiness , Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public BusinessServiceProvider<NestedSet> create(NestedSet nestedSet, Properties properties) {
		properties = addExecutionPhaseRunnables(properties, Boolean.TRUE, new Runnable() {
			@Override
			public void run() {
				if(nestedSet.getParent() == null){
					nestedSet.setLeftIndex(0);//First set
				}else {
					nestedSet.setGroup(nestedSet.getParent().getGroup());
					nestedSet.setLeftIndex(nestedSet.getParent().getRightIndex()+1);
					
					NestedSet parent = nestedSet.getParent();
					Integer parentRightIndex = parent.getRightIndex();
					nestedSet.setLeftIndex(parentRightIndex);
					Collection<NestedSet> nestedSetNodesWhereIndexesToBeRecomputedCandidate = getPersistence().readByGroupByLeftOrRightGreaterThanOrEqualTo(nestedSet.getGroup(), parentRightIndex);
					Collection<NestedSet> nestedSetNodesWhereIndexesToBeRecomputed = new ArrayList<>();
					
					nestedSetNodesWhereIndexesToBeRecomputed.add(parent);
					for(NestedSet index : nestedSetNodesWhereIndexesToBeRecomputedCandidate){
						// Because node parent can be changed by attach so to be ignore
						// only one instance of parent must be handled to avoid inconsistent update
						if(index.equals(nestedSet) || index.equals(parent)){
							
						}else
							nestedSetNodesWhereIndexesToBeRecomputed.add(index);
					}
					getPersistence().executeIncrementLeftIndex(getWhereBoundariesGreaterThanOrEqualTo(nestedSetNodesWhereIndexesToBeRecomputed, Boolean.TRUE, parentRightIndex), 2);
					getPersistence().executeIncrementRightIndex(getWhereBoundariesGreaterThanOrEqualTo(nestedSetNodesWhereIndexesToBeRecomputed, Boolean.FALSE, parentRightIndex), 2);
					getPersistence().executeIncrementNumberOfChildren(__injectCollectionHelper__().instanciate(nestedSet.getParent()), 1);
				}
				nestedSet.setRightIndex(nestedSet.getLeftIndex()+1);
				nestedSet.setNumberOfChildren(0);
				nestedSet.setNumberOfAscendant(getPersistence().countByGroupWhereLeftIndexAndRightIndexContain(nestedSet).intValue());
			}
		});
		
		return super.create(nestedSet, properties);
	}

	@Override
	public BusinessServiceProvider<NestedSet> delete(NestedSet nestedSet, Properties properties) {
		properties = addExecutionPhaseRunnables(properties, Boolean.TRUE, new Runnable() {
			@Override
			public void run() {
				List<NestedSet> tree = new ArrayList<>(getPersistence().readByGroupWhereLeftIndexAndRightIndexBetween(nestedSet));
				Collections.reverse(tree); //TODO this can be done in query using property value.
				tree.add(nestedSet);
				Collection<NestedSet> nestedSets = getPersistence().readByGroupByLeftOrRightGreaterThanOrEqualTo(nestedSet.getGroup(), nestedSet.getRightIndex()+1);
				Collection<NestedSet> nestedSetsLeftIndexToBeUpdate = new ArrayList<>();
				Collection<NestedSet> nestedSetsRightIndexToBeUpdate = new ArrayList<>();
				for(NestedSet index : nestedSets){
					Boolean left = index.getLeftIndex()>nestedSet.getRightIndex()?null:false;
					if(left==null || left)
						nestedSetsLeftIndexToBeUpdate.add(index);
					
					if(left==null || !left)
						nestedSetsRightIndexToBeUpdate.add(index);
				}
				
				int step = -tree.size()*2;
				
				getPersistence().executeDelete(tree);
				
				getPersistence().executeIncrementLeftIndex(nestedSetsLeftIndexToBeUpdate, step);
				getPersistence().executeIncrementRightIndex(nestedSetsRightIndexToBeUpdate, step);
				if(nestedSet.getParent()==null){
					
				}else{
					getPersistence().executeIncrementNumberOfChildren(__injectCollectionHelper__().instanciate(nestedSet.getParent()), -1);	
				}
				
				/*for(NestedSet n : nestedSets){
					//updateBoundaries(n,-step, n.getLeftIndex()>nestedSet.getRightIndex()?null:false);//both bounds or right only
					Boolean left = n.getLeftIndex()>nestedSet.getRightIndex()?null:false;
					
					//getPersistence().executeIncrementLeftIndex(nestedSets, step);
					
					if(left==null || left)
						nestedSet.setLeftIndex(nestedSet.getLeftIndex() + step);
					
					if(left==null || !left)
						nestedSet.setRightIndex(nestedSet.getRightIndex() + step);
					
				}*/	
				
				if(nestedSet.getParent()==null){
					//nestedSet.getSet().setRoot(null);
					//getPersistence().update(nestedSet.getSet());	
				}
				
				//delete tree
				/*for(NestedSet n : tree){
					if(n.getCode().equals(nestedSet.getCode()))
						continue;
					//n.computeLogMessage();
					n.setParent(null);
					getPersistence().delete(n);
				}*/
			}
		});
		properties.setFromPath(new Object[]{Properties.IS, Properties.CORE,Properties.EXECUTABLE},Boolean.FALSE);
		return super.delete(nestedSet, properties);
	}
	
	private Collection<NestedSet> getWhereBoundariesGreaterThanOrEqualTo(Collection<NestedSet> nestedSetNodes,Boolean left,Integer index){
		Collection<NestedSet> result = new ArrayList<>();
		for(NestedSet nestedSetNode : nestedSetNodes)
			if( (Boolean.TRUE.equals(left) && nestedSetNode.getLeftIndex()>=index) || (Boolean.FALSE.equals(left) && nestedSetNode.getRightIndex()>=index) )
				result.add(nestedSetNode);
		return result;
	}
	
	@Override
	protected void ____validateOne____(NestedSet nestedSet, SystemAction action) {
		if(action == null){
			//assert leftIndex > -1
			__inject__(AssertionBuilderComparison.class)
			.getAssertedValue1(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_LEFT_INDEX)).getParentAs(AssertionBuilderComparison.class)
			.getAssertedValue2(Boolean.TRUE).setValue(-1).getParentAs(AssertionBuilderComparison.class).setOperator(ComparisonOperator.GT).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
			.execute();
			
			//assert rightIndex > -1
			__inject__(AssertionBuilderComparison.class)
			.getAssertedValue1(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_RIGHT_INDEX)).getParentAs(AssertionBuilderComparison.class)
			.getAssertedValue2(Boolean.TRUE).setValue(-1).getParentAs(AssertionBuilderComparison.class).setOperator(ComparisonOperator.GT).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
			.execute();
			
			//assert leftIndex < rightIndex
			__inject__(AssertionBuilderComparison.class)
			.getAssertedValue1(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_LEFT_INDEX)).getParentAs(AssertionBuilderComparison.class)
			.getAssertedValue2(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_RIGHT_INDEX)).getParentAs(AssertionBuilderComparison.class)
			.setOperator(ComparisonOperator.LT).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
			.execute();
			
			Long numberOfSet = getPersistence().countByGroup(nestedSet.getGroup());
			//assert if (is leaf) then rightIndex - leftIndex = 1 else rightIndex - leftIndex > 1
			Integer gap = nestedSet.getRightIndex() - nestedSet.getLeftIndex();
			__inject__(AssertionBuilderComparison.class)
			.getAssertedValue1(Boolean.TRUE).setValue(gap).getParentAs(AssertionBuilderComparison.class)
			.getAssertedValue2(Boolean.TRUE).setValue(1).getParentAs(AssertionBuilderComparison.class)
			.setOperator(nestedSet.getParent() == null ? (numberOfSet == 1 ? ComparisonOperator.EQ : ComparisonOperator.GT) : ComparisonOperator.EQ).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
			.execute();
			
			//assert number of children from getter = number of children from query
			Long children = getPersistence().countByParent(nestedSet);
			__inject__(AssertionBuilderComparison.class)
			.getAssertedValue1(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_NUMBER_OF_CHILDREN)).getParentAs(AssertionBuilderComparison.class)
			.getAssertedValue2(Boolean.TRUE).setValue(children).getParentAs(AssertionBuilderComparison.class).setOperator(ComparisonOperator.EQ).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
			.execute();
			
			//assert number of descendant from query = rightIndex - leftIndex - 1
			Long descendants = getPersistence().countByGroupWhereLeftIndexAndRightIndexBetween(nestedSet);
			__inject__(AssertionBuilderComparison.class)
			.getAssertedValue1(Boolean.TRUE).setValue(descendants).getParentAs(AssertionBuilderComparison.class)
			.getAssertedValue2(Boolean.TRUE).setValue(gap-1).getParentAs(AssertionBuilderComparison.class)
			.setOperator(ComparisonOperator.EQ).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
			.execute();
			
			//assert number of descendant from query = number of descendant from getter
			__inject__(AssertionBuilderComparison.class)
			.getAssertedValue1(Boolean.TRUE).setValue(descendants).getParentAs(AssertionBuilderComparison.class)
			.getAssertedValue2(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_NUMBER_OF_DESCENDANT)).getParentAs(AssertionBuilderComparison.class)
			.setOperator(ComparisonOperator.EQ).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
			.execute();
			
			//System.out.println(descendants+" ::: "+nestedSet.getLeftIndex()+"-"+nestedSet.getRightIndex()+" = "+ nestedSet.getNumberOfDescendant());
		}else{
			
		}
	}
	
}
