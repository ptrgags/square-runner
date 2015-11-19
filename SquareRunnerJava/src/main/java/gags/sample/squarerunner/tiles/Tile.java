package gags.sample.squarerunner.tiles;

import gags.engine.core.Program;
import gags.engine.unit.Unit;
import gags.sample.squarerunner.Player;
import gags.sample.squarerunner.SquareRunnerObject;

/**
 * A tile is an object that activates when the player hovers over it
 * @author Peter Gagliardi
 */
public abstract class Tile extends SquareRunnerObject {

	/** if the hover event has been executed */
	private boolean hoverEvent;
	
	/**
	 * Constructor
	 * @param x the x position
	 * @param y the y position
	 * @param parent the parent program
	 */
	public Tile(int x, int y, Program parent) {
		super(x, y, parent);
		hoverEvent = false;
	}
	
	@Override
	public void onCollision(Unit otherUnit) {
		if (otherUnit.getType().equals(Player.TYPE) && !hoverEvent) {
			hoverEvent = true;
			onHover((Player) otherUnit);
		}
	}
	
	/**
	 * Called when the player passes over the tile
	 */
	public abstract void onHover(Player player);
}
