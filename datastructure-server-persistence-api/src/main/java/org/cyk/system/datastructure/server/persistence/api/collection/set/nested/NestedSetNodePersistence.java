package org.cyk.system.datastructure.server.persistence.api.collection.set.nested;

import java.util.Collection;

import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSetNode;

public interface NestedSetNodePersistence {

	Collection<NestedSetNode> readBySet(String set);
	Long countBySet(String set);
	
	Collection<NestedSetNode> readByParent(NestedSetNode node);
	Long countByParent(NestedSetNode node);
	
}
