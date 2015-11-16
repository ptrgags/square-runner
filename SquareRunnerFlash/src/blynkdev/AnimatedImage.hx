/*
 * Animated Image Class
 * Version 1.1 [SRF 1.1]
 * Last Revised on 1/1/2011
 * Copyright (c) 2010-2011 BlynkDev Games 
 */
package blynkdev;

import flash.display.Bitmap;
import flash.display.BitmapData;
import flash.display.PixelSnapping;
import flash.geom.Matrix;
import flash.geom.Point;
import flash.geom.Rectangle;

/**
 * An AnimatedImage is a bitmap with support for animation.
 * @author Peter Gagliardi
 */

 class AnimatedImage extends Bitmap
{
	//Version 1.0 Variables:====================
	public var imgStrip:BitmapData; //image with all frames
	public var currentFrame:Int;    //current frame in animation
	public var numOfFrames:Int;     //number of frames in animation
	public var frameSpeed:Int;      //How many frames are skipped per step
	public var hNumOfFrames:Int;	//How many frames are in the strip horizontally
	public var vNumOfFrames:Int;	//How many frames are in the strip vertically
	//1.0=======================================
	
	//Version 1.1 Variables:====================
	public var partialAnimation:Bool;	//If partial animation is on
	public var startFrame:Int;			//First frame for partial animation
	public var endFrame:Int;			//Last frame for partial animation
	public var rot:Int;					//Current rotation
	//1.1=======================================
	
	public function new(w:Int, h:Int, imgStrip:BitmapData):Void
	{
		super(new BitmapData(w, h), PixelSnapping.AUTO, false);
		this.imgStrip = imgStrip;
		this.hNumOfFrames = Math.floor(this.imgStrip.width / this.width);
		this.vNumOfFrames = Math.floor(this.imgStrip.height / this.height);
		this.numOfFrames = this.hNumOfFrames * this.vNumOfFrames;
		
		this.currentFrame = 0;
		this.frameSpeed = 1;
		this.partialAnimation = false;
		this.startFrame = 0;
		this.endFrame = 0;
		this.rot = 0;
	}
	
	//Version 1.0 Functions:----------------------------------------
	
	//Advance to the next frame
	//-1.1 Added support for partial animation
	public function nextFrame():Void
	{
		if (this.partialAnimation == true)
		{
			if (this.currentFrame < this.startFrame || this.currentFrame > this.endFrame)
				this.currentFrame = this.startFrame;
		}
		for (i in 0...this.frameSpeed)
		{
			if (this.partialAnimation == true)
			{
				if (this.currentFrame < this.endFrame)
					this.currentFrame++;
				else
					this.currentFrame = this.startFrame;
			}
			else
			{
				if (this.currentFrame < this.numOfFrames - 1)
					this.currentFrame++;
				else
					this.currentFrame = 0;
			}
		}
	}
	
	//Go to the previous frame in the animation
	//-1.1 Added support for partial animation
	public function previousFrame():Void
	{
		if (this.partialAnimation == true)
		{
			if (this.currentFrame < this.startFrame || this.currentFrame > this.endFrame)
				this.currentFrame = this.endFrame;
		}
		for (i in 0...this.frameSpeed)
		{
			if (this.partialAnimation == true)
			{
				if (this.currentFrame > this.startFrame)
					this.currentFrame--;
				else
					this.currentFrame = this.endFrame;
			}
			else
			{
				if (this.currentFrame > 0)
					this.currentFrame--;
				else
					this.currentFrame = this.numOfFrames - 1;
			}
		}
	}
	
	//Draw the current frame on the stage
	public function redraw():Void
	{
		var rect = new Rectangle(this.currentFrame % this.hNumOfFrames * this.width, 
			Math.floor(this.currentFrame / this.hNumOfFrames) * this.height, this.width, 
			this.height); 
		var point = new Point(0, 0);
		this.bitmapData.copyPixels(this.imgStrip, rect, point);
	}
	
	//Return a copy of the image
	public function copyOf():AnimatedImage
	{
		var copy:AnimatedImage;
		copy = new AnimatedImage(Math.floor(this.width), Math.floor(this.height), 
			this.imgStrip.clone());
		return copy;
	}
	
	//1.0-----------------------------------------------------------
	
	//Version 1.1 Functions:----------------------------------------
	
	//Rotate the image around 
	public function rotateImage(degrees:Int):Void
	{
		this.rot += degrees;
		var imgMatrix:Matrix = this.transform.matrix;
		var centerX = this.width / 2;
		var centerY = this.height / 2;
		var centerPoint:Point = new Point(centerX, centerY);
		var transformPoint:Point = imgMatrix.transformPoint(centerPoint);
		
		imgMatrix.translate( -transformPoint.x, -transformPoint.y);
		imgMatrix.rotate(degrees * Math.PI / 180);
		imgMatrix.translate(transformPoint.x, transformPoint.y);
		this.transform.matrix = imgMatrix;
	}
	
	//Set the image's angle
	public function setRotation(degrees:Int):Void
	{
		this.rotateImage( -this.rot);
		this.rot = 0;
		this.rotateImage(degrees);
	}
	
	//Apply partial animation
	public function setPartialAnimation(start:Int, end:Int):Void
	{
		this.partialAnimation = true;
		this.startFrame = start;
		this.endFrame = end;
	}
	
	//Set the position of the image
	public function setPosition(x:Int, y:Int):Void
	{
		this.x = x;
		this.y = y;
	}
	
	//Shift the image across the screen
	public function translateImage(horizontal:Int, vertical:Int):Void
	{
		this.x += horizontal;
		this.y += vertical;
	}
	
	//1.1-----------------------------------------------------------
	
	public function tileRect(x:Int, y:Int, w:Int, h:Int):AnimatedImage
	{
		var img:AnimatedImage;
		var bd:BitmapData;
		bd = new BitmapData(w, h);
		bd.copyPixels(this.bitmapData, new Rectangle(0, 0, this.width, this.height), new Point(0, 0));
		//for (i in 0...Math.floor(w / this.width))
		//{
		//	for (j in 0...Math.floor(h / this.height))
		//		bd.copyPixels(this.bitmapData, new Rectangle(0, 0, this.width, this.height), new Point(i, j));
		//}
		return new AnimatedImage(w, h, bd);
	}
	
}