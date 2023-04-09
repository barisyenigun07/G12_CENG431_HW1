package util;

import java.util.*;

public class QuestionUtil {
	public static String generateString() {
		Random random = new Random();
		String chars = "abcdefghijklmnopqrstuywxyz";
		String str = "";
		int length = random.nextInt(1, 11);
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(chars.length());
			str += String.valueOf(chars.charAt(index));
		}
		return str;
	}
}
