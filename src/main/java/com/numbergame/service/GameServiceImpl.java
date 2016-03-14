package com.numbergame.service;

import java.util.Random;

import com.numbergame.model.Answer;
import com.numbergame.model.GameState;
import com.numbergame.model.NumberRange;

public class GameServiceImpl implements GameService {
	
	private NumberRange numberRange;
	private Random random = new Random();

	public GameServiceImpl() {
		super();
		numberRange = new NumberRange(1,100);
	}

	public NumberRange getNumberRange() {
		return numberRange;
	}

	@Override
	public GameState validateAnswer(Answer answer) {
		return answer.getGameState();
	}	

	@Override
	public int guessNumber(Answer answer) {
		answer.adjustNumberRange(numberRange);
		int randomNumber = generateRandomNumber();
		numberRange.setCurrentValue(randomNumber);
		return randomNumber;
	}
	
	int generateRandomNumber(){
		return random.ints(numberRange.getStart(), numberRange.getEnd()+1).findFirst().getAsInt();		
	}

}
