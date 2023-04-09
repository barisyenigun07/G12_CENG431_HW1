package service;

import model.Leaderboard;
import model.League;
import model.Language;

import java.util.*;
import model.User;

public class LeaderboardService {
	private LanguageService languageService;
	private UserService userService;
	
	public LeaderboardService(LanguageService languageService, UserService userService) {
		this.languageService = languageService;
		this.userService = userService;
	}
	
	public Leaderboard generateRubyLeagueOfALanguage(String languageName) {
		List<User> rubyLeagueUsers = new ArrayList<>();
		Language language = languageService.findByName(languageName);
		List<User> usersByLanguage = userService.findAllByLanguageAndSortedByPoints(language);
		for (int i = 0; i < 5; i++) {
			if (usersByLanguage.get(i).getStreakDay() >= 30 && (usersByLanguage.get(i).getPoints() > 5000 || usersByLanguage.get(i).getCurrentUnit() >= 10)) {
				rubyLeagueUsers.add(usersByLanguage.get(i));
			}
		}
		Leaderboard leaderboard = new Leaderboard(language, League.RUBY, rubyLeagueUsers);
		return leaderboard;
	}
	
	public Leaderboard generateSapphireLeagueOfALanguage(String languageName) {
		List<User> sapphireLeagueUsers = new ArrayList<>();
		Language language = languageService.findByName(languageName);
		List<User> usersByLanguage = userService.findAllByLanguageAndSortedByPoints(language);
		for (int i = 0; i < 5; i++) {
			if (usersByLanguage.get(i).getStreakDay() >= 7) {
				sapphireLeagueUsers.add(usersByLanguage.get(i));
			}
		}
		Leaderboard leaderboard = new Leaderboard(language, League.SAPPHIRE, sapphireLeagueUsers);
		return leaderboard;
	}
	
	public Leaderboard generateGoldLeagueOfALanguage(String languageName) {
		List<User> goldLeagueUsers = new ArrayList<>();
		Language language = languageService.findByName(languageName);
		List<User> usersByLanguage = userService.findAllByLanguageAndSortedByPoints(language);
		for (int i = 5; i < 10; i++) {
			goldLeagueUsers.add(usersByLanguage.get(i));
		}
		Leaderboard leaderboard = new Leaderboard(language, League.GOLD, goldLeagueUsers);
		return leaderboard;
	}
	
	public Leaderboard generateSilverLeagueOfALanguage(String languageName) {
		List<User> silverLeagueUsers = new ArrayList<>();
		Language language = languageService.findByName(languageName);
		List<User> usersByLanguage = userService.findAllByLanguageAndSortedByPoints(language);
		for (int i = 10; i < 15; i++) {
			silverLeagueUsers.add(usersByLanguage.get(i));
		}
		Leaderboard leaderboard = new Leaderboard(language, League.SILVER, silverLeagueUsers);
		return leaderboard;
	}
	
	public Leaderboard generateBronzeLeagueOfALanguage(String languageName) {
		List<User> bronzeLeagueUsers = new ArrayList<>();
		Language language = languageService.findByName(languageName);
		List<User> usersByLanguage = userService.findAllByLanguageAndSortedByPoints(language);
		for (int i = 15; i < usersByLanguage.size(); i++) {
			bronzeLeagueUsers.add(usersByLanguage.get(i));
		}
		Leaderboard leaderboard = new Leaderboard(language, League.SILVER, bronzeLeagueUsers);
		return leaderboard;
	}
	
	
}
