/**
 * Button Class
 * Version 1.1 [SRF 1.1]
 * Last Revised on 1/1/2011
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package blynkdev;

import flash.Lib;
import flash.text.TextFormat;
import flash.text.TextFormatAlign;

/**
 * A button is a base for various clickable buttons
 * @author Peter Gagliardi
 */

 class Button extends ObjBase
{
	//Version 1.0 Variables:====================
	//-1.1 Variable 'bState' renamed to 'state'
	public var state:ButtonState; //The state of the button
	public var label:OutputBox;    //The button's label
	public var format:TextFormat;  //The label's format
	//...=======================================
	
	//Version 1.1 Variables:====================
	public var labelText:String;	//The label's text
	public var enabled:Bool;		//If the button is enabled
	public var labelOn:Bool;		//If the label should be displayed
	public var value:Int;			//A number associated with the button
	public var mode:Int;			//What mode the button is in
	public var numOfModes:Int;		//Number of modes the button has
	//1.1=======================================
	
	public function new(x:Int, y:Int, img:AnimatedImage, label = "", labelOn = true, 
						value = 0, numOfModes = 1, enabled = true):Void
	{
		super(x, y, img);
		
		this.state = ButtonState.NORMAL;
		this.labelText = label;
		this.enabled = enabled;
		this.labelOn = labelOn;
		this.value = value;
		this.mode = 1;
		this.numOfModes = numOfModes;
		
		this.format = new TextFormat();
		this.format.align = TextFormatAlign.CENTER;
		
		this.label = new OutputBox();
		this.label.x = this.x;
		this.label.y = this.y + (.25 * this.img.bitmapData.height);
		this.label.width = this.img.bitmapData.width;
		this.label.height = this.img.bitmapData.height;
		this.label.defaultTextFormat = this.format;
		if (this.labelOn)
			this.label.outputText(this.labelText);
	}
	
	override public function delete():Void
	{
		super.delete();
		this.label.delete();
	}
	
	//Version 1.0 Functions:----------------------------------------
	
	//Execute this once every frame:
	//1. Execute parent's step event
	//2. Change the button's state if necessary
	//3. Adjust the button's frame
	override public function frameEvent():Void
	{
		super.frameEvent();
		
		if (this.enabled)
		{
			if (this.state == ButtonState.NORMAL)
			{
				if (this.mouseIsHovering())
					this.state = ButtonState.HOVERING;
			}
			else
			if (this.state == ButtonState.HOVERING)
			{
				if (!this.mouseIsHovering())
					this.state = ButtonState.NORMAL;
				else
				if (/*this.keys.isDown(32)*/mouse.isDown())//mouse[0])
					this.state = ButtonState.PRESSED;
			}
			else
			if (this.state == ButtonState.PRESSED)
			{
				if (!this.mouseIsHovering())
					this.state = ButtonState.NORMAL;
				else
				if (!this.mouse.isDown())
				{
					this.state = ButtonState.RELEASED;
					if (this.mode < this.numOfModes)
						this.mode++;
					else
						this.mode = 0;
				}
			}
			else
			if (this.state == ButtonState.RELEASED)
			{
				if (this.mouseIsHovering())
					this.state = ButtonState.HOVERING;
				else
					this.state = ButtonState.NORMAL;
			}
		}
		else
			this.state = ButtonState.NORMAL;
			
		this.adjustFrame();
		this.refreshLabel();
	}
	
	//Adjust the button's sprite's frame according to the button's state
	public function adjustFrame():Void
	{
		if (this.enabled == false)
			this.img.currentFrame = 4 * (this.numOfModes - 1);
		else
		if (this.state == ButtonState.NORMAL)
			this.img.currentFrame = 4 * (this.numOfModes - 1) + 1;
		else
		if (this.state == ButtonState.HOVERING)
			this.img.currentFrame = 4 * (this.numOfModes - 1) + 2;
		else
		if (this.state == ButtonState.PRESSED)
			this.img.currentFrame = 3 * (this.numOfModes - 1) + 3;
	}
	
	//Check if the mouse is hovering over the button
	public function mouseIsHovering():Bool
	{
		return this.x <= this.mouse.x && this.mouse.x < this.x + this.img.width
			&& this.y <= this.mouse.y && this.mouse.y < this.y + this.img.height;
		//return this.x <= Lib.current.mouseX && Lib.current.mouseX < this.x + this.img.width
		//	&& this.y <= Lib.current.mouseY && Lib.current.mouseY < this.y + this.img.height;
	}
	
	//1.0-----------------------------------------------------------
	
	//Version 1.1 Functions:----------------------------------------
	
	//Redraw the label on the button
	public function refreshLabel():Void
	{
		if (this.labelOn)
			this.label.outputText(this.labelText);
		else
			this.label.outputText("");
		
		this.label.x = this.x;
		this.label.y = this.y + (.25 * this.img.bitmapData.height);
	}
	
	//Check if the button was clicked
	public function isClicked():Bool
	{
		return this.state == ButtonState.RELEASED;
	}
	
	//1.1-----------------------------------------------------------
}