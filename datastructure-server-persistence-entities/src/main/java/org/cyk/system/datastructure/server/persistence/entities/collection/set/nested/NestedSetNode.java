package org.cyk.system.datastructure.server.persistence.entities.collection.set.nested;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @Entity @Access(AccessType.FIELD)
public class NestedSetNode extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name=COLUMN_SET)
	private NestedSet set;
	
	@ManyToOne @JoinColumn(name=COLUMN_PARENT)
	private NestedSetNode parent;
	
	@NotNull @Column(nullable=false)
	private Integer leftIndex;
	
	@NotNull @Column(nullable=false)
	private Integer rightIndex;
	
	private Integer numberOfChildren;
	
	private Integer numberOfDescendant;
	
	//private String detachedIdentifier;
	
	/**/
	
	@Override
	public NestedSetNode setCode(String code) {
		return (NestedSetNode) super.setCode(code);
	}
	
	/**/
	
	public static final String FIELD_SET = "set";
	public static final String FIELD_PARENT = "parent";
	public static final String FIELD_LEFT_INDEX = "leftIndex";
	public static final String FIELD_RIGHT_INDEX = "rightIndex";
	public static final String FIELD_NUMBER_OF_CHILDREN = "numberOfChildren";
	public static final String FIELD_NUMBER_OF_DESCENDANT = "numberOfDescendant";
	
	public static final String COLUMN_SET = "the"+FIELD_SET;
	public static final String COLUMN_PARENT = FIELD_PARENT;
}
