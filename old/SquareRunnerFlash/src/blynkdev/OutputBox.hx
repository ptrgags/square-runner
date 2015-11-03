/**
 * Output Box Class
 * Version 1.1 [SRF 1.1]
 * Last Revised on 1/1/2011
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package blynkdev;

import flash.display.Shape;
import flash.Lib;
import flash.text.TextField;
import flash.text.TextFormat;
import flash.text.TextFormatAlign;

/**
 * An output box is a text field formatted for text output
 * @author Peter Gagliardi
 */

 class OutputBox extends TextField
{
	//Version 1.1 Variables:====================
	public var back:Shape;			//Semi-transparent background
	public var format:TextFormat;	//Text formatting
	//1.1=======================================
	
	public function new():Void
	{
		super();
		this.selectable = false;
		this.wordWrap = true;
		this.multiline = true;
		this.back = new Shape();
		this.format = new TextFormat();
		
		Lib.current.addChild(this.back);
		Lib.current.addChild(this);
	}
	
	public function delete():Void
	{
		Lib.current.removeChild(this);
		Lib.current.removeChild(this.back);
	}
	
	//Version 1.0 Functions:----------------------------------------
	
	//Add borders around the text field
	public function setBorders(color:UInt):Void
	{
		this.border = true;
		this.borderColor = color;
	}
	
	//Set the background and foreground colors of the text field
	//-1.1 bgColor changed from UInt to Null<UInt> for no background color
	public function setColors(textColor:UInt, bgColor:Null<UInt>):Void
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
	
	//Display some text
	public function outputText(text:String):Void
	{
		this.text = text;
	}
	
	//Bring text to the front of the screen
	//-1.1 transparent background can be brought to front with text
	public function bringToFront():Void
	{
		Lib.current.removeChild(this);
		Lib.current.removeChild(this.back);
		Lib.current.addChild(this.back);
		Lib.current.addChild(this);
	}
	
	//1.0-----------------------------------------------------------
	
	//Version 1.1 Functions:----------------------------------------
	
	//Set a transparent background
	public function setTransparency(color:UInt,alpha:Float):Void
	{
		this.back.graphics.beginFill(color, alpha);
		this.back.graphics.drawRect(this.x, this.y, this.width, this.height);
		this.back.graphics.endFill();
	}
	
	//Set the alignment of the text
	public function setTextAlign(align:TextFormatAlign):Void
	{
		this.format.align = align;
		this.defaultTextFormat = this.format;
	}
	
	//Apply an embedded font
	public function setFont(font:String, size=12):Void
	{
		this.format.font = font;
		this.format.size = size;
		this.embedFonts = true;
		this.defaultTextFormat = this.format;
	}
	
	//Set bold, italic, and underline
	public function formatText(bold:Bool, italic:Bool, underline:Bool, ?startIndex:Int, 
								?endIndex:Int ):Void
	{
		this.format.bold = bold;
		this.format.italic = italic;
		this.format.underline = underline;
		if (startIndex != null && endIndex != null)
			this.setTextFormat(this.format, startIndex, endIndex);
		else
			this.setTextFormat(this.format);
	}
	//1.1-----------------------------------------------------------
}