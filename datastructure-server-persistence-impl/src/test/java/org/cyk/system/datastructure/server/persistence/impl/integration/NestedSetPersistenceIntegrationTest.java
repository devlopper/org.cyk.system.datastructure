package org.cyk.system.datastructure.server.persistence.impl.integration;

import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSetNode;
import org.cyk.utility.server.persistence.test.arquillian.AbstractPersistenceEntityIntegrationTestWithDefaultDeploymentAsSwram;

@Deprecated
public class NestedSetPersistenceIntegrationTest extends AbstractPersistenceEntityIntegrationTestWithDefaultDeploymentAsSwram<NestedSet> {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected NestedSet __instanciateEntity__(Object action) throws Exception {
		NestedSet nestedSet = super.__instanciateEntity__(action);
		NestedSetNode nestedSetNode = new NestedSetNode();
		nestedSetNode.setLeftIndex(0).setRightIndex(1);
		__createEntity__(nestedSetNode);
		return nestedSet.setNode(nestedSetNode);
	}
	
}
