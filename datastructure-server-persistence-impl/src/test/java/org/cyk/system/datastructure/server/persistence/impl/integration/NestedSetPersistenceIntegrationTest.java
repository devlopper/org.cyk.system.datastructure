package org.cyk.system.datastructure.server.persistence.impl.integration;

import org.cyk.system.datastructure.server.persistence.api.collection.set.nested.NestedSetPersistence;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceEntityIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Assert;
import org.junit.Test;

public class NestedSetPersistenceIntegrationTest extends AbstractPersistenceEntityIntegrationTestWithDefaultDeploymentAsSwram<NestedSet> {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		//assertionHelper.setIsLogAssertionEnable(Boolean.FALSE);
	}
	
	@Override
	protected NestedSet __instanciateEntity__(Object action) throws Exception {
		return super.__instanciateEntity__(action).setLeftIndex(0).setRightIndex(1).setGroup(getRandomCode());
	}
	
	@Test
	public void createTree(){
		String set01 = getRandomCode();
		String set01Set01 = getRandomCode();
		String set01Set01Set01 = getRandomCode() , set01Set01Set02 = getRandomCode() , set01Set01Set03 = getRandomCode()
				, set01Set01Set01Set01 = getRandomCode()
						, set01Set01Set01Set02 = getRandomCode(), set01Set01Set03Set01 = getRandomCode(), set01Set01Set03Set02 = getRandomCode()
								, set01Set01Set03Set03 = getRandomCode(), set01Set01Set03Set04 = getRandomCode();
		//createSets(set01);
		createSets(set01, null, new Object[]{set01Set01,0,19});
		createSets(set01, set01Set01, new Object[]{set01Set01Set01,1,6},new Object[]{set01Set01Set02,7,8},new Object[]{set01Set01Set03,9,18});
		createSets(set01, set01Set01Set01, new Object[]{set01Set01Set01Set01,2,3},new Object[]{set01Set01Set01Set02,4,5});
		createSets(set01, set01Set01Set03, new Object[]{set01Set01Set03Set01,10,11},new Object[]{set01Set01Set03Set02,12,13}
			,new Object[]{set01Set01Set03Set03,14,15},new Object[]{set01Set01Set03Set04,16,17});
		
		Assert.assertEquals(new Long(10),__inject__(NestedSetPersistence.class).countByGroup(set01));
		
		Assert.assertEquals(new Long(9),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01)));
		
		Assert.assertEquals(new Long(2),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set01)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set02)));
		Assert.assertEquals(new Long(4),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set03)));
		
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set01Set01)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set01Set02)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set03Set01)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set03Set02)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set03Set03)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetPersistence.class).countByParent(__readByBusinessIdentifier__(NestedSet.class,set01Set01Set03Set04)));
	}
	
	/**/
	
	private void createSets(String setCode,String parentCode,Object[]...childrenArray){
		for(Object[] index : childrenArray){
			__createEntity__(new NestedSet().setCode(index[0].toString()).setLeftIndex((Integer) index[1]).setRightIndex((Integer) index[2])
					.setGroup(setCode)
					.setFromBusinessIdentifier(NestedSet.FIELD_PARENT,parentCode));
		}
	}
}
