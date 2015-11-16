/**
 * Lock Class
 * Last Revised on 5/16/2011 2:21 PM
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package squarerunner.blocks;

import blynkdev.AnimatedImage;

/**
 * A lock can only be passed if the player has a key
 * @author Peter Gagliardi
 */

 class Lock extends Block
{
	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl):Void
	{
		super(x, y, img, creator);
	}
	
	//Destroy the lock if the player has a key
	override public function collisionEvent():Void
	{
		if (this.bCreator.player.key)
			this.destroy = true;
		else
			super.collisionEvent();
	}	
}