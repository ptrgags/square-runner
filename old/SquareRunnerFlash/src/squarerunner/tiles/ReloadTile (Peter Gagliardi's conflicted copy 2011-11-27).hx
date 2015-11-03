/**
 * Reload Tile Class
 * Version 1.0 [SRF 1.0]
 * Last Revised on 12/28/2010
 * Copyright (c) 2010 BlynkDev Games
 */
package squarerunner.tiles;

import blynkdev.AnimatedImage;

/**
 * A reload tile adds to the player's ammo and score
 * @author Peter Gagliardi
 */

class ReloadTile extends Tile
{
	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl):Void
	{
		super(x, y, img, creator);
	}
	
	//Add to the player's ammo and score when the player hovers over the tile
	override public function hoverEvent():Void
	{
		super.hoverEvent();
		this.bCreator.player.ammo += 6;
		this.bCreator.player.score += 10;
	}
	
}