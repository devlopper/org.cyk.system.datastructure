package org.cyk.system.datastructure.server.persistence.impl.integration;

import org.cyk.system.datastructure.server.persistence.api.collection.set.nested.NestedSetNodePersistence;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSetNode;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceEntityIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Assert;
import org.junit.Test;

public class NestedSetNodePersistenceIntegrationTest extends AbstractPersistenceEntityIntegrationTestWithDefaultDeploymentAsSwram<NestedSetNode> {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void __listenPostConstruct__() {
		super.__listenPostConstruct__();
		//assertionHelper.setIsLogAssertionEnable(Boolean.FALSE);
	}
	
	@Test
	public void createTree(){
		String set01 = getRandomCode();
		String set01Node01 = getRandomCode();
		String set01Node01Node01 = getRandomCode() , set01Node01Node02 = getRandomCode() , set01Node01Node03 = getRandomCode()
				, set01Node01Node01Node01 = getRandomCode()
						, set01Node01Node01Node02 = getRandomCode(), set01Node01Node03Node01 = getRandomCode(), set01Node01Node03Node02 = getRandomCode()
								, set01Node01Node03Node03 = getRandomCode(), set01Node01Node03Node04 = getRandomCode();
		//createSets(set01);
		createNodes(set01, null, new Object[]{set01Node01,0,19});
		createNodes(set01, set01Node01, new Object[]{set01Node01Node01,1,6},new Object[]{set01Node01Node02,7,8},new Object[]{set01Node01Node03,9,18});
		createNodes(set01, set01Node01Node01, new Object[]{set01Node01Node01Node01,2,3},new Object[]{set01Node01Node01Node02,4,5});
		createNodes(set01, set01Node01Node03, new Object[]{set01Node01Node03Node01,10,11},new Object[]{set01Node01Node03Node02,12,13}
			,new Object[]{set01Node01Node03Node03,14,15},new Object[]{set01Node01Node03Node04,16,17});
		
		Assert.assertEquals(new Long(10),__inject__(NestedSetNodePersistence.class).countBySet(set01));
		
		Assert.assertEquals(new Long(9),__inject__(NestedSetNodePersistence.class).countByParent(__readByBusinessIdentifier__(NestedSetNode.class,set01Node01)));
		
		Assert.assertEquals(new Long(2),__inject__(NestedSetNodePersistence.class).countByParent(__readByBusinessIdentifier__(NestedSetNode.class,set01Node01Node01)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetNodePersistence.class).countByParent(__readByBusinessIdentifier__(NestedSetNode.class,set01Node01Node02)));
		Assert.assertEquals(new Long(4),__inject__(NestedSetNodePersistence.class).countByParent(__readByBusinessIdentifier__(NestedSetNode.class,set01Node01Node03)));
		
		Assert.assertEquals(new Long(0),__inject__(NestedSetNodePersistence.class).countByParent(__readByBusinessIdentifier__(NestedSetNode.class,set01Node01Node01Node01)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetNodePersistence.class).countByParent(__readByBusinessIdentifier__(NestedSetNode.class,set01Node01Node01Node02)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetNodePersistence.class).countByParent(__readByBusinessIdentifier__(NestedSetNode.class,set01Node01Node03Node01)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetNodePersistence.class).countByParent(__readByBusinessIdentifier__(NestedSetNode.class,set01Node01Node03Node02)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetNodePersistence.class).countByParent(__readByBusinessIdentifier__(NestedSetNode.class,set01Node01Node03Node03)));
		Assert.assertEquals(new Long(0),__inject__(NestedSetNodePersistence.class).countByParent(__readByBusinessIdentifier__(NestedSetNode.class,set01Node01Node03Node04)));
	}
	
	/**/
	
	private void createNodes(String setCode,String parentCode,Object[]...childrenArray){
		for(Object[] index : childrenArray){
			__createEntity__(new NestedSetNode().setCode(index[0].toString()).setLeftIndex((Integer) index[1]).setRightIndex((Integer) index[2])
					.setFromBusinessIdentifier(NestedSetNode.FIELD_PARENT,parentCode)
					.setFromBusinessIdentifier(NestedSetNode.FIELD_NESTED_SET,setCode));
		}
	}
}
