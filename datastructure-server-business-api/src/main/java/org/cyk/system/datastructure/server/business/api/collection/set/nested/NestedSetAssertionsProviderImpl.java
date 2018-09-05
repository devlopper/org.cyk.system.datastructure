package org.cyk.system.datastructure.server.business.api.collection.set.nested;

import java.io.Serializable;

import org.cyk.system.datastructure.server.persistence.api.collection.set.nested.NestedSetPersistence;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.__kernel__.computation.ComparisonOperator;
import org.cyk.utility.__kernel__.function.Function;
import org.cyk.utility.assertion.AbstractAssertionsProviderForImpl;
import org.cyk.utility.assertion.AssertionBuilderComparison;
import org.cyk.utility.server.business.BusinessFunction;
import org.cyk.utility.server.business.BusinessFunctionCreator;

public class NestedSetAssertionsProviderImpl extends AbstractAssertionsProviderForImpl<NestedSet> implements NestedSetAssertionsProvider,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void ____execute____(Function<?,?> function,Object filter, NestedSet nestedSet) {
		if(function instanceof BusinessFunctionCreator) {
			nestedSet = __inject__(NestedSetPersistence.class).readOneByBusinessIdentifier(((NestedSet) ((BusinessFunction)function).getEntity()).getCode());
			if(filter==null) {
				//assert leftIndex > -1
				__injectAssertionBuilderComparison__()
				.getAssertedValue1(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_LEFT_INDEX)).getParentAs(AssertionBuilderComparison.class)
				.getAssertedValue2(Boolean.TRUE).setValue(-1).getParentAs(AssertionBuilderComparison.class).setOperator(ComparisonOperator.GT).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
				.execute();
				
				//assert rightIndex > -1
				__inject__(AssertionBuilderComparison.class)
				.getAssertedValue1(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_RIGHT_INDEX)).getParentAs(AssertionBuilderComparison.class)
				.getAssertedValue2(Boolean.TRUE).setValue(-1).getParentAs(AssertionBuilderComparison.class).setOperator(ComparisonOperator.GT).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
				.execute();
				
				//assert leftIndex < rightIndex
				__inject__(AssertionBuilderComparison.class)
				.getAssertedValue1(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_LEFT_INDEX)).getParentAs(AssertionBuilderComparison.class)
				.getAssertedValue2(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_RIGHT_INDEX)).getParentAs(AssertionBuilderComparison.class)
				.setOperator(ComparisonOperator.LT).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
				.execute();
				
				Long numberOfSet = __inject__(NestedSetPersistence.class).countByGroup(nestedSet.getGroup());
				//assert if (is leaf) then rightIndex - leftIndex = 1 else rightIndex - leftIndex > 1
				Integer gap = nestedSet.getRightIndex() - nestedSet.getLeftIndex();
				__inject__(AssertionBuilderComparison.class)
				.getAssertedValue1(Boolean.TRUE).setValue(gap).getParentAs(AssertionBuilderComparison.class)
				.getAssertedValue2(Boolean.TRUE).setValue(1).getParentAs(AssertionBuilderComparison.class)
				.setOperator(nestedSet.getParent() == null ? (numberOfSet == 1 ? ComparisonOperator.EQ : ComparisonOperator.GT) : ComparisonOperator.EQ).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
				.execute();
				
				//assert number of children from getter = number of children from query
				Long children = __inject__(NestedSetPersistence.class).countByParent(nestedSet);
				__inject__(AssertionBuilderComparison.class)
				.getAssertedValue1(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_NUMBER_OF_CHILDREN)).getParentAs(AssertionBuilderComparison.class)
				.getAssertedValue2(Boolean.TRUE).setValue(children).getParentAs(AssertionBuilderComparison.class).setOperator(ComparisonOperator.EQ).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
				.execute();
				
				//assert number of descendant from query = rightIndex - leftIndex - 1
				Long descendants = __inject__(NestedSetPersistence.class).countByGroupWhereLeftIndexAndRightIndexBetween(nestedSet);
				__inject__(AssertionBuilderComparison.class)
				.getAssertedValue1(Boolean.TRUE).setValue(descendants).getParentAs(AssertionBuilderComparison.class)
				.getAssertedValue2(Boolean.TRUE).setValue(gap-1).getParentAs(AssertionBuilderComparison.class)
				.setOperator(ComparisonOperator.EQ).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
				.execute();
				
				//assert number of descendant from query = number of descendant from getter
				__inject__(AssertionBuilderComparison.class)
				.getAssertedValue1(Boolean.TRUE).setValue(descendants).getParentAs(AssertionBuilderComparison.class)
				.getAssertedValue2(Boolean.TRUE).setFieldValueGetter(__injectFieldValueGetter__().setObject(nestedSet).setField(NestedSet.FIELD_NUMBER_OF_DESCENDANT)).getParentAs(AssertionBuilderComparison.class)
				.setOperator(ComparisonOperator.EQ).setIsThrownWhenValueIsNotTrue(Boolean.TRUE)
				.execute()
				;
				
			}
		}else {
			
		}
	}
	
}