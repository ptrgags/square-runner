package gags.engine.geom;

/**
 * Point class
 * @author Peter Gagliardi
 */
public class Point implements Shape {

	/** x coordinate */
	private int x;
	/** y coordinate */
	private int y;

	/**
	 * Constructor
	 */
	public Point() {
		x = 0;
		y = 0;
	}
	
	/**
	 * Full constructor
	 * @param x the x position
	 * @param y the y position
	 */
	public Point(int x, int y) {
		setPosition(x, y);
	}
	
	@Override
	public final ShapeType getType() {
		return ShapeType.POINT;
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
		return new int[] {x};
	}

	@Override
	public final int[] getYCoords() {
		return new int[] {y};
	}

	@Override
	public final int getNumOfCoords() {
		return 1;
	}

	@Override
	public final int getWidth() {
		return 0;
	}

	@Override
	public final void setWidth(int width) { }

	@Override
	public final int getHeight() {
		return 0;
	}

	@Override
	public final void setHeight(int height) {	}

	@Override
	public final void setDimensions(int width, int height) { }

	@Override
	public final boolean intersects(Shape otherShape) {
		switch (otherShape.getType()) {
			case CIRCLE:
				return Intersection.intersects((Circle) otherShape, this);
			case LINE:
				return Intersection.intersects((Line) otherShape, this);
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
	
	@Override
	public final boolean equals(Object obj) {
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		return other.x == x && other.y == y;
		
	}

}
