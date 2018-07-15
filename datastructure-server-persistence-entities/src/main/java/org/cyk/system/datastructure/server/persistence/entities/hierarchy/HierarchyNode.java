package org.cyk.system.datastructure.server.persistence.entities.hierarchy;

import java.io.Serializable;

import javax.persistence.Entity;

import org.cyk.utility.server.persistence.jpa.AbstractEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @Entity
public class HierarchyNode extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
}
