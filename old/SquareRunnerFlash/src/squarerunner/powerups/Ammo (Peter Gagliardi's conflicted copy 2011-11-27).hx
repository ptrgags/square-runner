/**
 * Ammo Class
 * Last Revised on 5/16/2011 2:14 PM
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package squarerunner.powerups;

import blynkdev.AnimatedImage;

/**
 * An ammo powerup adds to the player's ammo
 * @author Peter Gagliardi
 */

 class Ammo extends Powerup
{
	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl):Void
	{
		super(x, y, img, creator);
	}
	
	//Give the player more ammo when the powerup is picked up
	override public function pickupEvent():Void
	{
		super.pickupEvent();
		this.bCreator.player.ammo += 3;
	}
	
}