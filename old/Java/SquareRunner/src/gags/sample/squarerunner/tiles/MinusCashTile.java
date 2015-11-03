package gags.sample.squarerunner.tiles;

import gags.engine.core.Program;
import gags.sample.squarerunner.Player;
import gags.sample.squarerunner.modes.Game;

/**
 * A minus cash tile subtracts from the player's score
 * @author Peter Gagliardi
 */
public class MinusCashTile extends Tile {

	/**
	 * Constructor
	 * @param x the x position
	 * @param y the y position
	 * @param parent the parent program
	 */
	public MinusCashTile(int x, int y, Program parent) {
		super(x, y, parent);
		setSprite(getResources().getSprite("minusCashTile"));
		setDimensionsBySprite();
		updateMask();
	}

	@Override
	public void onHover(Player player) {
		((Game) getParent()).decreaseScore(20);
	}

}
