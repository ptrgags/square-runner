package gags.sample.squarerunner.tiles;

import gags.engine.core.Program;
import gags.sample.squarerunner.Player;
import gags.sample.squarerunner.modes.Game;

/**
 * A reload tile adds to the player's ammo and score
 * @author Peter Gagliardi
 */
public class ReloadTile extends Tile {
	
	/**
	 * Constructor
	 * @param x the x position
	 * @param y the y position
	 * @param parent the parent program
	 */
	public ReloadTile(int x, int y, Program parent) {
		super(x, y, parent);
		setSprite(getResources().getSprite("reloadTile"));
		setDimensionsBySprite();
		updateMask();
	}
	

	@Override
	public void onHover(Player player) {
		player.increaseAmmo(6);
		((Game) getParent()).increaseScore(10);
	}

}
