package service;

import model.*;
import java.util.*;

public class QuestionService {
	private Reading createReading() {
		return new Reading();
	}
	
	private Listening createListening() {
		return new Listening();
	}
	
	private Speaking createSpeaking() {
		return new Speaking();
	}
	
	private WordMatching createWordMatching() {
		return new WordMatching();
	}
	
	public List<Question> createQuestions(){
		List<Question> questions = new ArrayList<>();
		Random random = new Random();
		int questionNumber = random.nextInt(8, 16);
		for (int i = 0; i < questionNumber; i++) {
			int randNum = random.nextInt(1, 5);
			switch (randNum) {
				case 1:
					questions.add(createReading());
					break;
				case 2:
					questions.add(createListening());
					break;
				case 3:
					questions.add(createSpeaking());
					break;
				case 4:
					questions.add(createWordMatching());
					break;
			}
		}
		return questions;
	}
}
