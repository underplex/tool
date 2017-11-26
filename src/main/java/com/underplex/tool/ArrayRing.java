package com.underplex.tool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * An implementation of {@code Ring<E>}.
 * @see com.underplex.tool.Ring
 * 
 * @author Brandon Irvine
 */
public class ArrayRing<E> implements Ring<E>, Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The roster can never be null.
	 * <p>
	 * This is implemented as an ArrayList because that makes it serializable.
	 */
	private final ArrayList<E> roster;
	
	public ArrayRing(){
		this.roster = new ArrayList<E>();
	}
	
	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#add(int, E)
	 */
	@Override
	public final boolean add(int index, E element){
		if ((index < 0 || index > size())){
			throw new IndexOutOfBoundsException();
		} else if (element == null){
			throw new NullPointerException();
		} else if (!roster.contains(element)){
			roster.add(index,element);
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#addFirst(E)
	 */
	@Override
	public final boolean addFirst(E element){
		if (element == null){
			throw new NullPointerException();
		} else if (this.roster.contains(element)){
			return false;
		}
		roster.add(0, element);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#addLast(E)
	 */
	@Override
	public final boolean addLast(E element){
		if (element == null){
			throw new NullPointerException();
		} else if (this.roster.contains(element)){
			return false;
		}
		roster.add(element);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#makeFirst(E)
	 */
	@Override
	public final boolean makeFirst(E element){
		if (element == null){
			throw new NullPointerException();
		} else if (!roster.contains(element)){
			throw new IllegalArgumentException();
		} else if (!this.roster.get(0).equals(element)){
			List<E> copy = new ArrayList<E>(roster);
			roster.clear();
			int p = copy.indexOf(element);
			
			for (int i = p; i < copy.size(); i++){
				roster.add(copy.get(i));
			}
			for (int i = 0; i < p; i++){
				roster.add(copy.get(i));
			}
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#clear()
	 */
	@Override
	public final void clear(){
		roster.clear();
	}	
	
	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#getFirst()
	 */
	@Override
	public final E getFirst(){
		if (roster.isEmpty()){
			return null;
		}
		return roster.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#getLast()
	 */
	@Override
	public final E getLast(){
		if (roster.isEmpty()){
			return null;
		}
		return roster.get(roster.size() - 1);
	}
	
	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#get(int)
	 */
	@Override
	public final E get(int index){
		if ((index < 0 || index >= size())){
			throw new IndexOutOfBoundsException();
		}
		return roster.get(index);
	}	

	/*
	 * Returns the index of the specified element in this ring, or -1 if this list does not contain the element.
	 * @return int index of the specified element or -1
	 */
	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#indexOf(E)
	 */
	@Override
	public final int indexOf(E element){
		return roster.indexOf(element);
	}
	
	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#isEmpty()
	 */
	@Override
	public final boolean isEmpty(){
		return roster.isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#next(E)
	 */
	@Override
	public final E next(E previousElement){
		
		if (previousElement == null){
			throw new NullPointerException();
		}

		if (roster.contains(previousElement)){
			if (roster.size() > 1){
				int i = roster.indexOf(previousElement);
				if (i == roster.size() - 1){
					return roster.get(0);
				} else {
					return roster.get(i + 1);
				}
			} else {
				return previousElement;
			}	
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#previous(E)
	 */
	@Override
	public final E previous(E nextElement){
		if (nextElement == null){
			throw new NullPointerException();
		}

		if (roster.contains(nextElement)){
			if (roster.size() > 1){
				int i = roster.indexOf(nextElement);
				if (i == 0){
					return roster.get(roster.size() - 1);
				} else {
					return roster.get(i - 1);
				}
			} else {
				return nextElement;
			}	
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#remove(E)
	 */
	@Override
	public final boolean remove(E element){
		if (element == null){
			throw new NullPointerException();
		}
		return roster.remove(element);
	}
	
	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#contains(E)
	 */
	@Override
	public final boolean contains(E element){
		return roster.contains(element);
	}
	
	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#size()
	 */
	@Override
	public final int size(){
		return roster.size();
	}
	
	/* (non-Javadoc)
	 * @see com.underplex.tool.Ring#toList()
	 */
	@Override
	public final List<E> toList(){
		return new ArrayList<E>(this.roster);
	}
}
