package gags.engine.geom;

/**
 * Rectangle class
 * @author Peter Gagliardi
 */
public class Rectangle implements Shape {

	/** x position */
	private int x;
	/** y position */
	private int y;
	/** width */
	private int width;
	/** height */
	private int height;
	
	/**
	 * Constructor
	 */
	public Rectangle() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}
	
	/**
	 * Full constructor
	 * @param x x position
	 * @param y y position
	 * @param width width
	 * @param height height
	 */
	public Rectangle(int x, int y, int width, int height) {
		setPosition(x, y);
		setDimensions(width, height);
	}
	
	@Override
	public final ShapeType getType() {
		return ShapeType.RECTANGLE;
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

	@Override
	public final int[] getXCoords() {
		return new int[] {x, x + width - 1, x + width - 1, x};
	}

	@Override
	public final int[] getYCoords() {
		return new int[] {y, y, y + height - 1, y + height - 1};
	}

	@Override
	public final int getNumOfCoords() {
		return 4;
	}

	@Override
	public final int getWidth() {
		return width;
	}

	@Override
	public final void setWidth(int width) {
		this.width = width;
	}

	@Override
	public final int getHeight() {
		return height;
	}

	@Override
	public final void setHeight(int height) {
		this.height = height;
	}

	@Override
	public final void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
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
				return Intersection.intersects(this, (Rectangle) otherShape);
			default:
				return false;
		}
	}

	/**
	 * Get the top of the rectangle
	 * @return the y coordinate of the top
	 * of the rectangle
	 */
	public final int getTop() {
		return y;
	}

	/**
	 * Get the bottom of the rectangle
	 * @return the y coordinate of the bottom
	 * of the rectangle
	 */
	public final int getBottom() {
		return y + height - 1;
	}

	/**
	 * Get the left side of the rectangle
	 * @return the x coordinate of the left
	 * side of the rectangle
	 */
	public final int getLeft() {
		return x;
	}

	/**
	 * get the right side of the rectangle
	 * @return the x coordinate of the right 
	 * side of the rectangle
	 */
	public final int getRight() {
		return x + width - 1;
	}

}
