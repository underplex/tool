package com.underplex.tool;

import java.util.ArrayList;

/**
 * List implementation that supports "inserting" elements that push later elements further down the list instead of removing them.
 * <p>
 * Thus, each <code>insert</code> operation will increase the size of the list by 1.
 * @author Brandon Irvine
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class InsertList<T> extends ArrayList<T>{
	
	/**
	 * Inserts the element at index point, pushing any other items further.
	 * <p>
	 * If index is equal or greater to this list's size, this method simply adds the element at the end.
	 * @param t
	 * @param index
	 */
	public void insert(int index, T element){
		
		int i = index;
		T replaced = element;
		while (i < this.size()){
			replaced = this.set(i, replaced);
			i++;
		}
		this.add(replaced);
	
	}
	

}
