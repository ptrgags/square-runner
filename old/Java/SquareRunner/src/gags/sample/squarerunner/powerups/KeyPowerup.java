package gags.sample.squarerunner.powerups;

import gags.engine.core.Program;
import gags.sample.squarerunner.Player;

/**
 * A key lets the player get past a lock block
 * @author Peter Gagliardi
 */
public class KeyPowerup extends Powerup {

	/**
	 * Create a key
	 * @param x the x position
	 * @param y the y position
	 * @param parent the parent Program
	 */
	public KeyPowerup(int x, int y, Program parent) {
		super(x, y, parent);
		setSprite(getResources().getSprite("key"));
		setDimensionsBySprite();
		updateMask();
	}

	@Override
	public void onPickup(Player player) {
		//LATER: Change from boolean key to int keys
		player.addKey();
	}

}
