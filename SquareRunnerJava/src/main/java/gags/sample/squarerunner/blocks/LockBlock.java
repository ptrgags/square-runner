package gags.sample.squarerunner.blocks;

import gags.engine.core.Program;
import gags.engine.unit.Unit;
import gags.sample.squarerunner.Player;

/**
 * Lock block
 * @author Peter Gagliardi
 */
public class LockBlock extends Block {

	/**
	 * Constructor
	 * @param x the x position
	 * @param y the y position
	 */
	public LockBlock(int x, int y, Program parent) {
		super(x, y, parent);
		setSprite(getResources().getSprite("lockBlock"));
		setDimensionsBySprite();
		updateMask();
	}
	
	@Override
	public void onCollision(Unit otherUnit) {
		if (otherUnit.getType().equals(Player.TYPE)) {
			Player player = (Player) otherUnit;
			if (player.getKeys() > 0) {
				destroy();
				player.useKey();
			}
			else
				super.onCollision(otherUnit);
		}
		else
			super.onCollision(otherUnit);
	}

}
