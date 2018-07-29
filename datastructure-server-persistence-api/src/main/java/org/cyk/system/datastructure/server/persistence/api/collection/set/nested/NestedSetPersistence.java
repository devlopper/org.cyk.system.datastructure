package org.cyk.system.datastructure.server.persistence.api.collection.set.nested;

import java.util.Collection;

import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.server.persistence.PersistenceEntity;

public interface NestedSetPersistence extends PersistenceEntity<NestedSet> {

	Collection<NestedSet> readByGroup(String group);
	Long countByGroup(String group);
	
	Collection<NestedSet> readByParent(NestedSet nestedSet);
	Collection<NestedSet> readByParent(String nestedSetCode);
	
	Long countByParent(NestedSet nestedSet);
	Long countByParent(String nestedSetCode);
	
	Collection<NestedSet> readByGroupByLeftOrRightGreaterThanOrEqualTo(String group,Integer value);
	Long countByGroupByLeftOrRightGreaterThanOrEqualTo(String group,Integer value);
	
	void executeIncrementLeftIndex(Collection<NestedSet> nestedSets, Integer increment);
	void executeIncrementRightIndex(Collection<NestedSet> nestedSets, Integer increment);
}
