package map;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entity.Block;
import entity.Box;
import entity.Player;
import game.Game;

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
 * Generates a map in a state where it can be solved
 *
 */
public class MapGenerator {
	
	protected static final int FREE_SPACE = 0;
	protected static final int OBSTACLE = 1;
	protected static final int BOX = 2;
	protected static final int GOAL = 3;
	protected static final int PLAYER = 4;
	
	private static final int DIMENSIONS = 8;
	protected Block[][] puzzle = new Block[DIMENSIONS][DIMENSIONS];
	protected Point playerLocation;
	protected List<Box> boxLocs;
	protected List<Point> goalLocs;
	protected Player player;
	
	/**
	 * Taken in difficulty
	 * @param diff
	 */
	public MapGenerator(String diff){
		int cost = 0;
		int intDiff = getIntDiff(diff);
		while(cost <= intDiff){
			if(!Game.isLoading) break;
			CreateMap cm  = new CreateMap();
			CreateSolution cs = new CreateSolution(cm.getPuzzle(), cm.getGoalLocs());
			this.puzzle = cs.getPuzzle();
			this.setPlayerLocation(cs.getPlayer());
			this.boxLocs = cs.getBoxLocs();
			this.goalLocs = cs.getGoalLocs();
			this.player = new Player((getPlayerLocation()));
			cost = cs.getCost();
		}	
	}

	public MapGenerator(){
		this.boxLocs = new ArrayList<Box>();
		this.goalLocs = new ArrayList<Point>();
	}
	
	/**
	 * Given difficulty it return the minimum length of solution
	 * @param diff
	 * @return
	 */
	private int getIntDiff(String diff) {
		if(diff.equals("EASY")){
			return 3;
		} else if(diff.equals("HARD")){
			return 10;
		} else {
			return 7;
		}
	}

	/**
	 * Displays the puzzle for the game
	 */
	public void displayPuzzle() {
		for(int i = 0; i < DIMENSIONS; i++){
			for(int j = 0; j < DIMENSIONS; j++){
				System.out.print(this.puzzle[j][i].getType());
			}
			System.out.println();
		}
	}

	/**
	 * Return an int[][] of the game as defined in the fields above
	 * @return
	 */
	public int[][] getGrid() {
		int[][] grid = new int[DIMENSIONS][DIMENSIONS];
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(this.puzzle[i][j].getType() != 9){
					grid[i][j] = this.puzzle[i][j].getType();
				} else {
					grid[i][j] = 0;
				}
			}
		}
		return grid;
	}
	
	/**
	 * Adds a box given x and y co-ordinate to the list of boxes
	 * @param x
	 * @param y
	 */
	public void addBox(int x, int y){
		this.boxLocs.add(new Box(new Point(x,y)));
	}
	
	/**
	 * updates Map[x][y] to equal CODE(0,1,2,3,4)
	 */
	public void updateMap(int code, int x, int y) {
		this.puzzle[x][y] = new Block(code, x, y);
	}

	/**
	 * Returns box at point p
	 * Return null if there is no box
	 * @param p
	 * @return
	 */
	public Box getBox(Point p) {
		Box b = null;
		if (this.getGrid()[(int) p.getX()][(int) p.getY()] == BOX) {
			for (Iterator<Box> i = boxLocs.iterator(); i.hasNext();) {
				Box x = i.next();
				if (x.getLoc().getX() == p.getX() && x.getLoc().getY() == p.getY()) {
					b = x;
				}
			}
		}
		return b;
	}

	/**
	 * 
	 * @return true if Point(x,y) is free -- no obstacle or entity currently
	 *         occupying space
	 */
	public boolean isFreeSpace(Point p) {
		try {if (this.getGrid()[(int) p.getX()][(int) p.getY()] == FREE_SPACE)
			return true;
		}
		catch (ArrayIndexOutOfBoundsException e) {}
		return false;
	}

	/**
	 * 
	 * @param p
	 * @return true if point p is a box
	 */
	public boolean isBox(Point p) {
		try {
		if (this.getGrid()[(int) p.getX()][(int) p.getY()] == BOX)
			return true;
		}
		catch (ArrayIndexOutOfBoundsException e) {}
		return false;
	}
	
	/**
	 * 
	 * @param p
	 * @returns true if point p is a goal
	 */
	public boolean isGoal(Point p) {
		try {
		if (this.getGrid()[(int) p.getX()][(int) p.getY()] == GOAL)
			return true;
		}
		catch (ArrayIndexOutOfBoundsException e) {}
		return false;
	}

	/**
	 * 
	 * @return player location
	 */
	public Point getStartingPlayerLoc() {
		return this.getPlayerLocation();
	}
	
	/**
	 * 
	 * @return list of boxes 
	 */
	public List<Box> getBoxLocs() {
		return this.boxLocs;
	}

	/**
	 * 
	 * @return list of goals
	 */
	public List<Point> getGoalLocs() {
		return this.goalLocs;
	}

	/**
	 * 
	 * @return player location
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * 
	 * @param point 
	 * @return true if p is player and false if p is not
	 */
	public boolean isPlayer(Point p) {
		try {
		if (this.getGrid()[(int) p.getX()][(int) p.getY()] == PLAYER)
			return true;
		}
		catch (ArrayIndexOutOfBoundsException e) {}
		return false;
	}
	
	/**
	 * 
	 * @return player location
	 */
	public Point getPlayerLocation() {
		return playerLocation;
	}
	
	/**
	 * 
	 * @param sets playerLocation
	 */
	public void setPlayerLocation(Point playerLocation) {
		this.playerLocation = playerLocation;
	}
}