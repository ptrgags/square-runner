package gags.engine.core;

import gags.lib.util.ThreadController;

/**
 * Defines other methods that a program uses to send 
 * messages back to the ProgramThread
 * @author Peter Gagliardi
 */
public interface ProgramThreadController extends ThreadController {
	
	/**
	 * Toggle paused status of the program
	 */
	void setPaused(boolean paused);
	
	/**
	 * Check if the program is paused
	 * @return if the program is paused
	 */
	boolean isPaused();
	
	/**
	 * Get the next program after swapProgram() 
	 * @return the next program
	 */
	Program getNextProgram();
	
	/**
	 * set the next program to run
	 * @param program the next program
	 */
	void setNextProgram(Program program);
}
