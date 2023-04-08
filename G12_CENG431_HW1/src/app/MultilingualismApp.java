package app;

import io.FileIO;
import model.*;
import repository.*;
import service.*;

import java.util.*;

public class MultilingualismApp {
	public static void main(String[] args) {
		FileIO languageIO = new FileIO("src/languages.csv");
		FileIO userIO = new FileIO("src/users.csv");
		
		LanguageRepository languageRepository = new LanguageRepository(languageIO);
		UserRepository userRepository = new UserRepository(userIO, languageRepository);
		
		QuestionService questionService = new QuestionService();
		QuizService quizService = new QuizService(questionService);
		UnitService unitService = new UnitService(quizService);
		
		LanguageService languageService = new LanguageService(languageRepository, unitService);
		UserService userService = new UserService(userRepository, languageService);
		
		languageService.initLanguages();
		userService.simulate();
		
		System.out.println("Spanish quiz number: " + languageService.getQuizNumbers(languageService.findByName("SPANISH")));
		System.out.println("Turkish quiz number: " + languageService.getQuizNumbers(languageService.findByName("TURKISH")));
		System.out.println("Italian quiz number: " + languageService.getQuizNumbers(languageService.findByName("ITALIAN")));
		System.out.println("German quiz number: " + languageService.getQuizNumbers(languageService.findByName("GERMAN")));
		
		System.out.println("Italian unit number: " + languageService.getUnitNumbers(languageService.findByName("ITALIAN")));
		
		Language language = languageService.findByName("GERMAN");
		System.out.println(language.toString());
		
		User user = userService.getUserWithMaxPoints();
		System.out.println(user.getUsername() + " " + user.getPoints() + " points");
		
		User advancedUnit = userService.findUserInMostAdvancedUnitInLanguage(language);
		System.out.println(advancedUnit.getUsername() + " " + "Unit " + advancedUnit.getCurrentUnit());
		
		List<User> rankingTable = userRepository.findAllByPoints();
		
		for (User u : rankingTable) {
			System.out.println(u.toString());
		}
	}
}
