package model;

import java.util.*;

public class Leaderboard {
	private Language language;
	private League league;
	private List<User> users;
	
	public Leaderboard(Language language, League league, List<User> users) {
		this.language = language;
		this.league = league;
		this.users = users;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
