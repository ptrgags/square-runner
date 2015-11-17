package gags.engine.geom;

/**
 * Denotes an object that has
 * a width and height
 * @author Peter Gagliardi
 */
public interface Dimensions {
	
	/**
	 * Get the width
	 * @return the width
	 */
	int getWidth();
	
	/**
	 * Set the width
	 * @param width the width
	 */
	void setWidth(int width);
	
	/**
	 * Get the height
	 * @return the height
	 */
	int getHeight();
	
	/**
	 * Set the height
	 * @param height the height
	 */
	void setHeight(int height);
	
	/**
	 * Set the dimensions of the object
	 * @param width the new width
	 * @param height the new height
	 */
	void setDimensions(int width, int height);
	
}
