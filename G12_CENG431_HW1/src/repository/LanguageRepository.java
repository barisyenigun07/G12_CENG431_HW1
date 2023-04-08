package repository;

import java.util.List;
import java.util.ArrayList;

import io.FileIO;
import model.Language;
import model.LanguageType;
import model.Unit;
import model.Quiz;
import model.Reading;
import model.Listening;
import model.Speaking;
import model.WordMatching;

public class LanguageRepository implements IRepository<Language, String> {
	private FileIO fileIO;
	
	public LanguageRepository(FileIO fileIO) {
		this.fileIO = fileIO;
	}

	@Override
	public List<Language> findAll() {
		List<Language> languages = new ArrayList<>();
		Language currentLanguage = null;
		Unit currentUnit = null;
		Quiz currentQuiz = null;
		String[][] data = fileIO.getData();
		
		for (String[] arrData : data) {
			String languageName = arrData[0];
			LanguageType languageType = findLanguageType(languageName);
			currentLanguage = new Language(languageType);
			languages.add(currentLanguage);
			currentUnit = null;
			currentQuiz = null;
			
			for (int i = 1; i < arrData.length; i++) {
				if (arrData[i].startsWith("Unit")) {
					String unitName = arrData[i].replaceFirst("Unit ", "");
					int unitId = Integer.parseInt(unitName);
					currentUnit = new Unit(unitId);
					currentLanguage.addUnit(currentUnit);
					currentQuiz = null;
				}
				else if (arrData[i].startsWith("Quiz")) {
					String quizName = arrData[i].replaceFirst("Quiz ", "");
					int quizId = Integer.parseInt(quizName);
					currentQuiz = new Quiz(quizId);
					currentUnit.addQuiz(currentQuiz);
					String[] questionData = arrData[i + 1].split(":");
					for (String question : questionData) {
						int questionCount = Integer.parseInt(String.valueOf(question.charAt(0)));
						for (int k = 0; k < questionCount; k++) {
							switch (question.charAt(1)) {
								case 'R':
									currentQuiz.addQuestion(new Reading());
									break;
								case 'L':
									currentQuiz.addQuestion(new Listening());
									break;
								case 'S':
									currentQuiz.addQuestion(new Speaking());
									break;
								case 'W':
									currentQuiz.addQuestion(new WordMatching());
							}
						}
					}
				}
			}
		}
		
		return languages;
	}

	@Override
	public void insert(Language obj) {
		if (!fileIO.fileExists()) {
			fileIO.writeData(obj.toString());
		}
		
	}

	@Override
	public Language findByID(String identifier) {
		List<Language> languages = findAll();
		Language searchedLanguage = null;
		for (Language language : languages) {
			if (language.getLanguageType().name().equals(identifier)) {
				searchedLanguage = language;
				break;
			}
		}
		return searchedLanguage;
	}

	@Override
	public void insertMultiple(List<Language> objs) {
		String strData = "";
		for (Language language : objs) {
			strData += language.toString();
		}
		
		if (!fileIO.fileExists()) {
			fileIO.writeData(strData);
		}
		
	}
	
	private LanguageType findLanguageType(String data) {
		LanguageType languageType = null;
		for (LanguageType lt : LanguageType.values()) {
			if (lt.name().equals(data)){
				languageType = lt;
				break;
			}
		}
		return languageType;
	}
}
