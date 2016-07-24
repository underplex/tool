package com.underplex.tool;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Utility class with some static methods for dealing with strings.
 * @author Brandon Irvine *
 */
public class GoodString {

	/**
	 * Returns string representation of current date in year-month-day form.
	 */
	public static String makeDateString(){

		Calendar cal = Calendar.getInstance();
		String month = String.valueOf( cal.get(Calendar.MONTH) + 1 );
		String year = String.valueOf( cal.get(Calendar.YEAR) );
		String day = String.valueOf( cal.get(Calendar.DAY_OF_MONTH) );
		return year + "-" + month + "-" + day;

	}
	
	/**
	 * Returns List version of the strings returned by the collection's toString values.
	 */
	public static <T> List<String> stringList(Collection<? extends T> original ){
		List<String> rList = new ArrayList<String>();
		for ( T t : original )
			rList.add( t.toString() );
		
		return rList;		
	}
}
