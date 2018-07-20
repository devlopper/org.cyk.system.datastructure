package org.cyk.system.datastructure.business.impl.integration.collection.set.nested;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.cyk.system.datastructure.business.api.collection.set.nested.NestedSetBusiness;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.server.business.test.arquillian.AbstractArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;

public class NestedSetBusinessIntegrationTest extends AbstractArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
		
	@Inject private NestedSetBusiness business;
	
	@Test
	public void create() throws Exception{
		String code = getRandomCode();
		NestedSet entity = new NestedSet().setCode(code);
		business.create(entity);
		assertThat(entity.getIdentifier()).isNotNull();
		assertionHelper.assertStartsWithLastLogEventMessage("Server Business Create NestedSet").assertContainsLastLogEventMessage("code="+code);
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
	
	@Test
	public void readByCode() throws Exception{
		String code = getRandomCode();
		NestedSet entity = new NestedSet().setCode(code);
		userTransaction.begin();
		persistence.create(entity);
		userTransaction.commit();
		
		entity =  persistence.readOneByBusinessIdentifier(code); 
		assertionHelper.assertNotNull(entity);
		assertionHelper.assertStartsWithLastLogEventMessage("Server Persistence Read NestedSet").assertContainsLastLogEventMessage("code="+code);
	}
	*/
}
