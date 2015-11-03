/**
 * Square Runner Flash (SRF)
 * Version 1.1
 * Last Revised on 2/9/2011
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package ;

import blynkdev.Controller;
import blynkdev.KeyboardController;
import blynkdev.MouseController;
import flash.events.Event;
import flash.events.KeyboardEvent;
import flash.events.MouseEvent;
import flash.Lib;
import func.Init;
import func.Mode0;
import func.Mode1;
import func.Mode2;
import func.Mode3;
import func.Mode4;
import func.Mode5;
import func.Mode6;
import func.Mode7;

/**
 * Program execution starts here
 * @author Peter Gagliardi
 */

class Main 
{
	static var mouse:MouseController;		//Mouse input
	static var keyboard:KeyboardController;	//Keyboard input
	static var control:Controller;			//Object that controls game
	
	//Program execution:
	//1. Initialize variables
	//2. Add event listeners
	static function main()
	{
		mouse = new MouseController();
		keyboard = new KeyboardController();
		control = new Controller(keyboard, mouse);
		
		control.addEvents(0, Mode0.init, Mode0.frame, Mode0.change, Mode0.end, null, Mode0.enter);
		control.addEvents(1, Mode1.init, Mode1.frame, Mode1.change, Mode1.end);
		control.addEvents(2, Mode2.init, Mode2.frame, Mode2.change, Mode2.end);
		control.addEvents(3, Mode3.init, Mode3.frame, Mode3.change, Mode3.end);
		control.addEvents(4, Mode4.init, Mode4.frame, Mode4.change, Mode4.end);
		control.addEvents(5);
		control.addEvents(6);
		control.addEvents(7);
		control.start(Init.start);
		
		Lib.current.addEventListener(Event.ENTER_FRAME, onEnterFrame);
		Lib.current.stage.addEventListener(KeyboardEvent.KEY_DOWN, keyDown);
		Lib.current.stage.addEventListener(KeyboardEvent.KEY_UP, keyUp);
		Lib.current.stage.addEventListener(MouseEvent.MOUSE_DOWN, mouseDown);
		Lib.current.stage.addEventListener(MouseEvent.MOUSE_UP, mouseUp);
	}
	
	//Execute this once every frame:
	//1. exectute the controller's step event
	static function onEnterFrame(event:Event):Void
	{
		control.frameEvent();
		mouse.refresh();
		keyboard.refresh();
	}
	
	//Update pressed keys
	static function keyDown(event:KeyboardEvent):Void
	{
		keyboard.keyDown(event);
	}
	
	//Update released keys
	static function keyUp(event:KeyboardEvent):Void
	{
		keyboard.keyUp(event);
	}
	
	//Update pressed mouse buttons
	static function mouseDown(event:MouseEvent):Void
	{
		mouse.mouseDown(event);
	}
	
	//Update released mouse buttons
	static function mouseUp(event:MouseEvent):Void
	{
		mouse.mouseUp(event);
	}
}
