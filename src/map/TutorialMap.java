package map;

import java.awt.Point;
import java.util.Scanner;

import entity.Box;
import entity.Player;

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
 * Creates a predefined map for the tutorial
 *
 */
public class TutorialMap extends MapGenerator {
	
	private String preDefMap;
	private int[][] grid;
	

	public TutorialMap(){
		this.preDefMap = "1 1 1 1 1 1 1 1 1 1\n" + 
						 "1 1 1 1 1 0 0 0 0 1\n" + 
						 "1 0 2 0 1 0 0 1 0 1\n" + 
						 "1 0 1 1 1 1 0 0 3 1\n" +
						 "1 0 0 0 1 0 0 1 0 1\n" +
						 "1 0 0 0 1 0 0 1 0 1\n" +
						 "1 0 0 0 1 0 0 1 0 1\n" +
						 "1 1 1 1 1 0 0 1 2 1\n" +
						 "1 0 3 0 0 0 0 0 0 1\n" +
						 "1 1 1 1 1 1 1 1 1 1\n";
		
		this.grid = new int[10][10];
		
		this.setUpTutorial();
		
		
		
		this.boxLocs.add(new Box(new Point(2, 2)));
		this.boxLocs.add(new Box(new Point(8, 7)));
		this.goalLocs.clear();
		this.goalLocs.add(new Point(8, 3));
		this.goalLocs.add(new Point(2, 8));
		this.playerLocation = new Point(2, 5);
		this.player = new Player(this.playerLocation);
	}
	
	/**
	 * sets up the tutorial
	 */
	protected void setUpTutorial(){
		int y = 0;
		
		Scanner scanner = new Scanner(this.preDefMap);
		while (scanner.hasNextLine()) {
			String[] mapCode = scanner.nextLine().split("\\s+");
			
			for(int x = 0; x < mapCode.length; x++){
				this.grid[x][y] = Integer.parseInt(mapCode[x]);
			}
			
			y++;
		}
		scanner.close();
	}
	
	@Override
	public int[][] getGrid() {
		return this.grid;
	}
	
	@Override
	public void updateMap(int code, int x, int y) {
		this.grid[x][y] = code;
	}
}
