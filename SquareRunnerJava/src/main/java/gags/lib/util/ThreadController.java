package gags.lib.util;

/**
 * Interface that defines thread
 * control methods
 * @author Peter Gagliardi
 */
public interface ThreadController {

	/**
	 * Start the thread. Postcondition:
	 * isRunning() should return true;
	 */
	void start();
	
	/**
	 * check if the thread is still running
	 * @return if the program is still in the
	 * while loop
	 */
	boolean isRunning();
	
	/**
	 * set a condition to stop the thread. 
	 * Postcondition: isRunning() should 
	 * return false
	 */
	void stop();
}
