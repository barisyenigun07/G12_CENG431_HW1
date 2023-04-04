package model;

import java.util.List;

public class Quiz {
	private int quizNumber;
	private List<Question> questions;
	
	

	public Quiz(int quizNumber, List<Question> questions) {
		
		this.quizNumber = quizNumber;
		this.questions = questions;
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
	
	public String toString() {
		String str = "Quiz " + quizNumber + ",";
		int reading = 0, listening = 0, speaking = 0, wordMatching = 0;
		for (Question question : questions) {
			switch (question.getClass().toString()) {
				case "Reading":
					reading++;
					break;
				case "Listening":
					listening++;
					break;
				case "Speaking":
					speaking++;
					break;
				case "WordMatching":
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
