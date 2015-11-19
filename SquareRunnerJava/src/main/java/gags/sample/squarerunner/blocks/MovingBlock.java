package gags.sample.squarerunner.blocks;

import gags.engine.core.Program;
import gags.sample.squarerunner.modes.Game;

public class MovingBlock extends Block {

	/**
	 * Create a moving block
	 * @param x the x position
	 * @param y the y position
	 * @param hSpeed the horizontal speed of the moving block
	 * @param parent the parent program
	 */
	public MovingBlock(int x, int y, int hSpeed, Program parent) {
		super(x, y, parent);
		setHSpeed(hSpeed);
		setSprite(getHSpeed() > 0 ? getResources().getSprite("movingBlock_right") : getResources().getSprite("movingBlock_left"));
	}
	
	@Override
	public void onFrame() {
		super.onFrame();
		
		int lBound = Game.GAME_X - getWidth();
		int rBound = Game.GAME_X + Game.GAME_WIDTH + getWidth();
		
		if (getX() <= lBound) {
			int offset = lBound - getX();
			setX(rBound - offset);
		}
		else if (getX() + getWidth() >= rBound) {
			int offset = rBound - getX();
			setX(lBound - offset);
		}
		
		updateMask();
	}

}
