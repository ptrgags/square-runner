package gags.engine.collision;

import gags.engine.geom.Rectangle;
import gags.engine.unit.Unit;

/**
 * Rectangle-shaped collision mask
 * @author Peter Gagliardi
 */
public class CollisionRectangle extends CollisionMask {

	/**
	 * Create a default collision rectangle for a unit
	 * with position (unit.x, unit.y) and dimensions
	 * unit.width by unit.height
	 * @param unit the unit to create a collision rectangle for
	 */
	public CollisionRectangle(Unit unit) {
		update(unit);
	}
	
	/**
	 * Create a custom collision rectangle with the default
	 * bounding box
	 * @param unit the unit to get the bounding box from
	 * @param mask the custom mask
	 */
	public CollisionRectangle(Unit unit, Rectangle mask) {
		setBoundingBox(unit);
		setMask(mask);
	}
	
	/**
	 * Create a custom collision rectangle
	 * @param boundingBox the bounding box
	 * @param mask the mask
	 */
	public CollisionRectangle(Rectangle boundingBox, Rectangle mask) {
		setBoundingBox(boundingBox);
		setMask(mask);
	}

	@Override
	public final void update(Unit unit) {
		setBoundingBox(unit);
		setMask(new Rectangle(unit.getX(), unit.getY(), unit.getWidth(), unit.getHeight()));
	}
}
