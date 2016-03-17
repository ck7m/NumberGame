package com.numbergame.service;

import java.util.Random;

import com.numbergame.constants.GameConstants;
import com.numbergame.model.Answer;
import com.numbergame.model.GameState;
import com.numbergame.model.NumberRange;

/**
 * Responsible to generate a number based on the range. This class also constantly adjusts the range for every "guess" method call to identify the player's guess in minimum invocations. The initial range is assumed between 1 to 100.
 * 
 * @author Chandru
 *
 */
public class NumberGuessGameServiceImpl implements GameService {

	private NumberRange numberRange;
	private Random random = new Random();

	public NumberGuessGameServiceImpl() {
		super();
		numberRange = new NumberRange(GameConstants.MIN_RANGE, GameConstants.MAX_RANGE);
	}

	public NumberRange getNumberRange() {
		return numberRange;
	}

	@Override
	public GameState determineGameState(Answer answer) {
		return answer.getGameState();
	}

	/**
	 * Does three things
	 * <ol>
	 * <li>Adjust the number range based on the Answer. For more details see {@link Answer#adjustNumberRange(NumberRange) adjustNumberRange}</li>
	 * <li>Guess a new number within the adjusted range</li>
	 * <li>Sets the generated number as the current value</li>
	 * </ol>
	 * 
	 * @see com.numbergame.service.GameService#guessNumber(com.numbergame.model.Answer)
	 * 
	 */
	@Override
	public int guessNumber(Answer answer) {	
		answer.adjustNumberRange(numberRange);
		int randomNumber = generateRandomNumber();
		numberRange.setCurrentValue(randomNumber);
		return randomNumber;
	}

	/**
	 * Using Math.Random generate an random number between the given range.
	 * @return
	 */
	int generateRandomNumber() {
		return random.ints(numberRange.getStart(), numberRange.getEnd() + 1).findFirst().getAsInt();
	}

}
