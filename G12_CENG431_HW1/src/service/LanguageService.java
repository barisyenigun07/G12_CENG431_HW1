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
	
	public int getUnitNumbers(Language language) {
		return language.getUnits().size();
	}
	
	public int getQuizNumbers(Language language) {
		int quizNumbers = 0;
		List<Unit> units = language.getUnits();
		for (Unit unit : units) {
			quizNumbers += unit.getQuizzes().size();
		}
		return quizNumbers;
	}
	
	public List<Quiz> getQuizzes(Language language, int quizNum){
		List<Quiz> allQuizzes = new ArrayList<>();
		List<Quiz> willBeSolvedQuizzes = new ArrayList<>();
		List<Unit> units = language.getUnits();
		for (Unit unit : units) {
			for (Quiz quiz : unit.getQuizzes()) {
				allQuizzes.add(quiz);
			}
		}
		
		int i = 0;
		
		while (i < quizNum) {
			willBeSolvedQuizzes.add(allQuizzes.get(i));
			i++;
		}
		
		return willBeSolvedQuizzes;
	}
	
}
