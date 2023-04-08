package repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;

import io.FileIO;
import model.User;
import model.Language;
import model.League;

public class UserRepository implements IRepository<User, String>{
	private FileIO fileIO;
	private LanguageRepository languageRepository;
	
	public UserRepository(FileIO fileIO, LanguageRepository languageRepository) {
		this.fileIO = fileIO;
		this.languageRepository = languageRepository;
	}

	@Override
	public List<User> findAll() {
		String[][] data = fileIO.getData();
		List<User> users = new ArrayList<>();
		for (String[] arrData : data) {
			User user = new User();
			String username = arrData[0];
			String password = arrData[1];
			user.setUsername(username);
			user.setPassword(password);
			if (arrData.length > 2) {
				String language = arrData[2];
				Language chosenLanguage = languageRepository.findByID(language);
				user.setChosenLanguage(chosenLanguage);
				String leagueName = arrData[3];
				League league = findLeague(leagueName);
				user.setLeague(league);
				int streakDay = Integer.parseInt(arrData[4]);
				user.setStreakDay(streakDay);
				int currentUnit = Integer.parseInt(arrData[5]);
				user.setCurrentUnit(currentUnit);
				int solvedQuizzes = Integer.parseInt(arrData[6]);
				user.setSolvedQuizzes(solvedQuizzes);
				int points = Integer.parseInt(arrData[7]);
				user.setPoints(points);
			}
			users.add(user);
		}
		return users;
	}
	
	public List<User> findAllByPoints(){
		List<User> users = findAll();
		return users.stream().sorted(Comparator.comparing(User::getPoints).reversed()).collect(Collectors.toList());
	}

	@Override
	public void insert(User obj) {
		fileIO.writeData(obj.toString());
	}

	@Override
	public User findByID(String identifier) {
		List<User> users = findAll();
		User searchedUser = null;
		for (User user : users) {
			if (user.getUsername().equals(identifier)) {
				searchedUser = user;
				break;
			}
		}
		return searchedUser;
	}

	@Override
	public void insertMultiple(List<User> objs) {
		String strData = "";
		for (User user : objs) {
			strData += user.toString();
		}
		fileIO.writeData(strData);
	}
	
	private League findLeague(String leagueName) {
		League[] leagues = League.values();
		League league = null;
		
		for (League l : leagues) {
			if (l.name().equals(leagueName)) {
				league = l;
				break;
			}
		}
		return league;
	}
	
}
