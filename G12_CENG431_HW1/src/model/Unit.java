package model;

import java.util.List;
import java.util.ArrayList;

public class Unit {
	private int unitNumber;
	private List<Quiz> quizzes;
	
	
	
	public Unit(int unitNumber) {
		this.quizzes = new ArrayList<>();
		this.unitNumber = unitNumber;
	}
	
	

	public int getUnitNumber() {
		return unitNumber;
	}



	public void setUnitNumber(int unitNumber) {
		this.unitNumber = unitNumber;
	}

	public List<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
	
	public void addQuiz(Quiz quiz) {
		this.quizzes.add(quiz);
	}
	
	
	public String toString() {
		String str = "Unit " + unitNumber + ",";
		for (Quiz quiz : quizzes) {
			str += quiz.toString() + ",";
		}
		return str;
	}
	
}
