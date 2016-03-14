package com.numbergame.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberRangeTest {

	@Test
	public void testInitialValues() {
		NumberRange numberRange = new NumberRange(1,100);
		assertEquals(1,numberRange.getStart());
		assertEquals(100,numberRange.getEnd());
		assertEquals(100,numberRange.getMaxValue());
		assertEquals(0,numberRange.getCurrentValue());
	}
	
	@Test
	public void testAdjustStart() {
		NumberRange numberRange = new NumberRange(1,100);
		numberRange.setCurrentValue(40);
		assertEquals(1,numberRange.getStart());
		assertEquals(100,numberRange.getEnd());
		assertEquals(100,numberRange.getMaxValue());
		numberRange.adjustStartRange();
		assertEquals(41,numberRange.getStart());
		assertEquals(100,numberRange.getEnd());
		assertEquals(100,numberRange.getMaxValue());
		assertEquals(40,numberRange.getCurrentValue());
	}
	
	@Test
	public void testAdjustEnd() {
		NumberRange numberRange = new NumberRange(1,100);
		numberRange.setCurrentValue(40);
		assertEquals(1,numberRange.getStart());
		assertEquals(100,numberRange.getEnd());
		assertEquals(100,numberRange.getMaxValue());
		numberRange.adjustEndRange();
		assertEquals(1,numberRange.getStart());
		assertEquals(39,numberRange.getEnd());
		assertEquals(100,numberRange.getMaxValue());
		assertEquals(40,numberRange.getCurrentValue());
	}

}
