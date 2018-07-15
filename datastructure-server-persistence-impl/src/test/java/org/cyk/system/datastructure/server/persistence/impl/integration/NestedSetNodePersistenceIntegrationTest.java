package org.cyk.system.datastructure.server.persistence.impl.integration;

import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSetNode;
import org.cyk.utility.server.persistence.test.TestPersistenceCreate;
import org.cyk.utility.server.persistence.test.arquillian.AbstractArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;

public class NestedSetNodePersistenceIntegrationTest extends AbstractArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
		
	@Test
	public void create() throws Exception{
		String set01Code = getRandomCode();
		NestedSet set01 = new NestedSet().setCode(set01Code);
		__inject__(TestPersistenceCreate.class).setObject(set01).execute().assertThrowableIsNull();
		String node01Code = getRandomCode();
		NestedSetNode node01 = new NestedSetNode().setSet(set01).setCode(node01Code).setLeftIndex(0).setRightIndex(1);
		__inject__(TestPersistenceCreate.class).setObject(node01).execute().assertThrowableIsNull();
	}
	/*
	@Test
	public void read() throws Exception{
		String code = getRandomCode();
		NestedSet entity = new NestedSet().setCode(code);
		userTransaction.begin();
		persistence.create(entity);
		userTransaction.commit();
		assertThat(entity.getIdentifier()).isNotNull();
		entity = persistence.readOne(entity.getIdentifier());
		assertThat(entity).isNotNull();
		assertThat(entity.getCode()).isEqualTo(code);
		assertionHelper.assertStartsWithLastLogEventMessage("Server Persistence Read NestedSet").assertContainsLastLogEventMessage("code="+code);
		
	}
	
	@Test
	public void update() throws Exception{
		String code = getRandomCode();
		NestedSet entity = new NestedSet().setCode(code);
		userTransaction.begin();
		persistence.create(entity);
		userTransaction.commit();
		entity = persistence.readOne(entity.getIdentifier());
		String newCode = getRandomCode();
		entity.setCode(newCode);
		userTransaction.begin();
		persistence.update(entity);
		userTransaction.commit();
		assertionHelper.assertStartsWithLastLogEventMessage("Server Persistence Update NestedSet").assertContainsLastLogEventMessage("code="+newCode);
		entity = persistence.readOne(entity.getIdentifier());
		assertThat(entity).isNotNull();
		assertThat(entity.getCode()).isEqualTo(newCode);
	}
	
	@Test
	public void delete() throws Exception{
		String code = getRandomCode();
		NestedSet entity = new NestedSet().setCode(code);
		userTransaction.begin();
		persistence.create(entity);
		userTransaction.commit();
		entity = persistence.readOne(entity.getIdentifier());
		assertThat(entity).isNotNull();
		userTransaction.begin();
		persistence.delete(entity);
		userTransaction.commit();
		assertionHelper.assertStartsWithLastLogEventMessage("Server Persistence Delete NestedSet").assertContainsLastLogEventMessage("code="+code);
		entity = persistence.readOne(entity.getIdentifier());
		assertThat(entity).isNull();
	}
	*/
	
}
