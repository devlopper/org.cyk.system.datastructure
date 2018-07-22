package org.cyk.system.datastructure.server.persistence.impl.integration;

import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSetNode;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceEntityIntegrationTestWithDefaultDeploymentAsSwram;

public class NestedSetNodePersistenceIntegrationTest extends AbstractPersistenceEntityIntegrationTestWithDefaultDeploymentAsSwram<NestedSetNode> {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected NestedSetNode __instanciateEntity__(Object action) throws Exception {
		NestedSetNode nestedSetNode = super.__instanciateEntity__(action);
		nestedSetNode.setSet(new NestedSet().setCode(getRandomCode())).setLeftIndex(0).setRightIndex(0);
		__createEntity__(nestedSetNode.getSet());
		return nestedSetNode;
	}
	
}
