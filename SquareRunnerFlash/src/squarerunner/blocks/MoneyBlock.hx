/**
 * Money Block Class
 * Version 1.0 [SRF 1.0]
 * Last Revised on 12/28/2010
 * Copyright (c) 2010 BlynkDev Games
 */
package squarerunner.blocks;

import blynkdev.AnimatedImage;
import squarerunner.GameCtrl;

/**
 * A money block adds to the player's score if it is destroyed by the player
 * @author Peter Gagliardi
 */

class MoneyBlock extends Block
{

	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl):Void
	{
		super(x, y, img, creator);
	}
	
	//Execute this once every frame:
	//1. execute superclass' step event
	//2. check if the player is invincible and collided with the block
	//3. check if a bullet collided with the block
	override public function frameEvent():Void
	{
		super.frameEvent();
			
		if (this.collisionTest(this.bCreator.player) && this.bCreator.player.invincible)
			this.bCreator.player.score += 50;
		
		for (i in this.bCreator.player.bullets)
		{
			if (this.collisionTest(i))
				this.bCreator.player.score += 50;
		}
	}
	
}
