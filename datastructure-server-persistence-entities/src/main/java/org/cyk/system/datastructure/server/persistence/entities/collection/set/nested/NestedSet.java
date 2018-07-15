package org.cyk.system.datastructure.server.persistence.entities.collection.set.nested;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.cyk.utility.server.persistence.jpa.AbstractEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true) @Entity
public class NestedSet extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne @JoinColumn(name=COLUMN_ROOT)
	private NestedSetNode root;
	
	/**/
	
	@Override
	public NestedSet setCode(String code) {
		return (NestedSet) super.setCode(code);
	}
	
	/**/
	
	public static final String FIELD_ROOT = "root";
	
	public static final String COLUMN_ROOT = FIELD_ROOT;
}
