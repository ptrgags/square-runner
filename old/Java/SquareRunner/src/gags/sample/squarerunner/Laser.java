package gags.sample.squarerunner;

import gags.engine.core.Program;
import gags.engine.unit.Unit;
import gags.sample.squarerunner.modes.Game;

/**
 * Laser the player fires to destroy blocks
 * @author Peter Gagliardi
 */
public class Laser extends Unit {

	/** Speed of the laser */
	private static final int SPEED = 8;
	
	/**
	 * Create a laser
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Laser(int x, int y, Program parent) {
		super(x, y);
		setParent(parent);
		setVSpeed(-SPEED);
		setSprite(getResources().getSprite("laser"));
		setDimensionsBySprite();
	}
	
	@Override
	public void onFrame() {
		move();
		updateMask();
		if (getY() <= Game.GAME_Y)
			destroy();
	}
}
