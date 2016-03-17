package com.numbergame.controller;

/**
 * Main class for starting the game.
 * @author Chandru
 *
 */
public class GameBoard {

	public static void main(String[] args) {
		/*
		 * If more than one game needs to be created then refactor to use factory pattern
		 */
		GameController gameController = new NumberGuessGameController();
		gameController.start();
	}

}
