package com.underplex.tool;

import java.util.Set;

/**
 * Implementing classes represent a network with nodes of type <code>T</code> and links of type <code>U</code>.
 * <p>
 * This interface uses some terminology from math, where a node is a point in a network and an edge is the connection between two points.
 *  
 * @author Brandon Irvine
 *
 * @param <T>	the type of each node
 * @param <U>	the type of each edge between nodes
 */
public interface Net<T, U> {

	/**
	 * Returns all nodes in this network.
	 */
	Set<T> nodes();
	
	/**
	 * Returns all edges in this network.
	 */
	Set<U> edges();
	
}
