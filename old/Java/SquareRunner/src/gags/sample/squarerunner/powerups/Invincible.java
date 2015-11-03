package gags.sample.squarerunner.powerups;

import gags.engine.core.Program;
import gags.sample.squarerunner.Player;

/**
 * An invincibility powerup makes the player
 * temporarily invincible
 * @author Peter Gagliardi
 */
public class Invincible extends Powerup {

	/**
	 * Create an invincibility powerup
	 * @param x the x position
	 * @param y the y position
	 * @param parent the parent Program
	 */
	public Invincible(int x, int y, Program parent) {
		super(x, y, parent);
		setSprite(getResources().getSprite("invincible"));
		setDimensionsBySprite();
		updateMask();
	}
	

	@Override
	public void onPickup(Player player) {
		player.setInvincible(true);
	}
}
