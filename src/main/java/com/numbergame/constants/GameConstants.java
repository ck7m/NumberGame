package com.numbergame.constants;

public interface GameConstants {
	String WELCOME_MSG = "Welcome to Number Guessing game. \nPlease think of a number between 1 to 100 and enter \"Ready\" to start the game.";
	String GUESS_MSG = "Is the number %d?";
	String THANK_YOU_MSG = "Thank you for playing.";
	String INVALID_MSG = "Invalid Answer. Please enter again";
	String END_MSG = "End of the game";
	String ERROR_MSG = "Sorry, something went wrong. Please restart the game and play again.";
	
	int MIN_RANGE = 1;
	int MAX_RANGE=100;
}
