package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
 * CreateMap creates a 8x8 map and adds templates and goals randomly on the map
 */
public class CreateMap {
	
	private static final int DIMENSIONS = 8;
	private Block[][] puzzle = new Block[DIMENSIONS][DIMENSIONS];
	private StartingMap s = new StartingMap();
	private ArrayList<Template> ts = new ArrayList<Template>();
	private List<Point> goalLocs;

	public CreateMap() {
		this.goalLocs = new ArrayList<>();
		this.puzzle = this.s.getBlockMap();
		createTemplates();
		addTemplate();
		checkNoGoal();
		addGoal();
	}
	
	/**
	 * Creates templates and adds them to a list of templates
	 */
	public void createTemplates(){
		for(int i = 0; i < 3; i++){
			Template t = new Template(i);
			this.ts.add(t);
		}
	}
	
	/**
	 * Adds templates randomly to the 8x8 prototype
	 */
	public void addTemplate(){
		for(Template t: this.ts){
			int w = t.getBlockTemp().length;
			int h = t.getBlockTemp()[0].length;
			boolean valid = false;
			while(!valid){
				Random randomGenerator = new Random();
				int x = randomGenerator.nextInt(DIMENSIONS);
				int y = randomGenerator.nextInt(DIMENSIONS);
				if(x+h < this.puzzle.length-2 && x >= 1){
					if(y+w < this.puzzle.length-2 && y >= 1){
						valid = true;
						for(int i = 0; i < h; i++){
							for(int j = 0; j < w; j++){
								this.puzzle[j+x][i+y].setType(t.getBlockTemp()[j][i].getType());
							}
						}
					}
				}
			}
			
		}
	}

	/**
	 * Checks if map has areas where a player can't push the box to and sets them 
	 * as NoGoal zones so goals aren't placed there 
	 */
	public void checkNoGoal(){
		for(int i = 0; i < this.puzzle.length; i++){
			for(int j = 0; j < this.puzzle[0].length; j++){
				if(this.puzzle[i][j].getType() == 0){
					if(!noGoal(i, j)){
						this.puzzle[i][j].setType(9);
					}
				}
			}
		}
	}
	
	/**
	 * Helper function of checkNoGoal and returns true or false after checking
	 * if a given block is a noGoal
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean noGoal(int i, int j) {
		if(j-1 >= 1 && this.puzzle[i][j-1].getType() ==  0 && this.puzzle[i][j-2].getType() == 0){
			return true;
		} else if(j+1 <= this.puzzle[0].length-2 && this.puzzle[i][j+1].getType() == 0 && this.puzzle[i][j+2].getType() == 0){
			return true;
		} else if(i-1 >= 1 && this.puzzle[i-1][j].getType() == 0 && this.puzzle[i-2][j].getType() == 0){
			return true;
		} else if(i+1 <= this.puzzle.length-2 && this.puzzle[i+1][j].getType() == 0 && this.puzzle[i+2][j].getType() == 0){
			return true;
		}
		return false;
	}

	/**
	 * Adds goals randomly to the map as long as a block is not a noGoal block
	 */
	public void addGoal(){
		for(int f = 0; f < 3; f++){
			int accepted = 0;
			while(accepted == 0){
				Random randomGenerator = new Random();
				int i = randomGenerator.nextInt(DIMENSIONS);
				int j = randomGenerator.nextInt(DIMENSIONS);
				if(this.puzzle[i][j].getType() == 0){
					this.puzzle[i][j].setType(3);
					Point p = new Point(i,j);
					p.setLocation(i, j);
					this.goalLocs.add(p);
					accepted = 1;
				}		
			}
		}
		
	}
	
	/**
	 * Return the map
	 * @return
	 */
	public Block[][] getPuzzle() {
		return this.puzzle;
	}

	/**
	 * Return list of goal co-ordinates
	 * @return
	 */
	public List<Point> getGoalLocs() {
		return this.goalLocs;
	}
}
