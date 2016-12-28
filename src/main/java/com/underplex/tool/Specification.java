package com.underplex.tool;

import java.util.List;

/**
 * Implementing classes indicate via methods how a simulation on a game should be run.
 * <p>
 * The game is not specified anywhere here, but this specification will only pass player strategies of type <code>E</code>, so if those strategies are
 * not appropriate to the game, errors may result. It's the responsibility of implementing code to match the specification to an appropriate game.
 */
public interface Specification<E> {
	
	/**
	 * Returns number of game iterations to perform.
	 * <p>
	 * This method is only invoked once (before any simulations are run) so changing the number of games run depending on outcomes 
	 * so far is not possible.
	 */
	int iterations();

	/**
	 * Returns </code>String</code> representing the expansions/type of the game to be played.
	 * <p>
	 * Games and simulators may vary widely on how they use this information, but with a simple system of arguments, it should be easy to indicate
	 * what combination of games/expansions the simulator would like to run.
	 * <p>
	 * To avoid confused results that are hard to parse, this will only be consulted once per simulator run 
	 * so that results can be written for a single combination of game versions and expansions.
	 * <p>
	 * The <code>String</code> returned is not guaranteed to be in any combination of upper or lower case.
	 * <p>
	 * @return	<code>String</code> with name of game type and any expansions to use
	 */
	String expansions();

	/**
	 * Returns instances of <code>E</code> strategies equal to the number of players to play the next <code>iteration</code> of the game.
	 * <p>
	 * The returned elements will each represent the strategy of a single player. These strategies will make decisions for the players.
	 * <p>
	 * In the returned <code>List</code>, the number of elements indicates how many players are supposed to play in the next iteration of the game.
	 * <p>
	 * The order of the <code>List</code> is the order in which players are "seated", whatever that means in the context of the game.
	 * <p>
	 * Generally, if the game is played by a circle of players, the second element of the <code>List</code> will be "seated" to the left of the first
	 * element, the third element will be "seated" left of the second element,
	 * and so on, so that the last element of the list will be seated to the
	 * right of the first element, completing the circle of players.
	 * 
	 * @param iteration
	 *            number of the iteration in the simulation
	 */
	List<E> assignStrategies(int iteration);

	
}
