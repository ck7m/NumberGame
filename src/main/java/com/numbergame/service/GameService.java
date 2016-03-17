package com.numbergame.service;

import com.numbergame.model.Answer;
import com.numbergame.model.GameState;

public interface GameService {

	/**
	 * Guess the number based on the player's answer. 
	 * 
	 * @param answer
	 * @return guessed number
	 */	
	int guessNumber(Answer answer);
	
	/**
	 * Determine the current state of the game.
	 * 
	 * @param answer
	 * @return Gamestate
	 */
	GameState determineGameState(Answer answer);
}
