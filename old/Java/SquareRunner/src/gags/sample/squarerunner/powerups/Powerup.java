package gags.sample.squarerunner.powerups;

import gags.engine.core.Program;
import gags.engine.unit.Unit;
import gags.sample.squarerunner.Player;
import gags.sample.squarerunner.SquareRunnerObject;

/**
 * A powerup is an object the player can pick up
 * @author Peter Gagliardi
 */
public abstract class Powerup extends SquareRunnerObject {

	/**
	 * Create a powerup
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param parent the parent Program
	 */
	public Powerup(int x, int y, Program parent) {
		super(x, y, parent);
	}
	
	@Override
	public void onCollision(Unit otherUnit) {
		if (otherUnit.getType().equals(Player.TYPE)) {
			onPickup((Player) otherUnit);
			destroy();
		}
	}
	
	/**
	 * Called when the player picks up the powerup
	 * @param player the player
	 */
	public abstract void onPickup(Player player);

}
