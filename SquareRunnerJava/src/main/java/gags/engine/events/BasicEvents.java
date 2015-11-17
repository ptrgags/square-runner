package gags.engine.events;

import gags.engine.graphics.Painter;

/**
 * Basic GagsEngine events
 * @author Peter Gagliardi
 */
public interface BasicEvents {

	/**
	 * Called when the object is created
	 */
	void onCreate();
	
	/**
	 * Called once every frame
	 */
	void onFrame();
	
	/**
	 * Called when the object is destroyed
	 */
	void onDestroy();
	
	/**
	 * Called when the object should be drawn
	 * @param painter The painter to draw with
	 */
	void onDraw(Painter painter);
}
