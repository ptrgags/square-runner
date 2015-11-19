package gags.sample.squarerunner.powerups;

import gags.engine.core.Program;
import gags.sample.squarerunner.Player;

/**
 * An ammo powerup adds to the player's ammo
 * @author Peter Gagliardi
 */
public class Ammo extends Powerup {

	/**
	 * Create a new ammo powerup
	 * @param x the x position
	 * @param y the y position
	 * @param parent the parent Program
	 */
	public Ammo(int x, int y, Program parent) {
		super(x, y, parent);
		setSprite(getResources().getSprite("ammo"));
		setDimensionsBySprite();
		updateMask();
	}
	

	@Override
	public void onPickup(Player player) {
		player.increaseAmmo(3);
	}
}
