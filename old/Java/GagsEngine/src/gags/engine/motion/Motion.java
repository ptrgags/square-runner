package gags.engine.motion;

import gags.lib.math.Math2;

/**
 * Library class for functions
 * to do with motion
 * @author Peter Gagliardi
 */
public class Motion {

	/**
	 * Get the horizontal change based on direction
	 * and speed 
	 * @param direction the direction
	 * @param speed the speed
	 * @return the horizontal velocity
	 */
	public static int getHSpeed(int direction, int speed) {
		return (int) (Math.cos(Math.toRadians(direction)) * speed);
	}
	
	/**
	 * Get the vertical change based on direction
	 * and speed
	 * @param direction the direction
	 * @param speed the speed
	 * @return the vertical velocity
	 */
	public static int getVSpeed(int direction, int speed) {
		return (int) (-Math.sin(Math.toRadians(direction)) * speed);
	}
	
	/**
	 * Get the direction based on horizontal and vertical speed or distance
	 * @param xDist the horizontal speed or distance
	 * @param yDist the vertical speed or distance
	 * @return the direction
	 */
	public static int getDirection(int xDist, int yDist) {
		if (xDist == 0) {
			if (yDist > 0)
				return 270;
			else if (yDist < 0)
				return 90;
			else
				return -1;
		}
		else
			return Math2.reduceAngle((int) Math.toDegrees(Math.atan2(-yDist, xDist)));
	}
	
	/**
	 * Get the direction between two points
	 * @param x the x coordinate of the first point
	 * @param y the y coordinate of the first point
	 * @param x2 the x coordinate of the second point
	 * @param y2 the y coordinate of the second point
	 * @return the direction from the first point to the second point
	 */
	public static int getDirection(int x, int y, int x2, int y2) {
		return (getDirection(x2 - x, y2 - y));
	}
	
}
