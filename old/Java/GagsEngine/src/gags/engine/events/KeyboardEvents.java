package gags.engine.events;

import gags.engine.io.Key;

/**
 * Defines methods for keyboard input
 * @author Peter Gagliardi
 */
public interface KeyboardEvents {

	/**
	 * Called when a key is pressed
	 * @param key the key that was pressed
	 */
	void onKeyPress(Key key);
	
	/**
	 * Called when a key is released
	 * @param key the key that was released
	 */
	void onKeyRelease(Key key);
}
