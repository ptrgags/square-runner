package gags.sample.squarerunner.modes;

import java.util.ArrayList;
import java.util.List;

import gags.engine.core.Program;
import gags.engine.events.Action;
import gags.engine.graphics.Color;
import gags.engine.graphics.Painter;
import gags.engine.graphics.Sprite;
import gags.engine.io.Key;
import gags.engine.io.MouseButton;
import gags.lib.form.Button;
import gags.sample.squarerunner.Title;

/**
 * The main menu mode
 * @author Peter Gagliardi
 */
public class Menu extends Program {

	/** X position of the left buttons */
	private static final int POSITION_LEFT = 176;
	/** X position of the right buttons */
	private static final int POSITION_RIGHT = 336;
	/** Y position of the title */
	private static final int POSITION_TOP = 0;
	/** Y position of the title in the intro animation */
	private static final int POSITION_TOP_INTRO = -320;
	/** Offset between buttons in the intro animation */
	private static final int BUTTON_OFFSET_HORIZONTAL = 64;
	/** Offset between buttons vertically */
	private static final int BUTTON_OFFSET_VERTICAL = 48;
	/** Top position of the button */
	private static final int BUTTON_POSITION_TOP = 320;
	/** 
	 * Offset between the first left button position 
	 * in the intro animation and POSITION_LEFT 
	 */
	private static final int OFFSET_LEFT = 480;
	/**
	 * Offset between the first right button position
	 * in the intro animation and POSITION_RIGHT
	 */
	private static final int OFFSET_RIGHT = 480;
	/** Speed of the intro animation */
	private static final int ANIMATION_SPEED = 16;
	
	/** If the introduction animation is complete */
	private static boolean introComplete = false;
	
	/** The title */
	private Title title;
	/** The buttons on the left side of the screen */
	private List<Button> leftButtons;
	/** The buttons on the right side of the screen*/
	private List<Button> rightButtons;
	/** If the shift key is held */
	private boolean shiftPressed;
	
	@Override
	public void onCreate() {
		setBackground(getResources().getBackground("main"));
		createButtons();
		title = new Title(160, introComplete ? POSITION_TOP : POSITION_TOP_INTRO, this);
	}
	
	@Override
	public void onFrame() {
		boolean buttonsDone = true;
		for (Button button : leftButtons) {
			if (button.getX() != POSITION_LEFT) {
				buttonsDone = false;
				button.move(ANIMATION_SPEED, 0);
				button.updateMask();
			}
		}
		for (Button button : rightButtons) {
			if (button.getX() != POSITION_RIGHT) {
				buttonsDone = true;
				button.move(-ANIMATION_SPEED, 0);
				button.updateMask();
			}
		}
		if (title.getY() != POSITION_TOP)
			title.move(0, ANIMATION_SPEED);
		if (buttonsDone && !introComplete)
			introComplete = true;
	}
	
	@Override
	public void onMousePress(MouseButton button, int x, int y) {
		testMousePress(leftButtons, button, x, y);
		testMousePress(rightButtons, button, x, y);
	}
	
	@Override
	public void onMouseRelease(MouseButton button, int x, int y) {
		testMouseRelease(leftButtons, button, x, y);
		testMouseRelease(rightButtons, button, x, y);
	}
	
	@Override
	public void onMouseMove(int x, int y) {
		testMouseMotion(leftButtons, x, y);
		testMouseMotion(rightButtons, x, y);
	}
	
	@Override
	public void onDraw(Painter painter) {
		painter.drawBackground(this);
		painter.drawSprite(title);
		drawUnits(leftButtons, painter);
		drawUnits(rightButtons, painter);
	}
	
	@Override
	public void onKeyPress(Key key) {
		if (key == Key.SHIFT)
			shiftPressed = true;
		else if (shiftPressed /* && key == Key.DELETE */) {
			//TODO: SR - Delete data
			//If shiftPressed && key == DELETE
			//	For every level
			//		Lock level
			//		Set high score to 0
			//	Unlock the first level
			//	Save level locked data
			//	Save high scores
		}
	}
	
	@Override
	public void onKeyRelease(Key key) {
		if (key == Key.SHIFT)
			shiftPressed = false;
		else if (key == Key.ESC)
			setNextProgram(null);
	}
	
	/**
	 * Create buttons on the screen
	 */
	private void createButtons() {
		final Sprite BUTTON_SPRITE = getResources().getSprite("button");
		int xLeft = introComplete ? POSITION_LEFT : POSITION_LEFT - OFFSET_LEFT;
		int xRight = introComplete ? POSITION_RIGHT : POSITION_RIGHT + OFFSET_RIGHT;
		int xOffset = introComplete ? 0 : BUTTON_OFFSET_HORIZONTAL;
		
		Button newButton;
		leftButtons = new ArrayList<Button>();
		newButton = new Button(xLeft, BUTTON_POSITION_TOP, BUTTON_SPRITE, "Play", true);
			newButton.setAction(new Action() {
				@Override
				public void performAction() {
					setNextProgram(new LevelSelect());
				}
			});
			leftButtons.add(newButton);
		newButton = new Button(xLeft - xOffset, BUTTON_POSITION_TOP + BUTTON_OFFSET_VERTICAL, BUTTON_SPRITE, "Info", false);
			//TODO: SR - Set Action: Go to Info
			leftButtons.add(newButton);
		newButton = new Button(xLeft - 2 * xOffset, BUTTON_POSITION_TOP + 2 * BUTTON_OFFSET_VERTICAL, BUTTON_SPRITE, "Credits", false);
			//TODO: SR - Set Action: Go to Credits
			leftButtons.add(newButton);
		rightButtons = new ArrayList<Button>();
		for (Button button : leftButtons)
			button.setLabelColor(Color.WHITE);
		
		newButton = new Button(xRight, BUTTON_POSITION_TOP, BUTTON_SPRITE, "Achievements", false);
			//TODO: SR - Set Action: Go to Achievements
			rightButtons.add(newButton);
		newButton = new Button(xRight + xOffset, BUTTON_POSITION_TOP + BUTTON_OFFSET_VERTICAL, BUTTON_SPRITE, "Options", true);
			newButton.setAction(new Action() {
				@Override
				public void performAction() {
					setNextProgram(new Options());
				}
			});
			rightButtons.add(newButton);
		newButton = new Button(xRight + 2 * xOffset, BUTTON_POSITION_TOP + 2 * BUTTON_OFFSET_VERTICAL, BUTTON_SPRITE, "Infinity Mode", true);
			newButton.setAction(new Action() {
				@Override
				public void performAction() {
					setNextProgram(new InfinityMode());
				}
			});
			rightButtons.add(newButton);
		for (Button button : rightButtons)
			button.setLabelColor(Color.WHITE);
		
	}

}
