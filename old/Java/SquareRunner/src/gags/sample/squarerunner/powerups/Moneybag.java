package gags.sample.squarerunner.powerups;

import gags.engine.core.Program;
import gags.sample.squarerunner.Player;
import gags.sample.squarerunner.modes.Game;

/**
 * A moneybag adds to the player's score
 * @author Peter Gagliardi
 */
public class Moneybag extends Powerup {

	/**
	 * Create a moneybag
	 * @param x the x position
	 * @param y the y position
	 * @param parent the parent Program
	 */
	public Moneybag(int x, int y, Program parent) {
		super(x, y, parent);
		setSprite(getResources().getSprite("moneyBag"));
		setDimensionsBySprite();
		updateMask();
	}
	

	@Override
	public void onPickup(Player player) {
		((Game) getParent()).increaseScore(30);
	}
}
