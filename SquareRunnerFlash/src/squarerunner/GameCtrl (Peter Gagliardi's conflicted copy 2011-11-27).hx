/**
 * Generic Game Controller Class
 * Copyright (c) 2011 BlynkDev Games
 */
package squarerunner;
import blynkdev.Controller;
import blynkdev.ObjBase;
import blynkdev.ObjGrid;
import blynkdev.OutputBox;
import blynkdev.FrameTimer;
import flash.text.TextFormatAlign;
import squarerunner.blocks.Block;
import squarerunner.blocks.BreakBlock;
import squarerunner.blocks.Lock;
import squarerunner.blocks.MoneyBlock;
import squarerunner.blocks.MovingBlock;
import squarerunner.tiles.FinishTile;
import squarerunner.tiles.FlipTile;
import squarerunner.tiles.GoldTile;
import squarerunner.tiles.MinusCashTile;
import squarerunner.tiles.ReloadTile;
import squarerunner.powerups.Ammo;
import squarerunner.powerups.Invincible;
import squarerunner.powerups.Key;
import squarerunner.powerups.Moneybag;
import squarerunner.powerups.Slow;

/**
 * ...
 * @author Peter Gagliardi
 */

class GameCtrl
{
	/*
	public var ctrl:Controller;			//Controller
	public var lvlData:SRFLevel;		//Level Information
	public var grid:ObjGrid;			//Objects
	*/
	public var player:Player;			//Player
	public var state:LevelState;		//State of the current level
	/*
	public var text:OutputBox;			//Level text
	public var pause:Bool;				//If the game is paused
	*/
	public var speed:Int;				//How fast the blocks are moving
	public var timer:FrameTimer;		//Counts down to the end of the level
	public var sTimer:FrameTimer;		//Timer for slow powerup
	/*
	public var pActive:Bool;			//If the 'P' key is active
	public var escape:Bool;				//If the level should end early
	public var level:Int;				//Current Level
	*/
	public var lineCount:Int;			//Counts pixels until next line
	
	public function new(ctrl:Controller/*,level:Int*/):Void
	{
		/*
		this.ctrl = ctrl;
		this.lvlData = ctrl.global[4][level];
		this.grid = new ObjGrid(ctrl.keys, ctrl.mouse);*/
		//this.player = new Player(/*x*/,/*y*/,/*spr*/);
		this.state = LevelState.START;
		/*
		this.text = new OutputBox();
		this.pause = false;
		*/
		this.speed = 8;
		this.timer = new FrameTimer();
		this.sTimer = new FrameTimer();
		/*
		this.pActive = true;
		this.escape = false;
		this.level = 0;
		this.lineCount = 0;
		*/
		
		//this.text.setDimensions(/*x*/,/*y*/,/*w*/,/*h*/);
		/*this.text.setColors(0xFFFFFF, 0x000000);
		this.text.setTextAlign(TextFormatAlign.CENTER);*/
		
		//Create first line of objects
		//this.createObjectLine(0);
	}
	
	public function delete():Void
	{
		/*
		this.grid.delete();
		this.player.delete();
		this.text.delete();
		*/
	}
	
	public function frameEvent():Void
	{
		/*
		var const:Const = new Const();
		
		if (this.ctrl.keys.isDown(const.KEY_P))
		{
			if (this.pActive)
				this.pause = !this.pause;
			this.pActive = false;
		}
		else
			this.pActive = true;
		
		if (this.ctrl.keys.isDown(const.KEY_ESC))
			this.escape = true;
			
		switch (this.state)
		{
			case LevelState.START:
			{
				this.text.outputText("Press Space to start");
				this.pause = false;
				if (this.ctrl.keys.isDown(const.KEY_SPACEBAR))
					this.state = LevelState.NORMAL;
			}
			case LevelState.NORMAL:
			{
				this.text.outputText("LEVEL TEXT");
				if (this.escape)
					this.state = LevelState.ESCAPE;
				else
				if (!this.pause)
				{
					this.sTimer.frameEvent();
					if (this.sTimer.time == 0)
						this.speed = 8;
				}
				//this.createCurrentLine();
				//Move Blocks
				this.grid.frameEvent();
				this.player.frameEvent();
				
			}
			case LevelState.COMPLETE:
			{
				this.text.outputText("Level Complete!");
				//Move Blocks
				this.grid.frameEvent();
				
				this.player.bringToFront();
				this.player.frameEvent();
				
				this.timer.frameEvent();
				if (this.timer.time == 0)
					this.state = LevelState.END;
			}
			case LevelState.END:
				this.text.outputText("");
			case LevelState.ESCAPE:
				this.text.outputText("");
		}
		
		*/
	}
	
	/*
	public function createLines():Void
	{
		
		var lines:Int = getLineQueue();
		if (lines == 0)
			return;
		//for (i in 0...lines)
		//	createLines(this.lineCount);
		
	}
	*/
	
	public function getLineQueue():Int
	{
		var count:Int = 0;
		while (16 < this.lineCount)
		{
			count++;
			lineCount -= 16;
		}
		return count;
	}
	
	/*
	public function createLine(offset:Int):Void
	{
		var index = this.grid.addType();
		for (i in 0...20)
			this.grid.addObject(index, getObject(i, index, offset));	
	}
	*/
		
}