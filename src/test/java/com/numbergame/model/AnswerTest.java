package com.numbergame.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AnswerTest {

	@Test
	public void testIsValidAnswer_lowercase() {
		assertTrue(Answer.values().length==5);
		assertTrue(Answer.isValidAnswer("ready"));
		assertTrue(Answer.isValidAnswer("yes"));
		assertTrue(Answer.isValidAnswer("higher"));
		assertTrue(Answer.isValidAnswer("lower"));
		assertTrue(Answer.isValidAnswer("end"));
	}
	
	@Test
	public void testIsValidAnswer_uppercase() {
		assertTrue(Answer.isValidAnswer("READY"));
		assertTrue(Answer.isValidAnswer("YES"));
		assertTrue(Answer.isValidAnswer("HIGHER"));
		assertTrue(Answer.isValidAnswer("LOWER"));
		assertTrue(Answer.isValidAnswer("END"));
	}
	
	@Test
	public void testIsValidAnswer_mixedcase() {
		assertTrue(Answer.isValidAnswer("ReaDy"));
		assertTrue(Answer.isValidAnswer("yES"));
		assertTrue(Answer.isValidAnswer("hIGher"));
		assertTrue(Answer.isValidAnswer("loWEr"));
		assertTrue(Answer.isValidAnswer("enD"));
	}
	
	@Test
	public void testIsValidAnswer_randomInput() {
		assertFalse(Answer.isValidAnswer("abcd"));
		assertFalse(Answer.isValidAnswer("12345"));
		assertFalse(Answer.isValidAnswer("Start"));		
	}
	
	@Test
	public void testAdjustNumberRange_Higher(){
		NumberRange numberRange = new NumberRange(1,100);
		numberRange.setCurrentValue(50);
		assertEquals(1,numberRange.getStart());
		assertEquals(100,numberRange.getEnd());
		Answer.HIGHER.adjustNumberRange(numberRange);
		assertEquals(51,numberRange.getStart());
		assertEquals(100,numberRange.getEnd());		
	}
	
	@Test
	public void testAdjustNumberRange_Lower(){
		NumberRange numberRange = new NumberRange(1,100);
		numberRange.setCurrentValue(50);
		assertEquals(1,numberRange.getStart());
		assertEquals(100,numberRange.getEnd());
		Answer.LOWER.adjustNumberRange(numberRange);
		assertEquals(1,numberRange.getStart());
		assertEquals(49,numberRange.getEnd());		
	}
	
	@Test
	public void testAdjustNumberRange_other(){
		NumberRange numberRange = new NumberRange(1,100);
		numberRange.setCurrentValue(50);
		assertEquals(1,numberRange.getStart());
		assertEquals(100,numberRange.getEnd());
		Answer.YES.adjustNumberRange(numberRange);
		assertEquals(1,numberRange.getStart());
		assertEquals(100,numberRange.getEnd());		
	}
}
