package org.cyk.system.datastructure.server.persistence.impl.integration;

import javax.inject.Inject;

import org.cyk.system.datastructure.server.persistence.api.collection.set.nested.NestedSetPersistence;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSetNode;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceEntityIntegrationTest;

public class NestedSetNodePersistenceIntegrationTest extends AbstractPersistenceEntityIntegrationTest<NestedSetNode> {
	private static final long serialVersionUID = 1L;
	
	@Inject private NestedSetPersistence nestedSetPersistence;
	
	@Override
	protected NestedSetNode __instanciateEntity__(Object action) throws Exception {
		NestedSetNode nestedSetNode = super.__instanciateEntity__(action);
		nestedSetNode.setSet(new NestedSet().setCode(getRandomCode())).setLeftIndex(0).setRightIndex(0);
		create(nestedSetPersistence,nestedSetNode.getSet());
		return nestedSetNode;
	}
	
}
