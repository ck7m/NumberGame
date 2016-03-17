package com.numbergame.controller;

import java.util.Scanner;

import com.numbergame.constants.GameConstants;
import com.numbergame.model.Answer;
import com.numbergame.model.GameState;
import com.numbergame.service.GameService;
import com.numbergame.service.NumberGuessGameServiceImpl;

/**
 * Responsible for the NumberGuess Game. Interact with the player and maintains the state of the game.
 * 
 * @author Chandru
 *
 */
public class NumberGuessGameController implements GameController {
	
	private GameState gameState = GameState.IN_PROGRESS;	
	private GameService gameService = new NumberGuessGameServiceImpl();

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	/**
	 * Validates the user input and guess the number. Continue running until the game state changes from Inprogress to End.
	 * 
	 * @param scanner
	 */
	protected void play(Scanner scanner) {
		String input = readInput(scanner);
		do {
			if (Answer.isValidAnswer(input)) {
				Answer answer = Answer.valueOf(input.toUpperCase());
				gameState=gameService.determineGameState(answer);
				if(GameState.END==gameState) break;
				int randomNum = gameService.guessNumber(answer);
				System.out.println(String.format(GameConstants.GUESS_MSG, randomNum));
				input=readInput(scanner);				
			} else {
				System.out.println(GameConstants.INVALID_MSG);
				input=readInput(scanner);
			} 
		} while (GameState.END!=gameState);		
	}

	
	/**
	 * Read user input from command line. Even though it is a single line it is easy to mock in unit test if it is a separate method.
	 * @param scanner
	 * @return
	 */
	protected String readInput(Scanner scanner) {
		return scanner.nextLine();
	}

	/**
	 * @see com.numbergame.controller.GameController#start()
	 */
	@Override
	public void start() {
		System.out.println(GameConstants.WELCOME_MSG);
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			play(scanner);
		} catch (Exception e) {
			System.out.println(GameConstants.ERROR_MSG);
		}
		if(scanner!=null)scanner.close();
		System.out.println(GameConstants.THANK_YOU_MSG);
	}
}
