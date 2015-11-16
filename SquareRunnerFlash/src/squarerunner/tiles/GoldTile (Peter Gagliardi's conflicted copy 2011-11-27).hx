/**
 * Gold Tile Class
 * Last Revised on 5/17/2011 2:10 PM
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package squarerunner.tiles;

import blynkdev.AnimatedImage;

/**
 * A gold tile adds to the player's score when the player hovers over it
 * @author Peter Gagliardi
 */

class GoldTile extends Tile
{
	public function new(x:Int,y:Int,img:AnimatedImage,creator:GameCtrl):Void
	{
		super(x, y, img, creator);
	}
	
	//Add to the player's score when the player hovers over the tile
	override public function hoverEvent():Void
	{
		super.hoverEvent();
		this.bCreator.player.score += 20;
	}
	
}