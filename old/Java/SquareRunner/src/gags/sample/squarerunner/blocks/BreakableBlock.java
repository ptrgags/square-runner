package gags.sample.squarerunner.blocks;

import gags.engine.core.Program;
import gags.engine.unit.Unit;
import gags.sample.squarerunner.modes.Game;

/**
 * Breakable block
 * @author Peter Gagliardi
 */
public class BreakableBlock extends Block {

	/**
	 * Constructor
	 * @param x the x position
	 * @param y the y position
	 */
	public BreakableBlock(int x, int y, Program parent) {
		super(x, y, parent);
		setSprite(getResources().getSprite("breakableBlock"));
		setDimensionsBySprite();
		updateMask();
	}
	
	@Override
	public void onCollision(Unit otherUnit) {
		destroy();
		((Game) getParent()).increaseScore(1);
	}

}
