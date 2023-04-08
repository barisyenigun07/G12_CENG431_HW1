package service;

import model.User;
import model.Language;
import model.Quiz;
import model.Question;
import model.LanguageType;
import model.Unit;
import model.League;
import repository.UserRepository;
import java.util.Random;
import java.util.stream.Collectors;
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
		//List<User> simulatedUsers = users.stream().map(user -> simulateUser(user)).collect(Collectors.toList());
		for (User u : users) {
			simulateUser(u);
		}
		
		for (User u : users) {
			u.setLeague(determineLeague(u));
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
		List<User> users = userRepository.findAll();
		List<User> usersByLanguage = users.stream().filter(user -> user.getChosenLanguage().equals(language)).collect(Collectors.toList());
		
		User userInMostAdvancedUnit = usersByLanguage.get(0);
		
		for (User user : usersByLanguage) {
			if (user.getCurrentUnit() > userInMostAdvancedUnit.getCurrentUnit()) {
				userInMostAdvancedUnit = user;
			}
		}
		return userInMostAdvancedUnit;
	}
	
	private void simulateUser(User user) {
		Random random = new Random();
		LanguageType[] languageTypes = LanguageType.values();
		int randomIndex = random.nextInt(languageTypes.length);
		Language chosenLanguage = languageService.findByName(languageTypes[randomIndex].name());
		int solvedQuizzes = random.nextInt(6, 21);
		int streakDay = random.nextInt(1, 366);
		List<Quiz> willBeSolvedQuizzes = languageService.getQuizzes(chosenLanguage, solvedQuizzes);
		int points = calculateTotalPoints(willBeSolvedQuizzes);
		int currentUnit = findCurrentUnit(chosenLanguage.getUnits(), solvedQuizzes);
		
		user.setChosenLanguage(chosenLanguage);
		user.setSolvedQuizzes(solvedQuizzes);
		user.setCurrentUnit(currentUnit);
		user.setStreakDay(streakDay);
		user.setPoints(points);
		
		
	}
	
	private League determineLeague(User user) {
		List<User> rankingTable = userRepository.findAllByPoints();
		
		League league = League.BRONZE;
		int index = -1;
		for (int i = 0; i < rankingTable.size(); i++) {
			if (rankingTable.get(i).equals(user)) {
				index = i;
				break;
			}
		}
		
		int rank = index + 1;
		System.out.println(user.toString());
		System.out.println(rank);
		
		if (user.getStreakDay() >= 30 && user.getPoints() > 5000) {
			//System.out.println(user.toString());
			league = League.RUBY;
		}
		
		else if (user.getStreakDay() >= 7 && rank <= 5) {
			league = League.SAPPHIRE;
		}
		
		else if (rank <= 10) {
			league = League.GOLD;
		}
		
		else if (rank <= 15) {
			league = League.SILVER;
		}
		
		return league;
		
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
