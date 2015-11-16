/**
 * Tile Class
 * Last Revised on 5/16/2011 2:12 PM
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package squarerunner.tiles;

import blynkdev.AnimatedImage;
import squarerunner.BlockBase;
import squarerunner.GameCtrl;

/**
 * A tile is an object that activates when the player hovers over it
 * @author Peter Gagliardi
 */

 class Tile extends BlockBase
{
	public var hEvent:Bool;  //If the tile has executed a hover event
	
	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl ):Void
	{
		super(x, y, img, creator);
		this.hEvent = false;
	}
	
	override public function frameEvent():Void
	{
		super.frameEvent();
		
		//Check collisions with the player
		if (!this.hEvent && this.collisionTest(this.bCreator.player))
			this.hoverEvent();
	}
	
	//Execute a hover event once when the player hovers over the tile
	public function hoverEvent():Void
	{
		this.hEvent = true;
	}
}
