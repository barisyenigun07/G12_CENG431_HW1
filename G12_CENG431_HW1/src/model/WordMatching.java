package model;

import java.util.*;
import util.QuestionUtil;

public class WordMatching extends Question {
	private Map<String, String> matchingStrings;
	
	public WordMatching() {
		Map<String, String> map = new HashMap<>();
		Random random = new Random();
		int count = random.nextInt(4, 9);
		for (int i = 0; i < count; i++) {
			map.put(QuestionUtil.generateString(), QuestionUtil.generateString());
		}
		matchingStrings = map;
	}
	
	public Map<String, String> getMatchingStrings(){
		return matchingStrings;
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return 5;
	}

}
