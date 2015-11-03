package gags.engine.geom;

/**
 * An object that has x and y
 * coordinates
 * @author Peter Gagliardi
 */
public interface Coords {
	
	/**
	 * Get the x coordinate
	 * @return the x coordinate
	 */
	int getX();
	
	/**
	 * set the x coordinate
	 * @param x the x coordinate
	 */
	void setX(int x);
	
	/**
	 * get the y coordinate
	 * @return the y coordinate
	 */
	int getY();
	
	/**
	 * set the y coordinate
	 * @param y the y coordinate
	 */
	void setY(int y);
	
	/**
	 * Reposition the object
	 * @param x the new x coordinate
	 * @param y the new y coordinate
	 */
	void setPosition(int x, int y);
}
