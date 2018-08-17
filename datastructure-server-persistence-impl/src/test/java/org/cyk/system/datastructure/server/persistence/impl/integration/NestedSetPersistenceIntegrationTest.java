package org.cyk.system.datastructure.server.persistence.impl.integration;

import java.util.Collection;

import org.cyk.system.datastructure.server.persistence.api.collection.set.nested.NestedSetPersistence;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceEntityIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Assert;
import org.junit.Test;

public class NestedSetPersistenceIntegrationTest extends AbstractPersistenceEntityIntegrationTestWithDefaultDeploymentAsSwram<NestedSet> {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected <T> T __instanciate__(Class<T> aClass, Object action) throws Exception {
		T t = super.__instanciate__(aClass, action);
		((NestedSet)t).setLeftIndex(0).setRightIndex(1).setGroup(__getRandomCode__());
		return t;
	}
	
	@Test
	public void createTree01(){
		String set01 = __getRandomCode__();
		String set01Set01 = __getRandomCode__();
		String set01Set01Set01 = __getRandomCode__() , set01Set01Set02 = __getRandomCode__() , set01Set01Set03 = __getRandomCode__()
				, set01Set01Set01Set01 = __getRandomCode__()
						, set01Set01Set01Set02 = __getRandomCode__(), set01Set01Set03Set01 = __getRandomCode__(), set01Set01Set03Set02 = __getRandomCode__()
								, set01Set01Set03Set03 = __getRandomCode__(), set01Set01Set03Set04 = __getRandomCode__();
		createSets(set01, null, new Object[]{set01Set01,0,19});
		createSets(set01, set01Set01, new Object[]{set01Set01Set01,1,6},new Object[]{set01Set01Set02,7,8},new Object[]{set01Set01Set03,9,18});
		createSets(set01, set01Set01Set01, new Object[]{set01Set01Set01Set01,2,3},new Object[]{set01Set01Set01Set02,4,5});
		createSets(set01, set01Set01Set03, new Object[]{set01Set01Set03Set01,10,11},new Object[]{set01Set01Set03Set02,12,13}
			,new Object[]{set01Set01Set03Set03,14,15},new Object[]{set01Set01Set03Set04,16,17});
		
		assertGroup(set01, 10);
		assertNestedSet(set01Set01, 0, 19, 3, 9,0);
		assertNestedSet(set01Set01Set01, 1, 6, 2, 2,1);
		assertNestedSet(set01Set01Set02, 7, 8, 0, 0,1);
		assertNestedSet(set01Set01Set03, 9, 18, 4, 4,1);
		assertNestedSet(set01Set01Set01Set01, 2, 3, 0, 0,2);
		assertNestedSet(set01Set01Set01Set02, 4, 5, 0, 0,2);
		assertNestedSet(set01Set01Set03Set01, 10, 11, 0, 0,2);
		assertNestedSet(set01Set01Set03Set02, 12, 13, 0, 0,2);
		assertNestedSet(set01Set01Set03Set03, 14, 15, 0, 0,2);
		assertNestedSet(set01Set01Set03Set04, 16, 17, 0, 0,2);
		
		__deleteEntitiesAll__(NestedSet.class);
		
	}
	
	//@Test
	public void createTree02(){
		NestedSetPersistence persistence = __inject__(NestedSetPersistence.class);
		String set01 = __getRandomCode__();
		String set01Set01 = __getRandomCode__();
		String set01Set01Set01 = __getRandomCode__() , set01Set01Set02 = __getRandomCode__() 
				/*, set01Set01Set03 = __getRandomCode__(), set01Set01Set01Set01 = __getRandomCode__(), set01Set01Set01Set02 = __getRandomCode__(), set01Set01Set03Set01 = __getRandomCode__()
				, set01Set01Set03Set02 = __getRandomCode__(), set01Set01Set03Set03 = __getRandomCode__(), set01Set01Set03Set04 = __getRandomCode__()*/
				;
		
		Assert.assertEquals(new Long(0),persistence.countByGroup(set01));
		__createEntity__(new NestedSet().setCode(set01Set01).setGroup(set01).setLeftIndex(0).setRightIndex(1));
		Assert.assertEquals(new Long(1),persistence.countByGroup(set01));
		Assert.assertEquals(new Long(0),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01)));
		
		try {
			Collection<NestedSet> nestedSets = persistence.readByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 0);
			userTransaction.begin();
			persistence.executeIncrementRightIndex(nestedSets, 2);
			userTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		__createEntity__(new NestedSet().setCode(set01Set01Set01).setGroup(set01).setLeftIndex(1).setRightIndex(2).setParentFromCode(set01Set01));
		Assert.assertEquals(new Long(2),persistence.countByGroup(set01));
		Assert.assertEquals(new Long(1),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01)));
		Assert.assertEquals(new Long(0),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set01)));
		
		try {
			Collection<NestedSet> nestedSets = persistence.readByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 2);
			userTransaction.begin();
			persistence.executeIncrementRightIndex(nestedSets, 2);
			userTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		__createEntity__(new NestedSet().setCode(set01Set01Set02).setGroup(set01).setLeftIndex(3).setRightIndex(4).setParentFromCode(set01Set01));
		Assert.assertEquals(new Long(3),persistence.countByGroup(set01));
		Assert.assertEquals(new Long(2),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01)));
		Assert.assertEquals(new Long(0),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set01)));
		Assert.assertEquals(new Long(0),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set02)));
		
		/*
		createSets(set01, null, new Object[]{set01Set01,0,19});
		createSets(set01, set01Set01, new Object[]{set01Set01Set01,1,6},new Object[]{set01Set01Set02,7,8},new Object[]{set01Set01Set03,9,18});
		createSets(set01, set01Set01Set01, new Object[]{set01Set01Set01Set01,2,3},new Object[]{set01Set01Set01Set02,4,5});
		createSets(set01, set01Set01Set03, new Object[]{set01Set01Set03Set01,10,11},new Object[]{set01Set01Set03Set02,12,13}
			,new Object[]{set01Set01Set03Set03,14,15},new Object[]{set01Set01Set03Set04,16,17});
		
		
		
		Assert.assertEquals(new Long(10),persistence.countByGroup(set01));
		
		Assert.assertEquals(new Long(9),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01)));
		
		Assert.assertEquals(new Long(2),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set01)));
		Assert.assertEquals(new Long(0),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set02)));
		Assert.assertEquals(new Long(4),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set03)));
		
		Assert.assertEquals(new Long(0),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set01Set01)));
		Assert.assertEquals(new Long(0),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set01Set02)));
		Assert.assertEquals(new Long(0),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set03Set01)));
		Assert.assertEquals(new Long(0),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set03Set02)));
		Assert.assertEquals(new Long(0),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set03Set03)));
		Assert.assertEquals(new Long(0),persistence.countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set03Set04)));
		
		Assert.assertEquals(new Long(10),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 0));
		Assert.assertEquals(new Long(10),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 1));
		Assert.assertEquals(new Long(10),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 2));
		Assert.assertEquals(new Long(9),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 3));
		Assert.assertEquals(new Long(9),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 4));
		Assert.assertEquals(new Long(8),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 5));
		Assert.assertEquals(new Long(7),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 6));
		Assert.assertEquals(new Long(7),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 7));
		Assert.assertEquals(new Long(6),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 8));
		Assert.assertEquals(new Long(6),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 9));
		Assert.assertEquals(new Long(6),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 10));
		Assert.assertEquals(new Long(5),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 11));
		Assert.assertEquals(new Long(5),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 12));
		Assert.assertEquals(new Long(4),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 13));
		Assert.assertEquals(new Long(4),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 14));
		Assert.assertEquals(new Long(3),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 15));
		Assert.assertEquals(new Long(3),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 16));
		Assert.assertEquals(new Long(2),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 17));
		Assert.assertEquals(new Long(1),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 18));
		Assert.assertEquals(new Long(0),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 19));
		Assert.assertEquals(new Long(0),persistence.countByGroupByLeftOrRightGreaterThanOrEqualTo(set01, 20));
		*/
	}
	
	@Test(expected=RuntimeException.class)
	public void throwable___groupIsNull_leftIndexIsNull_rightIndexIsNull(){
		__createEntity__(new NestedSet().setCode(__getRandomCode__()));
	}
	
	@Test(expected=RuntimeException.class)
	public void throwable___groupIsNull_leftIndexIsNull_rightIndexIsNotNull(){
		__createEntity__(new NestedSet().setCode(__getRandomCode__()).setRightIndex(0));
	}
	
	@Test(expected=RuntimeException.class)
	public void throwable___groupIsNull_leftIndexIsNotNull_rightIndexIsNull(){
		__createEntity__(new NestedSet().setCode(__getRandomCode__()).setLeftIndex(0));
	}
	
	@Test(expected=RuntimeException.class)
	public void throwable___groupIsNull_leftIndexIsNotNull_rightIndexIsNotNull(){
		__createEntity__(new NestedSet().setCode(__getRandomCode__()).setLeftIndex(0).setRightIndex(1));
	}
	
	@Test(expected=RuntimeException.class)
	public void throwable___groupIsNotNull_leftIndexIsNull_rightIndexIsNull(){
		__createEntity__(new NestedSet().setCode(__getRandomCode__()).setGroup(__getRandomCode__()));
	}
	
	@Test(expected=RuntimeException.class)
	public void throwable___groupIsNotNull_leftIndexIsNull_rightIndexIsNotNull(){
		__createEntity__(new NestedSet().setCode(__getRandomCode__()).setGroup(__getRandomCode__()).setRightIndex(0));
	}
	
	@Test(expected=RuntimeException.class)
	public void throwable___groupIsNotNull_leftIndexIsNotNull_rightIndexIsNull(){
		__createEntity__(new NestedSet().setCode(__getRandomCode__()).setGroup(__getRandomCode__()).setLeftIndex(0));
	}
	/*
	@Test(expected=RuntimeException.class)
	public void throwable___groupIsNotNull_leftIndexIsNotNull_rightIndexIsNotNull_leftIndexIsEqualToRightIndexIsEqualToZero(){
		__createEntity__(new NestedSet().setCode(__getRandomCode__()).setGroup(__getRandomCode__()).setLeftIndex(0).setRightIndex(0));
	}
	
	@Test(expected=RuntimeException.class)
	public void throwable___groupIsNotNull_leftIndexIsNotNull_rightIndexIsNotNull_leftIndexIsEqualToRightIndexIsEqualToOne(){
		__createEntity__(new NestedSet().setCode(__getRandomCode__()).setGroup(__getRandomCode__()).setLeftIndex(1).setRightIndex(1));
	}
	
	@Test(expected=RuntimeException.class)
	public void throwable___groupIsNotNull_leftIndexIsNotNull_rightIndexIsNotNull_leftIndexIsEqualToRightIndexIsEqualToTwo(){
		__createEntity__(new NestedSet().setCode(__getRandomCode__()).setGroup(__getRandomCode__()).setLeftIndex(2).setRightIndex(2));
	}
	*/
	/**/
	
	private void createSets(String setCode,String parentCode,Object[]...childrenArray){
		for(Object[] index : childrenArray){
			__createEntity__(new NestedSet().setCode(index[0].toString()).setLeftIndex((Integer) index[1]).setRightIndex((Integer) index[2])
					.setGroup(setCode)
					.setFromBusinessIdentifier(NestedSet.FIELD_PARENT,parentCode));
		}
	}
	
	private NestedSetPersistenceIntegrationTest assertGroup(String code,Integer numberOfNestedSet){
		Assert.assertEquals("Number of nested set in group is not correct",new Long(numberOfNestedSet),__inject__(NestedSetPersistence.class).countByGroup(code));
		return this;
	}
	
	private NestedSetPersistenceIntegrationTest assertNestedSet(String code,Integer left,Integer right,Integer numberOfChildren,Integer numberOfDescendant,Integer numberOfAscendant){
		NestedSet nestedSet = __inject__(NestedSetPersistence.class).readOneByBusinessIdentifier(code);
		Assert.assertEquals("Number of children from count is not correct",new Long(numberOfChildren),__inject__(NestedSetPersistence.class).countByParent(nestedSet));
		Assert.assertEquals("Number of descendant from count is not correct",new Long(numberOfDescendant),__inject__(NestedSetPersistence.class).countByGroupWhereLeftIndexAndRightIndexBetween(nestedSet));
		Assert.assertEquals("Number of descendant from get is not correct",new Long(numberOfDescendant),new Long(nestedSet.getNumberOfDescendant()));
		Assert.assertEquals("Number of ascendant from count is not correct",new Long(numberOfAscendant),__inject__(NestedSetPersistence.class).countByGroupWhereLeftIndexAndRightIndexContain(nestedSet));
		return this;
	}
}
