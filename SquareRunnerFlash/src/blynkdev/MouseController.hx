/**
 * Mouse Controller Class
 * Version 1.0 [APOC2 1.0]
 * Last Revised on 1/1/2011
 * Copyright (c) 2011 BlynkDev Games
 */
package blynkdev;
import flash.events.MouseEvent;
import flash.Lib;

/**
 * A mouse conroller handles mouse input
 * @author Peter Gagliardi
 */
class MouseController 
{
	public var button:KeyState;		//state of the left mouse button
	public var holdCount:Int;		//frames the button is held down
	public var x:Float;				//Mouse x position
	public var y:Float;				//Mouse y position
	
	public function new() 
	{
		this.button = KeyState.NORMAL;
		this.holdCount = 0;
		this.x = 0;
		this.y = 0;
	}
	
	public function refresh():Void
	{
		this.x = Lib.current.mouseX;
		this.y = Lib.current.mouseY;
		if (this.button == KeyState.PRESSED)
			this.button = KeyState.HELD;
		else
		if (this.button == KeyState.HELD)
			this.holdCount++;
		else
		if (this.button == KeyState.RELEASED)
			this.button = KeyState.NORMAL;
	}
	
	public function mouseDown(event:MouseEvent):Void
	{
		if (button == KeyState.PRESSED)
			button = KeyState.HELD;
		else
		if (button != KeyState.HELD)
			button = KeyState.PRESSED;
	}
	
	public function mouseUp(event:MouseEvent):Void
	{
		if (button == KeyState.PRESSED)
			button = KeyState.RELEASED;
		else
		if (button == KeyState.HELD)
		{
			button = KeyState.RELEASED;
			holdCount = 0;
		}
	}
	
	public function isDown():Bool
	{
		return this.button == KeyState.PRESSED
		|| this.button == KeyState.HELD;
	}
	
	public function isPressed():Bool
	{
		return this.button == KeyState.PRESSED;
	}
	
	public function isHeld(?framesHeld:Int):Bool
	{
		var held:Bool = this.button == KeyState.HELD;
		
		if (framesHeld != null)
			held = held && this.holdCount >= framesHeld;
		
		return held;
	}
	
	public function isReleased():Bool
	{
		return this.button == KeyState.RELEASED;
	}
	
	public function getHoldCount():Int
	{
		return this.holdCount;
	}
	
	public function getX():Float
	{
		return this.x;
	}
	
	public function getY():Float
	{
		return this.y;
	}
	
	public function isInsideRect(x1:Int, y1:Int, x2:Int, y2:Int):Bool
	{
		return (this.x >= x1 && this.x < x2) && (this.y >= y1 && this.y < y2);
	}
	
}