package org.cyk.system.datastructure.server.representation.api.collection.set.nested;

import javax.ws.rs.Path;

import org.cyk.system.datastructure.server.representation.entities.collection.set.nested.NestedSet;
import org.cyk.utility.server.representation.RepresentationEntity;

@Path(NestedSetRepresentation.PATH)
public interface NestedSetRepresentation extends RepresentationEntity<org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet,NestedSet> {

	String PATH = "/nestedset/";
}
