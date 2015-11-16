/**
 * Normal Game Controller Class
 * Version 1.1 [SRF 1.1]
 * Last Revised on 5/11/2011 2:28 PM
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

class NormalGameController extends GameCtrl
{
	public var ctrl:Controller;			//Controller
	public var lvlData:SRFLevel;		//Level Information
	public var grid:ObjGrid;			//Objects
	//public var player:Player;			//Player
	//public var state:LevelState;		//State of the current level
	public var text:OutputBox;			//Level text
	public var pause:Bool;				//If the game is paused
	//public var speed:Int;				//How fast the blocks are moving
	//public var timer:FrameTimer;		//Counts down to the end of the level
	//public var sTimer:FrameTimer;		//Timer for slow powerup
	public var pActive:Bool;			//If the 'P' key is active
	public var escape:Bool;				//If the level should end early
	public var level:Int;				//Current Level
	//public var lineCount:Int;			//Counts pixels until next line
	
	public function new(ctrl:Controller,level:Int):Void
	{
		var const:Const = new Const();
		
		super(ctrl);
		this.ctrl = ctrl;
		this.lvlData = ctrl.global[4][level];
		this.grid = new ObjGrid(ctrl.keys, ctrl.mouse);
		this.player = new Player(176,432,const.SPR_PLAYER.copyOf());
		this.player.setInput(ctrl.keys, ctrl.mouse);
		//this.state = LevelState.START;
		this.text = new OutputBox();
		this.pause = false;
		//this.speed = 8;
		//this.timer = new FrameTimer();
		this.sTimer = new FrameTimer();
		this.pActive = true;
		this.escape = false;
		this.level = 0;
		this.lineCount = 0;
		
		this.text.setDimensions(400,0,320,320);
		this.text.setColors(0xFFFFFF, 0x000000);
		this.text.setTextAlign(TextFormatAlign.CENTER);
	}
	
	override public function delete():Void
	{
		this.grid.delete();
		this.player.delete();
		this.text.delete();
	}
	
	override public function frameEvent():Void
	{
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
				this.createLines();
				this.lineCount += this.speed;
				this.moveBlocks();
				this.grid.frameEvent();
				this.player.bringToFront();
				this.player.frameEvent();
			}
			case LevelState.COMPLETE:
			{
				this.text.outputText("Level Complete!");
				this.moveBlocks();
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
			case LevelState.RESTART:
				this.text.outputText("");
		}
	}
	
	public function createLines():Void
	{
		var lines:Int = getLineQueue();
		if (lines == 0)
			return;
		for (i in 0...lines)
			createLine(this.lineCount);
	}
	
	public function moveBlocks():Void
	{
		for (i in this.grid.getAll())
			i.move();
	}
	
	/*
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
	*/
	
	//TODO: Fix createLine and getObject
	public function createLine(offset:Int):Void
	{
		var obj:ObjBase;
		var index = this.grid.addType();
		for (i in 0...20)
		{
			obj = getObject(i,index,offset);
			if (obj != null)
				this.grid.addObject(index, obj);
		}
	}
	
	public function getObject(x:Int, y:Int, offset:Int):ObjBase
	{
		var const:Const = new Const();
		var obj:ObjBase;
		var symbol:String = this.lvlData.line[y].charAt(x);
		
		switch (symbol)
		{
			case "1":
				obj = new Block (x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_BLOCK.copyOf(), this);
			case "2":
				obj = new MovingBlock(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_MOVING_BLOCK_LEFT.copyOf(), -3, this);
			case "3":
				obj = new Invincible(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_INVINCIBLE.copyOf(), this);
			case "4":
				obj = new Slow(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_SLOW.copyOf(), this);
			case "5":
				obj = new FinishTile(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_FINISH_TILE.copyOf(), this);
			case "6":
				obj = new Ammo(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_AMMO.copyOf(), this);
			case "7":
				obj = new MovingBlock(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_MOVING_BLOCK.copyOf(), 3, this);
			case "8":
				obj = new Moneybag(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_MONEYBAG.copyOf(), this);
			case "9":
				obj = new Key(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_KEY.copyOf(), this);
			case "A":
				obj = new BreakBlock(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_BREAK_BLOCK.copyOf(), this);
			case "B":
				obj = new Lock(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_LOCK.copyOf(), this);
			case "C":
				obj = new GoldTile(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_GOLD_TILE.copyOf(), this);
			case "D":
				obj = new MinusCashTile(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_MINUS_CASH.copyOf(), this);
			case "E":
				obj = new ReloadTile(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_RELOAD_TILE.copyOf(), this);
			case "F":
				obj = new MoneyBlock(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_MONEY_BLOCK.copyOf(), this);
			case "G":
				obj = new FlipTile(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_FLIP_TILE.copyOf(), true, this);
			case "H":
				obj = new FlipTile(x * 16 + const.NUM_GAME_X, offset + const.NUM_GAME_Y, const.SPR_FLIP_TILE2.copyOf(), false, this);
			default:
				obj = null;
		}
		return obj;
	}
}