/**
 * Keyboard Controller Class
 * Version 1.0 [APOC2 1.0]
 * Last Revised on 1/1/2011
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package blynkdev;

import flash.events.KeyboardEvent;

/**
 * A keyboard controller handles keyboard input
 * @author Peter Gagliardi
 */

 class KeyboardController 
{
	public var keys:Array<KeyState>;	//State of every key
	public var holdCount:Array<Int>;	//Number of frames a key is held

	public function new() 
	{
		this.keys = new Array<KeyState>();
		this.holdCount = new Array<Int>();
		
		for (i in 0...225)
			this.keys[i] = KeyState.NORMAL;
		for (i in 0...225)
			this.holdCount[i] = 0;
	}
	
	//Key press handling
	public function keyDown(event:KeyboardEvent):Void
	{
		if (this.keys[event.keyCode] == KeyState.PRESSED)
			this.keys[event.keyCode] = KeyState.HELD;
		else
		if (this.keys[event.keyCode] != KeyState.HELD)
			this.keys[event.keyCode] = KeyState.PRESSED;
	}
	
	//Key release handling
	public function keyUp(event:KeyboardEvent):Void
	{
		if (this.keys[event.keyCode] == KeyState.HELD)
		{
			this.keys[event.keyCode] = KeyState.RELEASED;
			this.holdCount[event.keyCode] = 0;
		}
		else
		if (this.keys[event.keyCode] == KeyState.PRESSED)
			this.keys[event.keyCode] = KeyState.RELEASED;
	}
	
	//Refresh keyboard input
	public function refresh():Void
	{
		for (i in 0...225)
		{
			if (this.keys[i] == KeyState.RELEASED)
				this.keys[i] = KeyState.NORMAL;
			else
			if (this.keys[i] == KeyState.HELD)
			{
				this.holdCount[i]++;
			}
		}
	}
	
	//Check if a key is down
	public function isDown(keyCode:Int):Bool
	{
		return this.keys[keyCode] == KeyState.PRESSED 
		|| this.keys[keyCode] == KeyState.HELD;
	}
	
	//Check if a key was pressed this frame
	public function isPressed(keyCode:Int)
	{
		return this.keys[keyCode] == KeyState.PRESSED;
	}
	
	//Check if a key is being held
	public function isHeld(keyCode:Int, ?framesHeld:Int):Bool
	{
		var held:Bool = this.keys[keyCode] == KeyState.HELD;
		
		if (framesHeld != null)
			held = held && this.holdCount[keyCode] >= framesHeld;
		
		return held;
	}
	
	//Check if a key was released this frame
	public function isReleased(keyCode:Int)
	{
		return this.keys[keyCode] == KeyState.RELEASED;
	}
	
	//Returns the number of frames a key has been held
	public function getHoldCount(keyCode:Int)
	{
		return this.holdCount[keyCode];
	}
	
	public function noKeyIsPressed():Bool
	{
		return true;
	}
	
	//Get key code from a key name
	public static function getKeyCode(keyName:KeyName):Int
	{
		switch (keyName)
		{
			case KeyName.BACKSPACE:
				return 8;
			case KeyName.TAB:
				return 9;
			case KeyName.ENTER:
				return 13;
			case KeyName.SHIFT:
				return 16;
			case KeyName.CTRL:
				return 17;
			case KeyName.PAUSE:
				return 19;
			case KeyName.CAPS_LOCK:
				return 20;
			case KeyName.ESC:
				return 27;
			case KeyName.SPACE:
				return 32;
			case KeyName.PAGE_UP:
				return 33;
			case KeyName.PAGE_DOWN:
				return 34;
			case KeyName.END:
				return 35;
			case KeyName.HOME:
				return 36;
			case KeyName.LEFT_ARROW:
				return 37;
			case KeyName.RIGHT_ARROW:
				return 39;
			case KeyName.UP_ARROW:
				return 38;
			case KeyName.DOWN_ARROW:
				return 40;
			case KeyName.INSERT:
				return 45;
			case KeyName.DELETE:
				return 46;
			case KeyName.NUM_0:
				return 48;
			case KeyName.NUM_1:
				return 49;
			case KeyName.NUM_2:
				return 50;
			case KeyName.NUM_3:
				return 51;
			case KeyName.NUM_4:
				return 52;
			case KeyName.NUM_5:
				return 53;
			case KeyName.NUM_6:
				return 54;
			case KeyName.NUM_7:
				return 55;
			case KeyName.NUM_8:
				return 56;
			case KeyName.NUM_9:
				return 57;
			case KeyName.LETTER_A:
				return 65;
			case KeyName.LETTER_B:
				return 66;
			case KeyName.LETTER_C:
				return 67;
			case KeyName.LETTER_D:
				return 68;
			case KeyName.LETTER_E:
				return 69;
			case KeyName.LETTER_F:
				return 70;
			case KeyName.LETTER_G:
				return 71;
			case KeyName.LETTER_H:
				return 72;
			case KeyName.LETTER_I:
				return 73;
			case KeyName.LETTER_J:
				return 74;
			case KeyName.LETTER_K:
				return 75;
			case KeyName.LETTER_L:
				return 76;
			case KeyName.LETTER_M:
				return 77;
			case KeyName.LETTER_N:
				return 78;
			case KeyName.LETTER_O:
				return 79;
			case KeyName.LETTER_P:
				return 80;
			case KeyName.LETTER_Q:
				return 81;
			case KeyName.LETTER_R:
				return 82;
			case KeyName.LETTER_S:
				return 83;
			case KeyName.LETTER_T:
				return 84;
			case KeyName.LETTER_U:
				return 85;
			case KeyName.LETTER_V:
				return 86;
			case KeyName.LETTER_W:
				return 87;
			case KeyName.LETTER_X:
				return 88;
			case KeyName.LETTER_Y:
				return 89;
			case KeyName.LETTER_Z:
				return 90;
			case KeyName.NUMPAD_0:
				return 96;
			case KeyName.NUMPAD_1:
				return 97;
			case KeyName.NUMPAD_2:
				return 98;
			case KeyName.NUMPAD_3:
				return 99;
			case KeyName.NUMPAD_4:
				return 100;
			case KeyName.NUMPAD_5:
				return 101;
			case KeyName.NUMPAD_6:
				return 102;
			case KeyName.NUMPAD_7:
				return 103;
			case KeyName.NUMPAD_8:
				return 104;
			case KeyName.NUMPAD_9:
				return 105;
			case KeyName.NUMPAD_ASTERISK:
				return 106;
			case KeyName.NUMPAD_PLUS:
				return 107;
			case KeyName.NUMPAD_MINUS:
				return 109;
			case KeyName.NUMPAD_DOT:
				return 110;
			case KeyName.NUMPAD_SLASH:
				return 111;
			case KeyName.F1:
				return 112;
			case KeyName.F2:
				return 113;
			case KeyName.F3:
				return 114;
			case KeyName.F4:
				return 115;
			case KeyName.F5:
				return 116;
			case KeyName.F6:
				return 117;
			case KeyName.F7:
				return 118;
			case KeyName.F8:
				return 119;
			case KeyName.F9:
				return 120;
			case KeyName.F10:
				return 121;
			case KeyName.F11:
				return 122;
			case KeyName.F12:
				return 123;
			case KeyName.NUM_LOCK:
				return 144;
			case KeyName.SCROLL_LOCK:
				return 145;
			case KeyName.SEMICOLON:
				return 186;
			case KeyName.EQUALS:
				return 187;
			case KeyName.COMMA:
				return 188;
			case KeyName.MINUS:
				return 189;
			case KeyName.PERIOD:
				return 190;
			case KeyName.SLASH:
				return 191;
			case KeyName.ACCENT:
				return 192;
			case KeyName.LEFT_BRACKET:
				return 219;
			case KeyName.BACKSLASH:
				return 220;
			case KeyName.RIGHT_BRACKET:
				return 221;
			case KeyName.APOSTROPHE:
				return 222;
		}
	}
	
}