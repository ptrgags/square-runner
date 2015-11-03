/**
 * Bullet Class
 * Version 1.0 [SRF 1.0]
 * Last Revised on 12/28/2010
 * Copyright (c) 2010 BlynkDev Games
 */
package squarerunner;

import blynkdev.AnimatedImage;
import blynkdev.ObjBase;

/**
 * A bullet is an object that the player can fire at the oncoming blocks
 * @author Peter Gagliardi
 */

 class Bullet extends ObjBase
{
	public var dead:Bool;
	
	public function new(x:Int, y:Int, img:AnimatedImage ):Void
	{
		super(x, y, img);
		this.vSpeed = -8;
		this.dead = false;
	}
	
	override public function frameEvent()
	{
		var const = new Const(false);
		super.frameEvent();
		if (this.y <= const.NUM_GAME_Y && !this.dead)
		{
			this.vSpeed = 0;
			this.delete();
			this.dead = true;
		}
			//this.destroy = true;
	}
}
