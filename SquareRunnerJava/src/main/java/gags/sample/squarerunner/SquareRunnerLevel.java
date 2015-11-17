package gags.sample.squarerunner;

import java.util.List;

import gags.engine.ommelet.Ommelet;

/**
 * Square Runner Level
 * @author Peter Gagliardi
 */
public class SquareRunnerLevel {

	/** The highscore for the level */
	private int highscore;
	/** If the level is unlocked */
	private boolean unlocked;
	/** The title of the level */
	private String title;
	/** The level data */
	private Ommelet levelData;
	/** Description of the level */
	private String description;
	/** 
	 * The longer level description 
	 * for tutorial information 
	 */
	private List<String> levelText;
	
	/**
	 * Create a new Square Runner level
	 */
	public SquareRunnerLevel() {
		highscore = 0;
		unlocked = false;
		title = "Level";
	}
	
	public int getHighscore() {
		return highscore;
	}
	
	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}
	
	public boolean isUnlocked() {
		return unlocked;
	}
	
	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Ommelet getLevelData() {
		return levelData;
	}
	
	public void setLevelData(Ommelet levelData) {
		this.levelData = levelData;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<String> getLevelText() {
		return levelText;
	}
	
	public void setLevelText(List<String> levelText) {
		this.levelText = levelText;
	}
	
	
}
