package org.cyk.system.datastructure.server.persistence.impl.collection.set.nested;

import java.io.Serializable;
import java.util.Collection;

import javax.inject.Singleton;

import org.cyk.system.datastructure.server.persistence.api.collection.set.nested.NestedSetNodePersistence;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSetNode;
import org.cyk.utility.__kernel__.computation.ComparisonOperator;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQuery;
import org.cyk.utility.server.persistence.query.PersistenceQueryRepository;
import org.cyk.utility.sql.builder.QueryStringBuilderSelect;

@Singleton
public class NestedSetNodePersistenceImpl extends AbstractPersistenceEntityImpl<NestedSetNode> implements NestedSetNodePersistence, Serializable {
	private static final long serialVersionUID = 1L;

	private String readBySet,readByParent;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readBySet, __instanciateQuerySelect__()
				.getWherePredicateBuilderAsEqual().addOperandBuilderByAttribute(NestedSetNode.FIELD_NESTED_SET,ComparisonOperator.EQ)
				.getParentAsWhereClause().getParentAs(QueryStringBuilderSelect.class));
		
		addQueryCollectInstances(readByParent, __instanciateQuerySelect__().getWherePredicateBuilderAsGroup()
				.addOperandBuilderByAttribute(NestedSetNode.FIELD_NESTED_SET,ComparisonOperator.EQ).and()
				.addOperandBuilderByAttribute(NestedSetNode.FIELD_LEFT_INDEX,ComparisonOperator.GT)
				.and().addOperandBuilderByAttribute(NestedSetNode.FIELD_RIGHT_INDEX,ComparisonOperator.LT).getParentAsWhereClause().getParent());
	}
	
	@Override
	public Collection<NestedSetNode> readBySet(String set) {
		return __readMany__(__getQueryParameters__(set));
	}
	
	@Override
	public Long countBySet(String set) {
		return __count__(__getQueryParameters__(set));
	}
	
	@Override
	public Collection<NestedSetNode> readByParent(NestedSetNode node) {
		return __readMany__(__getQueryParameters__(node));
	}
	
	@Override
	public Long countByParent(NestedSetNode node) {
		return __count__(__getQueryParameters__(node));
	}
	
	protected Object[] __getQueryParameters__(String queryIdentifier,Object...objects){
		PersistenceQuery persistenceQuery = __inject__(PersistenceQueryRepository.class).getBySystemIdentifier(queryIdentifier);
		
		if(persistenceQuery.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readBySet,queryIdentifier))
			return new Object[]{NestedSetNode.FIELD_NESTED_SET,objects[0]};
		
		if(persistenceQuery.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByParent,queryIdentifier)){
			NestedSetNode node = (NestedSetNode) objects[0];
			return new Object[]{NestedSetNode.FIELD_NESTED_SET, node.getNestedSet(),NestedSetNode.FIELD_LEFT_INDEX,node.getLeftIndex(),NestedSetNode.FIELD_RIGHT_INDEX
					,node.getRightIndex()};
		}
		
		return super.__getQueryParameters__(queryIdentifier, objects);
	}
}
