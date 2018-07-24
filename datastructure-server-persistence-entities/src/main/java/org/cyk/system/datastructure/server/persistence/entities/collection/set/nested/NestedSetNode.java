package org.cyk.system.datastructure.server.persistence.entities.collection.set.nested;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @Entity @Access(AccessType.FIELD)
//TODO add unique constraint (nestedSet,leftIndex),(nestedSet,rightIndex)
@Table(uniqueConstraints={
		@UniqueConstraint(name=NestedSetNode.FIELD_NESTED_SET+"_"+NestedSetNode.FIELD_LEFT_INDEX, columnNames={NestedSetNode.FIELD_NESTED_SET,NestedSetNode.FIELD_LEFT_INDEX})
		,@UniqueConstraint(name=NestedSetNode.FIELD_NESTED_SET+"_"+NestedSetNode.FIELD_RIGHT_INDEX,columnNames={NestedSetNode.FIELD_NESTED_SET,NestedSetNode.FIELD_RIGHT_INDEX})
})
public class NestedSetNode extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name=COLUMN_NESTED_SET)
	private NestedSet nestedSet;
	
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
	
	@Override
	public NestedSetNode setFromBusinessIdentifier(Field field, Object identifier) {
		return (NestedSetNode) super.setFromBusinessIdentifier(field, identifier);
	}
	
	/**/
	
	public static final String FIELD_NESTED_SET = "nestedSet";
	public static final String FIELD_PARENT = "parent";
	public static final String FIELD_LEFT_INDEX = "leftIndex";
	public static final String FIELD_RIGHT_INDEX = "rightIndex";
	public static final String FIELD_NUMBER_OF_CHILDREN = "numberOfChildren";
	public static final String FIELD_NUMBER_OF_DESCENDANT = "numberOfDescendant";
	
	public static final String COLUMN_NESTED_SET = FIELD_NESTED_SET;
	public static final String COLUMN_PARENT = FIELD_PARENT;
}
