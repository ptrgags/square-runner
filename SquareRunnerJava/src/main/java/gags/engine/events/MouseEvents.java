package gags.engine.events;

import gags.engine.io.MouseButton;

/**
 * Interface for mouse/touch
 * events
 * @author Peter Gagliardi
 */
public interface MouseEvents {

	/**
	 * Called when the mouse enters an object's bounding
	 * box
	 * @param x the x position of the cursor
	 * @param y the y position of the cursor
	 */
	void onMouseEnter(int x, int y);
	
	/**
	 * Called when the mouse moves
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 */
	void onMouseMove(int x, int y);

	/**
	 * Called when the mouse leaves an object's bounding
	 * box
	 * @param x the x position of the cursor
	 * @param y the y position of the cursor
	 */
	void onMouseExit(int x, int y);
	
	/**
	 * Check if the mouse is already within the object's
	 * bounding box
	 * @return if the mouse already is within the object's
	 * bounding box
	 */
	boolean mouseEntered();

	/**
	 * Called when a mouse button is pressed
	 * @param button the button that was pressed
	 * @param x the x position of the cursor
	 * @param y the y position of the cursor
	 */
	void onMousePress(MouseButton button, int x, int y);
	
	/**
	 * Called when a mouse button is released
	 * @param button the button that was pressed
	 * @param x the x position of the cursor
	 * @param y the y position of the cursor
	 */
	void onMouseRelease(MouseButton button, int x, int y);
}
