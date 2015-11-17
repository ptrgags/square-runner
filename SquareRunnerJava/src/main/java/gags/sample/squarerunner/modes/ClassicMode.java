package gags.sample.squarerunner.modes;

import gags.engine.events.Action;
import gags.engine.ommelet.Ommelet;
import gags.lib.game.Score;
import gags.lib.time.CountdownTimer;
import gags.lib.time.FrameTimer;
import gags.sample.squarerunner.Global;
import gags.sample.squarerunner.SquareRunnerLevel;

/**
 * Classic mode, where levels are loaded
 * from a file
 * @author Peter Gagliardi
 */
public class ClassicMode extends Game {
	
	/** Time for the finish timer */
	private static final int TIME_FINISH = 3;
	/** Speed of the blocks */
	private static final int BLOCK_SPEED = 8;

	/** Line count */
	private int lineCount;
	/** timer*/
	private FrameTimer finishTimer;
	/** If the level is finished */
	private boolean finished;
	/** Ommelet for storing level data */
	private Ommelet ommelet;
	/** Full level information */
	private SquareRunnerLevel level;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		//Load save data
		level = Global.getCurrentLevel();
		int highScore = level.getHighscore();
		setScore(new Score(highScore));
		
		//Load level data
		ommelet = level.getLevelData();
		
		finishTimer = new CountdownTimer(new Action() {
			@Override
			public void performAction() {
				setState(GameState.COMPLETE);
			}
		}, TIME_FINISH * FrameTimer.SECOND);
	}
	
	@Override
	public void onFrame() {
		if (getState() == GameState.NORMAL) {
			if (lineCount % 16 == 0)
				createLine();
			lineCount += BLOCK_SPEED;
			if (finished)
				finishTimer.onFrame();
			super.onFrame();
		}
		else if (getState() == GameState.RESTART)
			setNextProgram(new ClassicMode());
		else if (getState() == GameState.ESCAPE)
			setNextProgram(new LevelSelect());
		else if (getState() == GameState.COMPLETE)
			setNextProgram(new ClassicMode());
		else
			super.onFrame();
	}
	
	@Override
	public void onDestroy() {
		level.setHighscore(getScore().getHighValue());
		Global.saveProgress();
		if (getState() == GameState.COMPLETE)
			Global.advanceLevel();
	}
	
	/**
	 * start counting down to the end of the level
	 */
	public void startFinishTimer() {
		finished = true;
	}
	
	/**
	 * Create line of blocks
	 */
	private void createLine() {
		int index = lineCount / 16;
		if (index < ommelet.getText().size())
			createLine(ommelet.getText(index), 16, BLOCK_SPEED);
	}
}
