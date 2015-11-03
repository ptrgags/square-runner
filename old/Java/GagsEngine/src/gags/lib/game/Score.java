package gags.lib.game;

/**
 * Object for handling a score
 * with a highscore or other similar
 * value for a game
 * @author Peter Gagliardi
 */
public class Score {
	/** Score */
	private int value;
	/** High score */
	private int highValue;
	/** Minimum value */
	private int min;

	/**
	 * Create a new Score object
	 */
	public Score() {
		highValue = 0;
		min = 0;
		value = min;
	}
	
	/**
	 * Create a new Score object,
	 * specifying the high score
	 * @param highscore the existing high
	 * score
	 */
	public Score(int highscore) {
		this.highValue = highscore;
		min = 0;
		value = min;
	}
	
	/**
	 * Create a new Score object,
	 * specifiying a high score
	 * and a minimum value
	 * @param highscore the high score
	 * @param min the minimum value
	 */
	public Score(int highscore, int min) {
		this.highValue = Math.max(highscore, min);
		this.min = min;
		value = min;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getHighValue() {
		return highValue;
	}
	
	public void setHighValue(int highValue) {
		this.highValue = highValue;
	}
	
	public void setMin(int min) {
		this.min = min;
		value = min;
	}
	
	/**
	 * Increment the score by one
	 */
	public void increment() {
		increment(1);
	}
	
	/**
	 * Increase the score
	 * @param amount the amount to increase the score by
	 */
	public void increment(int amount) {
		value += amount;
		if (value > highValue)
			highValue = value;
	}
	
	/**
	 * Decrement the score by one
	 */
	public void decrement() {
		decrement(1);
	}
	
	/**
	 * Decrease the score
	 * @param amount the amount to decrease the score by
	 */
	public void decrement(int amount) {
		value -= amount;
		if (value < min)
			value = min;
	}
}
