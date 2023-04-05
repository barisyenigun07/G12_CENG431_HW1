package service;

import repository.LanguageRepository;

import java.util.Arrays;
import java.util.List;

import model.Language;
//import model.LanguageType;

public class LanguageService {
	private LanguageRepository languageRepository;
	private UnitService unitService;
	
	public LanguageService(LanguageRepository languageRepository, UnitService unitService) {
		this.languageRepository = languageRepository;
		this.unitService = unitService;
	}
	
	public void initLanguages() {
		//Language spanish = new Language(LanguageType.SPANISH, unitService.createUnits());
		//Language turkish = new Language(LanguageType.TURKISH, unitService.createUnits());
		//Language italian = new Language(LanguageType.ITALIAN, unitService.createUnits());
		//Language german = new Language(LanguageType.GERMAN, unitService.createUnits());
		
		//List<Language> languages = Arrays.asList(spanish, turkish, italian, german);
		
		//languageRepository.insertMultiple(languages);
	}
}
