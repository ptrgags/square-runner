/**
 * Flip Tile Class
 * Version 1.0 [SRF 1.0]
 * Last Revised on 12/28/2010
 * Copyright (c) 2010 BlynkDev Games
 */
package squarerunner.tiles;

import blynkdev.AnimatedImage;
import squarerunner.GameCtrl;

/**
 * A flip tile reverses the user's controls 
 * @author Peter Gagliardi
 */

 class FlipTile extends Tile
{
	public var reverse:Bool;	//Whether the tile reverses the controls or sets them back to normal
	
	public function new(x:Int, y:Int, img:AnimatedImage, reverse:Bool, creator:GameCtrl ):Void
	{
		super(x, y, img, creator);
		this.reverse = reverse;
	}

	//Reverse the conrols or set them back to normal when the player hovers over the tile
	override public function hoverEvent():Void
	{
		super.hoverEvent();
		this.bCreator.player.flip = this.reverse;
	}
	
}
