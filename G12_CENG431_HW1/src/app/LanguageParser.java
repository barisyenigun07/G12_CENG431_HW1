package app;

import java.util.List;
import java.util.ArrayList;


import model.Language;
import model.LanguageType;
import model.Unit;
import model.Quiz;
import model.Reading;
import model.Speaking;
import model.Listening;
import model.WordMatching;

public class LanguageParser {
	
	private String data;

	public LanguageParser(String data) {
		this.data = data;
	}
	
	public List<Language> Parse(){
		String lines[] = this.data.split("\\r?\\n");
		
		List<Language> languages = new ArrayList<>();
        Language currentLanguage = null;
        Unit currentUnit = null;
        Quiz currentQuiz = null;
		
		for(String line: lines) {
			String[] parts = line.split(",");
			 
	        String languageName = parts[0];
    	 	LanguageType languageType = this.parseLanguageType(languageName);
            currentLanguage = new Language(languageType);
            languages.add(currentLanguage);
            currentUnit = null;
            currentQuiz = null;
	            
            for (int i = 1; i < parts.length; i++) {
                String part = parts[i];

                if (part.startsWith("Unit")) {
                    String unitName = part.replaceFirst("Unit ","");
                    int unitID = Integer.parseInt(unitName);
                    currentUnit = new Unit(unitID);
                    currentLanguage.addUnit(currentUnit);
                    currentQuiz = null;
                } else if (part.startsWith("Quiz")) {
                    String quizName = part.replaceFirst("Quiz ","");
                    int quizID = Integer.parseInt(quizName);
                    currentQuiz = new Quiz(quizID);
                    currentUnit.addQuiz(currentQuiz);

                    String[] questionParts = parts[i + 1].split(":");
                    for (String questionPart: questionParts) {
                    	int questionCount = Integer.parseInt(String.valueOf(questionPart.charAt(0)));
                    	
                        for (int k = 0; k < questionCount; k++) {
                        	switch (questionPart.charAt(1)) {
                        	case 'R':
                        		currentQuiz.addQuestion(new Reading());
                        		break;
                        	case 'S':
                        		currentQuiz.addQuestion(new Speaking());
                        		break;
                        	case 'L':
                        		currentQuiz.addQuestion(new Listening());
                        		break;
                        	case 'W':
                        		currentQuiz.addQuestion(new WordMatching());
                        		break;
                        	}
                        }
                    }
                }
            }

		}
		
		return languages;
	}
	
	private LanguageType parseLanguageType(String data) {
		for (LanguageType type : LanguageType.values()) {
			if (type.name().toLowerCase().equals(data)) {
				return type;
			}
		}
		
		return null;
	}
}

