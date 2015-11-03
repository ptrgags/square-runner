package gags.sample.squarerunner;
import gags.engine.core.Program;
import gags.engine.unit.Unit;
import gags.sample.squarerunner.modes.Game;

/**
 * Defines the base object that
 * moves down the screen towards
 * the player 
 * @author Peter Gagliardi
 */
public abstract class SquareRunnerObject extends Unit {
	
	/** The size of one block */
	private static final int BLOCK_SIZE = 16;

	/**
	 * Constructor
	 * @param x the x position
	 * @param y the y position
	 * @param sprite The sprite to display
	 */
	public SquareRunnerObject(int x, int y, Program parent) {
		super(x, y);
		setParent(parent);
	}
	
	@Override
	public void onFrame() {
		move();
		updateMask();
		
		if (getY() >= Game.GAME_Y + Game.GAME_HEIGHT + BLOCK_SIZE)
			destroy();
	}
}
