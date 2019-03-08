package org.cyk.system.datastructure.server.representation.impl.collection.set.nested;

import java.io.Serializable;

import javax.inject.Singleton;

import org.cyk.system.datastructure.server.business.api.collection.set.nested.NestedSetBusiness;
import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.system.datastructure.server.representation.api.collection.set.nested.NestedSetRepresentation;
import org.cyk.system.datastructure.server.representation.entities.collection.set.nested.NestedSetDto;
import org.cyk.system.datastructure.server.representation.entities.collection.set.nested.NestedSetDtoCollection;
import org.cyk.utility.server.representation.AbstractRepresentationEntityImpl;

@Singleton
public class NestedSetRepresentationImpl extends AbstractRepresentationEntityImpl<NestedSet, NestedSetBusiness, NestedSetDto,NestedSetDtoCollection> implements NestedSetRepresentation,Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public Class<NestedSet> getPersistenceEntityClass() {
		return NestedSet.class;
	}
	
}
