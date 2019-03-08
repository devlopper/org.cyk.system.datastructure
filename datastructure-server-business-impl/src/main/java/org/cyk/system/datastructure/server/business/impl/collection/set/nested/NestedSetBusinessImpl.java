package org.cyk.system.datastructure.server.business.impl.collection.set.nested;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Singleton;

import org.cyk.system.datastructure.server.business.api.collection.set.nested.NestedSetBusiness;
import org.cyk.system.datastructure.server.persistence.api.collection.set.nested.NestedSetPersistence;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessFunctionCreator;
import org.cyk.utility.server.business.BusinessFunctionModifier;
import org.cyk.utility.server.business.BusinessFunctionRemover;

@Singleton
public class NestedSetBusinessImpl extends AbstractBusinessEntityImpl<NestedSet,NestedSetPersistence> implements NestedSetBusiness , Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Class<NestedSet> __getPersistenceEntityClass__() {
		return NestedSet.class;
	}
	
	@Override
	protected void __listenExecuteCreateOneBefore__(NestedSet nestedSet, Properties properties,BusinessFunctionCreator function) {
		super.__listenExecuteCreateOneBefore__(nestedSet, properties, function);
		function.try_().begin().addRunnables(new Runnable() {
			@Override
			public void run() {
				if(nestedSet.getParent() == null){
					nestedSet.setLeftIndex(0);//First set
					if(__injectStringHelper__().isBlank(nestedSet.getGroup())) {
						nestedSet.setGroup(nestedSet.getCode());
					}
				}else {		
					NestedSet parent = nestedSet.getParent();
					Integer parentRightIndex = parent.getRightIndex();
					nestedSet.setGroup(parent.getGroup());
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
	}
	
	@Override
	protected void __listenExecuteUpdateOneBefore__(NestedSet nestedSet, Properties properties,BusinessFunctionModifier function) {
		super.__listenExecuteUpdateOneBefore__(nestedSet, properties, function);
		function.try_().setIsCodeFromFunctionExecutable(Boolean.FALSE).run().addRunnables( new Runnable() {
			@Override
			public void run() {
				NestedSet nestedSetInDb = getPersistence().readOne(nestedSet.getIdentifier());
				Boolean hasBeenMoved = Boolean.TRUE.equals(hasBeenMoved(nestedSetInDb.getParent(), nestedSet.getParent()));
				if(hasBeenMoved){
					//Move nested set to its new parent
					//1 - get the nested sets instances
					Collection<NestedSet> nestedSets = getPersistence().readByGroupWhereLeftIndexAndRightIndexBetween(nestedSet.getGroup(), nestedSet.getLeftIndex()-1, nestedSet.getRightIndex()+1);
					//2 - delete nested set from its current parent
					delete(nestedSetInDb);
					//3 - instantiate nested sets under the new parent
					Collection<NestedSet> nestedSetsToBeCreated = new ArrayList<>();
					for(NestedSet index : nestedSets){
						NestedSet nestedSetToBeCreated = new NestedSet().setCode(index.getCode()).setGroup(index.getGroup());
						for(NestedSet indexNestedSetToBeCreated : nestedSetsToBeCreated)
							if(indexNestedSetToBeCreated.getCode().equals(index.getParent().getCode())){
								nestedSetToBeCreated.setParent(getPersistence().readOne(indexNestedSetToBeCreated.getIdentifier()));
								break;
							}
						if(nestedSetToBeCreated.getParent() == null)
							nestedSetToBeCreated.setParent(getPersistence().readOne(nestedSet.getParent().getIdentifier()));
						nestedSetsToBeCreated.add(nestedSetToBeCreated);
					}
					//4 - create nested sets under the new parent
					//TODO We are get validation issue (left index which is null) when using createMany.
					//createMany(nestedSetsToBeCreated);
					
					//We will iterate the collection and use create
					for(NestedSet index : nestedSetsToBeCreated)
						create(index);					
				}else{
					//simple update
					getPersistence().update(nestedSet);
				}
			}
		});
	}
	
	private Boolean hasBeenMoved(NestedSet source,NestedSet destination){
		if(source == null)
			if(destination == null)
				return Boolean.FALSE;
			else
				return Boolean.TRUE;
		else
			if(destination == null)
				return Boolean.TRUE;
			else
				return !source.equals(destination);
	}
	
	@Override
	protected void __listenExecuteDeleteOneBefore__(NestedSet nestedSet, Properties properties,BusinessFunctionRemover function) {
		super.__listenExecuteDeleteOneBefore__(nestedSet, properties, function);
		function.try_().setIsCodeFromFunctionExecutable(Boolean.FALSE).run().addRunnables(new Runnable() {
			@Override
			public void run() {
				Integer leftIndex = nestedSet.getLeftIndex()-1;
				Integer rightIndex = nestedSet.getRightIndex()+1;
				Integer increment = -getPersistence().countByGroupWhereLeftIndexAndRightIndexBetween(nestedSet.getGroup(),leftIndex,rightIndex).intValue()*2;
				//Delete the tree
				getPersistence().executeDeleteByGroupWhereLeftIndexAndRightIndexBetween(nestedSet.getGroup(),leftIndex,rightIndex);
				//Decrement indexes
				getPersistence().executeIncrementLeftIndexAndRightIndexByGroupByLeftIndexOrRightIndexGreaterThanOrEqualTo(
						nestedSet.getGroup(), rightIndex, nestedSet.getRightIndex(), increment);
				//Decrement parent children
				if(nestedSet.getParent()!=null)
					getPersistence().executeIncrementNumberOfChildren(__injectCollectionHelper__().instanciate(nestedSet.getParent()), -1);	
			}
		});
	}
	
	private Collection<NestedSet> getWhereBoundariesGreaterThanOrEqualTo(Collection<NestedSet> nestedSetNodes,Boolean left,Integer index){
		Collection<NestedSet> result = new ArrayList<>();
		for(NestedSet nestedSetNode : nestedSetNodes)
			if( (Boolean.TRUE.equals(left) && nestedSetNode.getLeftIndex()>=index) || (Boolean.FALSE.equals(left) && nestedSetNode.getRightIndex()>=index) )
				result.add(nestedSetNode);
		return result;
	}

	
	@Override
	public Collection<NestedSet> findByGroup(String group) {
		return getPersistence().readByGroup(group);
	}

	
	@Override
	public Long countByGroup(String group) {
		return getPersistence().countByGroup(group);
	}

	
	@Override
	public Collection<NestedSet> findByParent(NestedSet nestedSet, Properties properties) {
		return getPersistence().readByParent(nestedSet, properties);
	}

	
	@Override
	public Collection<NestedSet> findByParent(NestedSet nestedSet) {
		return findByParent(nestedSet,null);
	}

	
	@Override
	public Collection<NestedSet> findByParent(String nestedSetCode, Properties properties) {
		return getPersistence().readByParent(nestedSetCode,properties);
	}

	
	@Override
	public Collection<NestedSet> findByParent(String nestedSetCode) {
		return findByParent(nestedSetCode,null);
	}

	
	@Override
	public Long countByParent(NestedSet nestedSet, Properties properties) {
		return getPersistence().countByParent(nestedSet, properties);
	}

	
	@Override
	public Long countByParent(NestedSet nestedSet) {
		return countByParent(nestedSet,null);
	}

	
	@Override
	public Long countByParent(String nestedSetCode, Properties properties) {
		return getPersistence().countByParent(nestedSetCode, properties);
	}

	
	@Override
	public Long countByParent(String nestedSetCode) {
		return countByParent(nestedSetCode, null);
	}
	
}
