package model;

public class User {
	private String username;
	private String password;
	private Language chosenLanguage;
	private int currentUnit;
	private int solvedQuizzes;
	
	public User() {
		
	}

	public User(String username, String password, Language chosenLanguage, int currentUnit, int solvedQuizzes) {
		
		this.username = username;
		this.password = password;
		this.chosenLanguage = chosenLanguage;
		this.currentUnit = currentUnit;
		this.solvedQuizzes = solvedQuizzes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Language getChosenLanguage() {
		return chosenLanguage;
	}

	public void setChosenLanguage(Language chosenLanguage) {
		this.chosenLanguage = chosenLanguage;
	}

	public int getCurrentUnit() {
		return currentUnit;
	}

	public void setCurrentUnit(int currentUnit) {
		this.currentUnit = currentUnit;
	}

	public int getSolvedQuizzes() {
		return solvedQuizzes;
	}

	public void setSolvedQuizzes(int solvedQuizzes) {
		this.solvedQuizzes = solvedQuizzes;
	}
	
	public String toString() {
		return username + "," + password + "," + chosenLanguage.getLanguageType() + "," + currentUnit + "," + solvedQuizzes + "\n";
	}
	
}
