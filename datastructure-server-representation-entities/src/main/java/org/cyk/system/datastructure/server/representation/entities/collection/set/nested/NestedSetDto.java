package org.cyk.system.datastructure.server.representation.entities.collection.set.nested;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.cyk.utility.server.representation.AbstractEntityFromPersistenceEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@XmlRootElement @Getter @Setter @Accessors(chain=true) @NoArgsConstructor
public class NestedSetDto extends AbstractEntityFromPersistenceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String group;
	
	private NestedSetDto parent;
	
	private String leftIndex;
	
	private String rightIndex;
	
	private String numberOfChildren;
	
	private String numberOfDescendant;
	
	private String numberOfAscendant;
	
	@Override
	public NestedSetDto setCode(String code) {
		return (NestedSetDto) super.setCode(code);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}
