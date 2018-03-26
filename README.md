# Project-7-RockPaperScissors
Rock Paper Scissors game

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
