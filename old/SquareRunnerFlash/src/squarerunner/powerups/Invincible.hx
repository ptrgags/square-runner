/**
 * Invincibility Powerup Class
 * Last Revised on 5/16/2011 2:23 PM
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package squarerunner.powerups;

import blynkdev.AnimatedImage;
import squarerunner.GameCtrl;

/**
 * An invincibility powerup makes the player invincible temporarily
 * @author Peter Gagliardi
 */

class Invincible extends Powerup
{

	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl):Void 
	{
		super(x, y, img, creator);
	}
	
	//Make the player invincible when the player picks up the powerup
	override public function pickupEvent():Void
	{
		super.pickupEvent();
		this.bCreator.player.invincible = true;
		this.bCreator.player.iTimer.time = 70;
	}
	
}
