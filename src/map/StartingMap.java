package map;
import entity.Block;

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
 * Creates a map prototype
 *
 */
public class StartingMap {
	
	private static final int DIMENSIONS = 8;
	private Block[][] blocks = new Block[DIMENSIONS][DIMENSIONS];
	
	public StartingMap(){
		createPrototype();
	}
	
	/**
	 * Creates a starting map prototype 
	 * The map looks like 
	 * 
	 * 11111111
	 * 11111111
	 * 11000011
	 * 11011011
	 * 11000011
	 * 11111111
	 * 11111111
	 * 11111111
	 */
	public void createPrototype(){
		for(int i = 0; i < DIMENSIONS; i++){
			for(int j = 0; j < DIMENSIONS; j++){
				if(i <= 1 || i >= 5){
					this.blocks[i][j] = new Block(1, i ,j);
				} else {
					if(j <= 1 || j >= 6){
						this.blocks[i][j] = new Block(1, i ,j);
					} else {
						this.blocks[i][j] = new Block(0, i ,j);
					}
				}
			}
		}
		this.blocks[3][3].setType(1);
		this.blocks[3][4].setType(1);
	}
	
	/**
	 * 
	 * @return map as a Block[][]
	 */
	public Block[][] getBlockMap(){
		return this.blocks;
	}	
}
