/**
 * Controller Class
 * Version 1.0 [APOC2 1.0]
 * Last Revised on 1/1/2011
 * Copyright (c) 2010-2011 BlynkDev Games
 */

package blynkdev;

import flash.Lib;

/**
 * A Controller is a generic program handler
 * @author Peter Gagliardi
 */

class Controller 
{
	public var keys:KeyboardController;			//Keyboard Input
	public var mouse:MouseController;			//Mouse Input
	public var pause:Bool;						//If the mode is paused
	public var mode:Int;						//Mode
	public var phase:Int;						//Phase for loops
	public var nextMode:Int;					//Next mode
	public var initFunc:Array<Dynamic>;			//Initialize Phase
	public var enterFunc:Array<Dynamic>;		//Enter Phase
	public var frameFunc:Array<Dynamic>;		//frame Phase
	public var pauseFunc:Array<Dynamic>;		//Pause Phase
	public var changeFunc:Array<Dynamic>;		//Change Phase
	public var exitFunc:Array<Dynamic>;			//Exit Phase
	public var endFunc:Array<Dynamic>;			//End Phase
	public var vars:Array<Dynamic>;
	public var global:Array<Dynamic>;
	public var grid:ObjGrid;

	public function new(keys:KeyboardController, mouse:MouseController) 
	{
		this.keys = keys;
		this.mouse = mouse;
		this.pause = false;
		this.mode = 0;
		this.phase = 0;
		this.initFunc = new Array<Dynamic>();
		this.enterFunc = new Array<Dynamic>();
		this.frameFunc = new Array<Dynamic>();
		this.pauseFunc = new Array<Dynamic>();
		this.changeFunc = new Array<Dynamic>();
		this.exitFunc = new Array<Dynamic>();
		this.endFunc = new Array<Dynamic>();
		this.vars = new Array<Dynamic>();
		this.global = new Array<Dynamic>();
		this.grid = new ObjGrid(keys, mouse);
	}
	
	public function frameEvent()
	{
		if (this.phase == 1)
		{
			if (!this.enterFunc[this.mode](this))
				this.phase = 2;
		}
		else
		if (this.phase == 2)
		{
			if (!this.pause)
			{
				this.frameFunc[this.mode](this);
				this.changeMode();
			}
			else
			this.pauseFunc[this.mode](this);
		}
		else
		if (this.phase == 3)
		{
			if (!this.exitFunc[this.mode](this))
			{
				this.terminateMode();
				this.mode = this.nextMode;
				this.phase = 0; 
				this.initMode();
			}
		}
	}
	
	public function addEvents(mode:Int, ?init:Dynamic, ?frame:Dynamic, ?change:Dynamic, ?terminate:Dynamic, ?pause:Dynamic, ?enter:Dynamic, ?exit:Dynamic)
	{
		if (init != null)
			this.initFunc[mode] = init;
		else
			this.initFunc[mode] = function (ctrl:Controller) { };
		
		if (enter != null)
			this.enterFunc[mode] = enter;
		else
			this.enterFunc[mode] = function (ctrl:Controller) { return false; };
			
		if (frame != null)
			this.frameFunc[mode] = frame;
		else
			this.frameFunc[mode] = function (ctrl:Controller) { };
			
		if (pause != null)
			this.pauseFunc[mode] = pause;
		else
			this.pauseFunc[mode] = function (ctrl:Controller) { };
			
		if (change != null)
			this.changeFunc[mode] = change;
		else
			this.changeFunc[mode] = function (ctrl:Controller) { return -1; };
			
		if (exit != null)
			this.exitFunc[mode] = exit;
		else
			this.exitFunc[mode] = function (ctrl:Controller) { return false; };
			
		if (terminate != null)
			this.endFunc[mode] = terminate;
		else
			this.endFunc[mode] = function (ctrl:Controller) { };
	}
	
	public function initMode()
	{
		this.grid = new ObjGrid(this.keys, this.mouse);
		this.initFunc[this.mode](this);
		this.phase = 1;
	}
	
	public function changeMode()
	{
		this.nextMode = this.changeFunc[this.mode](this);
		if (this.nextMode != -1)
			this.phase = 3;
	}
	
	public function terminateMode()
	{
		this.endFunc[this.mode](this);
		Lib.current.stage.focus = Lib.current.stage;
	}
	
	public function start(startFunc:Dynamic)
	{
		startFunc(this);
		this.initMode();
	}
	
}
