package model;

public class User {
	private String username;
	private String password;
	private Language chosenLanguage;
	private League league = League.BRONZE;
	private int streakDay;
	private int currentUnit;
	private int solvedQuizzes;
	private int points;
	
	public User() {
		
	}

	public User(String username, String password, Language chosenLanguage, League league,int streakDay, int currentUnit, int solvedQuizzes, int points) {
		
		this.username = username;
		this.password = password;
		this.chosenLanguage = chosenLanguage;
		this.league = league;
		this.streakDay = streakDay;
		this.currentUnit = currentUnit;
		this.solvedQuizzes = solvedQuizzes;
		this.points = points;
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
	
	public League getLeague() {
		return league;
	}
	
	public void setLeague(League league) {
		this.league = league;
	}
	
	public int getStreakDay() {
		return streakDay;
	}
	
	public void setStreakDay(int streakDay) {
		this.streakDay = streakDay;
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
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public String toString() {
		return username + "," + password + "," + chosenLanguage.getLanguageType().name() + "," + league.name() + "," + streakDay + "," + currentUnit + "," + solvedQuizzes + "," + points + "\n";
	}
	
	public boolean equals(Object o) {
		User that = (User) o;
		return this.username.equals(that.username);
	}
	
}
