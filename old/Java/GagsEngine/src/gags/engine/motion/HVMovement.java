package gags.engine.motion;

/**
 * Designates an object that can be
 * moved by horizontal and vertical speed
 * @author Peter Gagliardi
 */
public interface HVMovement {
	
	/**
	 * Set the velocity of the object
	 * @param hSpeed the new horizontal speed
	 * @param vSpeed the new vertical sped
	 */
	void setVelocity(int hSpeed, int vSpeed);
	
	/**
	 * Move the object based on horizontal and vertical speed
	 */
	void move();
	
	/**
	 * Move the object relative to its current position
	 * @param deltaX the horizontal change 
	 * @param deltaY the vertical change
	 */
	void move(int deltaX, int deltaY);
	
	/**
	 * Stop the object from moving;
	 */
	void stop();
}
