package gags.sample.squarerunner.tiles;

import gags.engine.core.Program;
import gags.sample.squarerunner.Player;
import gags.sample.squarerunner.modes.Game;

/**
 * A gold tile adds to the player's score when the player hovers over it
 * @author Peter Gagliardi
 */
public class GoldTile extends Tile {

	/**
	 * Constructor
	 * @param x the x position
	 * @param y the y position
	 * @param parent the parent program
	 */
	public GoldTile(int x, int y, Program parent) {
		super(x, y, parent);
		setSprite(getResources().getSprite("goldTile"));
		setDimensionsBySprite();
		updateMask();
	}

	@Override
	public void onHover(Player player) {
		((Game) getParent()).increaseScore(20);
	}

}
