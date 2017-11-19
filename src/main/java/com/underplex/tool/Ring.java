package com.underplex.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * A ring of elements.
 * <p>
 * Rings contain no pair of elements {@code e1} and {@code e2} such that {@code e1.equals(e2)}. That is, rings cannot contain duplicates.
 * <p>
 * Rings cannot contain {@code null} elements.
 * <p>
 * Ring elements are ordered and indexed like lists. The element with index 0 is considered the first element.
 * <p>
 * Rings may have 0, 1, or any number of elements. If it has 0 elements, it is considered empty.
 * <p>
 * The {@code next} and {@code previous} methods are used to get elements relative to an specified element. The first element is considered the 
 * next element after the last element, and the last element is considered element previous to the first element.
 * <p>
 * If there is only one element in a ring, it is the first element, and is both the next and previous element to itself.
 * <p>
 * A ring is already fairly flexible, and so the methods have been made {@code final} in many cases since no overriden behavior makes sense.
 * <p>
 * Rings were designed to model circles of players in a board game, where each element might represent a player in the game. The turns start with the first player and go around the circle, returning
 * to the first player, but the first player may sometimes change depending on the game.
 * @param <E> - the type of elements held in this collection
 *
 * @author Brandon Irvine
 */
public class Ring<E> {

	/**
	 * The roster can never be null.
	 */
	private final List<E> roster;
	
	public Ring(){
		this.roster = new ArrayList<E>();
	}
	
	/**
	 * Inserts the specified element at the specified position in this ring. 
	 * <p>
	 * Shifts the element currently at that position (if any) and any subsequent elements further along the ring by adding one to their indices.
	 * <p>
	 * Since rings don't allow duplicate elements, this method will fail if the element is already present, and return false. Otherwise, it will return true.
	 * 
	 * @param index - index at which the specified element is to be inserted
	 * @param element - element to be inserted
	 * @return {@code true} iff the element was not already present and was added to this ring
	 * @throws IndexOutOfBoundsException iff the index is out of range (index < 0 || index > size())
	 * @throws NullPointerException iff the specified element is null
	 */
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
	
	/**
	 * Inserts the specified element at the first position of this ring.
	 * @param element - the element to add
	 * @return {@code true} iff the element was not already present and was added to this ring
	 * @throws NullPointerException iff the specified element is null 
	 */
	public final boolean addFirst(E element){
		if (element == null){
			throw new NullPointerException();
		} else if (this.roster.contains(element)){
			return false;
		}
		roster.add(0, element);
		return true;
	}

	/**
	 * Inserts the specified element at the last position of this ring.
	 * @param element - the element to add
	 * @return {@code true} iff the element was not already present and was added to this ring
	 * @throws NullPointerException iff the specified element is null 
	 */
	public final boolean addLast(E element){
		if (element == null){
			throw new NullPointerException();
		} else if (this.roster.contains(element)){
			return false;
		}
		roster.add(element);
		return true;
	}
	
	/**
	 * Gives the specified element, which should already be in this ring, the index of 0, making it first, and shifts all other indices accordingly without changing relative positions.
	 * <p>
	 * Returns {@code false} if this the element was already first in the ring.
	 * <p>
	 * After this method is invoked, the relationships between elements will not have changed and all relative positions will be the same. 
	 * @param element - element to be made first
	 * @return {@code true} iff the element was found and the ordering of the ring changed
	 * @throws NullPointerException iff the specified element is null
	 * @throws IllegalArgumentException iff this ring does not already containt the specified element
	 */
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
	
	/**
	 * Removes all of the elements from this ring.
	 */
	public final void clear(){
		roster.clear();
	}	
	
	/**
	 * Returns the first element of this ring without removing it, or {@code null} if the ring is empty.
	 * @return first element
	 */
	public final E getFirst(){
		if (roster.isEmpty()){
			return null;
		}
		return roster.get(0);
	}
	
	/**
	 * Returns the last element of this ring without removing it, or {@code null} if the ring is empty.
	 * @return last element
	 */
	public final E getLast(){
		if (roster.isEmpty()){
			return null;
		}
		return roster.get(roster.size() - 1);
	}

	/*
	 * Returns the index of the specified element in this ring, or -1 if this list does not contain the element.
	 * @return int index of the specified element or -1
	 */
	public final int indexOf(E element){
		return roster.indexOf(element);
	}
	
	/**
	 * Returns {@code true} true iff this ring contains no elements.
	 * @return {@code true} true iff this ring contains no elements
	 */
	public final boolean isEmpty(){
		return roster.isEmpty();
	}
	
	/**
	 * Returns the element in the ring after {@code element}.
	 * <p>
	 * This method doesn't change the state of this ring.
	 * <p>
	 * If this ring only has one element and the specified element is that element, the specified element itself will be returned.
	 * <p>
	 * If the specified element is not already in the ring, {@code null} is returned.
	 * <p>
	 * If the ring has no one in it or if {@code null} is passed, {@code null} will be returned, since the concept of the next element is meaningless in such a context.
	 * @param previousElement - the previous element
	 * @return the element with the next position after the specified element
	 * @throws NullPointerException if the specified element is null
	 */
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

	/**
	 * Returns the element in the ring before {@code element}.
	 * <p>
	 * This method doesn't change the state of this ring.
	 * <p>
	 * If this ring only has one element and the specified element is that element, the specified element itself will be returned.
	 * <p>
	 * If the specified element isn't already in the ring, {@code null} is returned.
	 * <p>
	 * If the ring has no one in it or if {@code null} is passed, {@code null} will be returned, since the concept of the next element is meaningless in such a context.
	 * @param nextElement - the next element
	 * @return the element with the previous position before the specified element
	 * @throws NullPointerException if the specified element is null
	 */
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

	/**
	 * Removes the specified element from this ring, if it is present.
	 * <p>
	 * If this ring does not contain the element, it is unchanged.
	 * <p>
	 * Returns true if this ring contained the specified element (or equivalently, if this ring changed as a result of the call).
	 * Returns false if the element isn't already in ring or is null.
	 * <p>
	 * @param element - element to be removed
	 * @return true iff this ring contained the specified element
	 * @throws NullPointerException if the specified element is null
	 */
	public final boolean remove(E element){
		if (element == null){
			throw new NullPointerException();
		}
		return roster.remove(element);
	}
	
	/**
	 * Returns true iff this ring contains the specified element.
	 * @return {@code true} iff this ring contains the specified element 
	 */
	public final boolean contains(E element){
		return roster.contains(element);
	}
	
	/**
	 * Returns the number of elements in this ring.
	 * @return the number of elements in this ring
	 */
	public final int size(){
		return roster.size();
	}
	
	/**
	 * Returns elements in order as a {@code List}, with the first element at 0.
	 * <p>
	 * The returned List is not the data structure underlying this ring, so changing it will not change this ring.
	 * @return List of elements in order
	 */
	public final List<E> toList(){
		return new ArrayList<E>(this.roster);
	}
}
