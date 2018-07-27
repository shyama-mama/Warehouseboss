package userinterface;

import java.io.IOException;

import game.Game;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

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
 * This class handles the main menu and any action needed to make it work.
 *
 */
public class MainUI {
	private Timeline backgroundAnim;
	private FadeTransition mainMFadeOut;
	private FadeTransition mainMFadeIn;
	private FadeTransition diffMFadeOut;
	private FadeTransition diffMFadeIn;
	
	private Image bgAnimFrame0;
	private Image bgAnimFrame1;
	private Image bgAnimFrame2;
	private Image bgAnimFrame3;
	private Image bgAnimFrame4;
	
	// Last minute addition
	private Scene gameUIScene;
	
	@FXML private ImageView backgroundImg;
	@FXML private Button startBtn;
	@FXML private Button tutBtn;
	@FXML private Button exitBtn;
	@FXML private Button easyBtn;
	@FXML private Button normalBtn;
	@FXML private Button hardBtn;
	@FXML private Button returnBtn;
	@FXML private GridPane mainMenu;
	@FXML private GridPane diffMenu;
	@FXML private ProgressBar progressBar;
	@FXML private Rectangle progressBG;
	@FXML private Label lblProgress;
	@FXML private Button cancelProgress;
	
	/**
	 * This function is called after all of the FXML elements are loaded into
	 * the class. As a result, we are able to set up the FXML elements. The
	 * initial variable states are also called in this function for convenience
	 * as the class functionality doesn't start in the constructor.
	 */
	@FXML
	public void initialize(){
		/*
		 * We load up all of the type of fades that are to be used by
		 * the two button menus.
		 */
		this.mainMFadeOut = new FadeTransition(Duration.millis(250));
		this.mainMFadeOut.setNode(mainMenu);
		this.mainMFadeOut.setFromValue(1.0);
		this.mainMFadeOut.setToValue(0.0);
		this.mainMFadeOut.setCycleCount(1);
		this.mainMFadeOut.setAutoReverse(false);
		
		this.mainMFadeIn = new FadeTransition(Duration.millis(250));
		this.mainMFadeIn.setNode(mainMenu);
		this.mainMFadeIn.setFromValue(0.0);
		this.mainMFadeIn.setToValue(1.0);
		this.mainMFadeIn.setCycleCount(1);
		this.mainMFadeIn.setAutoReverse(false);
		
		this.diffMFadeOut = new FadeTransition(Duration.millis(250));
		this.diffMFadeOut.setNode(diffMenu);
		this.diffMFadeOut.setFromValue(1.0);
		this.diffMFadeOut.setToValue(0.0);
		this.diffMFadeOut.setCycleCount(1);
		this.diffMFadeOut.setAutoReverse(false);
		
		this.diffMFadeIn = new FadeTransition(Duration.millis(250));
		this.diffMFadeIn.setNode(diffMenu);
		this.diffMFadeIn.setFromValue(0.0);
		this.diffMFadeIn.setToValue(1.0);
		this.diffMFadeIn.setCycleCount(1);
		this.diffMFadeIn.setAutoReverse(false);
		
		this.bgAnimFrame0 = new Image("/Images/whb1.png");
		this.bgAnimFrame1 = new Image("/Images/whb2.png");
		this.bgAnimFrame2 = new Image("/Images/whb3.png");
		this.bgAnimFrame3 = new Image("/Images/whb4.png");
		this.bgAnimFrame4 = new Image("/Images/whb5.png");
		
		/*
		 * We kickstart the animation that is suppose to run in the background
		 */
		this.backgroundAnim = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(this.backgroundImg.imageProperty(),
                		this.bgAnimFrame0)),
                new KeyFrame(Duration.millis(200), new KeyValue(this.backgroundImg.imageProperty(),
                		this.bgAnimFrame1)),
                new KeyFrame(Duration.millis(400), new KeyValue(this.backgroundImg.imageProperty(),
                		this.bgAnimFrame2)),
                new KeyFrame(Duration.millis(600), new KeyValue(this.backgroundImg.imageProperty(),
                		this.bgAnimFrame3)),
                new KeyFrame(Duration.millis(800), new KeyValue(this.backgroundImg.imageProperty(),
                		this.bgAnimFrame4)),
                new KeyFrame(Duration.millis(1000), new KeyValue(this.backgroundImg.imageProperty(),
                		this.bgAnimFrame2))
                );
		this.backgroundAnim.setCycleCount(Timeline.INDEFINITE);
		this.backgroundAnim.play();
	}
	
	/**
	 * Handles the buttons in the main buttons menu
	 * 
	 * @precondition event is not null
	 * @param event The action used to kick start this function
	 * @throws IOException
	 */
	@FXML
	private void onStandardBtnClick(ActionEvent event) throws IOException{
		if(event.getSource() == this.startBtn){
			/*
			 * If the start button is called, we will fade in the
			 * difficulty menu.
			 */
			this.mainMenu.setDisable(true);
			this.diffMenu.setDisable(false);
			this.diffMenu.setVisible(true);
			this.mainMFadeOut.playFromStart();
			this.diffMFadeIn.playFromStart();
			
		} else if (event.getSource() == this.returnBtn){
			/*
			 * If the return button is called, we will fade back to
			 * the main menu
			 */
			this.mainMenu.setDisable(false);
			this.diffMenu.setDisable(true);
			this.mainMFadeIn.playFromStart();
			this.diffMFadeOut.playFromStart();
			
		} else if (event.getSource() == this.tutBtn){
			if(Game.isLoading) return;
			/*
			 * We must always stop the animation for the background or else
			 * it will cause a memory leak due to the fact that it is set
			 * to run forever and forever.
			 */
			this.backgroundAnim.stop();
			this.backgroundAnim = null;
			Parent tutUIRoot = FXMLLoader.load(getClass().getResource("TutorialUILayout.fxml"));
			Scene tutUIScene = new Scene(tutUIRoot, MainApplication.WIDTH, MainApplication.HEIGHT);
			Stage stage = (Stage) this.startBtn.getScene().getWindow();
			stage.setScene(tutUIScene);
			stage.show();
			
		} else if (event.getSource() == this.cancelProgress){
			Game.isLoading = false;
			this.hideLoadingBar();
		} else if (event.getSource() == this.exitBtn){
			Platform.exit();
		}
	}
	
	/**
	 * Handles the buttons in the difficulty buttons menu
	 * 
	 * @precondition event is not null
	 * @param event The action used to kick start this function
	 * @throws IOException
	 */
	@FXML
	private void onDiffBtnClick(ActionEvent event) throws IOException{
		if(Game.isLoading) return;
		Game.isLoading = true;
		
		FXMLLoader gameUILoader = new FXMLLoader(getClass().getResource("GameUILayout.fxml"));
		this.gameUIScene = new Scene((Parent) gameUILoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
		GameUI gameUIController = gameUILoader.getController();
		
		this.showLoadingBar();
		
		/*
		 * Here we are going to delegate all of the code generation into a
		 * different thread.
		 * 
		 * We will then set the main UI to show a progress bar with a ETA
		 * on when this will finish.
		 */
		Task<Void> task = new Task<Void>(){
			@Override
			protected Void call() throws Exception {
				
				if(event.getSource() == easyBtn)
					gameUIController.setDifficulty(Game.Difficulty.EASY);
				else if(event.getSource() == normalBtn)
					gameUIController.setDifficulty(Game.Difficulty.NORMAL);
				else if(event.getSource() == hardBtn)
					gameUIController.setDifficulty(Game.Difficulty.HARD);
				
				Platform.runLater(() -> switchScene());
				return null;
			}
		};
		
		new Thread(task).start();
	}
	
	/**
	 * Last minute addition for the loading bar.
	 * 
	 * @precondition gameUIScene is not a null and has already being set before
	 * this function has being called.
	 */
	private void switchScene(){
		Game.isLoading = false;
		/*
		 * We must always stop the animation for the background or else
		 * it will cause a memory leak due to the fact that it is set
		 * to run forever and forever.
		 */
		this.backgroundAnim.stop();
		this.backgroundAnim = null;
		
		Stage stage = (Stage) this.startBtn.getScene().getWindow();
		stage.setScene(this.gameUIScene);
		stage.show();
	}
	
	/**
	 * Show the loading bar, black background and back button to the 
	 * user
	 */
	private void showLoadingBar(){
		this.lblProgress.setVisible(true);
		this.progressBar.setVisible(true);
		this.progressBG.setVisible(true);
		this.cancelProgress.setDisable(false);
		this.cancelProgress.setVisible(true);
	}
	
	/**
	 * Hides the loading bar, black background and back button to the 
	 * user
	 */
	private void hideLoadingBar(){
		this.lblProgress.setVisible(false);
		this.progressBar.setVisible(false);
		this.progressBG.setVisible(false);
		this.cancelProgress.setDisable(true);
		this.cancelProgress.setVisible(false);
	}
}
