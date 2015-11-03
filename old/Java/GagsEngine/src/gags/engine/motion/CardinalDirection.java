package gags.engine.motion;

/**
 * Enum constants for cardinal directions
 * @author Peter Gagliardi
 */
public enum CardinalDirection {
	NORTH(90),
	SOUTH(270),
	EAST(0),
	WEST(180);
	
	/** Direction in degrees */
	private int degrees;
	
	private CardinalDirection(int direction) {
		this.degrees = direction;
	}
	
	public int getDegrees() {
		return degrees;
	}

	/**
	 * Get a direction by an integer
	 * @param dir the direction as an int
	 * from 0 to 3, inclusive
	 * @return a direction value
	 */
	public static CardinalDirection getDirection(int dir) {
		return values()[dir];
	}

	/**
	 * Get the opposite direction
	 * @return the opposite direction
	 */
	public CardinalDirection getOpposite() {
		switch (this) {
			case NORTH:
				return SOUTH;
			case SOUTH:
				return NORTH;
			case EAST:
				return WEST;
			case WEST:
				return EAST;
			default:
				return null;
		}
	}
	
	/**
	 * Convert from a direction to it's corresponding
	 * orientation, horizontal or vertical movement
	 * @return the orientation
	 */
	public Orientation toOrientation() {
		return this == EAST || this == WEST ? Orientation.HORIZONTAL : Orientation.VERTICAL;
	}
	
	/**
	 * Check if a direction is one of the two
	 * horizontal directions
	 * @return if the direction is east or west
	 */
	public boolean isHorizontal() {
		return this.toOrientation() == Orientation.HORIZONTAL;
	}
	
	/**
	 * check if a direction is one of the two
	 * vertical directions
	 * @return if the direction is north or south
	 */
	public boolean isVertical()	 {
		return this.toOrientation() == Orientation.VERTICAL;
	}
}
