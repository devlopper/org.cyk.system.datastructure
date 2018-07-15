package org.cyk.system.datastructure.server.persistence.entities.hierarchy;

import java.io.Serializable;

import javax.persistence.Entity;

import org.cyk.utility.server.persistence.jpa.AbstractEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @Entity
public class Hierarchy extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long numberOfChildren;
	private Long numberOfDescendant;
	
	/**/
	
	@Override
	public Hierarchy setCode(String code) {
		return (Hierarchy) super.setCode(code);
	}
	
	/**/
	
	public static final String FIELD_NUMBER_OF_CHILDREN = "numberOfChildren";
	public static final String FIELD_NUMBER_OF_DESCENDANT = "numberOfDescendant";
}
