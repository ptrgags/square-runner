/**
 * Breakable Block Class
 * Last Revised on 5/16/2011 2:19 PM
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package squarerunner.blocks;

import blynkdev.AnimatedImage;

/**
 * A breakable block is destroyed on contact with the player, just for effect
 * @author Peter Gagliardi
 */

 class BreakBlock extends Block
{
	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl):Void
	{
		super(x, y, img, creator);
	}
	
	//Destroy block on contact with player
	override public function collisionEvent():Void
	{
		this.destroy = true;
	}
	
}