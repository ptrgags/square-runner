package gags.engine.geom;

/**
 * Line class
 * @author Peter Gagliardi
 */
public class Line implements Shape {

	/** x */
	private int x;
	/** y */
	private int y;
	/** x2 */
	private int x2;
	/** y2 */
	private int y2;

	public Line() {
		setCoordinates(0, 0, 0, 0);
	}
	
	/**
	 * Full constructor
	 * @param x the x position
	 * @param y the y position
	 * @param x2 the second x position
	 * @param y2 the second y position
	 */
	public Line(int x, int y, int x2, int y2) {
		setCoordinates(x, y, x2, y2);
	}
	
	@Override
	public final ShapeType getType() {
		return ShapeType.LINE;
	}

	@Override
	public final int getX() {
		return x;
	}

	@Override
	public final void setX(int x) {
		this.x = x;
	}

	@Override
	public final int getY() {
		return y;
	}

	@Override
	public final void setY(int y) {
		this.y = y;
	}

	@Override
	public final void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public final int getX2() {
		return x2;
	}

	public final void setX2(int x2) {
		this.x2 = x2;
	}

	public final int getY2() {
		return y2;
	}

	public final void setY2(int y2) {
		this.y2 = y2;
	}

	/**
	 * set the second coordinate
	 * @param x2 the second x position
	 * @param y2 the second y position
	 */
	public final void setPosition2(int x2, int y2) {
		this.x2 = x2;
		this.y2 = y2;
	}
	
	/**
	 * set coordinates
	 * @param x the x position
	 * @param y the y position
	 * @param x2 the second x position
	 * @param y2 the second y position
	 */
	public final void setCoordinates(int x, int y, int x2, int y2) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public final int[] getXCoords() {
		return new int[] {x, x2};
	}

	@Override
	public final int[] getYCoords() {
		return new int[] {y, y2};
	}

	@Override
	public final int getNumOfCoords() {
		return 2;
	}

	@Override
	public final int getWidth() {
		return getMaxX() - getMinX();
	}

	@Override
	public final void setWidth(int width) {
		x2 = x + width;
	}

	@Override
	public final int getHeight() {
		return getMaxY() - getMinY();
	}

	@Override
	public final void setHeight(int height) {
		y2 = y + height;
	}

	@Override
	public final void setDimensions(int width, int height) {
		x2 = x + width;
		y2 = y + height;
	}

	@Override
	public final boolean intersects(Shape otherShape) {
		switch (otherShape.getType()) {
			case CIRCLE:
				return Intersection.intersects((Circle) otherShape, this);
			case LINE:
				return Intersection.intersects(this, (Line) otherShape);
			case POINT:
				return Intersection.intersects(this, (Point) otherShape);
			case POLYGON:
				return Intersection.intersects((Polygon) otherShape, this);
			case RECTANGLE:
				return Intersection.intersects((Rectangle) otherShape, this);
			default:
				return false;
		}
	}

	/**
	 * get the smaller x coordinate
	 * @return the smaller x coordinate
	 */
	private int getMinX() {
		return Math.min(x2, x2);
	}

	/**
	 * get the larger x coordinate
	 * @return the larger x coordinate
	 */
	private int getMaxX() {
		return Math.max(x, x2);
	}

	/**
	 * get the smaller y coordinate
	 * @return the smaller y coordinate
	 */
	private int getMinY() {
		return Math.min(y, y2);
	}

	/**
	 * Get the larger y coordinate
	 * @return the larger y coordinate
	 */
	private int getMaxY() {
		return Math.max(y, y2);
	}

}
