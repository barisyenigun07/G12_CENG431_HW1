package model;

import util.QuestionUtil;

public class Listening extends Question{
	private String str = QuestionUtil.generateString();
	private Audio audio = new Audio();
	
	public String getStr() {
		return str;
	}
	
	public Audio getAudio() {
		return audio;
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return 7;
	}

}
