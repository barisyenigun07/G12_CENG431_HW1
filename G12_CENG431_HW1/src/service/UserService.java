package service;

import model.User;
import model.Language;
import model.Quiz;
import model.Question;
import model.LanguageType;
import model.Unit;
import repository.UserRepository;
import java.util.Random;
import java.util.List;

public class UserService {
	private UserRepository userRepository;
	private LanguageService languageService;
	
	
	public UserService(UserRepository userRepository, LanguageService languageService) {
		this.userRepository = userRepository;
		this.languageService = languageService;
		
	}
	
	public void simulate() {
		List<User> users = userRepository.findAll();
		for (User u : users) {
			simulateUser(u);
		}
		userRepository.insertMultiple(users);
	}
	
	public User getUserWithMaxPoints() {
		List<User> users = userRepository.findAll();
		User userWithMaxPoints = users.get(0);
		
		for (User user : users) {
			if (user.getPoints() > userWithMaxPoints.getPoints()) {
				userWithMaxPoints = user;
			}
		}
		
		return userWithMaxPoints;
	}
	
	public User findUserInMostAdvancedUnitInLanguage(Language language) {
		List<User> usersByLanguage = userRepository.findAllByLanguage(language);
		
		User userInMostAdvancedUnit = usersByLanguage.get(0);
		
		for (User user : usersByLanguage) {
			if (user.getCurrentUnit() > userInMostAdvancedUnit.getCurrentUnit()) {
				userInMostAdvancedUnit = user;
			}
		}
		return userInMostAdvancedUnit;
	}
	
	public List<User> findAllByLanguageAndSortedByPoints(Language language){
		List<User> usersByLanguage = userRepository.findAllByLanguage(language);
		return userRepository.findAllByPoints(usersByLanguage);
	}
	
	private void simulateUser(User user) {
		Random random = new Random();
		LanguageType[] languageTypes = LanguageType.values();
		int randomIndex = random.nextInt(languageTypes.length);
		Language chosenLanguage = languageService.findByName(languageTypes[randomIndex].name());
		int solvedQuizzes = random.nextInt(6, languageService.getQuizNumbers(chosenLanguage) + 1);
		int streakDay = random.nextInt(1, 366);
		List<Quiz> willBeSolvedQuizzes = languageService.getWillBeSolvedQuizzes(chosenLanguage, solvedQuizzes);
		int points = calculateTotalPoints(willBeSolvedQuizzes);
		int currentUnit = findCurrentUnit(chosenLanguage.getUnits(), solvedQuizzes);
		
		user.setChosenLanguage(chosenLanguage);
		user.setSolvedQuizzes(solvedQuizzes);
		user.setCurrentUnit(currentUnit);
		user.setStreakDay(streakDay);
		user.setPoints(points);
		
		
	}
	
	private int calculateTotalPoints(List<Quiz> quizzes) {
		Random random = new Random();
		int points = 0;
		for (Quiz quiz : quizzes) {
			for (Question question : quiz.getQuestions()) {
				boolean isCorrect = random.nextBoolean();
				if (isCorrect) {
					points += question.getPoints();
				}
			}
		}
		return points;
	}
	
	private int findCurrentUnit(List<Unit> units, int solvedQuizzes) {
		int currentUnit = 0;
		for (Unit unit : units) {
			if (solvedQuizzes <= 0) {
				break;
			}
			solvedQuizzes -= unit.getQuizzes().size();
			currentUnit++;
		}
		return currentUnit;
	}
	
}
