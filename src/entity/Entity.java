package entity;
import java.awt.Point;

import map.MapGenerator;

/**
 * GROUP 4 (FRIDAY 9AM BONGO LAB)
 * MEMBERS:
 * 	- Alen Huang (z5115782)
 * 	- Alan Nguyen (z3459160)
 * 	- Christine Bui (z5060473)
 * 	- Shyam Sudnar Ravishankar (z3460229)
 * 	- Charley Wong (z5060076)
 */

/**
 * Abstract class inherited by Box and Player.
 *
 */
public abstract class Entity {
	protected Point loc;

	public Entity(Point startingLoc) {
		this.loc = startingLoc;
	}
	
	/**
	 * All subclasses must implement a move() function
	 */
	public abstract boolean move(Move m, MapGenerator map);
	
	
	public Point getLoc() {
		return new Point(this.loc);
	}
	
	/**
	 * Checks if a location 'c' is between the locations bounded
	 * by 'a' and 'b'
	 * @param a the lower bound 
	 * @param b the upper bound
	 * @param c the location to check
	 * @return true if it is in between, false otherwise
	 */
	public boolean isBetween(int a, int b, int c) {
		return b >= a ? c >= a && c <= b : c > b && c < a;
	}
}
