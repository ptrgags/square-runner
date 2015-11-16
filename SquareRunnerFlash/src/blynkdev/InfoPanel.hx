/**
 * Info Panel Class
 * Version 1.0 [SRF 1.0]
 * Last Revised on 1/1/2011
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package blynkdev;

import flash.display.Bitmap;
import flash.display.BitmapData;
import flash.display.PixelSnapping;
import flash.Lib;
import flash.text.TextFormat;
import flash.text.TextFormatAlign;

/**
 * An info panel displays game information
 * @author Peter Gagliardi
 */

class InfoPanel
{
	public var textBox:OutputBox;			//Text box to display information
	public var titleBar:OutputBox;			//Title Bar
	public var page:Int;					//Current number of pages
	public var numOfPages:Int;				//Number of pages
	public var text:Array<String>;			//Text to be displayed
	public var title:Array<String>;			//Page Titles
	public var keysActive:Bool;				//If the keys are active
	public var imgs:Array < Array < Bitmap >> ;	//Images to be displayed
	public var keys:KeyboardController;		//Keyboard input
	
	public function new(keys:KeyboardController) 
	{
		var format = new TextFormat();
		format.align = TextFormatAlign.CENTER;
		
		this.textBox = new OutputBox();
		this.textBox.defaultTextFormat = format;
		this.titleBar = new OutputBox();
		this.titleBar.defaultTextFormat = format;
		this.text = new Array<String>();
		this.title = new Array<String>();
		this.imgs = new Array<Array<Bitmap>>();
		this.page = 0;
		this.numOfPages = 0;
		this.keysActive = true;
		this.keys = keys;
	}
	
	public function delete()
	{
		this.textBox.delete();
		this.titleBar.delete();
		this.exitPage();
	}
	
	//Execute this once every frame:
	//1. Switch page if necessary
	//2. Display the current page
	public function frameEvent()
	{
		this.switchPage();
		this.titleBar.outputText(this.title[this.page]);
		this.textBox.outputText(this.text[this.page]);
	}
	
	//Add a page of information
	public function addPage(title:String, text:String)
	{
		this.numOfPages++;
		this.text[this.numOfPages - 1] = text;
		this.title[this.numOfPages - 1] = title;
		this.imgs[this.numOfPages - 1] = new Array<Bitmap>();
	}
	
	//Add an image to a page of information
	public function addPic(pic:BitmapData, page:Int, x:Int, y:Int)
	{
		var l:Int = this.imgs[page].length;
		this.imgs[page][l] = new Bitmap(pic, PixelSnapping.AUTO, false);
		this.imgs[page][l].x = x;
		this.imgs[page][l].y = y;
	}
	
	//Initialize current page
	public function initPage()
	{
		for (b in this.imgs[this.page])
			Lib.current.addChild(b);
	}
	
	//Exit current page
	public function exitPage()
	{
		for (b in this.imgs[this.page])
			Lib.current.removeChild(b);
	}
	
	//Switch pages if necessary
	public function switchPage()
	{
		var LEFT = KeyboardController.getKeyCode(KeyName.LEFT_ARROW);
		var RIGHT = KeyboardController.getKeyCode(KeyName.RIGHT_ARROW);
		if (this.keys.isDown(LEFT))
		{
			if (this.keysActive)
			{
				this.exitPage();
				if (this.page > 0)
					this.page--;
				else
					this.page = this.numOfPages - 1;
					
				this.initPage();
			}
			this.keysActive = false;
		}
		if (this.keys.isDown(RIGHT))
		{
			if (this.keysActive)
			{
				this.exitPage();
				if (this.page < this.numOfPages - 1)
					this.page++;
				else
					this.page = 0;
				this.initPage();
			}
			this.keysActive = false;
		}
		if (!this.keys.isDown(LEFT) && !this.keys.isDown(RIGHT))
		{
			this.keysActive = true;
		}
	}
	
}
