package userinterface;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import game.Game;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import map.TutorialMap;

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
 * This class essentially extends off the GameUI class and as such
 * there is a working gameloop as well as ui management. A lot of
 * scripted elements have being added ontop of this, allowing for
 * a tutorial to take place.
 *
 */
public class TutorialUI extends GameUI{
	/*
	 * tutorialStage is what keeps track of the player's progress
	 * into the tutorial. The stage is represented as an integer
	 * and will just increments.
	 * 
	 * -- QUESTION --
	 * Should an enum be used to track all of these stages or should
	 * an integer just be used to keep track of it and just have
	 * the integer's representation be noted down in this comment.
	 * 
	 * The stages are as follow.
	 * 	Stage 0:
	 *    The initial stage in which the initial tutorial message
	 *    will pop up. The popup is linked under tut0.png
	 *
	 *  Stage 1:
	 *    The stage where the instructions are given to the player.
	 *    
	 *  Stage 2:
	 *    In this stage, the player will have to move to a marker
	 *    to get a feel of the game.
	 *    
	 *  Stage 3:
	 *    Once the player enters this stage, they will receive a
	 *    pop up to explain how they are meant to push the box.
	 *  
	 *  <<<STAGES TBA>>>
	 */
	private TutorialStage tutorialStage;
	
	private Image imgTutGoal;
	
	private Timeline animTimeline;
	
	private Point stage2Goal;
	private Point stage4Goal;
	private Point stage9Goal;
	
	private Image arrowIndFrame0;
	private Image arrowIndFrame1;
	private Image arrowIndFrame2;
	private Image arrowIndFrame3;
	private Image arrowIndFrame4;
	
	private Image vicFrame0;
	private Image vicFrame1;
	private Image vicFrame2;
	private Image vicFrame3;
	private Image vicFrame4;
	private Image vicFrame5;
	private Image vicFrame6;
	private Image vicFrame7;
	private Image vicFrame8;
	private Image vicFrame9;
	private Image vicFrame10;
	private Image vicFrame11;
	
	@FXML private ImageView popUp;
	@FXML private ImageView flashingArrow;
	@FXML private Button contBtn;
	@FXML private Button undoBtn;
	@FXML private Button backBtnPopUp;
	
	/**
	 * This function is called after all of the FXML elements are loaded into
	 * the class. As a result, we are able to set up the FXML elements. The
	 * initial variable states are also called in this function for convenience
	 * as the class functionality doesn't start in the constructor.
	 */
	@FXML
	public void initialize(){
		// We are going to first initialize the tutorial like a normal game first
		// before we move on to modify it.
		super.initialize();
		
		// Set up the tutorial map.
		TutorialMap mp = new TutorialMap();
		this.game = new Game(mp);
		this.tutorialStage = TutorialStage.S0_OPENING_MSG;
		this.tileSize = 56;
		this.canMove = false;
		
		/*
		 * Initially, we are going to hide the undo button and then
		 * show it to the player later.
		 */
		this.undoBtn.setVisible(false);
		this.undoBtn.setDisable(true);
		
		/*
		 *  Load up the location of where the player needs to be to
		 *  advance the tutorial.
		 */
		this.stage2Goal = new Point(1,2);
		this.stage4Goal = new Point(3,2);
		this.stage9Goal = new Point(5,2);
		
		// Set up the undos for the tutorial
		this.undoLeft = -1;
		this.lblUndosLeft.setText("Unlimited");
		this.lblDiff.setText("Tutorial");
		
		// Load up additional tutorial resources
		this.loadTutResources();
		
		// Draw up the map's initial state
		this.displayMap();
	}
	
	/**
	 * The tutorialUI has overridden this for a single purpose.
	 * To add the ability to draw a marker. Super is not called
	 * as the marker must be drawn under the player which means a
	 * complete change in the function calling structure.
	 */
	@Override
	protected void displayMap(){
		GraphicsContext gc = mainCanvas.getGraphicsContext2D();
		
		this.clearMap(gc);
		this.drawFloor(gc);
		this.drawShadows(gc);
		this.drawGoals(gc);
		this.drawTutGoal(gc);
		this.drawObjects(gc);
		this.drawPlayer(gc);
	}
	
	/**
	 * This function is not meant to be called in TutorialUI. This
	 * override to prevent an error from occurring if setDifficulty
	 * is called.
	 */
	@Override
	public void setDifficulty(Game.Difficulty diff){
		System.err.println("Difficulty is not meant to be called in TutorialUI");
		return;
	}
	
	/**
	 * This function loads up any new resources needed for the tutorial.
	 */
	private void loadTutResources(){
		this.imgTutGoal = new Image("/Images/circle-of-life.png");
		
		this.arrowIndFrame0 = new Image("/Images/btn-ui/flash-arrow/flash-arrow-0.png");
		this.arrowIndFrame1 = new Image("/Images/btn-ui/flash-arrow/flash-arrow-1.png");
		this.arrowIndFrame2 = new Image("/Images/btn-ui/flash-arrow/flash-arrow-2.png");
		this.arrowIndFrame3 = new Image("/Images/btn-ui/flash-arrow/flash-arrow-3.png");
		this.arrowIndFrame4 = new Image("/Images/btn-ui/flash-arrow/flash-arrow-4.png");
		
		this.vicFrame0 = new Image("/Images/pop-up/win/win_00.png");
		this.vicFrame1 = new Image("/Images/pop-up/win/win_01.png");
		this.vicFrame2 = new Image("/Images/pop-up/win/win_02.png");
		this.vicFrame3 = new Image("/Images/pop-up/win/win_03.png");
		this.vicFrame4 = new Image("/Images/pop-up/win/win_04.png");
		this.vicFrame5 = new Image("/Images/pop-up/win/win_05.png");
		this.vicFrame6 = new Image("/Images/pop-up/win/win_06.png");
		this.vicFrame7 = new Image("/Images/pop-up/win/win_07.png");
		this.vicFrame8 = new Image("/Images/pop-up/win/win_08.png");
		this.vicFrame9 = new Image("/Images/pop-up/win/win_09.png");
		this.vicFrame10 = new Image("/Images/pop-up/win/win_10.png");
		this.vicFrame11 = new Image("/Images/pop-up/win/win_11.png");
	}
	
	/**
	 * Drawing the marker to indicate which area the player must go
	 * @param gc The area in the mainCanvas where we can draw
	 */
	private void drawTutGoal(GraphicsContext gc){
		if(this.tutorialStage == TutorialStage.S2_MOVEMENT_TUT){
			gc.drawImage(this.imgTutGoal, 
					this.stage2Goal.getX() * this.tileSize, 
					this.stage2Goal.getY() * this.tileSize,
					this.tileSize, this.tileSize);
			
		} else if(this.tutorialStage == TutorialStage.S4_PUSH_TUT){
			gc.drawImage(this.imgTutGoal, 
					this.stage4Goal.getX() * this.tileSize, 
					this.stage4Goal.getY() * this.tileSize,
					this.tileSize, this.tileSize);
		} else if(this.tutorialStage == TutorialStage.S9_MOVE_OUT){
			gc.drawImage(this.imgTutGoal, 
					this.stage9Goal.getX() * this.tileSize, 
					this.stage9Goal.getY() * this.tileSize,
					this.tileSize, this.tileSize);
		}
	}
	
	/**
	 * This function contains the code to hide or show the pop up
	 * where the instructions are given to the player. The function can
	 * be used to show a different image right after it popped up.
	 * 
	 * @param show The variable to indicate whether the pop up should
	 * be shown or not.
	 * @param img The image of the pop up
	 */
	private void showPopUp(boolean show, Image img){
		// Here we check if it is the victory pop up to play the animation
		if(this.tutorialStage == TutorialStage.S12_FINISH){
			this.popUp.setImage(this.vicFrame0);
			this.animTimeline = new Timeline(
	                new KeyFrame(Duration.ZERO, new KeyValue(this.popUp.imageProperty(), 
	                		this.vicFrame0)),
	                new KeyFrame(Duration.millis(100), new KeyValue(this.popUp.imageProperty(), 
	                		this.vicFrame1)),
	                new KeyFrame(Duration.millis(200), new KeyValue(this.popUp.imageProperty(), 
	                		this.vicFrame2)),
	                new KeyFrame(Duration.millis(300), new KeyValue(this.popUp.imageProperty(), 
	                		this.vicFrame3)),
	                new KeyFrame(Duration.millis(400), new KeyValue(this.popUp.imageProperty(), 
	                		this.vicFrame4)),
	                new KeyFrame(Duration.millis(500), new KeyValue(this.popUp.imageProperty(), 
	                		this.vicFrame5)),
	                new KeyFrame(Duration.millis(600), new KeyValue(this.popUp.imageProperty(), 
	                		this.vicFrame6)),
	                new KeyFrame(Duration.millis(700), new KeyValue(this.popUp.imageProperty(), 
	                		this.vicFrame7)),
	                new KeyFrame(Duration.millis(800), new KeyValue(this.popUp.imageProperty(), 
	                		this.vicFrame8)),
	                new KeyFrame(Duration.millis(900), new KeyValue(this.popUp.imageProperty(), 
	                		this.vicFrame9)),
	                new KeyFrame(Duration.millis(1000), new KeyValue(this.popUp.imageProperty(), 
	                		this.vicFrame10)),
	                new KeyFrame(Duration.millis(1100), new KeyValue(this.popUp.imageProperty(), 
	                		this.vicFrame11)),
	                new KeyFrame(Duration.millis(1200), new KeyValue(this.popUp.imageProperty(), 
	                		this.vicFrame0))
	                );
			this.animTimeline.setCycleCount(Timeline.INDEFINITE);
			this.animTimeline.play();
		} else {
			if(img != null) this.popUp.setImage(img);
		}
		
		if(show)
			this.mainCanvas.setEffect(new GaussianBlur(10));
		else
			this.mainCanvas.setEffect(null);
		
		this.contBtn.setDisable(!show);
		this.contBtn.setVisible(show);
		this.backBtnPopUp.setDisable(!show);
		this.backBtnPopUp.setVisible(show);
		this.popUp.setDisable(!show);
		this.popUp.setVisible(show);
		this.bgCover.setVisible(show);
		this.canMove = !show;
		this.displayMap();
	}
	
	/**
	 * The function being useful here. It can be used to detect if the
	 * player is in the right position and thus advance the tutorial.
	 */
	@Override
	protected void finishAnimation(){
		if(this.tutorialStage == TutorialStage.S2_MOVEMENT_TUT){
			Point pLoc = this.game.getPlayer().getLoc();
			
			if(pLoc.getX() == this.stage2Goal.getX() && pLoc.getY() == this.stage2Goal.getY()){
				this.tutorialStage = TutorialStage.S3_PUSH_MSG;
				this.showPopUp(true, new Image("/Images/pop-up/tut2.png"));
			}
			
		} else if (this.tutorialStage == TutorialStage.S4_PUSH_TUT){
			ArrayList<Point> boxPts = (ArrayList<Point>) this.game.getBoxLocs();
			
			for(Point pt : boxPts){
				if(pt.getX() == this.stage4Goal.getX() && pt.getY() == this.stage2Goal.getY()){
					this.tutorialStage = TutorialStage.S5_BLOCK_MSG;
					this.showPopUp(true, new Image("/Images/pop-up/tut3.png"));
					break;
				}
			}
		} else if (this.tutorialStage == TutorialStage.S8_CHANGE_MSG){
			/*
			 * Since this particular tutorial stage required a special change
			 * to the animation process. We just need to revert it back to the
			 * original value.
			 */
			this.maxAnimCounter = 8;
			this.showPopUp(true, new Image("/Images/pop-up/tut5.png"));
			
		} else if (this.tutorialStage == TutorialStage.S9_MOVE_OUT){
			Point pLoc = this.game.getPlayer().getLoc();
			
			if(pLoc.getX() == this.stage9Goal.getX() && pLoc.getY() == this.stage9Goal.getY()){
				this.tutorialStage = TutorialStage.S10_VICTORY_MSG;
				this.showPopUp(true, new Image("/Images/pop-up/tut6.png"));
			}
			
		} else if (this.tutorialStage == TutorialStage.S11_VICTORY_TUT){
			ArrayList<Point> boxPts = (ArrayList<Point>) this.game.getBoxLocs();
			ArrayList<Point> goalPts = (ArrayList<Point>) this.game.getGoalLocs();
			
			int numOnGoal = 0;
			int numOfGoals = goalPts.size();
			
			for(Point gPt : goalPts){
				for(Point bPt : boxPts){
					if(gPt.getX() == bPt.getX() && gPt.getY() == bPt.getY()){
						numOnGoal++;
						break;
					}
				}
			}
			
			if(numOnGoal == numOfGoals){
				this.tutorialStage = TutorialStage.S12_FINISH;
				this.showPopUp(true, new Image("/Images/pop-up/tut7.png"));
			}
		}
	}
	
	/**
	 * Essentially allows for the player to go back to the main menu. This
	 * is overridden to make sure that the animation for the flashing arrow
	 * is properly deleted.
	 */
	@Override
	protected void goBackMain() throws IOException{
		/*
		 *  The timeline object can cause memory leaks if not stopped before
		 *  changing menus. Thus, it must be forcibly stopped.
		 */
		if(this.animTimeline != null){
			this.animTimeline.stop();
			this.animTimeline = null;
		}
		
		super.goBackMain();
	}
	
	/**
	 * This function is specially used to detect when the undo button is
	 * pressed. This because we have tied the tutorial to it.
	 * 
	 * @param event The button used to fire this function
	 * @throws IOException If we are unable to access the FXML elements
	 * (IO as its a separate FXML file)
	 */
	@FXML
	protected void onUndoClick(ActionEvent event) throws IOException{
		/*
		 * As the undo button will result in an instantaneous result when
		 * clicked. There will be no pause between the pop up showing and
		 * the clicking of the undo button, resulting in the player not
		 * seeing the undo effects. Since animations essentially pause the
		 * game, we can 'hack' it to be used a timer. This is what the
		 * following body of code does.
		 */
		if(this.tutorialStage == TutorialStage.S7_UNDO_TUT){
			this.tutorialStage = TutorialStage.S8_CHANGE_MSG;
			/*
			 * Before we move onto modifying the animations, we need to now
			 * stop the animation timeline.
			 */
			this.animTimeline.stop();
			this.animTimeline = null;
			this.flashingArrow.setVisible(false);
			
			/*
			 * maxAnimCounter controls the speed at which the animation will
			 * play. The default count is too slow for the delay and thus
			 * has being set to be double the default count.
			 */
			this.maxAnimCounter = 40;
			this.game.undoMove();
			this.displayMap();
			/*
			 * We launch the animation with a special code. Normally the code
			 * will indicate which direction the player is going to provide
			 * the correct animation. However, we can input a special code here
			 * which will tell the animation function to not animate. Thus,
			 * the animation process has being converted into a timer.
			 */
			this.startAnimation(ANIM_NONE);
		} else {
			this.undoGame();
		}
	}
	
	/**
	 * Set the pop up back button to go back to main
	 * @param event The type of action used to fire this function
	 */
	@FXML
	protected void goBackToMain(ActionEvent event){
		try {
			this.goBackMain();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function is linked to the contBtn. Whenever it is pressed,
	 * it fires this function.
	 * 
	 * @param event The button used to fire this function
	 */
	@FXML
	private void continueTutorial(ActionEvent event){
		
		/*
		 *  Remember, this is asking which stage it is currently in
		 *  before running the appropriate code.
		 */
		
		/*
		 * The following code will essentially check which stage
		 * the tutorial is in and will then run the necessary instructions
		 * to end the stage.
		 */
		switch(this.tutorialStage){
		case S0_OPENING_MSG:
			this.tutorialStage = TutorialStage.S1_MOVEMENT_MSG;
			this.popUp.setImage(new Image("/Images/pop-up/tut1.png"));
			break;
			
		case S1_MOVEMENT_MSG:
			this.tutorialStage = TutorialStage.S2_MOVEMENT_TUT;
			this.showPopUp(false, null);
			break;
			
		case S3_PUSH_MSG:
			this.tutorialStage = TutorialStage.S4_PUSH_TUT;
			this.showPopUp(false, null);
			break;
		
		case S5_BLOCK_MSG:
			this.tutorialStage = TutorialStage.S6_UNDO_MSG;
			this.popUp.setImage(new Image("/Images/pop-up/tut4.png"));
			break;
		
		case S6_UNDO_MSG:
			this.tutorialStage = TutorialStage.S7_UNDO_TUT;
			this.showPopUp(false, null);
			this.undoBtn.setVisible(true);
			this.undoBtn.setDisable(false);
			
			// Show the flashing arrow
			this.flashingArrow.setVisible(true);
			this.animTimeline = new Timeline(
	                new KeyFrame(Duration.ZERO, new KeyValue(this.flashingArrow.imageProperty(), 
	                		this.arrowIndFrame0)),
	                new KeyFrame(Duration.millis(200), new KeyValue(this.flashingArrow.imageProperty(), 
	                		this.arrowIndFrame1)),
	                new KeyFrame(Duration.millis(400), new KeyValue(this.flashingArrow.imageProperty(), 
	                		this.arrowIndFrame2)),
	                new KeyFrame(Duration.millis(600), new KeyValue(this.flashingArrow.imageProperty(), 
	                		this.arrowIndFrame3)),
	                new KeyFrame(Duration.millis(800), new KeyValue(this.flashingArrow.imageProperty(), 
	                		this.arrowIndFrame4)),
	                new KeyFrame(Duration.millis(1000), new KeyValue(this.flashingArrow.imageProperty(), 
	                		this.arrowIndFrame0))
	                );
			this.animTimeline.setCycleCount(Timeline.INDEFINITE);
			this.animTimeline.play();
			
			break;
			
		case S8_CHANGE_MSG:
			this.tutorialStage = TutorialStage.S8_CHANGE_MSG_2;
			this.showPopUp(true, new Image("/Images/pop-up/tut5second.png"));
			break;
			
		case S8_CHANGE_MSG_2:
			this.tutorialStage = TutorialStage.S9_MOVE_OUT;
			this.game.purgeUndos();
			this.game.updateMap(Game.FREE_SPACE, 4, 2);
			this.showPopUp(false, null);
			break;
			
		case S10_VICTORY_MSG:
			this.tutorialStage = TutorialStage.S11_VICTORY_TUT;
			this.showPopUp(false, null);
			break;
			
		case S12_FINISH:
			try {
				this.goBackMain();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * These are flags to indicate which stage the tutorial has advanced to.
	 */
	private enum TutorialStage{
		S0_OPENING_MSG, S1_MOVEMENT_MSG, S2_MOVEMENT_TUT, S3_PUSH_MSG, S4_PUSH_TUT,
		S5_BLOCK_MSG, S6_UNDO_MSG, S7_UNDO_TUT, S8_CHANGE_MSG, S8_CHANGE_MSG_2, S9_MOVE_OUT,
		S10_VICTORY_MSG, S11_VICTORY_TUT, S12_FINISH
	}
}
