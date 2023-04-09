package api;

import io.*;
import model.*;
import repository.*;
import service.*;

import java.util.*;

public class API {
	private FileIO userIO;
	private FileIO languageIO;
	private LanguageRepository languageRepository;
	private UserRepository userRepository;
	private QuestionService questionService;
	private QuizService quizService;
	private UnitService unitService;
	private UserService userService;
	private LanguageService languageService;
	private LeaderboardService leaderboardService;
	
	public API() {
		this.userIO = new FileIO("src/users.csv");
		this.languageIO = new FileIO("src/languages.csv");
		this.languageRepository = new LanguageRepository(languageIO);
		this.userRepository = new UserRepository(userIO, languageRepository);
		this.questionService = new QuestionService();
		this.quizService = new QuizService(questionService);
		this.unitService = new UnitService(quizService);
		this.languageService = new LanguageService(languageRepository, unitService);
		this.userService = new UserService(userRepository, languageService);
		this.leaderboardService = new LeaderboardService(languageService, userService);
		
		languageService.initLanguages();
		userService.simulate();
	}
	
	public String getUserWithMaximumPoints() {
		User maximumPointUser = userService.getUserWithMaxPoints();
		return maximumPointUser.getUsername() + " " + maximumPointUser.getPoints() + " points";  
	}
	
	public String getUserInTheMostAdvancedUnitInGerman() {
		Language language = languageService.findByName("GERMAN");
		User userInTheMostAdvancedUnitInGerman = userService.findUserInMostAdvancedUnitInLanguage(language);
		return userInTheMostAdvancedUnitInGerman.getUsername() + " " + "Unit " + userInTheMostAdvancedUnitInGerman.getCurrentUnit(); 
	}
	
	public String getLanguageWithMaximumNumberOfUnits() {
		Language languageWithMaxUnits = languageService.getLanguageWithMaximumNumberOfUnits();
		return languageWithMaxUnits.getLanguageType().name() + " " + languageWithMaxUnits.getUnits().size() + " Units";
	}
	
	public String getLanguageWithMaximumNumberOfQuizzes() {
		Language languageWithMaxQuizzes = languageService.getLanguageWithMaximumNumberOfQuizzes();
		return languageWithMaxQuizzes.getLanguageType().name() + " " + languageService.getQuizNumbers(languageWithMaxQuizzes) + " Quizzes";
	}
	
	public String getTopThreeOfItalianSilverLeague() {
		Leaderboard italianSilverLeague = leaderboardService.generateSilverLeagueOfALanguage("ITALIAN");
		List<User> silverUsersList = italianSilverLeague.getUsers();
		String str = "Italian Silver League Top 3: ";
		for (int i = 0; i < 3; i++) {
			str += (i + 1) + "." + silverUsersList.get(i).getUsername() + " ";
		}
		return str;
	}
	
	public void displayQueries() {
		System.out.println("1- " + getUserWithMaximumPoints());
		System.out.println("2- " + getUserInTheMostAdvancedUnitInGerman());
		System.out.println("3- " + getLanguageWithMaximumNumberOfUnits());
		System.out.println("4- " + getLanguageWithMaximumNumberOfQuizzes());
		System.out.println("5- " + getTopThreeOfItalianSilverLeague());
	}
}
