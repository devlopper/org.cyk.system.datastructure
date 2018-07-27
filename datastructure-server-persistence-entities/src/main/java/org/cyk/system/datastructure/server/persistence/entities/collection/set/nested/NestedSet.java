package org.cyk.system.datastructure.server.persistence.entities.collection.set.nested;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.cyk.utility.server.persistence.jpa.AbstractEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain = true) 
@Table(name = NestedSetNode.TABLE)
@Deprecated //@Entity
/*
 * This is used to group nodes. a set must have a node which is the root of the represented tree.
 * Hence for each set a node is created.
 * To enforce integrity :
 * 	1 - a set must have a node (to avoid set with no node , which means group with no nodes , a group exist because a nodes)
 *	2 - a node must have set
 *
 * 	1 & 2 create a reflexive dependency which is impossible to satisfy
 * 
 * 	To resolve this , we will use the set(group) identifier only in the node
 */
public class NestedSet extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull @ManyToOne @JoinColumn(name = COLUMN_NODE)
	private NestedSetNode node;

	/**/

	@Override
	public NestedSet setCode(String code) {
		return (NestedSet) super.setCode(code);
	}

	/**/

	public static final String FIELD_NODE = "node";

	public static final String COLUMN_NODE = FIELD_NODE;

	public static final String TABLE = Constant.TABLE_NAME_PREFIX + "NestedSet";
}
