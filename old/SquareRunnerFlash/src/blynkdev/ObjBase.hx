/**
 * Object Base Class
 * Version 1.3 [APOC2 1.0]
 * Last Revised on 1/1/2011
 * Copyright (c) 2010-2011 Blynkdev Games
 */
package blynkdev;

import flash.display.Shape;
import flash.geom.Rectangle;
import flash.Lib;

/**
 * An object base is a base class for various objects with animated sprites
 * @author Peter Gagliardi
 */

 class ObjBase
{
	//Version 1.0 Variables:====================
	public var x:Int;						//X position on screen
	public var y:Int;						//Y position on screen
	public var img:AnimatedImage;			//Animated sprite
	public var destroy:Bool;				//If the object should be destroyed
	//1.0=======================================
	
	//Version 1.1 Variables:====================
	public var hSpeed:Int;					//Horizontal Speed
	public var vSpeed:Int;					//Vertical Speed
	//1.1=======================================
	
	//Version 1.2 Variables:====================
	public var direction:Int;				//Movement Direction
	public var speed:Int;					//Speed
	public var xPrev:Int;					//X previous
	public var yPrev:Int;					//Y previous
	//1.2=======================================
	
	//Version 1.3 Variables:====================
	public var keys:KeyboardController;		//Keyboard input
	public var mouse:MouseController;		//Mouse input
	public var stepFunc:Dynamic;			//Step event
	//1.3=======================================
	
	public var bBox:Rectangle;				//Bounding box
	
	public function new(x:Int, y:Int, img:AnimatedImage ):Void
	{
		this.x = x;
		this.y = y;
		this.img = img;
		this.img.x = x;
		this.img.y = y;
		this.destroy = false;
		this.hSpeed = 0;
		this.vSpeed = 0;
		
		this.direction = 0;
		this.speed = 0;
		this.xPrev = x;
		this.yPrev = y;
		this.keys = new KeyboardController();
		this.mouse = new MouseController();
		this.stepFunc = null;
		
		this.bBox = new Rectangle(0, 0, this.img.width, this.img.height);
		
		Lib.current.addChild(this.img);
		this.img.redraw();
	}
	
	public function delete():Void
	{
		Lib.current.removeChild(this.img);
	}
	
	//Version 1.0 Functions:----------------------------------------
	
	//Execute this once every frame:
	//1. update image's position on screen
	//2. redraw the image
	public function frameEvent():Void
	{
		this.xPrev = this.x;
		this.yPrev = this.y; 
		
		this.applyHVSpeed();
		this.applyDirMovement();
		
		if (this.stepFunc != null)
		this.stepFunc(this);
		
		this.img.x = this.x;
		this.img.y = this.y;
		this.img.redraw();
		
		
	}
	
	//Check if objects collide
	public function collisionTest(obj2:ObjBase):Bool
	{
		return obj2.x - this.img.width < this.x && this.x < obj2.x + obj2.img.width
			&& obj2.y - this.img.height < this.y && this.y < obj2.y + obj2.img.height;
	}
	
	//Bring object to front of screen
	public function bringToFront():Void
	{
		Lib.current.removeChild(this.img);
		Lib.current.addChild(this.img);
	}
	
	//1.0-----------------------------------------------------------
	
	//Version 1.1 Functions:----------------------------------------
	
	//Change object's position based on horizontal and vertical speed
	public function applyHVSpeed():Void
	{
		this.x += this.hSpeed;
		this.y += this.vSpeed;
	}
	
	//1.1-----------------------------------------------------------
	
	//Version 1.2 Functions:----------------------------------------
	
	public function applyDirMovement():Void
	{
		this.x += Math.floor(Math.cos(Math2.degToRad(this.direction)) * speed);
		this.y += Math.floor(-Math.sin(Math2.degToRad(this.direction)) * speed);
	}
	
	public function goToPrevious():Void
	{
		this.x = this.xPrev;
		this.y = this.yPrev;
	}
	
	//Moves the current object away from the second object
	public function escapeCollision(obj2:ObjBase):Void
	{
		//Fix this logic
		if (obj2.x - this.img.width < this.x && this.x < obj2.x + obj2.img.width)
		{
			if (this.xPrev < this.x) //Collide to the right
				this.x = Math.floor(obj2.x - this.img.width);
			else
			if (this.xPrev > this.x)
				this.x = Math.floor(obj2.x + obj2.img.width);
		}
		
		//Fix this logic
		if (obj2.y - this.img.height < this.y && this.y < obj2.y + obj2.img.height)
		{
			if (this.yPrev < this.y)
				this.y = Math.floor(obj2.y - this.img.height);
			else
			if (this.yPrev > this.y)
				this.y = Math.floor(obj2.y + obj2.img.height);
		}
	}
	
	public function escCollisionDir(obj2:ObjBase):Void
	{
		if (this.direction > 45 && this.direction <= 135)
		{
			
		}
	}
	
	//1.2-----------------------------------------------------------
	
	//Version 1.3 Functions:----------------------------------------
	
	public function setInput(keyboard:KeyboardController, mouse:MouseController):Void
	{
		this.keys = keyboard;
		this.mouse = mouse;
	}
	
	public function addStepEvent(event:Dynamic)
	{
		this.stepFunc = event;
	}
	
	//1.3-----------------------------------------------------------
	public function bounce(obj:ObjBase):Void
	{
		
	}
	
	public function solidCollision(obj:ObjBase):Bool
	{
		return true;
	}
}