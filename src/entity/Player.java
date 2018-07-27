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

public class Player extends Entity {

	private static final int FREE_SPACE = 0;
	private static final int OBSTACLE = 1;
	private static final int BOX = 2;
	private static final int GOAL = 3;
	private static final int PLAYER = 4;
	
	public Player(Point startingLoc) {
		super(startingLoc);
	}

	/**
	 * An action is performed based on the given Move object. If the action specified
	 * in the Move object is legal, the map is updated accordingly. If the legal action
	 * involves a box push, the move() method for the corresponding box is called. 
	 * @param m the move to be made
	 * @param map the map of the game
	 * @return true if move is successful, false otherwise
	 */
	public boolean move(Move m, MapGenerator map) {
		Point newLoc = m.getNewPoint(this.loc); // new player location
		// undo move
		if (m.isUndo()) {
			Point savedLoc = m.getSavedPoint();
			map.updateMap(PLAYER, savedLoc.x, savedLoc.y);
			if (map.getGoalLocs().contains(this.loc))	
				map.updateMap(GOAL, this.loc.x, this.loc.y);
			else 
				map.updateMap(FREE_SPACE, this.loc.x, this.loc.y);
			Entity e = m.getEntityMoved(); 
			if (e != null && e instanceof Box) { // if a box was pushed with this move
				e.move(m, map);
			}
			this.loc = savedLoc;
			return true;
		// normal move
		} else {
			m.setSavedPoint(new Point(this.loc));
			if (!this.isBetween(0, map.getGrid().length, (int) newLoc.getX()) ||
					!this.isBetween(0, map.getGrid().length, (int)newLoc.getY())) return false;
			
			if (map.isFreeSpace(newLoc) || map.isGoal(newLoc)) { // if free space, player moves there
				if (map.getGoalLocs().contains(this.loc))	
					map.updateMap(GOAL, this.loc.x, this.loc.y);
				else 
					map.updateMap(FREE_SPACE, this.loc.x, this.loc.y);
				map.updateMap(PLAYER, newLoc.x, newLoc.y);
				this.loc = newLoc;
				return true;
				
			} else if (map.isBox(newLoc)) { //if box, check if point next to box is free or goal then move there
				Point newBoxLoc = m.getNewPoint(newLoc);
				if (!this.isBetween(0, map.getGrid().length, (int) newBoxLoc.getX()) ||
						!this.isBetween(0, map.getGrid().length, (int) newBoxLoc.getY())) 
					return false;
				
				if (map.isFreeSpace(newBoxLoc) || map.isGoal(newBoxLoc)) {
					Box b = map.getBox(newLoc);
					b.move(m, map);
					if (map.getGoalLocs().contains(this.loc))	
						map.updateMap(GOAL, this.loc.x, this.loc.y);
					else 
						map.updateMap(FREE_SPACE, this.loc.x, this.loc.y);
					map.updateMap(PLAYER, newLoc.x, newLoc.y);
					this.loc = newLoc;
					return true;
				}
			}
		}
		return false;
	}
}
