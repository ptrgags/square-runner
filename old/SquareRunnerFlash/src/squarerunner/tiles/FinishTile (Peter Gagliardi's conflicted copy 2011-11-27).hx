/**
 * Finish Tile Class
 * Version 1.0 [SRF 1.0]
 * Last Revised on 12/28/2010
 * Copyright (c) 2010 BlynkDev Games
 */
package squarerunner.tiles;

import blynkdev.AnimatedImage;

/**
 * A finish tile marks the end of the level
 * @author Peter Gagliardi
 */

class FinishTile extends Tile
{
	public function new(x:Int, y:Int, img:AnimatedImage, creator:GameCtrl ):Void
	{
		super(x, y, img, creator);
	}
	
	//When the player hovers over a finish tile, bring the level to an end
	override public function hoverEvent():Void
	{
		super.hoverEvent();
		
		if (this.bCreator.timer.time == -1)
		{
			this.bCreator.timer.time = 100;
			this.bCreator.state = LevelState.COMPLETE;
		}
	}
}