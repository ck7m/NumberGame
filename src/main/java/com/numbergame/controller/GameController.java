package com.numbergame.controller;

import java.util.Scanner;

import com.numbergame.model.Answer;
import com.numbergame.model.GameState;
import com.numbergame.service.GameService;
import com.numbergame.service.GameServiceImpl;

public class GameController {
	
	private GameState gameState = GameState.IN_PROGRESS;	
	private GameService gameService = new GameServiceImpl();

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public static void main(String[] args) {
		GameController gameController = new GameController();
		gameController.start();
	}

	/**
	 * @param scanner
	 */
	public void play(Scanner scanner) {
		String input = readInput(scanner);
		do {
			if (Answer.isValidAnswer(input)) {
				Answer answer = Answer.valueOf(input.toUpperCase());
				gameState=gameService.validateAnswer(answer);
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

	public void start() {
		System.out.println(GameConstants.WELCOME_MSG);
		Scanner scanner = new Scanner(System.in);
		play(scanner);
		scanner.close();
		System.out.println(GameConstants.THANK_YOU_MSG);
	}
}
