package gags.lib.form;

import gags.engine.events.Action;
import gags.engine.graphics.Sprite;
import gags.engine.io.MouseButton;
import gags.engine.unit.Unit;

/**
 * Clickable object on the scren
 * @author Peter Gagliardi
 */
public class Clickable extends Unit {

	/** The action to perform on click */
	private Action action;

	/**
	 * Create a clickable object
	 * @param x the x position
	 * @param y the y position
	 * @param sprite the sprite
	 * @param action the action
	 */
	public Clickable(int x, int y, Sprite sprite, Action action) {
		super(x, y);
		setSprite(sprite);
		setDimensionsBySprite();
		updateMask();
		this.action = action;
	}
	
	@Override
	public void onMouseRelease(MouseButton button, int x, int y) {
		action.performAction();
	}

}
