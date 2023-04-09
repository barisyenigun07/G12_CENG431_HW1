package model;

import util.QuestionUtil;

public class Reading extends Question {
	private String englishStr = QuestionUtil.generateString();
	private String translatedStr = QuestionUtil.generateString();
	
	public String getEnglishStr() {
		return englishStr;
	}
	
	public String getTranslatedStr() {
		return translatedStr;
	}


	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return 10;
	}

}
