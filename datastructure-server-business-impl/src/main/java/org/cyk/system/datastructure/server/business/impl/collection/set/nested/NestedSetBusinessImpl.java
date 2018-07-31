package org.cyk.system.datastructure.server.business.impl.collection.set.nested;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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
				}
				nestedSet.setRightIndex(nestedSet.getLeftIndex()+1);
			}
		});
		
		return super.create(nestedSet, properties);
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
			//assert post condition : leftIndex > -1
			__inject__(AssertionBuilderComparison.class)
			.getAssertedValue1(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_LEFT_INDEX)).getParentAs(AssertionBuilderComparison.class)
			.getAssertedValue2(Boolean.TRUE).setValue(-1).getParentAs(AssertionBuilderComparison.class).setOperator(ComparisonOperator.GT).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
			.execute();
			
			//assert post condition : rightIndex > -1
			__inject__(AssertionBuilderComparison.class)
			.getAssertedValue1(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_RIGHT_INDEX)).getParentAs(AssertionBuilderComparison.class)
			.getAssertedValue2(Boolean.TRUE).setValue(-1).getParentAs(AssertionBuilderComparison.class).setOperator(ComparisonOperator.GT).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
			.execute();
			
			//assert post condition : leftIndex < rightIndex
			__inject__(AssertionBuilderComparison.class)
			.getAssertedValue1(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_LEFT_INDEX)).getParentAs(AssertionBuilderComparison.class)
			.getAssertedValue2(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_RIGHT_INDEX)).getParentAs(AssertionBuilderComparison.class)
			.setOperator(ComparisonOperator.LT).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
			.execute();
			
			Long numberOfSet = getPersistence().countByGroup(nestedSet.getGroup());
			//assert post condition : if (is leaf) then rightIndex - leftIndex = 1 else rightIndex - leftIndex > 1
			Integer gap = nestedSet.getRightIndex() - nestedSet.getLeftIndex();
			__inject__(AssertionBuilderComparison.class)
			.getAssertedValue1(Boolean.TRUE).setValue(gap).getParentAs(AssertionBuilderComparison.class)
			.getAssertedValue2(Boolean.TRUE).setValue(1).getParentAs(AssertionBuilderComparison.class)
			.setOperator(nestedSet.getParent() == null ? (numberOfSet == 1 ? ComparisonOperator.EQ : ComparisonOperator.GT) : ComparisonOperator.EQ).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
			.execute();
			
			//assert post condition : count descendant (query) = rightIndex - leftIndex - 1
			Long descendants = getPersistence().countByParent(nestedSet);
			__inject__(AssertionBuilderComparison.class)
			.getAssertedValue1(Boolean.TRUE).setValue(descendants).getParentAs(AssertionBuilderComparison.class)
			.getAssertedValue2(Boolean.TRUE).setValue(gap-1).getParentAs(AssertionBuilderComparison.class)
			.setOperator(ComparisonOperator.EQ).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
			.execute();
			
		}else{
			
		}
	}
	
}
