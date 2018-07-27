package org.cyk.system.datastructure.server.persistence.api.collection.set.nested;

import java.util.Collection;

import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;

public interface NestedSetPersistence {

	Collection<NestedSet> readByGroup(String group);
	Long countByGroup(String group);
	
	Collection<NestedSet> readByParent(NestedSet nestedSet);
	Long countByParent(NestedSet nestedSet);
	
}
