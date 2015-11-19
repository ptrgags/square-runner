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
import gags.lib.form.OutputBox;
import gags.sample.squarerunner.Global;
import gags.sample.squarerunner.SquareRunnerLevel;

/**
 * Level select mode
 * @author Peter Gagliardi
 */
public class LevelSelect extends Program {
	
	/** Number of buttons to display */
	private static final int NUM_BUTTONS = 10;
	
	/** If the levels have been loaded from the resources */
	private static boolean levelsLoaded = false;
	
	/** 
	 * Level index of the first level
	 * shown in the levels panel
	 */
	private int panelStartIndex;
	/** Level index of the selected level */
	private int selectIndex;
	/** Index of the selected button */
	private int selectPanelIndex;
	/** The level buttons */
	private List<Button> levelButtons;
	/** the selection up button */
	private Button upButton;
	/** The selection down button */
	private Button downButton;
	/** The play button */
	private Button playButton;
	/** The Infinity Mode button */
	private Button infinityButton;
	/** The back button */
	private Button backButton;
	/** Text box */
	private OutputBox textBox;

	@Override
	public void onCreate() {
		setBackground(getResources().getBackground("main"));
		panelStartIndex = 0;
		selectIndex = 0;
		selectPanelIndex = 0;
		
		if (!levelsLoaded) {
			Global.loadLevels(getResources());
			levelsLoaded = true;
		}
		
		
		createLevelButtons();
		createButtons();
		
		textBox = new OutputBox(352, 32);
		textBox.setTextColor(Color.WHITE);
	}
	
	@Override
	public void onFrame() {
		updateLevelButtons();
		updateButtons();
		updateTextBox();
	}
	
	@Override
	public void onKeyRelease(Key key) {
		if (key == Key.DOWN)
			moveSelectionDown();
		else if (key == Key.UP)
			moveSelectionUp();
	}
	
	@Override
	public void onMousePress(MouseButton button, int x, int y) {
		testMousePress(levelButtons, button, x, y);
		testMousePress(getButtons(), button, x, y);
	}
	
	@Override
	public void onMouseRelease(MouseButton button, int x, int y) {
		testMouseRelease(levelButtons, button, x, y);
		testMouseRelease(getButtons(), button, x, y);
	}
	
	@Override
	public void onMouseMove(int x, int y) {
		testMouseMotion(levelButtons, x, y);
		testMouseMotion(getButtons(), x, y);
	}

	@Override
	public void onDraw(Painter painter) {
		painter.drawBackground(this);
		painter.setColor(Color.CYAN);
			painter.fillRect(32, 32 + (selectPanelIndex * 32), 256, 32);
		painter.resetColor();
		drawUnits(levelButtons, painter);
		drawUnits(getButtons(), painter);
		drawUnit(textBox, painter);
	}
	
	/**
	 * Create buttons
	 */
	private void createLevelButtons() {
		//TODO: Text should be aligned left
		levelButtons = new ArrayList<Button>();
		for (int i = 0; i < NUM_BUTTONS; i++) {
			final int index = i;
			Button button = new Button(32, 32 * i + 32, getResources().getSprite("levelButton"), "", true);
				button.setLabelColor(Color.WHITE);
				button.setAction(new Action() {
					@Override
					public void performAction() {
						selectIndex = panelStartIndex + index;
						selectPanelIndex = index;
					}
				});
			levelButtons.add(button);
		}
		updateLevelButtons();
	}
	
	/**
	 * Create other buttons
	 */
	private void createButtons() {
		final Sprite SPR_BUTTON = getResources().getSprite("button");
		
		upButton = new Button(288, 32, getResources().getSprite("upButton"), "", true);
			upButton.setAction(new Action() {
				@Override
				public void performAction() {
					moveSelectionUp();
				}
			});
		downButton = new Button(288, 320, getResources().getSprite("downButton"), "", true);
			downButton.setAction(new Action() {
				@Override
				public void performAction() {
					moveSelectionDown();
				}
			});
		infinityButton = new Button(208, 368, SPR_BUTTON, "Infinity Mode", true);
			infinityButton.setAction(new Action() {
				@Override
				public void performAction() {
					setNextProgram(new InfinityMode());
				}
			});
			infinityButton.setLabelColor(Color.WHITE);
		playButton = new Button(368, 368, SPR_BUTTON, "Play", true);
			playButton.setAction(new Action() {
				@Override
				public void performAction() {
					Global.setCurrentLevel(selectIndex);
					setNextProgram(new ClassicMode());
				}
			});
			playButton.setLabelColor(Color.WHITE);
		backButton = new Button(48, 368, SPR_BUTTON, "Back", true);
			backButton.setAction(new Action() {
				@Override
				public void performAction() {
					setNextProgram(new Menu());
				}
			});
			backButton.setLabelColor(Color.WHITE);	
	}
	
	/**
	 * Get all buttons that are not level buttons
	 */
	private List<Button> getButtons() {
		List<Button> buttons = new ArrayList<Button>();
			buttons.add(upButton);
			buttons.add(downButton);
			buttons.add(infinityButton);
			buttons.add(playButton);
			buttons.add(backButton);
		return buttons;
	}
	
	/**
	 * Called when the up button or up arrow is clicked/pressed
	 */
	private void moveSelectionDown() {
		if (selectIndex != Global.getNumLevels() - 1)
			selectIndex++;
		if (selectPanelIndex != NUM_BUTTONS - 1)
			selectPanelIndex++;
		else if (panelStartIndex != Global.getNumLevels() - NUM_BUTTONS)
			panelStartIndex++;
	}
	
	/**
	 * Called when the up button or up arrow was clicked/pressed
	 */
	private void moveSelectionUp() {
		if (selectIndex != 0)
			selectIndex--;
		if (selectPanelIndex != 0)
			selectPanelIndex--;
		else if (panelStartIndex != 0)
			panelStartIndex--;
	}
	
	/**
	 * Update level buttons
	 */
	private void updateLevelButtons() {
		String label;
		for (int i = 0; i < levelButtons.size(); i++) {
			Button button = levelButtons.get(i);
			int index = i + panelStartIndex;
			SquareRunnerLevel level = Global.getLevel(index);
				
			if (i + panelStartIndex >= Global.getNumLevels()) {
				button.setEnabled(false);
				label = "";
			}
			else if (!level.isUnlocked()) {
				button.setEnabled(false);
				label = (index + 1) + ". --- Locked ---";
			}
			else {
				button.setEnabled(true);
				label = (index + 1) + ". " + level.getTitle();
			}
			
			button.setLabel(label);
		}
	}
	
	/**
	 * Update other buttons
	 */
	private void updateButtons() {
		upButton.setEnabled(selectIndex != 0);
		downButton.setEnabled(selectIndex != Global.getNumLevels() - 1);
		playButton.setEnabled(Global.getLevel(selectIndex).isUnlocked());
	}
	
	/**
	 * Update the text box
	 */
	private void updateTextBox() {
		SquareRunnerLevel level = Global.getLevel(selectIndex);
		textBox.clear();
		textBox.addLine(level.getTitle());
		textBox.addLine("");
		for (String i : level.getLevelText())
			textBox.addLine(i);
		textBox.addLine(level.getDescription());
	}
}
