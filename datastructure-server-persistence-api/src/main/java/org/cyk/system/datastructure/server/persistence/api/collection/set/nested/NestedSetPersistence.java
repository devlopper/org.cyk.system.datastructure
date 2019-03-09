package org.cyk.system.datastructure.server.persistence.api.collection.set.nested;

import java.util.Collection;

import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface NestedSetPersistence extends PersistenceEntity<NestedSet> {

	Collection<NestedSet> readByGroup(String group);
	Long countByGroup(String group);
	
	Collection<NestedSet> readByParent(NestedSet nestedSet,Properties properties);
	Collection<NestedSet> readByParent(NestedSet nestedSet);
	Collection<NestedSet> readByParent(String nestedSetCode,Properties properties);
	Collection<NestedSet> readByParent(String nestedSetCode);
	
	Long countByParent(NestedSet nestedSet,Properties properties);
	Long countByParent(NestedSet nestedSet);
	Long countByParent(String nestedSetCode,Properties properties);
	Long countByParent(String nestedSetCode);
	/*
	Collection<NestedSet> readByDescendant(NestedSet nestedSet,Properties properties);
	Collection<NestedSet> readByDescendant(NestedSet nestedSet);
	Collection<NestedSet> readByDescendant(String nestedSetCode,Properties properties);
	Collection<NestedSet> readByDescendant(String nestedSetCode);
	
	Long countByDescendant(NestedSet nestedSet,Properties properties);
	Long countByDescendant(NestedSet nestedSet);
	Long countByDescendant(String nestedSetCode,Properties properties);
	Long countByDescendant(String nestedSetCode);
	*/
	Collection<NestedSet> readByGroupWhereLeftIndexAndRightIndexBetweenOrderByRightIndexDescending(String group,Integer leftIndex,Integer rightIndex);
	Long countByGroupWhereLeftIndexAndRightIndexBetweenOrderByRightIndexDescending(String group,Integer leftIndex,Integer rightIndex);
	
	Collection<NestedSet> readByGroupWhereLeftIndexAndRightIndexBetweenOrderByRightIndexAscending(String group,Integer leftIndex,Integer rightIndex);
	Long countByGroupWhereLeftIndexAndRightIndexBetweenOrderByRightIndexAscending(String group,Integer leftIndex,Integer rightIndex);
	
	Collection<NestedSet> readByGroupWhereLeftIndexAndRightIndexBetween(NestedSet nestedSet);
	Long countByGroupWhereLeftIndexAndRightIndexBetween(NestedSet nestedSet);
	
	Collection<NestedSet> readByGroupWhereLeftIndexAndRightIndexContain(String group,Integer leftIndex,Integer rightIndex);
	Long countByGroupWhereLeftIndexAndRightIndexContain(String group,Integer leftIndex,Integer rightIndex);
	
	Collection<NestedSet> readByGroupWhereLeftIndexAndRightIndexContain(NestedSet nestedSet);
	Long countByGroupWhereLeftIndexAndRightIndexContain(NestedSet nestedSet);
	
	Collection<NestedSet> readByGroupByLeftOrRightGreaterThanOrEqualTo(String group,Integer value);
	Long countByGroupByLeftOrRightGreaterThanOrEqualTo(String group,Integer value);
	
	void executeIncrementLeftIndex(Collection<NestedSet> nestedSets, Integer increment);
	void executeIncrementRightIndex(Collection<NestedSet> nestedSets, Integer increment);
	void executeIncrementNumberOfChildren(Collection<NestedSet> nestedSets, Integer increment);
	void executeIncrementLeftIndexAndRightIndexByGroupByLeftIndexOrRightIndexGreaterThanOrEqualToByLeftIndexGreaterThan(String group,Integer value1,Integer value2, Integer increment);
	void executeIncrementRightIndexByGroupByLeftIndexOrRightIndexGreaterThanOrEqualToByLeftIndexLessThan(String group,Integer value1,Integer value2, Integer increment);
	void executeIncrementLeftIndexAndRightIndexByGroupByLeftIndexOrRightIndexGreaterThanOrEqualTo(String group,Integer value1,Integer value2, Integer increment);
	
	void executeDelete(Collection<NestedSet> nestedSets);
	void executeDeleteByGroupWhereLeftIndexAndRightIndexBetween(String group,Integer leftIndex,Integer rightIndex);
}
