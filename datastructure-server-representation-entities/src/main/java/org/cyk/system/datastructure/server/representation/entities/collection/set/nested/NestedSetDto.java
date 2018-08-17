package org.cyk.system.datastructure.server.representation.entities.collection.set.nested;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyk.utility.server.representation.AbstractDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class NestedSetDto extends AbstractDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String group;
	
	private String parent;
	
	private String leftIndex;
	
	private String rightIndex;
	
	private String numberOfChildren;
	
	private String numberOfDescendant;
	
	private String numberOfAscendant;
	/*
	@Override
	public org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet getPersistenceEntity() {
		org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet persistenceEntity = new org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet();
		persistenceEntity.setCode(getCode());
		persistenceEntity.setGroup(group);
		persistenceEntity.setParent(__getFromBusinessIdentifier__(org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet.class, parent));
		persistenceEntity.setLeftIndex(__getIntegerFrom__(leftIndex));
		persistenceEntity.setRightIndex(__getIntegerFrom__(rightIndex));
		persistenceEntity.setNumberOfChildren(__getIntegerFrom__(numberOfChildren));
		persistenceEntity.setNumberOfDescendant(__getIntegerFrom__(numberOfDescendant));
		persistenceEntity.setNumberOfAscendant(__getIntegerFrom__(numberOfAscendant));
		return persistenceEntity;
	}
	*/
	
}
