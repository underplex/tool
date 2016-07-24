package com.underplex.tool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sorter {

	/**
	 * Returns <code>List</code> sorted by <code>comparators</code>.
	 * @param list	original unordered list		
	 * @param comparators	list of comparators to use in the priority that they should be evaluated
	 * @return ordered list of the original elements
	 */
	public static <T> List<T> sortByOrder(List<T> list, List<Comparator<T>> comparators){
		List<T> rList = new ArrayList<T>();
		int last;
		if ( !comparators.isEmpty() ) { // if there are any comparators not yet applied
			last = comparators.size() - 1;
			for ( Set<T> tierSet : subSort( list, comparators.get(last) ) ){
	
				if ( tierSet.size() > 1 ){
					
					List<Comparator<T>> smallerComparators = new ArrayList<Comparator<T>>( comparators );
					smallerComparators.remove(last);
					// sort this by every progressive comparator
					rList.addAll( sortByOrder( new ArrayList<T>( tierSet ), smallerComparators ) );
	
				} else { // that is, if tierSet is only 1
	
					rList.addAll( new ArrayList<T>( tierSet ) );
				
				}
		
			} // end for tierSet
		} else { // that is, if there are no comparators left, just return the list as it was
			rList = new ArrayList<T>(list);
		}
		return rList;
	}
	
	public static <T> List<Set<T>> subSort(List<T> list, Comparator<T> comparator){
		
		List<Set<T>> rList = new ArrayList<Set<T>>();
		
		if ( !list.isEmpty() ){
			rList = nonEmptySort(list, comparator);
		}
		
		return rList;
		
	}

	// assumes list is not empty
	private static <T> List<Set<T>> nonEmptySort(List<T> list, Comparator<T> comparator){
		List<Set<T>> rList = new ArrayList<Set<T>>();
		
		List<T> overlist = new ArrayList<T>(list);
		Collections.sort(overlist, comparator);
		
		T boundary = overlist.get(0);
		Set<T> currentSet = new HashSet<T>();
		currentSet.add(boundary);
		rList.add(currentSet);
		
		for (int i = 1; i < overlist.size(); i++ ){
			if ( comparator.compare(overlist.get(i), boundary) == 0 ){
				currentSet.add( overlist.get(i) );
			} else if ( comparator.compare(overlist.get(i), boundary) > 0 ){
				boundary = overlist.get(i);
				currentSet = new HashSet<T>();
				currentSet.add(boundary);
				rList.add(currentSet);
			}
		}
		return rList;
	}	
}
