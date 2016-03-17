package com.numbergame.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.numbergame.constants.GameConstants;
import com.numbergame.model.Answer;
import com.numbergame.model.GameState;
import com.numbergame.service.GameService;

@RunWith(MockitoJUnitRunner.class)
public class NumberGuessGameControllerTest {
	@Spy
	NumberGuessGameController gameController;
	@Mock GameService gameService;
	
	@Before
	public void setUp(){
		System.setOut(mock(PrintStream.class));
		gameService = mock(GameService.class);
		gameController.setGameService(gameService);
	}
	
	@After
	public void tearDown(){
		System.setOut(null);
	}

	@Test
	public void testPlay_normalflow() {
		doReturn("ready").doReturn("higher").doReturn("lower").doReturn("yes").when(gameController).readInput(Matchers.<Scanner>any());
		doReturn(30).doReturn(68).doReturn(40).when(gameService).guessNumber(Matchers.any());
		doReturn(GameState.END).when(gameService).determineGameState(Matchers.eq(Answer.YES));
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		gameController.play(Matchers.<Scanner>any());
		verify(gameController,(times(4))).readInput(Matchers.<Scanner>any());
		verify(gameService,times(3)).guessNumber(Matchers.any(Answer.class));
		verify(System.out, times(3)).println(argument.capture());
		List<String> argumentValues = argument.getAllValues();
		assertTrue(argumentValues.size()==3);
		assertEquals(String.format(GameConstants.GUESS_MSG,30), argumentValues.get(0));
		assertEquals(String.format(GameConstants.GUESS_MSG,68), argumentValues.get(1));
		assertEquals(String.format(GameConstants.GUESS_MSG,40), argumentValues.get(2));
	}
	
	@Test
	public void testPlay_userTerminted() {
		doReturn("ready").doReturn("higher").doReturn("end").doReturn("yes").when(gameController).readInput(Matchers.<Scanner>any());
		doReturn(30).doReturn(68).doReturn(40).when(gameService).guessNumber(Matchers.any());
		doReturn(GameState.END).when(gameService).determineGameState(Matchers.eq(Answer.END));
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		gameController.play(Matchers.<Scanner>any());
		verify(gameController,(times(3))).readInput(Matchers.<Scanner>any());
		verify(gameService,times(2)).guessNumber(Matchers.any(Answer.class));
		verify(System.out, times(2)).println(argument.capture());
		List<String> argumentValues = argument.getAllValues();
		assertTrue(argumentValues.size()==2);
		assertEquals(String.format(GameConstants.GUESS_MSG,30), argumentValues.get(0));
		assertEquals(String.format(GameConstants.GUESS_MSG,68), argumentValues.get(1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPlay_failedInBetween() {
		doReturn("ready").doReturn("higher").doReturn("lower").doReturn("yes").when(gameController).readInput(Matchers.<Scanner>any());
		doReturn(30).doReturn(68).doThrow(IllegalArgumentException.class).when(gameService).guessNumber(Matchers.any());
		doReturn(GameState.END).when(gameService).determineGameState(Matchers.eq(Answer.YES));
		gameController.play(Matchers.<Scanner>any());		
	}

	@Test
	public void testStart() {
		doNothing().when(gameController).play(Matchers.<Scanner>any());
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		gameController.start();
		verify(System.out, times(2)).println(argument.capture());
		List<String> argumentValues = argument.getAllValues();
		assertTrue(argumentValues.size()==2);
		assertEquals(GameConstants.WELCOME_MSG, argumentValues.get(0));
		assertEquals(GameConstants.THANK_YOU_MSG, argumentValues.get(1));		
	}
	

	@Test
	public void testStart_ErrorOnPlay() {
		doThrow(IllegalArgumentException.class).when(gameController).play(Matchers.<Scanner>any());
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		gameController.start();
		verify(System.out, times(3)).println(argument.capture());
		List<String> argumentValues = argument.getAllValues();
		assertTrue(argumentValues.size()==3);
		assertEquals(GameConstants.WELCOME_MSG, argumentValues.get(0));
		assertEquals(GameConstants.ERROR_MSG, argumentValues.get(1));
		assertEquals(GameConstants.THANK_YOU_MSG, argumentValues.get(2));
	}
	
	@Test
	public void testStart_ErrorWhileReading() {
		doThrow(IllegalStateException.class).when(gameController).readInput(Matchers.<Scanner>any());
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		gameController.start();
		verify(System.out, times(3)).println(argument.capture());
		List<String> argumentValues = argument.getAllValues();
		assertTrue(argumentValues.size()==3);
		assertEquals(GameConstants.WELCOME_MSG, argumentValues.get(0));
		assertEquals(GameConstants.ERROR_MSG, argumentValues.get(1));
		assertEquals(GameConstants.THANK_YOU_MSG, argumentValues.get(2));
	}

}
