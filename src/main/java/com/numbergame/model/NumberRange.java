package com.numbergame.model;

public class NumberRange {
	private int currentValue;
	private int maxValue;
	
	public NumberRange(int startValue,int maxValue) {
		this.maxValue = maxValue;
		this.start=startValue;
		this.end=this.maxValue;
	}
	private int start;
	private int end;
	
	public int getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}
	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
	public int getMaxValue() {
		return maxValue;
	}
	
	public void adjustEndRange(){
		this.end = this.currentValue-1;
	}
	
	public void adjustStartRange(){
		this.start = this.currentValue+1;
	}
	
}
