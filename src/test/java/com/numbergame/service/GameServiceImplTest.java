package com.numbergame.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.numbergame.model.Answer;
import com.numbergame.model.NumberRange;

public class GameServiceImplTest {
	GameServiceImpl gameService=new GameServiceImpl();

	@Test
	public void testGenerateRandomNumber() {
		NumberRange numberRange = gameService.getNumberRange();
		numberRange.setCurrentValue(10);
		numberRange.adjustStartRange();
		numberRange.setCurrentValue(30);
		numberRange.adjustEndRange();
		int randomNumber = gameService.generateRandomNumber();
		assertTrue(randomNumber >=numberRange.getStart());
		assertTrue(randomNumber <=numberRange.getEnd());		
	}
	
	@Test
	public void testGuessNumber_Higher() {
		NumberRange numberRange = gameService.getNumberRange();
		numberRange.setCurrentValue(40);
		int randomNumber = gameService.guessNumber(Answer.HIGHER);
		assertTrue(randomNumber >=41);
		assertTrue(randomNumber <=100);		
	}
	
	@Test
	public void testGuessNumber_Lower() {
		NumberRange numberRange = gameService.getNumberRange();
		numberRange.setCurrentValue(40);
		int randomNumber = gameService.guessNumber(Answer.LOWER);
		assertTrue(randomNumber >=1);
		assertTrue(randomNumber <=39);		
	}
}
