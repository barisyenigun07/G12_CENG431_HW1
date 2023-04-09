package model;

public class Speaking extends Question{
	private Audio audio1 = new Audio();
	private Audio audio2 = new Audio();
	
	public Audio getAudio1() {
		return audio1;
	}
	
	public Audio getAudio2() {
		return audio2;
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return 8;
	}
	
}
