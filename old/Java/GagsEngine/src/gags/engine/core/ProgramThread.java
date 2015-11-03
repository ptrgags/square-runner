package gags.engine.core;

/**
 * Thread for running a Program
 * @author Peter Gagliardi
 */
public abstract class ProgramThread extends Thread {
	
	/** The program */
	private Program program;
	/** Parent object */
	private HasProgramThread parent;
	/** The time before the frame */
	private long timeBefore;
	/** The time after the frame */
	private long timeAfter;
	/** Frame rate in milliseconds */
	private long frameRate;
	/** Frames per second*/
	private int fps;
	
	public ProgramThread(Program program, HasProgramThread parent) {
		this.program = program;
		this.parent = parent;
		setName("Program"); //LATER: GE - Make this custom
		fps = 30;
		updateFrameRate();
	}
	
	public final Program getProgram() {
		return program;
	}
	
	public final void setFps(int fps) {
		this.fps = fps;
	}
	
	@Override
	public final void run() {
		loadResources();
		program.start();
		executeLoop();
		program.onDestroy();
		nextProgram();
	}
	
	/**
	 * Update the frame rate based on the 
	 * set frames per second
	 */
	private void updateFrameRate() {
		frameRate = 1000 / fps;
	}
	
	/**
	 * Execute the game loop
	 */
	private void executeLoop() {
		while (program.isRunning()) {
			captureTimeBefore();
			if (!program.isPaused())
				program.onFrame();
			onDraw();
			captureTimeAfter();
			syncFrame();
		}
	}

	/**
	 * Capture the time before the program
	 * updates/renders
	 */
	private void captureTimeBefore() {
		timeBefore = System.currentTimeMillis();
	}

	/**
	 * Capture the time after the program
	 * updates/renders
	 */
	private void captureTimeAfter() {
		timeAfter = System.currentTimeMillis();
	}

	/**
	 * Sync the frame rate for smoother animation
	 */
	private void syncFrame() {
		final long ELAPSED_TIME = timeAfter - timeBefore;
		try {
			if (ELAPSED_TIME < frameRate)
				Thread.sleep(frameRate - ELAPSED_TIME);
			else
				Thread.sleep(5);
		}
		catch (InterruptedException e) {
			return;
		}
	}

	/**
	 * Move to the next program or exit
	 */
	private void nextProgram() {
		Program nextProgram = program.getNextProgram();
		program.setNextProgram(null);
		if (nextProgram != null) {
			nextProgram.setResources(program.getResources());
			parent.setProgramThread(nextProgram);
		}
		else
			exit();
	}

	/**
	 * Load the resources if they have not already been loaded
	 */
	protected abstract void loadResources();

	/**
	 * Call the repaint() or postInvalidate() method
	 * to refresh the screen
	 */
	protected abstract void onDraw();
	
	/**
	 * exit the program
	 */
	protected abstract void exit();

	/**
	 * Designates an object that contains a
	 * program thread
	 * @author Peter Gagliardi
	 */
	public interface HasProgramThread {
		
		/**
		 * Set and run a new program thread
		 * based on a program
		 * @param program the program to run
		 */
		void setProgramThread(Program program);
	}
	
}
