package service;

import model.User;
import repository.UserRepository;

public class UserService {
	private UserRepository userRepository;
	private LanguageService languageService;
	
	public UserService(UserRepository userRepository, LanguageService languageService) {
		this.userRepository = userRepository;
		this.languageService = languageService;
	}
	
	public void simulateQuizSolving() {
		User user = userRepository.findByID(null);
		
	}
	
}
