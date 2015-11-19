package gags.engine.graphics;

/**
 * Defines an object that has a displayable background
 * @author Peter Gagliardi
 */
public interface HasBackground {

	/**
	 * Get the background's x coordinate
	 * @return the background x coordinate
	 */
	int getBgX();
	
	/**
	 * Get the background's y coordinate
	 * @return the background's y coordinate
	 */
	int getBgY();
	
	/**
	 * Get the background's width
	 * @return the background's width
	 */
	int getBgWidth();
	
	/**
	 * Get the background's height
	 * @return the background's height
	 */
	int getBgHeight();
	
	/**
	 * Get the background image
	 * @return the image
	 */
	Background getBackground();
	
	/**
	 * Set the background 
	 * @param background the background
	 */
	void setBackground(Background background);
	
	/**
	 * Get the background color
	 * @return the background color
	 */
	Color getBgColor();
	
	/**
	 * Set the background color
	 * @param color the background color
	 */
	void setBgColor(Color color);
	
	/**
	 * Check if the background color should
	 * be displayed
	 * @return if the background color should be displayed
	 */
	boolean displayBgColor();
	
	/**
	 * Set if the background color should be displayed
	 * @param display if the background color should
	 * be displayed
	 */
	void setDisplayBgColor(boolean display);
	
}
