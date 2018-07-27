package entity;
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
 * Block Class represents a block of type wall (1), path (0),
 * box(3), goal (2) or player (4). If the type is a path then 
 * it also contains a list of block this block is adjacent to.
 *
 */
public class Block {
	
	private int type;
	private int x; 
	private int y; 
	private ArrayList<Block> canGoTo = new ArrayList<Block>();
	
	public Block(int type, int x, int y){
		this.type = type;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the time of the block
	 * @return
	 */
	public int getType(){
		return this.type;
	}

	/**
	 * Sets the type of this block
	 * @param i
	 */
	public void setType(int i) {
		this.type = i;
	}

	/**
	 * Returns the Y co-ordinate of block
	 * @return
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Returns the X co-ordinate of block
	 * @return
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Given a block it adds it to the list of blocks current block
	 * can go to
	 * @param block
	 */
	public void addCanGoTo(Block block) {
		this.canGoTo.add(block);
	}

	/**
	 * Returns all the adjacent blocks for an instance of block
	 * @return
	 */
	public ArrayList<Block> connectingBlocks() {
		return this.canGoTo;
	}
		
}
