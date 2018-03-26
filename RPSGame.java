/*Write a class (RPSGame.java) that represents the game play.

This class only represents the game itself. 
This class does not interact with the user. 
A game is made up of many matches. 
A match is a single selection of rock, paper, or scissors. 
Thus, the RPSGame object is described by three characteristics: number of computer wins, number of user wins, and number of ties.

Note that you do not need to store the user move or computer move as instance data. You are only storing the win/loss/tie counts.

For Part 1:
Write the instance data variables for these three characteristics and appropriate getters/setters.
Use enums or constants to represent the possible match outcomes (user win, computer win, tie) and the possible moves (rock, paper, scissors).
Declare and use your constants or enums throughout both your game class and the GUI class (described in Part 2).
For example, you will need to determine a winner. To do this, your code should compare whether userMove == ROCK or userMove = MoveType.ROCK, 
rather than userMove == 1 or userMove = "Rock"
Be sure to post to the discussion board if you do not understand this requirement! 
You need to use constants or an enum to get full credit for the project.
Write a generateComputerPlay method that generates and returns a random move by the computer.
The return type of this  method will be determined by whether you use an enum or a constant!
Write a findWinner method that takes in two moves as parameters (the userMove and the computerMove) 
and determines and returns the outcome (user wins, computer wins, or tie).
Determining the winner will require you to compare a lot of possible match-ups through a series of nested conditionals.
The type of the parameters and the return type of the method will depend on whether you chose to use an enum or a constant!
The method should update the appropriate win/loss/tie count (the instance data variables) before returning a value.*/

import java.util.Random;

public class RPSGame {
	
	private int numComputerWins;
	private int numUserWins;
	private int numTies;
	
	private int betAmount;
	private int balance;
	
	public enum Result { WIN, LOSE, TIE }
	public enum Move { ROCK, PAPER, SCISSORS }
	
	public RPSGame(int betAmount) {
		this.betAmount = betAmount;
		balance = 0;
	}
	
	public Result findWinner(Move userMove, Move computerMove) {
		
		Result result = Result.TIE;
		
		if(userMove.equals(Move.ROCK)) {
			if(computerMove.equals(Move.SCISSORS)) {
				result = Result.WIN;
			} else if(computerMove.equals(Move.PAPER)) {
				result = Result.LOSE;
			} 
		} else if(userMove.equals(Move.PAPER)) {
			if(computerMove.equals(Move.SCISSORS)) {
				result = Result.LOSE;
			} else if(computerMove.equals(Move.ROCK)) {
				result = Result.WIN;
			}
		} else if(userMove.equals(Move.SCISSORS)){
			if(computerMove.equals(Move.PAPER)) {
				result = Result.WIN;
			} else if(computerMove.equals(Move.ROCK)) {
				result = Result.LOSE;
			}
		}
		
		if(result.equals(Result.WIN)) {
			numUserWins++;
			balance+=betAmount;
		} else if(result.equals(Result.LOSE)) {
			numComputerWins++;
			balance-=betAmount;
		} else {
			numTies++;
		}
		return result;
	}
	
	public Move generateComputerPlay() {
		
		Move move = null;
		Random rand = new Random();
		int  randomMove = rand.nextInt(3);
		
		switch(randomMove) {
		
			case 0:		move = Move.ROCK;
						break;
						
			case 1: 	move = Move.PAPER;
						break;
						
			case 2: 	move = Move.SCISSORS;
						break;
		}
		return move;
	}
	
	public int getNumComputerWins() {
		return numComputerWins;
	}
	public int getNumUserWins() {
		return numUserWins;
	}
	public int getNumTies() {
		return numTies;
	}
	public int getBetAmount() {
		return betAmount;
	}
	public int getBalance() {
		return balance;
	}
	
	public void setNumComputerWins(int numComputerWins) {
		this.numComputerWins = numComputerWins;
	}
	public void setNumUserWins(int numUserWins) {
		this.numUserWins = numUserWins;
	}
	public void setNumTies(int numTies) {
		this.numTies = numTies;
	}	
	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	

}
