package com.underplex.tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Utility class for manipulating collections.
 * 
 * @author irvin_000
 *
 */
public class Picker {

	/**
	 * Returns random item from collection.
	 * <p>
	 * If collection is empty or null, null is returned.
	 * <p>
	 * If collection has just one element, that element is returned.
	 */
	public static <T> T selectRandom(Collection<? extends T> coll) {
		List<T> list = new ArrayList<T>(coll);
		T rT = null;
		if (coll != null) {
			if (list.size() == 1) {
				rT = list.get(0);
			} else if (list.size() > 1) {
				Random r = new Random();
				rT = list.get(r.nextInt(list.size() - 1));
			} // implicitly, else if list.size == 0, leave rT as null
		}
		return rT;
	}

	/**
	 * Returns an element from the passed Collection or null if the Collection is null or empty.
	 * <p>
	 * This method is useful when passed a Set known to be of length 1, but with no easy way to reference the element.
	 * @param coll	Collection of size 1
	 * @return		single element in <code>coll</code>
	 */
	public static <T> T selectOnly(Collection<? extends T> coll) throws NullPointerException {
		T t = null;
		if (coll != null){
			List<T> list = new ArrayList<>(coll);
			if (!list.isEmpty())
				t = list.get(0);
		}
		
		return t;
	}
}
