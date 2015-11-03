package gags.lib.time;

import gags.engine.events.Action;

/**
 * A basic timer fires an event every n frames
 * @author Peter Gagliardi
 */
public final class BasicTimer implements FrameTimer{

	/** Delay between events */
	private int delay;
	/** The action to perform */
	private Action action;
	/** The frame number */
	private int frame;
	
	public BasicTimer() {
		delay = 0;
		action = null;
		frame = 0;
	}
	
	/**
	 * Create and initialize a basic timer
	 * @param action the action to perform
	 * @param frames the delay between events
	 * in frames
	 */
	public BasicTimer(Action action, int frames) {
		setEvent(action, frames);
	}
	
	@Override
	public void setEvent(Action action, int frames) {
		this.action = action;
		delay = frames;
		restart();
	}

	@Override
	public int getFrame() {
		return frame;
	}

	@Override
	public int getElapsedFrames() {
		return frame;
	}

	@Override
	public int getFramesUntil() {
		return delay - frame;
	}

	@Override
	public int getDelay() {
		return delay;
	}

	@Override
	public void onFrame() {
		frame++;
		if (frame % delay == 0 && action != null)
			action.performAction();
	}

	@Override
	public void restart() {
		frame = 0;
	}

}
