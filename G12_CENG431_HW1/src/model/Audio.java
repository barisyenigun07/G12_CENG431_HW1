package model;

import java.util.*;

public class Audio {
	private int length;
	
	public Audio() {
		Random random = new Random();
		int len = random.nextInt(1, 240);
		length = len;
	}
	
	public int getLength() {
		return length;
	}
}
