/**
 * Minus Cash Tile Class
 * Version 1.0 [SRF 1.0]
 * Last Revised on 12/28/2010
 * Copyright (c) 2010 BlynkDev Games
 */
package squarerunner.tiles;

import blynkdev.AnimatedImage;
import squarerunner.GameCtrl;

/**
 * A minus cash tile subtracts from the player's score
 * @author Peter Gagliardi
 */

 class MinusCashTile extends Tile
{

	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl):Void
	{
		super(x, y, img, creator);
	}
	
	//Subtract from the player's score when the player hovers over the tile
	override public function hoverEvent():Void
	{
		super.hoverEvent();
		this.bCreator.player.score -= 20;
	}
	
}
