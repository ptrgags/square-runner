package gags.engine.geom;

import java.util.ArrayList;
import java.util.List;

/**
 * Polygon class
 * @author Peter Gagliardi
 */
public class Polygon implements Shape {

	/** x coordinates */
	private List<Integer> x;
	/** y coordinates */
	private List<Integer> y;

	public Polygon() {
		x = new ArrayList<Integer>();
		y = new ArrayList<Integer>();
	}
	
	@Override
	public final ShapeType getType() {
		return ShapeType.POLYGON;
	}

	@Override
	public final int getX() {
		return x.get(0);
	}

	@Override
	public final void setX(int x) {
		this.x.set(x, 0);
	}

	@Override
	public final int getY() {
		return y.get(0);
	}

	@Override
	public final void setY(int y) {
		this.y.set(y, 0);
	}

	@Override
	public final void setPosition(int x, int y) {
		this.x.set(x, 0);
		this.y.set(y, 0);
	}
	
	@Override
	public final int[] getXCoords() {
		int[] xCoords = new int[x.size()];
		for (int i = 0; i < x.size(); i++)
			xCoords[i] = x.get(i);
		return xCoords;
	}

	@Override
	public final int[] getYCoords() {
		int[] yCoords = new int[y.size()];
		for (int i = 0; i < y.size(); i++)
			yCoords[i] = y.get(i);
		return yCoords;
	}

	@Override
	public final int getNumOfCoords() {
		return x.size();
	}

	@Override
	public final int getWidth() {
		return getMaxX() - getMinX();
	}

	@Override
	public final void setWidth(int width) { }

	@Override
	public final int getHeight() {
		return getMaxY() - getMinY();
	}

	@Override
	public final void setHeight(int height) { }

	@Override
	public final void setDimensions(int width, int height) { }

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
				return Intersection.intersects(this, (Polygon) otherShape);
			case RECTANGLE:
				return Intersection.intersects(this, (Rectangle) otherShape);
			default:
				return false;
		}
	}

	/**
	 * Add a coordinate
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public final void addCoordinate(int x, int y) {
		this.x.add(x);
		this.y.add(y);
	}

	/**
	 * get the smallest x coordinate
	 * @return the smallest x coordinate
	 */
	private int getMinX() {
		return getMin(x);
	}

	/**
	 * get the largest x coordinate
	 * @return the largest x coordinate
	 */
	private int getMaxX() {
		return getMax(x);
	}

	/**
	 * get the smallest y coordinate
	 * @return the smallest y coordinate
	 */
	private int getMinY() {
		return getMin(y);
	}

	/**
	 * get the largest y coordinate
	 * @return the largest y coordinate
	 */
	private int getMaxY() {
		return getMax(y);
	}
	
	/**
	 * Get the maximum value in a list
	 * @param list the list to work with
	 * @return the maximum value of the list
	 */
	private int getMax(List<Integer> list) {
		int max = 0;
		for (Integer i : list) {
			if (i > max)
				max = i;
		}
		return max;
	}
	
	/**
	 * get the minimum value in a list
	 * @param list the list to work with
	 * @return the minimum value in the list
	 */
	private int getMin(List<Integer> list) {
		int min = list.get(0);
		for (Integer i : list) {
			if (i < min)
				min = i;
		}
		return min;
	}

}
