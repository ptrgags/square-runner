/**
 * Slow Powerup Class
 * Last Revised on 5/16/2011 2:25 PM
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package squarerunner.powerups;

import blynkdev.AnimatedImage;

/**
 * A slow powerup slows down the blocks falling towards the player
 * @author Peter Gagliardi
 */

 class Slow extends Powerup
{
	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl):Void
	{
		super(x, y, img, creator);
	}
	
	//Slow down the falling blocks temporarily
	override public function pickupEvent():Void
	{
		super.pickupEvent();
		this.bCreator.sTimer.time = 80;
		this.bCreator.speed = 2;
	}
	
}