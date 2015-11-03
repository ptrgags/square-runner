package gags.lib.form;

import gags.engine.events.Action;
import gags.engine.graphics.Color;
import gags.engine.graphics.Painter;
import gags.engine.graphics.Sprite;
import gags.engine.io.MouseButton;
import gags.engine.unit.Unit;

/**
 * A GUI button
 * @author Peter Gagliardi
 */
public class Button extends Unit {

	/** The button is disabled */
	private static final int STATE_DISABLED = 0;
	/** The mouse is not on the button */
	private static final int STATE_NORMAL = 1;
	/** The mouse is on the button */
	private static final int STATE_HOVER = 2;
	/** The button is pressed */
	private static final int STATE_PRESSED = 3;
	
	/** if the mouse is hovering over the button */
	private boolean hovering;
	/** if the button is enabled */
	private boolean enabled;
	/** if the button is pressed */
	private boolean pressed;
	/** Draw the label on the screen */
	private String label;
	/** The action to perform when clicked */
	private Action action;
	/** The color of the label */
	private Color labelColor;
	
	/**
	 * Constructor
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param buttonSprite a 4-framed sprite
	 * for the button. The frames are as follows:
	 * <ol>
	 * 	<li>Disabled</li>
	 * 	<li>Normal</li>
	 * 	<li>Hovering</li>
	 * 	<li>Pressed</li>
	 * </ol>
	 * @param enabled if the button is enabled
	 */
	public Button(int x, int y, Sprite buttonSprite, String label, boolean enabled) {
		super(x, y);
		this.enabled = enabled;
		this.label = label;
		hovering = false;
		pressed = false;
		labelColor = Color.BLACK;
		
		setSprite(buttonSprite);
		setDimensionsBySprite();
		updateMask();
		updateFrame();
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		updateFrame();
	}

	public final void setLabel(String label) {
		this.label = label;
	}

	public final void setAction(Action action) {
		this.action = action;
	}

	public final void setLabelColor(Color labelColor) {
		this.labelColor = labelColor;
	}

	@Override
	public final void onMouseEnter(int x, int y) {
		super.onMouseEnter(x, y);
		hovering = true;
		updateFrame();
	}
	
	@Override
	public final void onMouseExit(int x, int y) {
		super.onMouseExit(x, y);
		hovering = false;
		pressed = false;
		updateFrame();
	}
	
	@Override
	public final void onMousePress(MouseButton button, int x, int y) {
		pressed = true;
		updateFrame();
	}
	
	@Override
	public final void onMouseRelease(MouseButton button, int x, int y) {
		pressed = false;
		if (enabled && action != null)
			action.performAction();
		updateFrame();
	}

	@Override
	public final void onDraw(Painter painter) {
		painter.drawSprite(this);
		painter.setColor(labelColor);
			painter.drawCenteredString(label, getX() + getWidth() / 2, getY() + getHeight() / 4);
		painter.resetColor();
	}
	
	/**
	 * Update sprite index
	 */
	private void updateFrame() {
		if (!enabled)
			setSpriteFrame(STATE_DISABLED);
		else if (pressed)
			setSpriteFrame(STATE_PRESSED);
		else if (hovering)
			setSpriteFrame(STATE_HOVER);
		else
			setSpriteFrame(STATE_NORMAL);
	}
}
