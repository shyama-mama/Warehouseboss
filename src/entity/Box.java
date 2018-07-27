package entity;
import java.awt.Point;

import game.Game;
import map.MapGenerator;

/**
 * /**
 * GROUP 4 (FRIDAY 9AM BONGO LAB)
 * MEMBERS:
 * 	- Alen Huang (z5115782)
 * 	- Alan Nguyen (z3459160)
 * 	- Christine Bui (z5060473)
 * 	- Shyam Sudnar Ravishankar (z3460229)
 * 	- Charley Wong (z5060076)
 */

public class Box extends Entity {
	public Box(Point startingLoc) {
		super(startingLoc);
	}

	// let the box update the map when it moves
	
	/**
	 * Makes a move and updates map accordingly
	 */
	public boolean move(Move m, MapGenerator map) {
		Point newBoxLoc = null;
		if (m.isUndo()) {
			newBoxLoc = m.getSavedEntityPoint();
		} else {
			m.setEntityMoved(this);
			m.setSavedEntityPoint(new Point(this.loc));
			newBoxLoc = m.getNewPoint(this.loc);
		}
		map.updateMap(Game.BOX, newBoxLoc.x, newBoxLoc.y);
		if (map.getGoalLocs().contains(this.loc)) 
			map.updateMap(Game.GOAL, this.loc.x, this.loc.y);
		else
			map.updateMap(Game.FREE_SPACE, this.loc.x, this.loc.y);
		this.loc.setLocation(newBoxLoc);
	return true;
	}
	
}
