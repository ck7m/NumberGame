package com.numbergame.model;

public enum Answer {
	READY,
	YES{
		@Override
		public GameState getGameState() {
			return GameState.END;
		}		
	},END{
		@Override
		public GameState getGameState() {
			return GameState.END;
		}		
	},
	HIGHER{
		@Override
		public NumberRange adjustNumberRange(NumberRange numberRange) {
			numberRange.adjustStartRange();
			return numberRange;
		}		
	},
	LOWER{
		@Override
		public NumberRange adjustNumberRange(NumberRange numberRange) {
			numberRange.adjustEndRange();
			return numberRange;
		}
	};
	
	public NumberRange adjustNumberRange(NumberRange numberRange){
		return numberRange;			
	}
	
	public GameState getGameState(){
		return GameState.IN_PROGRESS;			
	}
	public static boolean isValidAnswer(String input){
		for(Answer answer:Answer.values()){
			if (answer.name().equalsIgnoreCase(input)){
				return true;			
			}
		}
		return false;
	}
}
