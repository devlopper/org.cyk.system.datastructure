package org.cyk.system.datastructure.server.business.api.collection.set.nested;

import java.util.Collection;

import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.BusinessEntity;

public interface NestedSetBusiness extends BusinessEntity<NestedSet> {

	Collection<NestedSet> findByGroup(String group);
	Long countByGroup(String group);
	
	Collection<NestedSet> findByParent(NestedSet nestedSet,Properties properties);
	Collection<NestedSet> findByParent(NestedSet nestedSet);
	Collection<NestedSet> findByParent(String nestedSetCode,Properties properties);
	Collection<NestedSet> findByParent(String nestedSetCode);
	
	Long countByParent(NestedSet nestedSet,Properties properties);
	Long countByParent(NestedSet nestedSet);
	Long countByParent(String nestedSetCode,Properties properties);
	Long countByParent(String nestedSetCode);
	
}
