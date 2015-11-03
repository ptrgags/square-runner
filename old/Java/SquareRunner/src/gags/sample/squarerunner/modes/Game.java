package gags.sample.squarerunner.modes;

import java.util.ArrayList;
import java.util.List;

import gags.engine.core.Program;
import gags.engine.graphics.Color;
import gags.engine.graphics.Painter;
import gags.engine.io.Key;
import gags.lib.form.OutputBox;
import gags.lib.game.Score;
import gags.sample.squarerunner.Laser;
import gags.sample.squarerunner.Player;
import gags.sample.squarerunner.SquareRunnerObject;
import gags.sample.squarerunner.blocks.*;
import gags.sample.squarerunner.powerups.*;
import gags.sample.squarerunner.tiles.*;

/**
 * Game mode
 * @author Peter Gagliardi
 */
public abstract class Game extends Program {
	
	/** X coordinate of the game area */
	public static final int GAME_X = 32;
	/** Y coordinate of the game area */
	public static final int GAME_Y = 32;
	/** the width of the game area */
	public static final int GAME_WIDTH = 320;
	/** Height of the game area */
	public static final int GAME_HEIGHT = 400;
	
	/** Size of one block */
	protected static final int BLOCK_SIZE = 16;
	
	/** Blocks and other obstacles */
	private List<SquareRunnerObject> blocks;
	/** The lasers the player fires */
	private List<Laser> lasers;
	/** The player */
	private Player player;
	/** Text box for displaying stats */
	private OutputBox textBox;
	/** Text box for displaying scores*/
	private OutputBox scoreBox;
	/** State of the game */
	private GameState state;
	/** The player's score */
	private Score score;
	
	protected OutputBox getTextBox() {
		return textBox;
	}
	
	protected OutputBox getScoreBox() {
		return scoreBox;
	}
	
	protected GameState getState() {
		return state;
	}
	
	protected void setState(GameState state) {
		this.state = state;
	}
	
	protected Score getScore() {
		return score;
	}
	
	protected void setScore(Score score) {
		this.score = score;
	}

	@Override
	public void onCreate() {
		setBgColor(Color.BLACK);
		setDisplayBgColor(false);
		setBackground(getResources().getBackground("game"));
		
		state = GameState.START;
		
		blocks = new ArrayList<SquareRunnerObject>();
		lasers = new ArrayList<Laser>();
		player = new Player(176, 416, this);
		textBox = new OutputBox(388, 132);
			textBox.setTextColor(Color.WHITE);
		scoreBox = new OutputBox(388, 32);
			scoreBox.setTextColor(Color.WHITE);
	}
	
	@Override
	public void onFrame() {
		if (state == GameState.START)
			updateTextBox();
		else if (state == GameState.NORMAL) {
			player.onFrame();
			onFrame(blocks);
			onFrame(lasers);
			
			testCollisions(player, blocks);
			testCollisions(blocks, lasers);
			
			testDestruction(blocks);
			testDestruction(lasers);
			updateTextBox();
			updateScoreBox();
		}
	}
	
	@Override
	public void onKeyPress(Key key) {
		if (state == GameState.NORMAL)
			player.onKeyPress(key);
	}
	
	@Override
	public void onKeyRelease(Key key) {
		if (key == Key.P) {
			textBox.setText("Paused");
			setPaused(!isPaused());
		}
		else if (key == Key.SPACEBAR && state == GameState.START)
			state = GameState.NORMAL;
		else if (key == Key.ESC)
			state = GameState.ESCAPE;
		else
			player.onKeyRelease(key);
	}
	
	@Override
	public void onDraw(Painter painter) {
		painter.drawBackgroundColor(this);
		
		painter.drawSprites(lasers);
		painter.drawSprites(blocks);
		drawUnit(player, painter);
		drawUnit(textBox, painter);
		drawUnit(scoreBox, painter);
		
		painter.drawBackground(this);
	}
	
	/**
	 * Restart the current level
	 */
	public void restart() {
		state = GameState.RESTART;
	}
	
	/**
	 * Fire a laser at the blocks
	 */
	public void fire() {
		lasers.add(player.produceUnit());
	}
	
	/**
	 * Increase the score
	 * @param amount the amount to increase the score by
	 */
	public void increaseScore(int amount) {
		score.increment(amount);
	}
	
	/**
	 * Decrease the score
	 * @param amount the amount to decrease the score by
	 */
	public void decreaseScore(int amount) {
		score.decrement(amount);
	}
	
	/**
	 * Create a line of blocks
	 * @param line a textual representation of the blocks
	 * to create
	 * @param yOffset the offset from GAME_X in pixels
	 */
	protected void createLine(String line, int yOffset, int speed) {
		for (int i = 0; i < line.length(); i++) {
			SquareRunnerObject object = createBlock(line.charAt(i), GAME_X + i * BLOCK_SIZE, GAME_Y - yOffset);
			if (object != null) {
				object.setVSpeed(speed);
				blocks.add(object);
			}
		}
	}
	
	/**
	 * Update the text box text
	 */
	protected void updateTextBox() {
		String string;
		
		textBox.clear();
		
		if (state == GameState.START)
			textBox.addLine("Press Space to begin");
		string = "Test Controls:";
			textBox.addLine(string);
		string = "Arrows: Move";
			textBox.addLine(string);
		string = "P: Pause";
			textBox.addLine(string);
		string = "Keys: " + player.getKeys();
			textBox.addLine(string);
		string = "Ammo: " + player.getAmmo();
			textBox.addLine(string);
		string = "Space: Fire laser (If you have ammo)";
			textBox.addLine(string);
			textBox.addLine("");
	}
	
	/**
	 * Update the highscore box
	 */
	protected void updateScoreBox() {
		String string;
		
		scoreBox.clear();
		string = "Score: " + score.getValue() + " Highscore: " + score.getHighValue();
		scoreBox.addLine(string);
	}

	
	/**
	 * Create a block from a symbol character
	 * @param symbol single character "symbol"
	 * @param x the x position to create the block at
	 * @param y the y position to create the block at
	 * @return a block or null;
	 */
	private SquareRunnerObject createBlock(char symbol, int x, int y) {
		if (symbol == '1')
			return new Block(x, y, this);
		else if (symbol == '>')
			return new MovingBlock(x, y, 3, this);
		else if (symbol == '<')
			return new MovingBlock(x, y, -3, this);
		else if (symbol == 'M')
			return new MoneyBlock(x, y, this);
		else if (symbol == 'L')
			return new LockBlock(x, y, this);
		else if (symbol == 'B')
			return new BreakableBlock(x, y, this);
		else if (symbol == 'I')
			return new Invincible(x, y, this);
		else if (symbol == '$')
			return new Moneybag(x, y, this);
		else if (symbol == 'K')
			return new KeyPowerup(x, y, this);
		else if (symbol == 'A')
			return new Ammo(x, y, this);
		else if (symbol == 'F')
			return new FlipTile(x, y, player.isFlipped() ? false : true, player, this);
		else if (symbol == 'R')
			return new ReloadTile(x, y, this);
		else if (symbol == 'G')
			return new GoldTile(x, y, this);
		else if (symbol == '!')
			return new MinusCashTile(x, y, this);
		else if (symbol == 'X')
			return new FinishTile(x, y, this);
		else
			return null;
	}

	/**
	 * The state of the level
	 * @author Peter Gagliardi
	 */
	protected enum GameState {
		/** Level is starting */
		START,
		/** Level is being played */
		NORMAL,
		/** 
		 * Level has been completed,
		 * victory message is displayed 
		 */
		COMPLETE,
		/** 
		 * Level has been completed, move on to the
		 * next mode 
		 */
		END,
		/** Level has been failed, set to restart */
		RESTART,
		/** Level has been terminated by user */
		ESCAPE;
	}
}
