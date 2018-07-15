package org.cyk.system.datastructure.server.persistence.impl.integration;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.cyk.system.datastructure.server.persistence.entities.hierarchy.Hierarchy;
import org.cyk.utility.server.persistence.jpa.Persistence;
import org.cyk.utility.server.persistence.test.arquillian.AbstractArquillianIntegrationTestWithDefaultDeploymentAsSwram;
import org.junit.Test;

public class HierarchyPersistenceIntegrationTest extends AbstractArquillianIntegrationTestWithDefaultDeploymentAsSwram {
	private static final long serialVersionUID = 1L;
		
	@Inject private Persistence persistence;
	
	@Test
	public void create() throws Exception{
		Hierarchy hierarchy = new Hierarchy().setCode("mc001");
		userTransaction.begin();
		persistence.create(hierarchy);
		userTransaction.commit();
		assertThat(hierarchy.getIdentifier()).isNotNull();
		assertionHelper.assertContainsLastLogEventMessage("Server Persistence Create Hierarchy")
			.assertContainsLastLogEventMessage("code=mc001");
	}
	
	@Test
	public void read() throws Exception{
		Hierarchy hierarchy = new Hierarchy().setCode("mc001");
		userTransaction.begin();
		persistence.create(hierarchy);
		userTransaction.commit();
		assertThat(hierarchy.getIdentifier()).isNotNull();
		hierarchy = persistence.readOne(Hierarchy.class,hierarchy.getIdentifier());
		assertThat(hierarchy).isNotNull();
		assertThat(hierarchy.getCode()).isEqualTo("mc001");
		assertionHelper.assertContainsLastLogEventMessage("Server Persistence Read Hierarchy")
		.assertContainsLastLogEventMessage("code=mc001");
		
	}
	/*
	@Test
	public void update() throws Exception{
		MyEntity myEntity = new MyEntity().setCode("mc001");
		userTransaction.begin();
		persistence.create(myEntity);
		userTransaction.commit();
		myEntity = persistence.read(MyEntity.class,myEntity.getIdentifier());
		myEntity.setCode("nc001");
		userTransaction.begin();
		persistence.update(myEntity);
		userTransaction.commit();
		assertThat(getLastLogEventMessage()).startsWith("Server Persistence Update MyEntity");
		myEntity = persistence.read(MyEntity.class,myEntity.getIdentifier());
		assertThat(myEntity).isNotNull();
		assertThat(myEntity.getCode()).isEqualTo("nc001");
	}
	
	@Test
	public void delete() throws Exception{
		MyEntity myEntity = new MyEntity().setCode("mc001");
		userTransaction.begin();
		persistence.create(myEntity);
		userTransaction.commit();
		myEntity = persistence.read(MyEntity.class,myEntity.getIdentifier());
		assertThat(myEntity).isNotNull();
		userTransaction.begin();
		persistence.delete(myEntity);
		userTransaction.commit();
		assertThat(getLastLogEventMessage()).startsWith("Server Persistence Delete MyEntity");
		myEntity = persistence.read(MyEntity.class,myEntity.getIdentifier());
		assertThat(myEntity).isNull();
	}
	*/
	
}
