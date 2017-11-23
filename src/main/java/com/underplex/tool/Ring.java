package com.underplex.tool;

import java.util.List;

/**
 * A ring of elements.
 * <p>
 * Rings contain no pair of elements {@code e1} and {@code e2} such that {@code e1.equals(e2)}. That is, rings cannot contain duplicates.
 * <p>
 * For this reason, it is not recommended to use elements where some instances are equal to others but which are not completely interchangeable in practice.
 * Rings are designed not to take duplicates in order to prevent the same element from being added twice inadvertently.
 * <p>
 * Rings cannot contain {@code null} elements.
 * <p>
 * ArrayRing elements are ordered and indexed like lists. The element with index 0 is considered the first element.
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
 * Rings are meant to model circles of players in a board game, where each element might represent a player in the game. The turns start with the first player and go around the circle, returning
 * to the first player, but the player considered "first" (and all the implied ordering of the players after that may sometimes change depending on the game. Players also may leave the circle,
 * in which case their immediate neighbors become neighbors themselves, "sewing up" the circle.
 * @param <E> - the type of elements held in this collection
 *
 * @author Brandon Irvine
 */
public interface Ring<E> {

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
	boolean add(int index, E element);

	/**
	 * Inserts the specified element at the first position of this ring.
	 * @param element - the element to add
	 * @return {@code true} iff the element was not already present and was added to this ring
	 * @throws NullPointerException iff the specified element is null 
	 */
	boolean addFirst(E element);

	/**
	 * Inserts the specified element at the last position of this ring.
	 * @param element - the element to add
	 * @return {@code true} iff the element was not already present and was added to this ring
	 * @throws NullPointerException iff the specified element is null 
	 */
	boolean addLast(E element);

	/**
	 * Gives the specified element, which should already be in this ring, the index of 0, making it first, and shifts all other indices accordingly without changing relative positions.
	 * <p>
	 * Returns {@code false} if this the element was already first in the ring.
	 * <p>
	 * After this method is invoked, the relationships between elements will not have changed and all relative positions will be the same. 
	 * @param element - element to be made first
	 * @return {@code true} iff the element was found and the ordering of the ring changed
	 * @throws NullPointerException iff the specified element is null
	 * @throws IllegalArgumentException iff this ring does not already contain the specified element
	 */
	boolean makeFirst(E element);

	/**
	 * Removes all of the elements from this ring.
	 */
	void clear();

	/**
	 * Returns the first element of this ring without removing it, or {@code null} if the ring is empty.
	 * @return first element
	 */
	E getFirst();

	/**
	 * Returns the last element of this ring without removing it, or {@code null} if the ring is empty.
	 * @return last element
	 */
	E getLast();

	/**
	 * Returns the element of this ring with the specified index.
	 * <p>
	 * Implicitly, if this ring is empty, this method will always through an exception since it will have no valid indices.
	 * @return element at specified index
	 * @throws IndexOutOfBoundsException iff the index is out of range (index < 0 || index >= size())
	 */
	E get(int index);

	/*
	 * Returns the index of the specified element in this ring, or -1 if this list does not contain the element.
	 * @return int index of the specified element or -1
	 */
	int indexOf(E element);

	/**
	 * Returns {@code true} true iff this ring contains no elements.
	 * @return {@code true} true iff this ring contains no elements
	 */
	boolean isEmpty();

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
	E next(E previousElement);

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
	E previous(E nextElement);

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
	boolean remove(E element);

	/**
	 * Returns true iff this ring contains the specified element.
	 * @return {@code true} iff this ring contains the specified element 
	 */
	boolean contains(E element);

	/**
	 * Returns the number of elements in this ring.
	 * @return the number of elements in this ring
	 */
	int size();

	/**
	 * Returns elements in order as a {@code List}, with the first element at 0.
	 * <p>
	 * The returned List is not the data structure underlying this ring, so changing it will not change this ring.
	 * @return List of elements in order
	 */
	List<E> toList();

}