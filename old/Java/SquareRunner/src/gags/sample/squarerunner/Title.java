package gags.sample.squarerunner;

import gags.engine.core.Program;
import gags.engine.unit.Unit;

/**
 * Visual title for Square Runner
 * @author Peter Gagliardi
 */
public class Title extends Unit {

	/**
	 * Create a title object
	 * @param x the x position
	 * @param y the y position
	 */
	public Title(int x, int y, Program parent) {
		super(x, y);
		setParent(parent);
		setSprite(getResources().getSprite("title"));
	}
}
