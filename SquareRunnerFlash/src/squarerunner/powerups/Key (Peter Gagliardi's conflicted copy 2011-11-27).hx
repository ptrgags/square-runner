/**
 * Key Class
 * Last Revised on 5/20/2011 9:08 AM
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package squarerunner.powerups;

import blynkdev.AnimatedImage;

/**
 * A key lets the player get past locks
 * @author Peter Gagliardi
 */

 class Key extends Powerup
{

	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl):Void
	{
		super(x, y, img, creator);
	}
	
	//Give the player a key when the powerup is picked up
	override public function pickupEvent():Void
	{
		super.pickupEvent();
		this.bCreator.player.key = true;
	}
}