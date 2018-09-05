package org.cyk.system.datastructure.server.business.impl.collection.set.nested;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

import org.cyk.system.datastructure.server.business.api.collection.set.nested.NestedSetAssertionsProvider;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.__kernel__.AbstractApplicationScopeLifeCycleListener;
import org.cyk.utility.assertion.AssertionsProviderClassMap;

@ApplicationScoped
public class ApplicationScopeLifeCycleListener extends AbstractApplicationScopeLifeCycleListener implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __initialize__(Object object) {
		__inject__(AssertionsProviderClassMap.class).set(NestedSet.class, NestedSetAssertionsProvider.class);
	}
	
	@Override
	protected void __destroy__(Object object) {}
	
}