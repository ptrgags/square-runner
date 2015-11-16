/**
 * Input Field Class
 * Version 1.0 [SRF 1.0]
 * Last Revised on 1/1/2011
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package blynkdev;

import flash.Lib;
import flash.text.TextField;
import flash.text.TextFieldType;

/**
 * An input field is a text field formatted for text inputg
 * @author Peter Gagliardi
 */

 class InputField extends TextField
{
	public var inputText:String; //Inputted text
	
	public function new():Void
	{
		super();
		this.type = TextFieldType.INPUT;
		this.inputText = "";
		this.wordWrap = true;
		this.multiline = true;
		Lib.current.addChild(this);
	}
	
	public function delete():Void
	{
		Lib.current.removeChild(this);
	}
	
	//Execute this once every frame
	public function stepEvent(enterDwn:Bool):Void
	{
		if (enterDwn)
		{
			this.inputText = this.text;
			this.text = "";
		}
	}
	
	//Add borders around the text field
	public function setBorders(color:UInt):Void
	{
		this.border = true;
		this.borderColor = color;
	}
	
	//Set the background and foreground colors of the text field
	public function setColors(textColor:UInt, bgColor:UInt):Void
	{
		this.textColor = textColor;
		this.opaqueBackground = bgColor;
	}
	
	//Set the size and position of the text area
	public function setDimensions(x:Int, y:Int, w:Int, h:Int):Void
	{
		this.x = x;
		this.y = y;
		this.width = w-1;
		this.height = h-1;
	}
	
	//Bring text to the front of the screen
	public function bringToFront():Void
	{
		Lib.current.removeChild(this);
		Lib.current.addChild(this);
	}
}