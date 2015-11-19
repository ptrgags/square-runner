package gags.lib.time;

import gags.engine.events.Action;

/**
 * A countdown timer counts down to an event
 * @author Peter Gagliardi
 */
public final class CountdownTimer implements FrameTimer {

	/** The current frame number */
	private int frame;
	/** Frames until the event fires */
	private int framesUntil;
	/** Number of elapsed frames */
	private int elapsedFrame;
	/** The action to perform */
	private Action action;
	
	/**
	 * Create a countdown timer
	 */
	public CountdownTimer() {
		framesUntil = 0;
		frame = 0;
		elapsedFrame = 0;
		action = null;
	}
	
	/**
	 * Create and initialize a timer
	 * @param action the action to perform
	 * @param frames the frames until the event
	 * should be performed
	 */
	public CountdownTimer(Action action, int frames) {
		setEvent(action, frames);
	}
	
	@Override
	public void setEvent(Action action, int frames) {
		this.action = action;
		framesUntil = frames;
		restart();
	}

	@Override
	public int getFrame() {
		return frame;
	}
	
	@Override
	public int getElapsedFrames() {
		return elapsedFrame;
	}
	
	@Override
	public int getFramesUntil() {
		return frame;
	}
	
	@Override
	public int getDelay() {
		return framesUntil;
	}

	@Override
	public void onFrame() {
		elapsedFrame++;
		if (frame > 0)
			frame--;
		if (frame == 0 && action != null) {
			action.performAction();
			restart();
		}
	}

	@Override
	public void restart() {
		frame = action != null ? framesUntil : 0; 
		elapsedFrame = 0;
	}
}
