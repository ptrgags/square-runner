package gags.lib.time;

import gags.engine.events.Action;

/**
 * Defines the basic structure
 * of a frame timer
 * @author Peter Gagliardi
 */
public interface FrameTimer {

	/** The default frames per second */
	int SECOND = 30;
	
	/**
	 * Set the event the timer monitors
	 * @param action the action to perform
	 * @param frames the frames until the 
	 * event fires next
	 */
	void setEvent(Action action, int frames);
	
	/**
	 * Get the current frame number
	 * @return the current frame number
	 */
	int getFrame();
	
	/**
	 * Get the current frame number
	 * in elapsed time
	 * @return the current elapsed frame
	 * number
	 */
	int getElapsedFrames();
	
	/**
	 * get the number of frames until the next event
	 * @return the number of frames until the next event
	 */
	int getFramesUntil();
	
	/**
	 * Get the length of time from
	 * start to the first event 
	 * @return the delay time
	 */
	int getDelay();
	
	/**
	 * Advance the frame and perform the frame event 
	 * if necessary
	 */
	void onFrame();
	
	/**
	 * reset the timer. Should
	 * also reset the elapsed number
	 * of frames
	 */
	void restart();
}
