package com.underplex.tool.stat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class that normalizes double values associated with a series of objects.
 * @author Brandon rvine
 *
 */
public class Normalizer {

	private Normalizer(){
		// don't instantiate
	}
	
	/**
	 * Returns map where the double values have been normalized so they all fall in range from 0 to 1 (inclusive).
	 * 
	 * Returns empty map if null or empty map is passed, or if map only has 1 key-value pairing (since it's impossible to normalize a single value).
	 * @param map
	 * @return
	 */
	public static <T> Map<T,Double> normalize(Map<T,Double> map){
		Map<T,Double> normed = new HashMap<T,Double>();
		
		if (map != null){
			if (map.size() > 1){
				List<Double> values = new ArrayList<Double>(map.values());
				Collections.sort(values);
				double min = values.get(0);
				double denominator = values.get(values.size() - 1) - min;
				for (T t : map.keySet()){
					double value = map.get(t);
					double normalized = (value - min)/denominator;
					normed.put(t, normalized);
				}
			}
		}		
		return normed;
	}
	
}
