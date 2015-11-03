/**
 * Controller Class
 * Version 1.0 [SRF 1.0]
 * Last Revised on 12/29/2010
 * Copyright (c) 2010 BlynkDev Games
 */
package ;

import blynkdev.AnimatedImage;
import blynkdev.AudioObj;
import blynkdev.Button;
import blynkdev.InfoPanel;
import blynkdev.KeyboardController;
import blynkdev.KeyName;
import blynkdev.MouseController;
import blynkdev.ObjGrid;
import blynkdev.OutputBox;
import flash.display.Bitmap;
import flash.display.PixelSnapping;
import flash.Lib;
import flash.net.SharedObject;
import flash.text.TextFormat;
import flash.text.TextFormatAlign;
import Graphics;
import Audio;

/**
 * The controller controls the game
 * @author Peter Gagliardi
 */

class Controller2
{
	public var mode:Int;							//Current mode
	public var level:Int;							//Current level
	public var background:Bitmap;					//Background
	public var background2:Bitmap;					//Second background for scrolling
	public var text:OutputBox;						//Text output
	public var lineCreator:BlockCreator;			//Block creator to control gameplay
	public var lvlComplete:Array<Bool>;				//Charts user's progress
	public var highscore:Array<Int>;				//Holds user's highscores
	public var soundBoard:Array<AudioObj>;			//Holds all the sounds in the game
	public var sndFX:Array<AudioObj>;				//Holds all the sound effects in the game
	public var objShare:SharedObject;				//Saves the game
	public var sound:Bool;							//Whether or not sound should be playing
	public var info:InfoPanel;						//Info Panel
	public var keys:KeyboardController;				//Keyboard input
	public var mouse:MouseController;				//Mouse input
	public var grid:ObjGrid;						//Objects
	
	public function new(keys:KeyboardController, mouse:MouseController):Void
	{
		this.objShare = SharedObject.getLocal("saveData");
		
		if (this.objShare.data.lvlComplete == null)
		{
			this.objShare.data.lvlComplete = new Array<Bool>();
			for (i in 0...20)
				this.objShare.data.lvlComplete[i] = false;
		}
		
		if (this.objShare.data.highscore == null)
		{
			this.objShare.data.highscore = new Array<Int>();
			for (i in 0...20)
				this.objShare.data.highscore[i] = 0;
			
		}
		
		this.lvlComplete = new Array<Bool>();
		this.highscore = new Array<Int>();
		
		for (i in 0...20)
			this.lvlComplete[i] = this.objShare.data.lvlComplete[i];
			
		for (i in 0...20)
			this.highscore[i] = this.objShare.data.highscore[i];
		
		this.mode = 0;
		this.sound = true;
		this.grid = new ObjGrid(this.keys, this.mouse);
		this.initializeMode();
		this.level = 0;
		this.keys = keys;
		this.mouse = mouse;
		
		
		
		this.soundBoard = new Array<AudioObj>();
		this.soundBoard[0] = new AudioObj(new Theme01());
		this.soundBoard[1] = new AudioObj(new Theme02());
		this.soundBoard[2] = new AudioObj(new Theme03());
		this.soundBoard[2].setVolume(1);
		this.soundBoard[3] = new AudioObj(new SndBob());
		this.soundBoard[3].setVolume(5);
		
		this.sndFX = new Array<AudioObj>();
		//this.sndFX[0] = new AudioObj(new SndButton());
		//this.sndFX[1] = new AudioObj(new SndLaserGet());
		this.sndFX[2] = new AudioObj(new SndSlow());
		this.sndFX[3] = new AudioObj(new SndSpeedUp());
		
		//this.soundBoard[3].play();
		//this.sndFX[3].play();
	}