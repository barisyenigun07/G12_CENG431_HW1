package model;

import java.util.List;

public class Unit {
	private int unitNumber;
	private List<Quiz> quizzes;
	
	
	
	public Unit(int unitNumber, List<Quiz> quizzes) {
		
		this.unitNumber = unitNumber;
		this.quizzes = quizzes;
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
	
	public String toString() {
		String str = "Unit " + unitNumber + ",";
		for (Quiz quiz : quizzes) {
			str += quiz.toString() + ",";
		}
		return str;
	}
	
}
