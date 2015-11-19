package gags.sample.squarerunner.blocks;

import gags.engine.core.Program;
import gags.engine.unit.Unit;
import gags.sample.squarerunner.Laser;
import gags.sample.squarerunner.Player;
import gags.sample.squarerunner.modes.Game;

/**
 * Money Block
 * @author Peter Gagliardi
 */
public class MoneyBlock extends Block {

	/**
	 * Constructor
	 * @param x the x position
	 * @param y the y position
	 */
	public MoneyBlock(int x, int y, Program parent) {
		super(x, y, parent);
		setSprite(getResources().getSprite("moneyBlock"));
		setDimensionsBySprite();
		updateMask();
	}
	
	@Override
	public void onCollision(Unit otherUnit) {
		Game game = (Game) getParent();
		if (otherUnit.getType().equals(Player.TYPE)) {
			Player player = (Player) otherUnit;
			if (player.isInvincible()) {
				game.increaseScore(50);
				destroy();
			}
			else
				game.restart();
		}
		else if (otherUnit.getType().equals(Laser.TYPE)) {
			game.increaseScore(50);
			destroy();
		}
	}
	
}
