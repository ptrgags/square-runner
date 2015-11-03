/**
 * Moneybag Class
 * Version 1.0 [SRF 1.0]
 * Last Revised on 12/28/2010
 * Copyright (c) 2010 BlynkDev Games
 */
package squarerunner.powerups;

import blynkdev.AnimatedImage;
import squarerunner.GameCtrl;

/**
 * A moneybag adds to the player's score
 * @author Peter Gagliardi
 */

 class Moneybag extends Powerup
{

	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl):Void
	{
		super(x, y, img, creator);
	}
	
	//Add to the player's score when the player picks up the powerup
	override public function pickupEvent()
	{
		super.pickupEvent();
		this.bCreator.player.score += 30;
	}
}
