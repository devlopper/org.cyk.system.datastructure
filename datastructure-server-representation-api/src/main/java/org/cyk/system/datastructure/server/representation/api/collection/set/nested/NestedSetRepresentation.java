package org.cyk.system.datastructure.server.representation.api.collection.set.nested;

import javax.ws.rs.Path;

import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.system.datastructure.server.representation.entities.collection.set.nested.NestedSetDto;
import org.cyk.system.datastructure.server.representation.entities.collection.set.nested.NestedSetDtoCollection;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(NestedSetRepresentation.PATH)
public interface NestedSetRepresentation extends RepresentationEntity<NestedSet,NestedSetDto,NestedSetDtoCollection> {

	String PATH = "/nestedset";
}
