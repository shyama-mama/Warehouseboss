package entity;
import java.awt.Point;

/**
 * GROUP 4 (FRIDAY 9AM BONGO LAB)
 * MEMBERS:
 * 	- Alen Huang (z5115782)
 * 	- Alan Nguyen (z3459160)
 * 	- Christine Bui (z5060473)
 * 	- Shyam Sudnar Ravishankar (z3460229)
 * 	- Charley Wong (z5060076)
 */

public class Move {
	private char dir;
	private boolean undo;
	private Point savedPoint;
	private Point savedEntityPoint;
	private Entity entityMoved;
	
	public Move(char dir) {
		this.dir = dir;
		undo = false;
		entityMoved = null;
		savedPoint = null;
		savedEntityPoint = null;
	}
	/**
	 * checks if an undo is requested and returns true or false
	 * @return
	 */
	public boolean isUndo() {
		return undo;
	}
	/**
	 * returns the entity which has been moved
	 * @return
	 */
	public Entity getEntityMoved() {
		return entityMoved;
	}
	
	/**
	 * Assigns entityMoved to be a specific entity
	 * @param e
	 */
	public void setEntityMoved(Entity e) {
		entityMoved = e;
	}
	
	public Point getSavedPoint() {
		return savedPoint;
	}
	
	
	public void setSavedPoint(Point p) {
		savedPoint = p;
	}
	
	public Point getSavedEntityPoint() {
		return savedEntityPoint;
	}
	
	
	public void setSavedEntityPoint(Point p) {
		savedEntityPoint = p;
	}
	
	public void setUndo() {
		undo = true;
	}
	
	/**
	 *
	 * @param p is the curr Point to be moved in the direction specified in the Move object
	 * @return 
	 */
	public Point getNewPoint(Point p) {
		Point curr = new Point(p);
		if (undo) { // move in opposite direction if undo flag is set
			switch (this.dir) {
			 	case 'w':
			 			curr.setLocation(curr.getX(), curr.getY()+1);
			 			break;
			 	case 'a':
			 			curr.setLocation(curr.getX()+1, curr.getY());
			 			break;
			 	case 's':
			 			curr.setLocation(curr.getX(), curr.getY()-1);
			 			break;
			 	case 'd':
			 			curr.setLocation(curr.getX()-1, curr.getY());
			 			break;
			 }
		} else {
			switch (this.dir) {
			 	case 'w':
			 			curr.setLocation(curr.getX(), curr.getY()-1);
			 			break;
			 	case 'a':
			 			curr.setLocation(curr.getX()-1, curr.getY());
			 			break;
			 	case 's':
			 			curr.setLocation(curr.getX(), curr.getY()+1);
			 			break;
			 	case 'd':
			 			curr.setLocation(curr.getX()+1, curr.getY());
			 			break;
			}
		}
		return curr;
	}
	
	
}
