package gags.sample.squarerunner.tiles;

import gags.engine.core.Program;
import gags.sample.squarerunner.Player;
import gags.sample.squarerunner.modes.ClassicMode;

/**
 * A finish tile marks the end of the level
 * @author Peter Gagliardi
 */
public class FinishTile extends Tile {

	/**
	 * Constructor
	 * @param x the x position
	 * @param y the y position
	 * @param parent the parent program
	 */
	public FinishTile(int x, int y, Program parent) {
		super(x, y, parent);
		setSprite(getResources().getSprite("finishTile"));
		setDimensionsBySprite();
		updateMask();
	}
	

	@Override
	public void onHover(Player player) {
		((ClassicMode) getParent()).startFinishTimer();
	}

}
