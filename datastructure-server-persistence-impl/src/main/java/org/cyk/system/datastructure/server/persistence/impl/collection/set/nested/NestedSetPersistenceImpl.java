package org.cyk.system.datastructure.server.persistence.impl.collection.set.nested;

import java.io.Serializable;
import java.util.Collection;

import javax.inject.Singleton;

import org.cyk.system.datastructure.server.persistence.api.collection.set.nested.NestedSetPersistence;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.__kernel__.computation.ComparisonOperator;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQuery;
import org.cyk.utility.server.persistence.query.PersistenceQueryRepository;
import org.cyk.utility.sql.builder.QueryStringBuilderSelect;

@Singleton
public class NestedSetPersistenceImpl extends AbstractPersistenceEntityImpl<NestedSet> implements NestedSetPersistence, Serializable {
	private static final long serialVersionUID = 1L;

	private String readByGroup,readByParent;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByGroup, __instanciateQuerySelect__()
				.getWherePredicateBuilderAsEqual().addOperandBuilderByAttribute(NestedSet.FIELD_GROUP,ComparisonOperator.EQ)
				.getParentAsWhereClause().getParentAs(QueryStringBuilderSelect.class));
		
		addQueryCollectInstances(readByParent, __instanciateQuerySelect__().getWherePredicateBuilderAsGroup()
				.addOperandBuilderByAttribute(NestedSet.FIELD_GROUP,ComparisonOperator.EQ).and()
				.addOperandBuilderByAttribute(NestedSet.FIELD_LEFT_INDEX,ComparisonOperator.GT)
				.and().addOperandBuilderByAttribute(NestedSet.FIELD_RIGHT_INDEX,ComparisonOperator.LT).getParentAsWhereClause().getParent());

	}
	
	@Override
	public Collection<NestedSet> readByGroup(String group) {
		return __readMany__(____getQueryParameters____(group));
	}
	
	@Override
	public Long countByGroup(String group) {
		return __count__(____getQueryParameters____(group));
	}
	
	@Override
	public Collection<NestedSet> readByParent(NestedSet nestedSet) {
		return __readMany__(____getQueryParameters____(nestedSet));
	}
	
	@Override
	public Long countByParent(NestedSet nestedSet) {
		return __count__(____getQueryParameters____(nestedSet));
	}
	
	protected Object[] __getQueryParameters__(String queryIdentifier,Object...objects){
		PersistenceQuery persistenceQuery = __inject__(PersistenceQueryRepository.class).getBySystemIdentifier(queryIdentifier);
		
		if(persistenceQuery.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByGroup,queryIdentifier))
			return new Object[]{NestedSet.FIELD_GROUP,objects[0]};
		
		if(persistenceQuery.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByParent,queryIdentifier)){
			NestedSet nestedSet = (NestedSet) objects[0];
			return new Object[]{NestedSet.FIELD_GROUP, nestedSet.getGroup(),NestedSet.FIELD_LEFT_INDEX,nestedSet.getLeftIndex()
					,NestedSet.FIELD_RIGHT_INDEX,nestedSet.getRightIndex()};
		}
		
		return super.__getQueryParameters__(queryIdentifier, objects);
	}
}
