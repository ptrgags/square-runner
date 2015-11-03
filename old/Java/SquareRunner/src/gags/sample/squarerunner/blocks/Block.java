package gags.sample.squarerunner.blocks;

import gags.engine.core.Program;
import gags.engine.unit.Unit;
import gags.sample.squarerunner.Laser;
import gags.sample.squarerunner.Player;
import gags.sample.squarerunner.SquareRunnerObject;
import gags.sample.squarerunner.modes.Game;

/**
 * Basic block
 * @author Peter Gagliardi
 */
public class Block extends SquareRunnerObject {

	/**
	 * Create a new block
	 * @param x the x position of the block
	 * @param y the y position of the block
	 */
	public Block(int x, int y, Program parent) {
		super(x, y, parent);
		setSprite(getResources().getSprite("block"));
		setDimensionsBySprite();
		updateMask();
	}
	
	@Override
	public void onCollision(Unit otherUnit) {
		Game game = (Game) getParent();
		if (otherUnit.getType().equals(Player.TYPE)) {
			Player player = (Player) otherUnit;
			if (player.isInvincible()) {
				game.increaseScore(1);
				destroy();
			}
			else
				game.restart();
		}
		else if (otherUnit.getType().equals(Laser.TYPE)) {
			game.increaseScore(2);
			destroy();
		}
	}

}
