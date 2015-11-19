package gags.engine.collision;

import gags.engine.geom.Point;
import gags.engine.geom.Rectangle;
import gags.engine.geom.Shape;
import gags.engine.unit.Unit;

/**
 * Basic collision mask
 * @author Peter Gagliardi
 */
public abstract class CollisionMask {

	/** The bounding box of the collision mask */
	private Rectangle boundingBox;
	/** The collision mask */
	private Shape mask;
	
	public final Rectangle getBoundingBox() {
		return boundingBox;
	}

	public final void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}
	
	/**
	 * Set the bounding box from a unit's dimensions
	 * @param unit the unit to get the bounding box for
	 */
	public final void setBoundingBox(Unit unit) {
		boundingBox = new Rectangle(unit.getX(), unit.getY(), unit.getWidth(), unit.getHeight());
	}

	public final Shape getMask() {
		return mask;
	}

	public final void setMask(Shape mask) {
		this.mask = mask;
	}

	/**
	 * Check if bounding boxes intersect
	 * @param otherMask the other collision mask
	 * @return if the collision masks intersect
	 */
	public final boolean boundingBoxesIntersect(CollisionMask otherMask) {
		return boundingBox.intersects(otherMask.getBoundingBox());
	}
	
	/**
	 * Check if a point is within the bounding box
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return if the point is within the bounding box
	 */
	public final boolean containsPoint(int x, int y) {
		return boundingBox.intersects(new Point(x, y));
	}
	
	/**
	 * Check if the collision mask intersects another collision mask
	 * @param otherMask the other collision mask
	 * @return if the collision masks intersect
	 */
	public final boolean intersects(CollisionMask otherMask) {
		if (!boundingBoxesIntersect(otherMask))
			return false;
		else
			return mask.intersects(otherMask.getMask());
	}
	
	/**
	 * Update the collision mask
	 * @param unit the unit to get the mask for
	 */
	public abstract void update(Unit unit);
}
