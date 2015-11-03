/**
 * Block Base Class
 * Last Revised on 5/16/2011 1:55 PM
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package squarerunner;

import blynkdev.AnimatedImage;
import blynkdev.ObjBase;

/**
 * A block base is the base for objects that will fall towards the player
 * @author Peter Gagliardi
 */

class BlockBase extends ObjBase
{
	public var bCreator:GameCtrl;  //Parent object
	
	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl ):Void
	{
		super(x, y, img);
		this.bCreator = creator;
	}
	
	//Move the object down the screen
	public function move():Void
	{
		var const = new Const(false);
		this.vSpeed = this.bCreator.speed;
		
		if (this.y >= const.NUM_GAME_Y + const.NUM_GAME_H)
		this.destroy = true;
	}
}