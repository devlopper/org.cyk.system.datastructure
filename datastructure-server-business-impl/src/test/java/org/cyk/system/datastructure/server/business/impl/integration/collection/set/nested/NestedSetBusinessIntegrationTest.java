package org.cyk.system.datastructure.server.business.impl.integration.collection.set.nested;

import org.cyk.system.datastructure.server.persistence.api.collection.set.nested.NestedSetPersistence;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessEntityIntegrationTestWithDefaultDeploymentAsSwram;
import org.cyk.utility.server.persistence.Persistence;
import org.junit.Assert;
import org.junit.Test;

public class NestedSetBusinessIntegrationTest extends AbstractBusinessEntityIntegrationTestWithDefaultDeploymentAsSwram<NestedSet> {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected NestedSet __instanciateEntity__(Object action) throws Exception {
		return super.__instanciateEntity__(action).setGroup(getRandomCode());
	}
	
	/**
	 * <img src="icons8-phone-64.png" />
	 */
	@Test
	public void createTree(){
		/*                                                     group01set01(0,19)
		 *                group01set01set01(1,6)                group01set01set02(,7,8)                    group01set01set03(9,18) 
		 * group01set01set01set01(2,3) group01set01set01set02(4,5) group01set01set03set01(10,11) group01set01set03set02(12,13) group01set01set03set03(14,15) group01set01set03set04(16,17)
		 */
		NestedSetPersistence persistence = __inject__(NestedSetPersistence.class);
		String group01 = getRandomCode();
		String group01Set01 = getRandomCode() //, group01Set02 = getRandomCode() , group01Set03 = getRandomCode()
				, group01Set01Set01 = getRandomCode(), group01Set01Set02 = getRandomCode(), group01Set01Set03 = getRandomCode(), group01Set03Set01 = getRandomCode()
				, group01Set03Set02 = getRandomCode()
								, group01Set03Set03 = getRandomCode(), group01Set03Set04 = getRandomCode();
		
		Assert.assertEquals(new Long(0),persistence.countByGroup(group01));
		__createEntity__(new NestedSet().setCode(group01Set01).setGroup(group01));
		assertGroup(group01, 1).assertNestedSet(group01Set01, 0, 1, 0, 0,0);
		
		__createEntity__(new NestedSet().setCode(group01Set01Set01).setParentFromCode(group01Set01));
		assertGroup(group01, 2).assertNestedSet(group01Set01, 0, 3, 1, 1,0)
			.assertNestedSet(group01Set01Set01, 1, 2, 0, 0,1);
		
		__createEntity__(new NestedSet().setCode(group01Set01Set02).setParentFromCode(group01Set01));
		assertGroup(group01, 3).assertNestedSet(group01Set01, 0, 5, 2, 2,0)
		.assertNestedSet(group01Set01Set01, 1, 2, 0, 0,1)
		.assertNestedSet(group01Set01Set01, 3, 4, 0, 0,1);
		
		__createEntity__(new NestedSet().setCode(group01Set01Set03).setParentFromCode(group01Set01));
		assertGroup(group01, 4).assertNestedSet(group01Set01, 0, 7, 3, 3,0)
		.assertNestedSet(group01Set01Set01, 1, 2, 0, 0,1)
		.assertNestedSet(group01Set01Set01, 3, 4, 0, 0,1)
		.assertNestedSet(group01Set01Set01, 5, 6, 0, 0,1);
		
		//createSets(group01, null, group01);
		//Assert.assertEquals(new Long(10),__inject__(NestedSetBusiness.class).countByGroup(group01));
		
		//createSets(group01, group01, group01Set01,group01Set02,group01Set03);
		/*createSets(group01, group01Set01, new Object[]{set01Set01Set01,2,3},new Object[]{set01Set01Set02,4,5});
		createSets(group01, group01Set03, new Object[]{set01Set03Set01,10,11},new Object[]{set01Set03Set02,12,13}
			,new Object[]{set01Set03Set03,14,15},new Object[]{set01Set03Set04,16,17});
		*/
		/*
		Assert.assertEquals(new Long(10),__inject__(NestedSetBusiness.class).countByGroup(group01));
		
		Assert.assertEquals(new Long(9),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,group01)));
		
		Assert.assertEquals(new Long(2),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,group01Set01)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,group01Set02)));
		Assert.assertEquals(new Long(4),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,group01Set03)));
		
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,group01Set01Set01)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,group01Set01Set02)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,group01Set03Set01)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,group01Set03Set02)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,group01Set03Set03)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,group01Set03Set04)));
		*/
	}
	
	/**/
	
	private NestedSetBusinessIntegrationTest assertGroup(String code,Integer numberOfNestedSet){
		Assert.assertEquals("Number of nested set in group is not correct",new Long(numberOfNestedSet),__inject__(NestedSetPersistence.class).countByGroup(code));
		return this;
	}
	
	private NestedSetBusinessIntegrationTest assertNestedSet(String code,Integer left,Integer right,Integer numberOfChildren,Integer numberOfDescendant,Integer numberOfAscendant){
		NestedSet nestedSet = __inject__(NestedSetPersistence.class).readOneByBusinessIdentifier(code);
		Assert.assertEquals("Number of children from count is not correct",new Long(numberOfChildren),__inject__(NestedSetPersistence.class).countByParent(nestedSet));
		Assert.assertEquals("Number of children from get is not correct",new Long(numberOfDescendant),new Long(nestedSet.getNumberOfChildren()));
		Assert.assertEquals("Number of descendant from count is not correct",new Long(numberOfDescendant),__inject__(NestedSetPersistence.class).countByGroupWhereLeftIndexAndRightIndexBetween(nestedSet));
		Assert.assertEquals("Number of descendant from get is not correct",new Long(numberOfDescendant),new Long(nestedSet.getNumberOfDescendant()));
		Assert.assertEquals("Number of ascendant from count is not correct",new Long(numberOfAscendant),__inject__(NestedSetPersistence.class).countByGroupWhereLeftIndexAndRightIndexContain(nestedSet));
		Assert.assertEquals("Number of ascendant from get is not correct",new Long(numberOfAscendant),new Long(nestedSet.getNumberOfAscendant()));
		return this;
	}
	
	private void createSets(String setCode,String parentCode,String...children){
		for(String index : children){
			__createEntity__(new NestedSet().setCode(index).setGroup(setCode).setFromBusinessIdentifier(NestedSet.FIELD_PARENT,parentCode));
		}
	}
	
}
