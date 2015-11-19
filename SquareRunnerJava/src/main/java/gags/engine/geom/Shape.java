package gags.engine.geom;

/**
 * Defines a basic geometric shape
 * @author Peter Gagliardi
 */
public interface Shape extends Coords, Dimensions {

	/**
	 * Get the type of the shape
	 * @return the type of the shape
	 */
	ShapeType getType();
	
	/**
	 * Get all the x coordinates
	 * @return all x coordinates
	 */
	int[] getXCoords();
	
	/**
	 * get all the y coordinates
	 * @return all y coordinates
	 */
	int[] getYCoords();
	
	/**
	 * Get the number of coordinates
	 * @return the number of coordinates
	 */
	int getNumOfCoords();

	/**
	 * Check if this shape is intersecting another shape
	 * @param otherShape the other shape
	 * @return if the shapes intersect
	 */
	boolean intersects(Shape otherShape);
}
