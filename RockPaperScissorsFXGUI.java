
import java.util.Optional;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class RockPaperScissorsFXGUI extends Application {

	private ImageView computerMoveImageView, userMoveImageView;
	private Image rockImage, paperImage, scissorsImage;
	private Image cRock, cPaper, cScissors, pRock, pPaper, pScissors;
	private Text matchOutcomeText, cWinsText, uWinsText, tieText;
	private Text betText, balanceText;
	private Button rockButton, paperButton, scissorsButton;
	private HBox labelBox;

	private RPSGame game;

	public void start(Stage primaryStage) {
		
		betText = new Text();
		betText.setFont(Font.font("Helvetica", 16));
		betText.setFill(Color.TRANSPARENT);
		balanceText = new Text();
		balanceText.setFont(Font.font("Helvetica", 16));
		balanceText.setFill(Color.TRANSPARENT);

		int betAmount = getBetAmount(); // only if completing the extra credit!

		game = new RPSGame(betAmount);
		
		/* the image and labels for the computer and user move */
		rockImage = new Image("rock.jpg");
		paperImage = new Image("paper.jpg");
		scissorsImage = new Image("scissors.jpg");
		
		cRock = new Image("rockL.jpg");
		cPaper = new Image("paperL.jpg");
		cScissors = new Image("scissorsL.jpg");
		pRock = new Image("rockR.jpg");
		pPaper = new Image("paperR.jpg");
		pScissors = new Image("scissorsR.jpg");

		computerMoveImageView = new ImageView(rockImage);
		computerMoveImageView.setVisible(false); // used to make the initial screen layout appear the same as when the game starts
		userMoveImageView = new ImageView(rockImage);
		userMoveImageView.setVisible(false); 
		HBox imageBox = new HBox(computerMoveImageView, userMoveImageView);
		imageBox.setAlignment(Pos.CENTER);
		imageBox.setSpacing(20);

		Text computerLabel = new Text("Computer's Move");
		computerLabel.setFont(Font.font("Helvetica", 14));
		Text yourLabel = new Text("Your Move");
		yourLabel.setFont(Font.font("Helvetica", 14));
		labelBox = new HBox(computerLabel, yourLabel);
		labelBox.setAlignment(Pos.CENTER);
		labelBox.setSpacing(30);
		labelBox.setVisible(false);

		/* the results of each match */
		matchOutcomeText = new Text();
		matchOutcomeText.setFill(Color.GREEN);
		matchOutcomeText.setFont(Font.font("Helvetica", 20));

		/* the buttons for the user's play */
		rockButton = new Button("Play Rock");
		rockButton.setOnAction(this::handleUserPlay);
		paperButton = new Button("Play Paper");
		paperButton.setOnAction(this::handleUserPlay);
		scissorsButton = new Button("Play Scissors");
		scissorsButton.setOnAction(this::handleUserPlay);
		HBox buttonBox = new HBox(rockButton, paperButton, scissorsButton);
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.CENTER);
		
		/* the game statistics */
		cWinsText = new Text("Computer Wins: " + game.getNumComputerWins());
		cWinsText.setFont(Font.font("Helvetica", 16));
		cWinsText.setFill(Color.BLUE);
		uWinsText = new Text("User Wins: " + game.getNumUserWins());
		uWinsText.setFont(Font.font("Helvetica", 16));
		uWinsText.setFill(Color.BLUE);
		tieText = new Text("Ties: " + game.getNumTies());
		tieText.setFont(Font.font("Helvetica", 16));
		tieText.setFill(Color.BLUE);
		HBox statsBox = new HBox(cWinsText, uWinsText, tieText);
		statsBox.setSpacing(10);
		statsBox.setAlignment(Pos.CENTER);
		
		HBox betBox = new HBox(betText, balanceText);
		betBox.setSpacing(10);
		betBox.setAlignment(Pos.CENTER);
		
		VBox pane = new VBox(imageBox, labelBox, matchOutcomeText, buttonBox, statsBox, betBox);
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(20);
		pane.setStyle("-fx-background-color: white");

		Scene scene = new Scene(pane, 400, 400, Color.TRANSPARENT);
		primaryStage.setTitle("Rock, Paper, Scissors, Go!");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// only implemented this method if completing the extra credit
	private int getBetAmount() {
		
		// YOUR EXTRA CREDIT CODE HERE
		int betAmount = 0;
		
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
			alert.setHeaderText(null);
			alert.setTitle("Bet prompt");
			alert.setContentText("Would you like to place a bet?");
			Optional<ButtonType> userSelection = alert.showAndWait();
			if(userSelection.isPresent() && userSelection.get() == ButtonType.YES) {
				TextInputDialog inputDialog = new TextInputDialog();
				inputDialog.setHeaderText(null);
				inputDialog.setTitle("Bet amount");
				inputDialog.setContentText("How much would you like to bet?");
				Optional<String> userInput = inputDialog.showAndWait();
				if(userInput.isPresent()) {
					String userInputString = userInput.get();
					betAmount = Integer.parseInt(userInputString);
					betText.setText("Bet Amount: " + betAmount);
					betText.setFill(Color.BLUE);
					balanceText.setText("Balance: 0");
					balanceText.setFill(Color.BLUE);
				}
				
			}
		} catch(NumberFormatException ex) {
			System.out.println("Invalid entry.");
		}
		return betAmount;
	}

	private void handleUserPlay(ActionEvent event) {
		// to make all aspects of the display visible
		userMoveImageView.setVisible(true);
		computerMoveImageView.setVisible(true);
		labelBox.setVisible(true);
		
		// YOUR CODE HERE
		RPSGame.Move userMove = null;
		RPSGame.Move computerMove = game.generateComputerPlay();
		RPSGame.Result result;
		
		if(event.getSource().equals(rockButton)) {
			userMove = RPSGame.Move.ROCK;
			userMoveImageView.setImage(pRock);
		} else if(event.getSource().equals(paperButton)) {
			userMove = RPSGame.Move.PAPER;
			userMoveImageView.setImage(pPaper);
		} else if(event.getSource().equals(scissorsButton)) {
			userMove = RPSGame.Move.SCISSORS;
			userMoveImageView.setImage(pScissors);
		}
		
		if(computerMove.equals(RPSGame.Move.ROCK)) {
			computerMoveImageView.setImage(cRock);
		} else if(computerMove.equals(RPSGame.Move.PAPER)) {
			computerMoveImageView.setImage(cPaper);
		} else if(computerMove.equals(RPSGame.Move.SCISSORS)) {
			computerMoveImageView.setImage(cScissors);
		}
		
		result = game.findWinner(userMove, computerMove);
		
		if(result.equals(RPSGame.Result.WIN)) {
			matchOutcomeText.setText("You win");
		} else if(result.equals(RPSGame.Result.LOSE)) {
			matchOutcomeText.setText("You lose");
		} else if(result.equals(RPSGame.Result.TIE)){
			matchOutcomeText.setText("Tie");
		}
		
		cWinsText.setText("Computer Wins: " + game.getNumComputerWins());
		uWinsText.setText("User Wins: " + game.getNumUserWins());
		tieText.setText("Ties: " + game.getNumTies());
		balanceText.setText("Balance: " + game.getBalance());
		
		/*get the move from the user (determine which button was clicked)
		update the display of the user’s move 
		generate a move by the computer (by invoking a method on the RPSGame object)
		update the display of the computer’s move 
		determine the winner (by invoking a method on the RPSGame object)
		update the display of the outcome and the game stats (obtained from RPSGame object)*/

	}

	public static void main(String[] args) {
		launch(args);
	}

}
