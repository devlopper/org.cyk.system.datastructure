package org.cyk.system.datastructure.server.business.impl.collection.set.nested;

import java.io.Serializable;

import org.cyk.system.datastructure.server.business.api.collection.set.nested.NestedSetBusiness;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.__kernel__.properties.Properties;
import org.cyk.utility.server.business.AbstractBusinessEntityImpl;
import org.cyk.utility.server.business.BusinessServiceProvider;
import org.cyk.utility.throwable.ThrowableHelper;

public class NestedSetBusinessImpl extends AbstractBusinessEntityImpl<NestedSet> implements NestedSetBusiness , Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public BusinessServiceProvider<NestedSet> create(NestedSet nestedSet, Properties properties) {
		if(nestedSet.getLeftIndex() != null || nestedSet.getRightIndex() != null)
			__inject__(ThrowableHelper.class).throwRuntimeException("Left and right index must not be set");
		if(nestedSet.getParent() == null){
			nestedSet.setLeftIndex(0);
		}else {
			nestedSet.setLeftIndex(nestedSet.getParent().getRightIndex()+1);
		}
		nestedSet.setRightIndex(nestedSet.getLeftIndex()+1);
		
		return super.create(nestedSet, properties);
	}
}
