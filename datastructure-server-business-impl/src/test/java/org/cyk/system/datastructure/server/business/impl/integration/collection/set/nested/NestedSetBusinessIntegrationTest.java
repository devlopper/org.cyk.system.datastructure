package org.cyk.system.datastructure.server.business.impl.integration.collection.set.nested;

import org.cyk.system.datastructure.server.business.api.collection.set.nested.NestedSetBusiness;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.server.business.test.arquillian.AbstractBusinessEntityIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Assert;
import org.junit.Test;

public class NestedSetBusinessIntegrationTest extends AbstractBusinessEntityIntegrationTestWithDefaultDeploymentAsSwram<NestedSet> {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected NestedSet __instanciateEntity__(Object action) throws Exception {
		return super.__instanciateEntity__(action).setGroup(getRandomCode());
	}
	
	@Test
	public void createTree(){
		String set01 = getRandomCode();
		String set01Set01 = getRandomCode() , set01Set02 = getRandomCode() , set01Set03 = getRandomCode()
				, set01Set01Set01 = getRandomCode()
						, set01Set01Set02 = getRandomCode(), set01Set03Set01 = getRandomCode(), set01Set03Set02 = getRandomCode()
								, set01Set03Set03 = getRandomCode(), set01Set03Set04 = getRandomCode();
		//createSets(set01);
		createSets(set01, null, set01);
		//Assert.assertEquals(new Long(10),__inject__(NestedSetBusiness.class).countByGroup(set01));
		
		createSets(set01, set01, set01Set01,set01Set02,set01Set03);
		/*createSets(set01, set01Set01, new Object[]{set01Set01Set01,2,3},new Object[]{set01Set01Set02,4,5});
		createSets(set01, set01Set03, new Object[]{set01Set03Set01,10,11},new Object[]{set01Set03Set02,12,13}
			,new Object[]{set01Set03Set03,14,15},new Object[]{set01Set03Set04,16,17});
		*/
		/*
		Assert.assertEquals(new Long(10),__inject__(NestedSetBusiness.class).countByGroup(set01));
		
		Assert.assertEquals(new Long(9),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01)));
		
		Assert.assertEquals(new Long(2),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set02)));
		Assert.assertEquals(new Long(4),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set03)));
		
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set01)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set02)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set03Set01)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set03Set02)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set03Set03)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set03Set04)));
		*/
	}
	
	/**/
	
	private void createSets(String setCode,String parentCode,String...children){
		for(String index : children){
			__createEntity__(new NestedSet().setCode(index).setGroup(setCode).setFromBusinessIdentifier(NestedSet.FIELD_PARENT,parentCode));
		}
	}
	
}
