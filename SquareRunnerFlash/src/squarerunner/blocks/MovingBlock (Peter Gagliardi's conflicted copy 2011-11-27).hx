/**
 * Moving Block Class
 * Version 1.0 [SRF 1.0]
 * Last Revised on 12/28/2010
 * Copyright (c) 2010 BlynkDev Games
 */
package squarerunner.blocks;

import blynkdev.AnimatedImage;

/**
 * A moving block is a block that moves horizontally
 * @author Peter Gagliardi
 */

class MovingBlock extends Block
{

	public function new(x:Int, y:Int, img:AnimatedImage, hSpeed:Int, creator:GameCtrl):Void
	{
		super(x, y, img, creator);
		this.hSpeed = hSpeed;
	}
	
	//Execute this once every frame:
	//1. execute superclass' step event
	//2. move horizontally
	//3. wrap around the screen
	override public function frameEvent():Void
	{
		var const = new Const(false);
		super.frameEvent();
		
		if (this.x <= const.NUM_GAME_X)
			this.x = const.NUM_GAME_X + const.NUM_GAME_W - 16;
			
		if (this.x >= const.NUM_GAME_X + const.NUM_GAME_W)
			this.x = const.NUM_GAME_X;
	}
}