/**
 * Block Class
 * Last Revised on 5/16/2011 1:58 PM
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package squarerunner.blocks;

import blynkdev.AnimatedImage;

/**
 * A block is an obstacle that the player can collide with
 * @author Peter Gagliardi
 */

 class Block extends BlockBase
{
	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl):Void
	{
		super(x, y, img, creator);
	}
	
	override public function frameEvent():Void
	{
		super.frameEvent();
		
		//Check collisions with the player
		if (this.collisionTest(this.bCreator.player))
		{
			if (this.bCreator.player.invincible)
			{
				this.bCreator.player.score += 1;
				this.destroy = true;
			}
			else
				this.collisionEvent();
		}
		
		//Check collisions with the player's bullets
		for (i in this.bCreator.player.bullets)
		{
			if (this.collisionTest(i))
			{
				this.bCreator.player.score += 2;
				this.destroy = true;
			}
		}
	}
	
	//Restart the level if the player collides with a block
	public function collisionEvent():Void
	{
		this.bCreator.state = LevelState.RESTART;
	}
	
}