package com.numbergame.model;

/**
 * Enum class containing allowed answers from the player.
 * 
 * @author Chandru
 *
 */
public enum Answer {
	READY, 
	YES {
		@Override
		public GameState getGameState() {
			return GameState.END;
		}
	},
	END {
		@Override
		public GameState getGameState() {
			return GameState.END;
		}
	},
	HIGHER {
		/**
		 * The Answer is Higher so the generated number is less than the
		 * player's guess. So adjusting the start range. <br>
		 * <code>startRange = currentNumber +1</code>
		 * 
		 * @see com.numbergame.model.Answer#adjustNumberRange(com.numbergame.model.NumberRange)
		 */
		@Override
		public NumberRange adjustNumberRange(NumberRange numberRange) {
			numberRange.adjustStartRange();
			return numberRange;
		}
	},
	LOWER {
		/**
		 * The Answer is Lower so the generated number is higher than the
		 * player's guess. So adjusting the end range. <br>
		 * <code>endRange = currentNumber - 1</code>
		 * 
		 * @see com.numbergame.model.Answer#adjustNumberRange(com.numbergame.model.NumberRange)
		 */
		@Override
		public NumberRange adjustNumberRange(NumberRange numberRange) {
			numberRange.adjustEndRange();
			return numberRange;
		}
	};

	/**
	 * Adjusts the number range based on the current number and player's answer.
	 * On every invocation the range is recalculated and reduced, so the
	 * probability of the guess become high for every adjust. Note: Not all the
	 * answers has to adjust the number range. See the subclass for details.
	 * 
	 * @param numberRange
	 * @return Adjusted NumberRange
	 */
	public NumberRange adjustNumberRange(NumberRange numberRange) {
		return numberRange;
	}

	/**
	 * Returns the the game state associated with the answer. Default is Inprogress
	 * 
	 * @return
	 */
	public GameState getGameState() {
		return GameState.IN_PROGRESS;
	}

	/**
	 * Utility method to validate the player entered answer.
	 * 
	 * @param userInput
	 * @return
	 */
	public static boolean isValidAnswer(String userInput) {
		for (Answer answer : Answer.values()) {
			if (answer.name().equalsIgnoreCase(userInput)) {
				return true;
			}
		}
		return false;
	}
}
