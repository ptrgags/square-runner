/**
 * Player Class
 * Version 1.0 [SRF 1.0]
 * Last Revised on 12/29/2010
 * Copyright (c) 2010 BlynkDev Games
 */
package squarerunner;

import blynkdev.AnimatedImage;
import blynkdev.KeyboardController;
import blynkdev.KeyName;
import blynkdev.ObjBase;
import blynkdev.OutputBox;
import blynkdev.FrameTimer;

/**
 * A player is the object the user controls
 * @author Peter Gagliardi
 */

 import flash.Lib;
 import Graphics;

 class Player extends ObjBase
{
	public var ammo:Int;                //Amount of ammo the player has
	public var score:Int;				//Player's score
	public var stats:OutputBox;         //Text displaying the player's stats
	public var invincible:Bool;         //If the player is invincible
	public var flip:Bool;               //If the controls are flipped
	public var key:Bool;				//If the player picked up a key
	public var fired:Bool;				//If the player has fired
	public var iTimer:FrameTimer;		//Times how long the player is invincible
	public var bullets:Array<Bullet>;	//Bullets the player has fired


	public function new(x:Int, y:Int, img:AnimatedImage):Void
	{
		super(x, y, img);
		this.ammo = 0;
		this.stats = new OutputBox();
		this.stats.setDimensions(384,32,224,64);
		this.stats.textColor = 0x00FF00;
		this.invincible = false;
		this.iTimer = new FrameTimer();
		this.flip = false;
		this.bullets = new Array<Bullet>();
		this.fired = false;
		this.score = 0;
	}

	override public function delete():Void
	{
		super.delete();
		this.stats.delete();
		for (i in this.bullets)
		{
			if (i != null && !i.dead)
			i.delete();
		}
	}

	//Execute this once every frame:
	//1. execute the superclass' step event
	//2. check if the player picked up a key
	//3. move the character
	//4. execute the player's bullets' step events
	//5. fire the player's gun
	//6. display stats on the screen
	//7. bring the player and the stats to the front of the screen
	//8. update the invincibility timer
	//9. adjust the player's animation frame
	override public function frameEvent():Void
	{
		var kText:String;	//Text that displays whether or not you have a key
		var LEFT = KeyboardController.getKeyCode(KeyName.LEFT_ARROW);
		var RIGHT = KeyboardController.getKeyCode(KeyName.RIGHT_ARROW);
		var SPACE = KeyboardController.getKeyCode(KeyName.SPACE);

		super.frameEvent();

		if (this.key)
			kText = "	Key: Yes";
		else
			kText = "	Key: No";

		if (!this.flip)
		{
			this.moveLeft(this.keys.isDown(LEFT));
			this.moveRight(this.keys.isDown(RIGHT));
		}
		else
		{
			this.moveRight(this.keys.isDown(LEFT));
			this.moveLeft(this.keys.isDown(RIGHT));
		}

		for (i in 0...this.bullets.length)
		{
			if (this.bullets[i] != null)
				this.bullets[i].frameEvent();
		}

		this.fire(this.keys.isDown(SPACE));

		this.stats.outputText("Ammo:" + this.ammo + kText + "	Score:" + this.score);

		this.bringToFront();
		this.stats.bringToFront();

		this.iTimer.frameEvent();
		if (this.iTimer.time == 0)
			this.invincible = false;

		this.adjustFrame();
	}

	//Move to the left if the specified key is pressed
	public function moveLeft(keyIsPressed:Bool):Void
	{
		var const = new Const();
		if (keyIsPressed)
		{
			if (x > const.NUM_GAME_X)
			x -= 8;
		}
	}

	//Move to the right if the specified key is pressed
	public function moveRight(keyIsPressed:Bool):Void
	{
		var const = new Const();
		if (keyIsPressed)
		{
			if (x < const.NUM_GAME_X + const.NUM_GAME_W - 16)//304)
			x += 8;
		}
	}

	//Update the player's image's current frame
	public function adjustFrame():Void
	{
		if (this.invincible)
			this.img.currentFrame = 1;
		else
			this.img.currentFrame = 0;
	}

	//Fire the player's gun
	public function fire(keyIsPressed:Bool):Void
	{
		var b = new AnimatedImage(8, 8, new SprBullet(0, 0));	//Bullet image

		if (keyIsPressed)
		{
			if (this.ammo > 0)
			{
				if (!this.fired)
				{
					this.bullets[this.bullets.length] = new Bullet(x+4, y, b.copyOf());
					this.ammo -= 1;
					this.fired = true;
				}
			}
		}
		else
		this.fired = false;
	}
}
