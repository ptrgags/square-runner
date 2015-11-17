package gags.engine.geom;

/**
 * Circle class
 * @author Peter Gagliardi
 */
public class Circle implements Shape {

	/** x position */
	private int x;
	/** y position */
	private int y;
	/** radius of the circle */
	private int radius;

	/**
	 * Constructor
	 */
	public Circle() {
		x = 0;
		y = 0;
		radius = 0;
	}
	
	/**
	 * Full constructor
	 * @param x the x position
	 * @param y the y position
	 * @param radius the radius of the circle
	 */
	public Circle(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	@Override
	public final ShapeType getType() {
		return ShapeType.CIRCLE;
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

	public final int getRadius() {
		return radius;
	}

	public final void setRadius(int radius) {
		this.radius = radius;
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
		return radius * 2 + 1;
	}

	@Override
	public final void setWidth(int width) {
		radius = (width - 1) / 2;
	}

	@Override
	public final int getHeight() {
		return radius * 2 + 1;
	}

	@Override
	public final  void setHeight(int height) {
		radius = (height - 1) / 2;
	}

	@Override
	public final void setDimensions(int width, int height) {
		radius = (width - 1) / 2;
	}
	
	@Override
	public final boolean intersects(Shape otherShape) {
		switch (otherShape.getType()) {
			case CIRCLE:
				return Intersection.intersects(this, (Circle) otherShape);
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

}
