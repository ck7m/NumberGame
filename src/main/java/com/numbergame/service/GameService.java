package com.numbergame.service;

import com.numbergame.model.Answer;
import com.numbergame.model.GameState;

public interface GameService {

	int guessNumber(Answer answer);
	GameState validateAnswer(Answer answer);
}
