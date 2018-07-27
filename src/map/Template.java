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
 * Creates templates
 *
 */
public class Template {
	private Block[][] b;
	
	public Template(int i){
		if(i == 0){
			template1();
		} else if (i == 1){
			template2();
		} else if (i == 2){
			template3();
		}
	}
	
	/**
	 * Creates template that looks like this:
	 * 00
	 * 00
	 * 00
	 */
	public void template1(){
		b = new Block[2][3];
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 3; j++){
				b[i][j] = new Block(0, i ,j);
			}
		}
		
	}
	
	/**
	 * Creates template that looks like this:
	 * 00
	 * 00
	 */
	public void template2(){
		b = new Block[2][2];
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
				b[i][j] = new Block(0, i ,j);
			}
		}
	}
	
	/**
	 * Creates template that looks like this:
	 * 000
	 * 010
	 * 000
	 */
	public void template3(){
		b = new Block[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				b[i][j] = new Block(0, i ,j);
			}
			b[1][1] = new Block(1, 1, 1);
		}
	}
	
	/**
	 * 
	 * @return template as a Block[][]
	 */
	public Block[][] getBlockTemp(){
		return this.b;
	}
}
