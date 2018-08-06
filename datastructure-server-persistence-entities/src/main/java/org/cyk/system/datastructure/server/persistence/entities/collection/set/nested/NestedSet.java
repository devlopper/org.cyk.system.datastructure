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
@Table(name=NestedSet.TABLE,
		uniqueConstraints={
		@UniqueConstraint(name=NestedSet.TABLE_CONSTRAINT_LEFT_INDEX_IS_UNIQUE, columnNames={NestedSet.COLUMN_GROUP,NestedSet.FIELD_LEFT_INDEX})
		,@UniqueConstraint(name=NestedSet.TABLE_CONSTRAINT_RIGHT_INDEX_IS_UNIQUE,columnNames={NestedSet.COLUMN_GROUP,NestedSet.FIELD_RIGHT_INDEX})
})
public class NestedSet extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @Column(name=COLUMN_GROUP,nullable=false)
	private String group;
	
	@ManyToOne @JoinColumn(name=COLUMN_PARENT)
	private NestedSet parent;
	
	@NotNull @Column(nullable=false)
	private Integer leftIndex;
	
	@NotNull @Column(nullable=false)
	private Integer rightIndex;
	
	/**
	 * This is to mark that the nested set has been detach from its parent but still available.
	 * Hence , its indexes must have the opposite value
	 */
	//private String detachedIdentifier;
	
	/* Derived */
	
	private Integer numberOfChildren;
	
	private Integer numberOfDescendant;
	
	private Integer numberOfAscendant;
	
	
	/**/
	
	public Integer getNumberOfDescendant(){
		if(numberOfDescendant == null)
			numberOfDescendant =  (rightIndex == null || leftIndex == null ? 0 : rightIndex - leftIndex - 1)/2;
		return numberOfDescendant;
	}
	
	/**/
	
	@Override
	public NestedSet setCode(String code) {
		return (NestedSet) super.setCode(code);
	}
	
	public NestedSet setParentFromCode(String code){
		return setFromBusinessIdentifier(FIELD_PARENT, code);
	}
	
	@Override
	public NestedSet setFromBusinessIdentifier(Field field, Object identifier) {
		return (NestedSet) super.setFromBusinessIdentifier(field, identifier);
	}
	
	@Override
	public NestedSet setFromBusinessIdentifier(String fieldName, Object identifier) {
		return (NestedSet) super.setFromBusinessIdentifier(fieldName, identifier);
	}
	
	/**/
	
	@Override
	public String toString() {
		return getCode()+","+getLeftIndex()+","+getRightIndex();
	}
	
	/**/
	
	public static final String FIELD_GROUP = "group";
	public static final String FIELD_PARENT = "parent";
	public static final String FIELD_LEFT_INDEX = "leftIndex";
	public static final String FIELD_RIGHT_INDEX = "rightIndex";
	public static final String FIELD_NUMBER_OF_CHILDREN = "numberOfChildren";
	public static final String FIELD_NUMBER_OF_DESCENDANT = "numberOfDescendant";
	
	public static final String COLUMN_PARENT = FIELD_PARENT;
	public static final String COLUMN_GROUP = FIELD_GROUP+"_";
	
	public static final String TABLE = Constant.TABLE_NAME_PREFIX+"NestedSet";
	public static final String TABLE_CONSTRAINT_LEFT_INDEX_IS_UNIQUE = "LeftIndexIsUnique";
	public static final String TABLE_CONSTRAINT_RIGHT_INDEX_IS_UNIQUE = "RightIndexIsUnique";
	
	/**/
	
	public static String generateCode(String parent,Integer index){
		return parent+"_"+index;
	}
}
