package org.cyk.system.datastructure.server.business.impl.collection.set.nested;

import java.util.Comparator;

import org.cyk.system.datastructure.server.persistence.entities.collection.set.nested.NestedSet;
import org.cyk.utility.__kernel__.computation.SortOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain=true)
public class NestedSetComparator implements Comparator<NestedSet> {

	private SortOrder sortOrder;
	
	@Override
	public int compare(NestedSet nestedSet1, NestedSet nestedSet2) {
		if(nestedSet1!=null && nestedSet2!=null && nestedSet1.getGroup()!=null && nestedSet2.getGroup()!=null &&
				nestedSet1.getGroup().equals(nestedSet2.getGroup())) {
			if( 
					(nestedSet1.getParent() == null && nestedSet2.getParent() == null) || 
					(nestedSet1.getParent() != null && nestedSet2.getParent() != null && nestedSet1.getParent().getIdentifier().equals(nestedSet2.getParent().getIdentifier())) ) {
				
				if(nestedSet1.getLeftIndex() < nestedSet2.getLeftIndex())
					return sortOrder == null || SortOrder.ASCENDING.equals(sortOrder) ? -1 : 1;
				else
					return sortOrder == null || SortOrder.ASCENDING.equals(sortOrder) ? 1 : -1;
			}
		}
		
		if(nestedSet1.getParent() == null)
			if(nestedSet2.getParent() == null)
				return 0;
			else
				return sortOrder == null || SortOrder.ASCENDING.equals(sortOrder) ? -1 : 1;
		else
			if(nestedSet2.getParent() == null)
				return 1;
			else
				return 0;
	}

}
