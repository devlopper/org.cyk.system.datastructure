package org.cyk.system.datastructure.server.persistence.impl.collection.set.nested;

import java.io.Serializable;
import java.util.Collection;

import javax.inject.Singleton;

import org.cyk.system.datastructure.server.persistence.api.collection.set.nested.NestedSetNodePersistence;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSetNode;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;

@Singleton
public class NestedSetNodePersistenceImpl extends AbstractPersistenceEntityImpl<NestedSetNode> implements NestedSetNodePersistence, Serializable {
	private static final long serialVersionUID = 1L;

	private String readBySet,readByParent;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readBySet, "SELECT r FROM NestedSetNode r WHERE r.nestedSet = :nestedSet");
		addQueryCollectInstances(readByParent, "SELECT r FROM NestedSetNode r WHERE r.nestedSet = :nestedSet AND r.leftIndex > :leftIndex AND r.rightIndex < :rightIndex");
	}
	
	@Override
	public Collection<NestedSetNode> readBySet(NestedSet set) {
		return __readMany__(NestedSetNode.FIELD_NESTED_SET, set);
	}
	
	@Override
	public Long countBySet(NestedSet set) {
		return __count__(NestedSetNode.FIELD_NESTED_SET, set);
	}
	
	@Override
	public Collection<NestedSetNode> readByParent(NestedSetNode node) {
		return __readMany__(NestedSetNode.FIELD_NESTED_SET, node.getNestedSet(),NestedSetNode.FIELD_LEFT_INDEX,node.getLeftIndex(),NestedSetNode.FIELD_RIGHT_INDEX
				,node.getRightIndex());
	}
	
	@Override
	public Long countByParent(NestedSetNode node) {
		return __count__(NestedSetNode.FIELD_NESTED_SET, node.getNestedSet(),NestedSetNode.FIELD_LEFT_INDEX,node.getLeftIndex(),NestedSetNode.FIELD_RIGHT_INDEX
				,node.getRightIndex());
	}
	
}
