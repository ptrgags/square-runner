/**
 * Powerup Class
 * Last Revised on 5/16/2011 2:08 PM
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package squarerunner.powerups;

import blynkdev.AnimatedImage;

/**
 * A powerup is an object the player can pick up
 * @author Peter Gagliardi
 */

class Powerup extends BlockBase
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
			this.pickupEvent();
	}
	
	//Destroy the powerup when the player picks it up
	public function pickupEvent():Void
	{
		this.destroy = true;
	}
}