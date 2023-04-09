package service;

import repository.LanguageRepository;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


import model.Language;
import model.LanguageType;
import model.Unit;
import model.Quiz;

public class LanguageService {
	private LanguageRepository languageRepository;
	private UnitService unitService;
	
	public LanguageService(LanguageRepository languageRepository, UnitService unitService) {
		this.languageRepository = languageRepository;
		this.unitService = unitService;
	}
	
	public void initLanguages() {
		Language spanish = new Language(LanguageType.SPANISH);
		Language turkish = new Language(LanguageType.TURKISH);
		Language italian = new Language(LanguageType.ITALIAN);
		Language german = new Language(LanguageType.GERMAN);
		
		spanish.setUnits(unitService.createUnits());
		turkish.setUnits(unitService.createUnits());
		italian.setUnits(unitService.createUnits());
		german.setUnits(unitService.createUnits());
		
		List<Language> languages = Arrays.asList(spanish, turkish, italian, german);
		
		languageRepository.insertMultiple(languages);
	}
	
	public Language findByName(String name) {
		return languageRepository.findByID(name);
	}
	
	
	
	public int getQuizNumbers(Language language) {
		
		return getAllQuizzesOfALanguage(language).size();
	}
	
	public Language getLanguageWithMaximumNumberOfUnits() {
		List<Language> languages = languageRepository.findAll();
		Language languageWithMaximumNumUnits = languages.get(0);
		
		for (int i = 0; i < languages.size(); i++) {
			if (languages.get(i).getUnits().size() > languageWithMaximumNumUnits.getUnits().size()) {
				languageWithMaximumNumUnits = languages.get(i);
			}
		}
		
		return languageWithMaximumNumUnits;
		
	}
	
	public Language getLanguageWithMaximumNumberOfQuizzes() {
		List<Language> languages = languageRepository.findAll();
		Language languageWithMaximumNumQuizzes = languages.get(0);
		
		for (int i = 0; i < languages.size(); i++) {
			if (getQuizNumbers(languages.get(i)) > getQuizNumbers(languageWithMaximumNumQuizzes)) {
				languageWithMaximumNumQuizzes = languages.get(i);
			}
		}
		return languageWithMaximumNumQuizzes;
	}
	
	public List<Quiz> getAllQuizzesOfALanguage(Language language) {
		List<Quiz> allQuizzes = new ArrayList<>();
		List<Unit> unitsOfLanguage = language.getUnits();
		for (Unit unit : unitsOfLanguage) {
			for (Quiz quiz : unit.getQuizzes()) {
				allQuizzes.add(quiz);
			}
		}
		return allQuizzes;
	}
	
	public List<Quiz> getWillBeSolvedQuizzes(Language language, int quizNum){
		
		List<Quiz> allQuizzes = getAllQuizzesOfALanguage(language);
		List<Quiz> willBeSolvedQuizzes = new ArrayList<>();
		
		int i = 0;
		
		while (i < quizNum) {
			willBeSolvedQuizzes.add(allQuizzes.get(i));
			i++;
		}
		
		return willBeSolvedQuizzes;
	}
	
	
	
}
