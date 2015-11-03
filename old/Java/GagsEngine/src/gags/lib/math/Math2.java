package gags.lib.math;

/**
 * Library for extra 
 * math functions
 * @author Peter Gagliardi
 */
public class Math2 {
	
	/**
	 * Clamp a number into a given range
	 * @param min the minimum value
	 * @param max the maximum value
	 * @param value the current value
	 * @return the clamped value
	 */
	public static int clamp(int value, int min, int max) {
		if (value < min)
			value = min;
		if (value > max)
			value = max;
		return value;
	}
	
	/**
	 * Reduce an angle measure 
	 * @param angle
	 * @return
	 */
	public static int reduceAngle(int angle) {
		while (angle >= 360)
			angle -= 360;
		while (angle < 0)
			angle += 360;
		return angle;
	}
}
