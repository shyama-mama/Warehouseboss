package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import entity.Block;
import entity.Box;
import entity.State;

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
 * AStar class implements AStar search with priority queue on a given map 
 * where given boxes are considered walls. It is used to check if there is 
 * a path between player and block b 
 *
 */
public class AStar {
	
	private Block[][] map;
	private Block[][] currMap;
	private List<Box> boxes;
	private Point player;
	private Block end;
	private PriorityQueue<State> queue = new PriorityQueue<State>();
	
	public AStar(Block[][] map, List<Box> boxes, Point player, Block b) {
		this.map = map;
		this.boxes = boxes;
		this.player = player;
		this.end = b;
	}
	
	/**
	 * After making the map it return true if there is a path for the given
	 * parameters or false
	 * @return
	 */
	public boolean aStarSearch(){
		makeCurrState();
		canGoTo();
		return search();
	}

	/**
	 * Conducts the search and return true or false depending on if there
	 * is a path for this object
	 * @return
	 */
	public boolean search() {
		createInitialStates();
		
		// Get the best State from priority queue and expand it and add it to queue
		// and keep repeating until the best state reaches the goal. 
		while(!this.queue.isEmpty()){
			State bestState = this.queue.poll();
			if(!bestState.isEndState()){
				expandState(bestState);	
			} else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Given a best state it expands a state and adds new states to the 
	 * priority queue
	 * @param bestState
	 */
	private void expandState(State bestState) {
		ArrayList<State> newStates = bestState.expandState();
		for(int i = 0; i < newStates.size(); i++){
			this.queue.add(newStates.get(i));
		}
	}

	/**
	 * Create initial states to conduct the AStar search
	 */
	private void createInitialStates(){
		State newState = new State(this.player);
		int Ex = this.end.getX();
		int Ey = this.end.getY();
		newState.addBlock(this.currMap[Ex][Ey]);
		this.queue.add(newState);
	}
	
	/**
	 * For the map it computes which paths are adjacent to which 
	 */
	public void canGoTo(){
		for(int i = 0; i < this.currMap.length; i++){
			for(int j = 0; j < this.currMap[0].length; j++){
				if(this.currMap[i][j].getType() != 1){
					canGoToV(i, j);
					canGoToH(i, j);
				}
				
			}
		}
	}
	
	/**
	 * Helper function for canGoTo and checks for adjacency for a block 
	 * horizontally
	 * @param i
	 * @param j
	 */
	private void canGoToH(int i, int j) {
		if(j >= 1 || j+1 <= this.currMap[0].length-1){
			if(this.currMap[i][j+1].getType() != 1){ 
				this.currMap[i][j].addCanGoTo(this.currMap[i][j+1]);
			}
			if(this.currMap[i][j-1].getType() != 1){
				this.currMap[i][j].addCanGoTo(this.currMap[i][j-1]);
			}
		}
	}

	/**
	 * Helper function for canGoTo and checks for adjacency for a block 
	 * vertically
	 * @param i
	 * @param j
	 */
	private void canGoToV(int i, int j) {
		if(i >= 1 || i+1 <= this.currMap.length-1){
			if(this.currMap[i+1][j].getType() != 1){ 
				this.currMap[i][j].addCanGoTo(this.currMap[i+1][j]);
			}
			if(this.currMap[i-1][j].getType() != 1){
				this.currMap[i][j].addCanGoTo(this.currMap[i-1][j]);
			}
		}
	}

	/**
	 * Makes the map such that boxes are considered as wall too.
	 */
	private void makeCurrState() {
		this.currMap = new Block[this.map.length][this.map[0].length];
		for(int i = 0; i < this.currMap.length; i++){
			for(int j = 0; j < this.currMap[0].length; j++){
				this.currMap[i][j] = new Block(this.map[i][j].getType(), i ,j);
				if(this.currMap[i][j].getType() == 3){
					this.currMap[i][j].setType(0);
				}
			}
		}
		
		for(Box b : this.boxes){
			Point p = b.getLoc();			
			int Bx = (int) p.getX();
			int By = (int) p.getY();
			this.currMap[Bx][By].setType(1);
		}	
		
	}

}
