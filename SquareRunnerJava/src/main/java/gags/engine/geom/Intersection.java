package gags.engine.geom;

import gags.lib.math.Math2;

/**
 * Class for determining shape intersections
 * @author Peter Gagliardi
 */
public class Intersection {
	
	/**
	 * LATER: Polygon-Circle intersection
	 * @param polygon
	 * @param circle
	 * @return
	 */
	public static boolean intersects(Polygon polygon, Circle circle) {
		return false;
	}
	
	/**
	 * LATER: Polygon-Rectangle intersection
	 * @param polygon
	 * @param rectangle
	 * @return
	 */
	public static boolean intersects(Polygon polygon, Rectangle rectangle) {
		return false;
	}
	
	/**
	 * Circle-Rectangle intersection
	 * @param circle the circle
	 * @param rectangle the rectangle
	 * @return if the circle and rectangle intersect
	 */
	public static boolean intersects(Circle circle, Rectangle rectangle) {
		int closestX = Math2.clamp(circle.getX(), rectangle.getX(), rectangle.getX() + rectangle.getWidth() - 1);
		int closestY = Math2.clamp(circle.getY(), rectangle.getY(), rectangle.getY() + rectangle.getHeight() - 1);
		
		Point closestPoint = new Point(closestX, closestY);
		return intersects(circle, closestPoint);
	}
	
	/**
	 * LATER: Polygon-Line intersection
	 * @param polygon
	 * @param line
	 * @return
	 */
	public static boolean intersects(Polygon polygon, Line line) {
		return false;
	}
	
	/**
	 * LATER: Circle-Line collision
	 * @param circle the circle
	 * @param line the line
	 * @return if the circle and line intersect
	 */
	public static boolean intersects(Circle circle, Line line) {
		return false;
	}
	
	/**
	 * LATER: Rectangle-Line intersection
	 * @param rectangle
	 * @param line
	 * @return
	 */
	public static boolean intersects(Rectangle rectangle, Line line) {
		return false;
	}
	
	/**
	 * LATER: Polygon-Point intersection
	 * @param polygon
	 * @param point
	 * @return
	 */
	public static boolean intersects(Polygon polygon, Point point) {
		return false;
	}
	
	/**
	 * Circle-Point intersection
	 * @param circle the circle
	 * @param point the point
	 * @return if the circle and point intersect
	 */
	public static boolean intersects(Circle circle, Point point) {
		return getDistance(circle.getX(), circle.getY(), point.getX(), point.getY()) <= circle.getRadius();
	}
	
	/**
	 * Rectangle-Point intersection
	 * @param rectangle the rectangle
	 * @param point the point
	 * @return if the rectangle and point intersect
	 */
	public static boolean intersects(Rectangle rectangle, Point point) {
		return point.getX() >= rectangle.getLeft()
				&& point.getX() < rectangle.getRight()
				&& point.getY() >= rectangle.getTop()
				&& point.getY() < rectangle.getBottom();
	}

	/**
	 * LATER: Line-Point intersection
	 * @param line
	 * @param point
	 * @return
	 */
	public static boolean intersects(Line line, Point point) {
		return false;
	}
	
	/**
	 * LATER: Polygon-Polygon intersection
	 * @param polygon
	 * @param polygon2
	 * @return
	 */
	public static boolean intersects(Polygon polygon, Polygon polygon2) {
		return false;
	}
	
	/**
	 * Check if two circles intersect
	 * @param circle the first circle
	 * @param circle2 the second circle
	 * @return if the circles intersect
	 */
	public static boolean intersects(Circle circle, Circle circle2) {
		return getDistance(circle.getX(), circle.getY(), circle2.getX(), circle2.getY()) <= circle.getRadius() + circle2.getRadius();
	}
	
	/**
	 * Rectangle-Rectangle intersection
	 * @param rectangle the first rectangle
	 * @param rectangle2 the second rectangle
	 * @return if the rectangles intersect
	 */
	public static boolean intersects(Rectangle rectangle, Rectangle rectangle2) {
		return !(rectangle.getBottom() < rectangle2.getTop()
				|| rectangle.getTop() > rectangle2.getBottom()
				|| rectangle.getLeft() > rectangle2.getRight()
				|| rectangle.getRight() < rectangle2.getLeft());
	}

	/**
	 * LATER: Line-Line intersection
	 * @param line
	 * @param line2
	 * @return
	 */
	public static boolean intersects(Line line, Line line2) {
		return false;
	}
	
	/**
	 * Point-Point intersection
	 * @param point first point
	 * @param point2 second point
	 * @return if the two points intersect
	 */
	public static boolean intersects(Point point, Point point2) {
		return point.getX() == point2.getX() && point.getY() == point2.getY();
	}

	/**
	 * Get the distance between two points
	 * @param x the first x coordinate
	 * @param y the first y coordinate
	 * @param x2 the second x coordinate
	 * @param y2 the second y coordinate
	 * @return the distance between the two points
	 */
	private static int getDistance(int x, int y, int x2, int y2) {
		return (int) Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));
	}
}
