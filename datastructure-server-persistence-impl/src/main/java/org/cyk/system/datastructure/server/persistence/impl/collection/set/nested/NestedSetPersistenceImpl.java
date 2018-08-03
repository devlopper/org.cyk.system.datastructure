package org.cyk.system.datastructure.server.persistence.impl.collection.set.nested;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Singleton;

import org.cyk.system.datastructure.server.persistence.api.collection.set.nested.NestedSetPersistence;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.__kernel__.computation.ComparisonOperator;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.persistence.AbstractPersistenceEntityImpl;
import org.cyk.utility.server.persistence.query.PersistenceQuery;
import org.cyk.utility.server.persistence.query.PersistenceQueryRepository;
import org.cyk.utility.sql.builder.QueryStringBuilderSelect;

@Singleton
public class NestedSetPersistenceImpl extends AbstractPersistenceEntityImpl<NestedSet> implements NestedSetPersistence, Serializable {
	private static final long serialVersionUID = 1L;

	private String readByGroup,readByParent,readByGroupByLeftOrRightGreaterThanOrEqualTo,readByGroupWhereLeftIndexAndRightIndexBetween
	,readByGroupWhereLeftIndexAndRightIndexContain
		,executeIncrementLeftIndex,executeIncrementRightIndex,executeIncrementNumberOfChildren;
	
	@Override
	protected void __listenPostConstructPersistenceQueries__() {
		super.__listenPostConstructPersistenceQueries__();
		addQueryCollectInstances(readByGroup, __instanciateQuerySelect__()
				.getWherePredicateBuilderAsEqual().addOperandBuilderByAttribute(NestedSet.FIELD_GROUP,ComparisonOperator.EQ)
				.getParentAsWhereClause().getParentAs(QueryStringBuilderSelect.class));
		
		addQueryCollectInstances(readByParent, __instanciateQuerySelect__()
				.getWherePredicateBuilderAsEqual().addOperandBuilderByAttribute(NestedSet.FIELD_PARENT,ComparisonOperator.EQ)
				.getParentAsWhereClause().getParentAs(QueryStringBuilderSelect.class));
		
		addQueryCollectInstances(readByGroupWhereLeftIndexAndRightIndexBetween, __instanciateQuerySelect__().getWherePredicateBuilderAsGroup()
				.addOperandBuilderByAttribute(NestedSet.FIELD_GROUP,ComparisonOperator.EQ).and()
				.addOperandBuilderByAttribute(NestedSet.FIELD_LEFT_INDEX,ComparisonOperator.GT)
				.and().addOperandBuilderByAttribute(NestedSet.FIELD_RIGHT_INDEX,ComparisonOperator.LT).getParentAsWhereClause().getParent());
		
		addQueryCollectInstances(readByGroupWhereLeftIndexAndRightIndexContain, __instanciateQuerySelect__().getWherePredicateBuilderAsGroup()
				.addOperandBuilderByAttribute(NestedSet.FIELD_GROUP,ComparisonOperator.EQ).and()
				.addOperandBuilderByAttribute(NestedSet.FIELD_LEFT_INDEX,ComparisonOperator.LT)
				.and().addOperandBuilderByAttribute(NestedSet.FIELD_RIGHT_INDEX,ComparisonOperator.GT).getParentAsWhereClause().getParent());
		
		addQueryCollectInstances(readByGroupByLeftOrRightGreaterThanOrEqualTo, __instanciateQuerySelect__().getWherePredicateBuilderAsGroup()
				.addOperandBuilderByAttribute(NestedSet.FIELD_GROUP,ComparisonOperator.EQ).and()
				.lp()
					.addOperandBuilderByAttribute(NestedSet.FIELD_LEFT_INDEX,ComparisonOperator.GT,"value")
					.or()
					.addOperandBuilderByAttribute(NestedSet.FIELD_RIGHT_INDEX,ComparisonOperator.GT,"value")
				.rp()
				.getParentAsWhereClause().getParent());
		
		addQuery(executeIncrementLeftIndex, "UPDATE NestedSet nestedSet SET nestedSet.leftIndex = nestedSet.leftIndex"
				+ " + :increment WHERE nestedSet.identifier IN :identifiers",null);
		addQuery(executeIncrementRightIndex, "UPDATE NestedSet nestedSet SET nestedSet.rightIndex = nestedSet.rightIndex"
				+ " + :increment WHERE nestedSet.identifier IN :identifiers",null);
		addQuery(executeIncrementNumberOfChildren, "UPDATE NestedSet nestedSet SET nestedSet.numberOfChildren = nestedSet.numberOfChildren"
				+ " + :increment WHERE nestedSet.identifier IN :identifiers",null);
	}
	
	@SuppressWarnings("unchecked")
	protected Object[] __getQueryParameters__(String queryIdentifier,Object...objects){
		PersistenceQuery persistenceQuery = __inject__(PersistenceQueryRepository.class).getBySystemIdentifier(queryIdentifier);
		
		if(persistenceQuery.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByGroup,queryIdentifier))
			return new Object[]{NestedSet.FIELD_GROUP,objects[0]};
		
		if(persistenceQuery.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByParent,queryIdentifier))
			return new Object[]{NestedSet.FIELD_PARENT, objects[0]};
		
		if(persistenceQuery.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByGroupWhereLeftIndexAndRightIndexBetween,queryIdentifier))
			return new Object[]{NestedSet.FIELD_GROUP, objects[0],NestedSet.FIELD_LEFT_INDEX,objects[1],NestedSet.FIELD_RIGHT_INDEX,objects[2]};
		
		if(persistenceQuery.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByGroupWhereLeftIndexAndRightIndexContain,queryIdentifier))
			return new Object[]{NestedSet.FIELD_GROUP, objects[0],NestedSet.FIELD_LEFT_INDEX,objects[1],NestedSet.FIELD_RIGHT_INDEX,objects[2]};
		
		if(persistenceQuery.isIdentifierEqualsToOrQueryDerivedFromQueryIdentifierEqualsTo(readByGroupByLeftOrRightGreaterThanOrEqualTo,queryIdentifier)){
			String group = (String) objects[0];
			Integer value = (Integer) objects[1];
			return new Object[]{NestedSet.FIELD_GROUP, group,"value",value};
		}
		
		if(executeIncrementLeftIndex.equals(queryIdentifier)){
			Collection<Long> identifiers = (Collection<Long>) objects[0];
			Integer increment = (Integer) objects[1];
			return new Object[]{"identifiers", identifiers,"increment",increment};
		}
		
		if(executeIncrementRightIndex.equals(queryIdentifier)){
			Collection<Long> identifiers = (Collection<Long>) objects[0];
			Integer increment = (Integer) objects[1];
			return new Object[]{"identifiers", identifiers,"increment",increment};
		}
		
		if(executeIncrementNumberOfChildren.equals(queryIdentifier)){
			Collection<Long> identifiers = (Collection<Long>) objects[0];
			Integer increment = (Integer) objects[1];
			return new Object[]{"identifiers", identifiers,"increment",increment};
		}
		
		return super.__getQueryParameters__(queryIdentifier, objects);
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
	public Collection<NestedSet> readByParent(NestedSet nestedSet,Properties properties) {
		return __readMany__(____getQueryParameters____(nestedSet));
	}
	
	@Override
	public Collection<NestedSet> readByParent(NestedSet nestedSet) {
		return readByParent(nestedSet, null);
	}
	
	@Override
	public Collection<NestedSet> readByParent(String nestedSetCode,Properties properties) {
		return readByParent(readOneByBusinessIdentifier(nestedSetCode),properties);
	}
	
	@Override
	public Collection<NestedSet> readByParent(String nestedSetCode) {
		return readByParent(nestedSetCode,null);
	}
	
	@Override
	public Long countByParent(NestedSet nestedSet,Properties properties) {
		return __count__(____getQueryParameters____(nestedSet));
	}
	
	@Override
	public Long countByParent(NestedSet nestedSet) {
		return countByParent(nestedSet, null);
	}
	
	@Override
	public Long countByParent(String nestedSetCode,Properties properties) {
		return countByParent(readOneByBusinessIdentifier(nestedSetCode),properties);
	}
	
	@Override
	public Long countByParent(String nestedSetCode) {
		return countByParent(nestedSetCode,null);
	}
	
	@Override
	public Collection<NestedSet> readByGroupWhereLeftIndexAndRightIndexBetween(String group, Integer leftIndex,Integer rightIndex) {
		return __readMany__(____getQueryParameters____(group,leftIndex,rightIndex));
	}
	
	@Override
	public Long countByGroupWhereLeftIndexAndRightIndexBetween(String group, Integer leftIndex, Integer rightIndex) {
		return __count__(____getQueryParameters____(group,leftIndex,rightIndex));
	}
	
	@Override
	public Collection<NestedSet> readByGroupWhereLeftIndexAndRightIndexBetween(NestedSet nestedSet) {
		return readByGroupWhereLeftIndexAndRightIndexBetween(nestedSet.getGroup(), nestedSet.getLeftIndex(), nestedSet.getRightIndex());
	}
	
	@Override
	public Long countByGroupWhereLeftIndexAndRightIndexBetween(NestedSet nestedSet) {
		return countByGroupWhereLeftIndexAndRightIndexBetween(nestedSet.getGroup(), nestedSet.getLeftIndex(), nestedSet.getRightIndex());
	}
	
	@Override
	public Collection<NestedSet> readByGroupWhereLeftIndexAndRightIndexContain(String group, Integer leftIndex,Integer rightIndex) {
		return __readMany__(____getQueryParameters____(group,leftIndex,rightIndex));
	}
	
	@Override
	public Long countByGroupWhereLeftIndexAndRightIndexContain(String group, Integer leftIndex, Integer rightIndex) {
		return __count__(____getQueryParameters____(group,leftIndex,rightIndex));
	}
	
	@Override
	public Collection<NestedSet> readByGroupWhereLeftIndexAndRightIndexContain(NestedSet nestedSet) {
		return readByGroupWhereLeftIndexAndRightIndexContain(nestedSet.getGroup(), nestedSet.getLeftIndex(), nestedSet.getRightIndex());
	}
	
	@Override
	public Long countByGroupWhereLeftIndexAndRightIndexContain(NestedSet nestedSet) {
		return countByGroupWhereLeftIndexAndRightIndexContain(nestedSet.getGroup(), nestedSet.getLeftIndex(), nestedSet.getRightIndex());
	}
	
	@Override
	public Collection<NestedSet> readByGroupByLeftOrRightGreaterThanOrEqualTo(String group, Integer value) {
		return __readMany__(____getQueryParameters____(group,value));
	}
	
	@Override
	public Long countByGroupByLeftOrRightGreaterThanOrEqualTo(String group, Integer value) {
		return __count__(____getQueryParameters____(group,value));
	}
	
	@Override
	public void executeIncrementLeftIndex(Collection<NestedSet> nestedSets, Integer increment) {
		Collection<Long> identifiers = new ArrayList<>();
		for(NestedSet index : nestedSets)
			identifiers.add(index.getIdentifier());
		__modify__(____getQueryParameters____(identifiers,increment));
	}
	
	@Override
	public void executeIncrementRightIndex(Collection<NestedSet> nestedSets, Integer increment) {
		Collection<Long> identifiers = new ArrayList<>();
		for(NestedSet index : nestedSets)
			identifiers.add(index.getIdentifier());
		__modify__(____getQueryParameters____(identifiers,increment));
	}
	
	@Override
	public void executeIncrementNumberOfChildren(Collection<NestedSet> nestedSets, Integer increment) {
		Collection<Long> identifiers = new ArrayList<>();
		for(NestedSet index : nestedSets)
			identifiers.add(index.getIdentifier());
		__modify__(____getQueryParameters____(identifiers,increment));
	}
}
