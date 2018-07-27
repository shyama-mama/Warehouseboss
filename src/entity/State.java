package entity;
import java.awt.Point;
import java.util.ArrayList;

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
 * An instance of state contains the path and a goal block
 * It also contains the states f function value calculated from 
 * f() = g() + h()
 */
public class State implements Comparable<State> {
	
	private int f;
	private int g;
	private int h;
	private Point end;
	ArrayList<Block> path = new ArrayList<Block>();
	
	public State(Point end) {
		this.end = end;
	}

	@Override 
	public int compareTo(State s) {
		if(this.f > s.getF()){
			return 1;
		} else if (this.f < s.getF()){
			return -1;
		}
		return 0;
	}
	
	/**
	 * Returns this states f value
	 * @return
	 */
	public int getF() {
		return this.f;
	}

	/**
	 * Checks if this state is end state i.e path contains goal
	 * @return
	 */
	public boolean isEndState() {
		if(this.path.get(this.path.size()-1).getX() == (int) this.end.getX() && this.path.get(this.path.size()-1).getY() == (int) this.end.getY()){
			return true;
		}
		return false;
	}

	/**
	 * Expands the current state but creating all possible states from current state
	 * @return
	 */
	public ArrayList<State> expandState() {
		ArrayList<State> newStates = new ArrayList<State>();
		ArrayList<Block> connectingBlocks = this.path.get(this.path.size()-1).connectingBlocks();
		for(int i = 0; i < connectingBlocks.size(); i++){
			State newState = new State(this.end);
			newState.addBlocks(this.path);
			int initSize = newState.getPath().size();
			newState.addBlock(connectingBlocks.get(i));
			int finalSize = newState.getPath().size();
			if(initSize != finalSize){
				newStates.add(newState);
			}
			
		}
		return newStates;
	}

	/**
	 * Returns the path so far for this state
	 * @return
	 */
	public ArrayList<Block> getPath(){
		return this.path;
	}

	/**
	 * Given a list of blocks it adds it to this states path
	 * @param path
	 */
	private void addBlocks(ArrayList<Block> path) {
		for(int i = 0; i < path.size(); i++){
			this.path.add(path.get(i));
		}
		calculateF();
	}

	/**
	 * Calculatses the f function value for state
	 */
	private void calculateF() {
		this.f = calculateG() + calculateH();
	}

	/**
	 * Calculates the G function value which is the cost of path
	 * @return
	 */
	private int calculateG() {
		this.g = this.path.size();
		return this.g;
	}

	/**
	 * Calculates heuristic. Heuristic is the manhatten distance from 
	 * end of path to goal
	 * @return
	 */
	private int calculateH() {
		Block last = this.path.get(this.path.size()-1);
		this.h = (int) (Math.abs(last.getX() - this.end.getX())+ Math.abs(last.getY() - this.end.getY()));
		return this.h;
	}

	/**
	 * Adds a block to the end of path
	 * @param start
	 */
	public void addBlock(Block start) {
		if(!this.path.contains(start)){
			this.path.add(start);
			calculateF();
		}
	}
}

