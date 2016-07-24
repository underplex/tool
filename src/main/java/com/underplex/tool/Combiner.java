package com.underplex.tool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Combiner {

	private Combiner(){
		// don't instantiate
	}
	
	/**
	 * Returns Set of Sets representing every combination of <code>k</code> elements from <code>among</code>.
	 * <p>
	 * Four conditions will return a set of representing no choices made, that is, a set containing exactly one empty set:
	 * <ul>
	 * <li><code>among</code> is null</code></li>
	 * <li><code>among</code> is empty</code></li>
	 * <li><code>k <= 0</code> is null</code></li>
	 * <li><code>k >= among.size()</code></li>
	 * </ul>
	 */
	public static <T> Set<Set<T>> chooseK(Set<T> among, int k){
		Set<Set<T>> master = new HashSet<Set<T>>(); // the master set of all sets
		if (among == null){
			master.add(new HashSet<T>());
		} else if (among.isEmpty() || k <= 0 || k > among.size()){
			master.add(new HashSet<T>());
		} else {
			Set<T> working = new HashSet<T>(); // the current working set of T
			List<T> chooseFrom = new ArrayList<T>(among); // items to use in choosing
			makeSets(master, k, working, 0, chooseFrom);
		}
		return master;
	}
	
	/**
	 * Returns Set representing every combination of choosing exactly 1 element from each of the sets provided.
	 * <p>
	 * Any empty Sets present in <code>among</code> will be ignored as though they did not belong to <code>among</code>.
	 * <p>
	 * Two conditions will return a set of representing no choices made, that is, a set containing exactly one empty set:
	 * <ul>
	 * <li><code>among</code> is null</code></li>
	 * <li><code>among</code> is empty or has no non-empty Sets</code></li>
	 * </ul> 
	 */
	public static <T> Set<Set<T>> chooseOneFromEach(Set<Set<T>> among){
		Set<Set<T>> master = new HashSet<Set<T>>(); // the master set of all sets
		
		if (among == null){
			master.add(new HashSet<T>());
		} else if (among.isEmpty()){
			master.add(new HashSet<T>());
		} else {
			List<Set<T>> chooseFrom = new ArrayList<Set<T>>();
			for (Set<T> s : among)
				if (!s.isEmpty())
					chooseFrom.add(s);
			Set<T> working = new HashSet<T>(); // the current working set of T
			
			makeEachSets(master, working, 0, chooseFrom);
		}
		return master;
	}	

	/**
	 * Recursive method searching out all possible n choose k combinations.
	 * <p>
	 * It must be that <code>k <= working.size()</code> or this method won't work properly and has no guaranteed behavior.  
	 */
	private static <T> void makeSets(Set<Set<T>> master, int k, Set<T> working, int start, List<T> chooseFrom){
		if (working.size() == k){
			master.add(working);
		} else {
			for (int i = start; i < chooseFrom.size(); i++){
				Set<T> copy = new HashSet<T>(working);
				copy.add(chooseFrom.get(i));
				makeSets(master, k, copy, i+1, chooseFrom);
			}	
		}
	}


	/**
	 * Recursive method searching out all possible ways to choose exactly 1 element from each Set in <code>chooseFrom</code> to form a new Set.
	 */
	private static <T> void makeEachSets(Set<Set<T>> master, Set<T> working, int start, List<Set<T>> chooseFrom){
		 for (int i = start; i < chooseFrom.size() ; i++){
			 List<T> chooseList = new ArrayList<T>(chooseFrom.get(i));
			 for (int j = 0 ; j < chooseList.size() ; j++){
				 Set<T> copy = new HashSet<T>(working);
				 copy.add(chooseList.get(j));
				 makeEachSets(master, copy, i+1, chooseFrom);
			 }
		 }
		 if (working.size() == chooseFrom.size())
			 master.add(working);			
	}
	
	public static <T> void printSets(Set<Set<T>> setOfSets){
		
		System.out.println("Total Sets: " + Integer.toString(setOfSets.size()));
		int i = 1;
		for (Set<T> set : setOfSets){
			System.out.println("Set # : " + Integer.toString(i));
			for (T t : set){
				System.out.print(t.toString());
			}
			System.out.println();
			i++;
		}
	}
}
