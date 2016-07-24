package com.underplex.tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Utility class with static methods for finding elements in collections.
 * @author Brandon Irvine
 */

public class Finder {
	
	private Finder(){
		// don't instantiate
	}
	
	/**
	 * Returns element of collection with <code>toString</code> containing <code>search</code> string.
	 * <p>
	 * Returns <code>null</code> if the collection is empty or if the item can't be found or matched.
	 * <p>
	 * This method isn't case-sensitive. Passing "cadiz" or "Cadiz" or "CADIZ" will have the same effects if your locale is appropriate.
	 * <p>
	 * Since <code>toString</code> might be overriden and/or the search string might be found in multiple elements, this method isn't 
	 * guaranteed to find the desired element. However, this method is a fast, easy way to locate elements known primarily by some name when 
	 * <code>toString</code> is well-defined and the search string is known to uniquely identify the element that you are searching for.
	 * 
	 * @param collection	collection of elements to be searched
	 * @param search		String varargs that need to be found in the string, not case-sensitive
	 */
	public static <T> T find(Collection<T> collection, String...search){
		T rT = null;
		String compare;	
		boolean flag = false;
		
		for ( T t : collection ){
			flag = true;
			compare = t.toString().toUpperCase();
			
			for ( String s : search )
				if ( !compare.contains( s.toUpperCase() ) )
					flag = false;
			
			if ( flag )
				rT = t;
		}
		return rT;
	}
	
	/**
	 * Returns element of collection with <code>toString</code> containing <code>search</code> Strings but not containing <code>without</code> String.
	 * <p>
	 * Returns <code>null</code> if the collection is empty or if no item can be found or matched.
	 * <p>
	 * This method isn't case-sensitive. Passing "cadiz" or "Cadiz" or "CADIZ" will have the same effects if your locale is appropriate.
	 * <p>
	 * Since <code>toString</code> might be overriden and/or the search string might be found in multiple elements, this method isn't 
	 * guaranteed to find the desired element. However, this method is a fast, easy way to locate elements known primarily by some name when 
	 * <code>toString</code> is well-defined and the search string is known to uniquely identify the element that you are searching for.
	 * 
	 * @param without		String that can't be present in returned element, not case-sensitive
	 * @param collection	collection of elements to be searched
	 * @param search		String varargs that need to be found in the string, not case-sensitive
	 */
	public static <T> T findExcluding( String without, Collection<T> collection, String...search ){
		T rT = null;
		String compare;
		boolean flag = false;
		
		for ( T t : collection ){
			flag = true;
			compare = t.toString().toUpperCase();
			
			if ( compare.contains( without.toUpperCase() ) )
				flag = false;
			
			for ( String s : search )
				if ( !compare.contains( s.toUpperCase() ) )
					flag = false;
			
			if ( flag )
				rT = t;
		}
		return rT;
	}
	
	/**
	 * Returns the element of <code>collection</code> that has the highest-ranked element according to <code>comparator</code>.
	 * 
	 * Implemented slowly, this will have a similar effect to finding the last element in a list on which an ascending sort has already been performed.
	 * However, this method ought to be faster since it does not sort the entire list. 
	 * 
	 * If the <code>compare</code> method of <code>comparator</code> would return 0 (that is, does not give a sort priority to either element) then
	 * this method may return either, with no guarantee that one or the other will be returned.
	 * 
	 * If <code>collection</code> is empty or <code>null</code>, this method just returns <code>null</code>.
	 * @param collection	Collection containing elements to examine 
	 * @param comparator	Comparator of the elements
	 * @return
	 */
	public static <T> T last(Collection<T> collection, Comparator<T> comparator){
		T best = null;
		if (collection != null && !collection.isEmpty()){
			List<T> list = new ArrayList<T>(collection);
			best = list.get(0); // collection is guaranteed to have at least 1 element
			
			for (int i = 1; i < collection.size() ; i++)
				if (comparator.compare(list.get(i), best) > 0)
					best = list.get(i);
			
		}

		return best;	
	}
	
	/**
	 * Returns the element of <code>collection</code> that is the lowest-ranked element according to <code>comparator</code>.
	 * 
	 * Implemented slowly, this will have a similar effect to finding the first element in a list on which an ascending sort has already been performed.
	 * However, this method ought to be faster since it does not sort the entire list. 
	 * 
	 * If the <code>compare</code> method of <code>comparator</code> would return 0 (that is, does not give a sort priority to either element) then
	 * this method may return either, with no guarantee that one or the other will be returned.
	 * 
	 * If <code>collection</code> is empty or <code>null</code>, this method just returns <code>null</code>.
	 * @param collection	Collection containing elements to examine 
	 * @param comparator	Comparator of the elements
	 * @return
	 */
	public static <T> T first(Collection<T> collection, Comparator<T> comparator){
		T best = null;
		if (collection != null && !collection.isEmpty()){
			List<T> list = new ArrayList<T>(collection);
			best = list.get(0); // collection is guaranteed to have at least 1 element
			
			for (int i = 1; i < collection.size() ; i++)
				if (comparator.compare(list.get(i), best) < 0)
					best = list.get(i);
			
		}

		return best;	
	}
	
	
	
	
}
