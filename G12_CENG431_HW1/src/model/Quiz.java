package model;

import java.util.List;
import java.util.ArrayList;

public class Quiz {
	private int quizNumber;
	private List<Question> questions;
	
	

	public Quiz(int quizNumber) {
		this.questions = new ArrayList<>();
		this.quizNumber = quizNumber;
	}
	
	
	public int getQuizNumber() {
		return quizNumber;
	}

	public void setQuizNumber(int quizNumber) {
		this.quizNumber = quizNumber;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(Question question) {
		this.questions.add(question);
	}
	
	public String toString() {
		String str = "Quiz " + quizNumber + ",";
		int reading = 0, listening = 0, speaking = 0, wordMatching = 0;
		for (Question question : questions) {
			switch (question.getClass().getName()) {
				case "model.Reading":
					reading++;
					break;
				case "model.Listening":
					listening++;
					break;
				case "model.Speaking":
					speaking++;
					break;
				case "model.WordMatching":
					wordMatching++;
					break;
			}
		}
		str += ((reading > 0 ) ? reading + "R:" : "") + 
				((listening > 0) ? listening + "L:" : "") + 
				((speaking > 0) ? speaking + "S:" : "") + 
				((wordMatching > 0) ? wordMatching + "W" : "");
		return str;
	}
}
