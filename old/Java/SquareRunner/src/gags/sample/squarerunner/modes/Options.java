package gags.sample.squarerunner.modes;

import java.util.ArrayList;
import java.util.List;

import gags.engine.core.Program;
import gags.engine.events.Action;
import gags.engine.graphics.Color;
import gags.engine.graphics.Painter;
import gags.engine.graphics.Sprite;
import gags.engine.io.MouseButton;
import gags.lib.form.Button;
import gags.lib.form.Clickable;
import gags.sample.squarerunner.Global;

/**
 * The options mode allows the user to 
 * @author Peter Gagliardi
 */
public class Options extends Program {
	
	/** Number of ship colors */
	private static final int NUM_SHIP_COLORS = 5;
	
	/** Back button takes you the  */
	private Button backButton;
	/** Erase button erases high scores */
	private Button eraseButton;
	/** Color buttons allow the player to  */
	private List<Clickable> colorButtons;
	/** Ship colors sprite sheet */
	private Sprite shipColors;
	
	@Override
	public void onCreate() {
		setBackground(getResources().getBackground("main"));
		shipColors = getResources().getSprite("shipColors");
		
		backButton = new Button(0, 0, getResources().getSprite("button"), "Back", true);
			backButton.setAction(new Action() {
				@Override
				public void performAction() {
					setNextProgram(new Menu());
				}
			});
			backButton.setLabelColor(Color.WHITE);
		
		eraseButton = new Button(128, 400, getResources().getSprite("button"), "Erase Scores", true);
			eraseButton.setAction(new Action() {
				@Override
				public void performAction() {
					Global.getSaveData().clear();
				}
			});
			eraseButton.setLabelColor(Color.WHITE);
			
		colorButtons = new ArrayList<Clickable>();
		createClickables();
	}
	
	@Override
	public void onMousePress(MouseButton button, int x, int y) {
		testMousePress(backButton, button, x, y);
		testMousePress(eraseButton, button, x, y);
	}
	
	@Override
	public void onMouseRelease(MouseButton button, int x, int y) {
		testMouseRelease(backButton, button, x, y);
		testMouseRelease(eraseButton, button, x, y);
		testMouseRelease(colorButtons, button, x, y);
	}
	
	@Override
	public void onMouseMove(int x, int y) {
		testMouseMotion(backButton, x, y);
		testMouseMotion(eraseButton, x, y);
	}
	
	@Override
	public void onDraw(Painter painter) {
		painter.drawBackground(this);
		
		drawUnit(backButton, painter);
		drawUnit(eraseButton, painter);
		painter.drawSprites(colorButtons);
		painter.drawSprite(shipColors, Global.getColorIndex(), 128, 128);
		
		painter.setColor(Color.WHITE);
			painter.drawString("Ship Color:", 0, 80);
			painter.drawString("Current color:", 0, 128);
		painter.resetColor();
	}
	
	/**
	 * Create clickables
	 */
	private void createClickables() {
		Sprite source = getResources().getSprite("shipColors");
		for (int i = 0; i < NUM_SHIP_COLORS; i++) {
			final int index = i;
			Sprite dest = new Sprite(source.getFrame(i));
			Clickable clickable = new Clickable(i * 32, 96, dest, new Action() {
				@Override
				public void performAction() {
					Global.setColorIndex(index);
				}
			});
			colorButtons.add(clickable);
		}
	}
}
