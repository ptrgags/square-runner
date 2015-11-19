package gags.sample.squarerunner.modes;

import java.util.List;
import java.util.Random;

import gags.engine.events.Action;
import gags.engine.ommelet.Ommelet;
import gags.lib.game.Score;
import gags.lib.time.BasicTimer;
import gags.lib.time.FrameTimer;
import gags.sample.squarerunner.Global;

/**
 * Infinity game mode, where lines
 * are generated randomly
 * @author Peter Gagliardi
 */
public class InfinityMode extends Game {

	/** The starting wave */
	private static final int START_WAVE = 0;
	/** Number of waves focused on a single set of lines */
	private static final int WAVES_PER_SET = 10;
	/** Number of lines in a set of lines */
	private static final int LINES_PER_SET = 3;
	/** Block speed */
	private static final int BLOCK_SPEED = 4;
	/** Key for the high score */
	private static final String KEY_HIGH_SCORE = "infinityHighscore";
	/** Key for the high wave */
	private static final String KEY_HIGH_WAVE = "infinityHighWave";
	/** Random number generator */
	private static final Random RANDOM = new Random();
	
	/** Timer for spawning blocks*/
	private FrameTimer spawnTimer;
	/** "chunks" of blocks to create */
	private List<Ommelet> chunks;
	/** Wave number */
	private Score wave;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		int highWave = Global.getSaveData().getInt(KEY_HIGH_WAVE, 0);
		int highScore = Global.getSaveData().getInt(KEY_HIGH_SCORE, 0);
		wave = new Score(highWave, START_WAVE);
		setScore(new Score(highScore));
		
		Ommelet ommelet = getResources().loadOmmelet("omml/", "lines");
		chunks = ommelet.getChildren();
		
		initTimers();
	}
	
	@Override
	public void onFrame() {
		super.onFrame();
		if (getState() == GameState.NORMAL)
			spawnTimer.onFrame();
		else if (getState() == GameState.RESTART)
			setNextProgram(new InfinityMode());
		else if (getState() == GameState.ESCAPE)
			setNextProgram(new Menu());
	}
	
	@Override
	public void onDestroy() {
		Global.getSaveData().putInt(KEY_HIGH_SCORE, getScore().getHighValue());
		Global.getSaveData().putInt(KEY_HIGH_WAVE, wave.getHighValue());
	}
	
	@Override
	protected void updateScoreBox() {
		super.updateScoreBox();
		getScoreBox().addLine("Wave: " + wave.getValue() + " Highest Wave: " + wave.getHighValue());
	}
	
	/**
	 * Initialize the timers
	 */
	private void initTimers() {
		spawnTimer = new BasicTimer(new Action() {
			@Override
			public void performAction() {
				wave.increment();
				spawnLine();
			}
		}, FrameTimer.SECOND);
	}
	
	@Override
	protected void createLine(String line, int lineIndex, int speed) {
		super.createLine(line, lineIndex * BLOCK_SIZE, speed);
	}
	
	/**
	 * Spawn a line of blocks
	 */
	private void spawnLine() {
		int randMax = Math.min((wave.getValue() / WAVES_PER_SET + 1) * LINES_PER_SET, chunks.size());
		int index = RANDOM.nextInt(randMax);
		Ommelet ommelet = chunks.get(index);
		for (int i = 0; i < Math.min(ommelet.getText().size(), 4); i++)
			createLine(ommelet.getText(i), i, BLOCK_SPEED);
	}

	
}
