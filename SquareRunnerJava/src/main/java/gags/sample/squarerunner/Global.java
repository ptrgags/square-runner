package gags.sample.squarerunner;

import gags.engine.ommelet.Ommelet;
import gags.engine.resources.ResourceManager;
import gags.lib.util.SaveData;

import java.util.ArrayList;
import java.util.List;

/**
 * The Global class stores 
 * global variables
 * @author Peter Gagliardi
 */
public class Global {

	/** Name of the root node */
	private static final String ROOT_NODE_NAME = "gags.game.squarerunner";
	/** Preference key stub for level unlocked status */
	private static final String KEY_UNLOCKED = "classicUnlocked";
	/** Preference key stub for a highscore */
	private static final String KEY_HIGHSCORE = "classicHighScore";
	
	/** If initialization is complete */
	private static boolean initComplete;
	/** The current level index */
	private static int currentLevel;
	/** The level data */
	private static List<SquareRunnerLevel> levels;
	/** The frame index for the ship colors */
	private static int colorIndex;
	/** Save data for high scores */
	private static SaveData saveData;
	
	/**
	 * Initialize global variables. Subsequent
	 * calls are ignored
	 */
	public static void init() {
		if (!initComplete) {
			levels = new ArrayList<SquareRunnerLevel>();
			saveData = new SaveData(ROOT_NODE_NAME);
			currentLevel = 0;
			colorIndex = 0;
			initComplete = true;
		}
	}
	
	/**
	 * Get the current level
	 * @return the current level
	 */
	public static SquareRunnerLevel getCurrentLevel() {
		return levels.get(currentLevel);
	}
	
	public static void setCurrentLevel(int currentLevel) {
		Global.currentLevel = currentLevel;
	}
	
	public static int getColorIndex() {
		return colorIndex;
	}
	
	public static void setColorIndex(int colorIndex) {
		Global.colorIndex = colorIndex;
	}
	
	public static SaveData getSaveData() {
		return saveData;
	}
	
	/**
	 * Get the number of levels
	 * @return the number of levels
	 */
	public static int getNumLevels() {
		return levels.size();
	}
	
	/**
	 * Get a level
	 * @param index the index of the level
	 * @return the level
	 */
	public static SquareRunnerLevel getLevel(int index) {
		return levels.get(index);
	}
	
	/**
	 * Add a level
	 * @param level the level to add
	 */
	public static void addLevel(SquareRunnerLevel level) {
		Global.levels.add(level);
	}

	/**
	 * Save highscore and level unlocked information
	 */
	public static void saveProgress() {
		for (int i = 0; i < 20; i++) {
			String paddedIndex = pad(i);
			saveData.putInt(KEY_HIGHSCORE + paddedIndex, levels.get(i).getHighscore());
			saveData.putBoolean(KEY_UNLOCKED + paddedIndex, levels.get(i).isUnlocked());
		}
	}
	
	/**
	 * Advance to the next level
	 */
	public static void advanceLevel() {
		currentLevel++;
		if (currentLevel < levels.size())
			getCurrentLevel().setUnlocked(true);
	}
	
	/**
	 * Load levels from the resources
	 */
	public static void loadLevels(ResourceManager resources) {
		for (int i = 0; i < 20; i++) {
			SquareRunnerLevel level = new SquareRunnerLevel();
			String paddedIndex = pad(i);
			Ommelet ommelet = resources.loadOmmelet("omml/", "level" + paddedIndex);
			ommelet = ommelet.getFirstChild();
			level.setTitle(ommelet.getPropertyValue("title"));
			level.setLevelText(ommelet.getText());
			level.setDescription(level.getLevelText().remove(0));
			level.setLevelData(ommelet.getFirstChild());
			level.setUnlocked(saveData.getBoolean(KEY_UNLOCKED + paddedIndex, false));
			level.setHighscore(saveData.getInt(KEY_HIGHSCORE + paddedIndex, 0));
			levels.add(level);
		}
		levels.get(0).setUnlocked(true);
	}
	
	/**
	 * Pad a number with preceding zeroes
	 * if it has less than enough zeroes
	 * @param number the number to pad
	 * @return the padded number
	 */
	private static String pad(int number) {
		if (number < 10)
			return "0" + number;
		else
			return Integer.toString(number);
	}
}
