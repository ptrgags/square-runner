package gags.engine.events;

import gags.engine.unit.Unit;

/**
 * Defines collision events
 * @author Peter Gagliardi
 */
public interface CollisionEvents {

	/**
	 * Perform a collision between units
	 * @param otherUnit the other unit
	 */
	void onCollision(Unit otherUnit);
}
