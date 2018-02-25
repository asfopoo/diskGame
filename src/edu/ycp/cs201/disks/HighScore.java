package edu.ycp.cs201.disks;

public class HighScore {
	
	private String initials;
	private int score;
	
	public HighScore(String initials, int score) {
		this.setInitials(initials);
		this.setScore(score);
	}

	public String getInitials() {
		
		return initials;
	}
	
	public int getScore() {
		
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}
	
}
