/**
 * ...
 * @author BlynkDev
 */

package ;
import blynkdev.AnimatedImage;
import blynkdev.KeyboardController;
import blynkdev.KeyName;
import flash.text.TextFormat;
import Graphics;

class Const
{
	//Key Codes
	public var KEY_SHIFT				:Int;
	public var KEY_DELETE				:Int;
	public var KEY_UP					:Int;
	public var KEY_DOWN					:Int;
	public var KEY_SPACEBAR				:Int;
	public var KEY_ESC					:Int;
	public var KEY_P					:Int;
	//Sprites:
	public var SPR_BUTTON				:AnimatedImage;
	public var SPR_LEVEL_BUTTON			:AnimatedImage;
	public var SPR_UP_BUTTON			:AnimatedImage;
	public var SPR_DOWN_BUTTON			:AnimatedImage;
	public var SPR_BLOCK				:AnimatedImage;
	public var SPR_MOVING_BLOCK			:AnimatedImage;
	public var SPR_MOVING_BLOCK_LEFT	:AnimatedImage;
	public var SPR_INVINCIBLE			:AnimatedImage;
	public var SPR_SLOW					:AnimatedImage;
	public var SPR_FINISH_TILE			:AnimatedImage;
	public var SPR_AMMO					:AnimatedImage;
	public var SPR_MONEYBAG				:AnimatedImage;
	public var SPR_KEY					:AnimatedImage;
	public var SPR_BREAK_BLOCK			:AnimatedImage;
	public var SPR_LOCK					:AnimatedImage;
	public var SPR_GOLD_TILE			:AnimatedImage;
	public var SPR_MINUS_CASH			:AnimatedImage;
	public var SPR_RELOAD_TILE			:AnimatedImage;
	public var SPR_MONEY_BLOCK			:AnimatedImage;
	public var SPR_FLIP_TILE			:AnimatedImage;
	public var SPR_FLIP_TILE2			:AnimatedImage;
	public var SPR_PLAYER				:AnimatedImage;
	//Integers:
	public var NUM_LEVELS				:Int;
	public var NUM_GAME_X				:Int;
	public var NUM_GAME_Y				:Int;
	public var NUM_GAME_W				:Int;
	public var NUM_GAME_H				:Int;
	//Text:
	public var STR_CREDITS				:String;

	//public static inline var SPR_SOUND_TOGGLE = new AnimatedImage(16, 16, new SprSoundToggle());

	public function new(imgs=true)
	{
		//Key Codes
		this.KEY_SHIFT = KeyboardController.getKeyCode(KeyName.SHIFT);
		this.KEY_DELETE = KeyboardController.getKeyCode(KeyName.DELETE);
		this.KEY_UP = KeyboardController.getKeyCode(KeyName.UP_ARROW);
		this.KEY_DOWN = KeyboardController.getKeyCode(KeyName.DOWN_ARROW);
		this.KEY_SPACEBAR = KeyboardController.getKeyCode(KeyName.SPACE);
		this.KEY_ESC = KeyboardController.getKeyCode(KeyName.ESC);
		this.KEY_P = KeyboardController.getKeyCode(KeyName.LETTER_P);
		//Sprites
		if (imgs)
		{
			this.SPR_BUTTON = new AnimatedImage(128, 32, new SprButton(0, 0));
			this.SPR_LEVEL_BUTTON = new AnimatedImage(256, 32, new SprLevelButton(0, 0));
			this.SPR_UP_BUTTON = new AnimatedImage(32, 32, new SprUpButton(0, 0));
			this.SPR_DOWN_BUTTON = new AnimatedImage(32, 32, new SprDownButton(0, 0));
			this.SPR_BLOCK = new AnimatedImage(16, 16, new SprBlock(0, 0));
			this.SPR_MOVING_BLOCK = new AnimatedImage(16, 16, new SprMovingBlock2(0, 0));
			this.SPR_MOVING_BLOCK_LEFT = new AnimatedImage(16, 16, new SprMovingBlock(0, 0));
			this.SPR_INVINCIBLE = new AnimatedImage(16, 16, new SprInvincible(0, 0));
			this.SPR_SLOW = new AnimatedImage(16, 16, new SprSlow(0, 0));
			this.SPR_FINISH_TILE = new AnimatedImage(16, 16, new SprFinish(0, 0));
			this.SPR_AMMO = new AnimatedImage(16, 16, new SprAmmo(0, 0));
			this.SPR_MONEYBAG = new AnimatedImage(16, 16, new SprMoneybag(0, 0));
			this.SPR_KEY = new AnimatedImage(16, 16, new SprKey(0, 0));
			this.SPR_BREAK_BLOCK = new AnimatedImage(16, 16, new SprBreakBlock(0, 0));
			this.SPR_LOCK = new AnimatedImage(16, 16, new SprLock(0, 0));
			this.SPR_GOLD_TILE = new AnimatedImage(16, 16, new SprGoldTile(0, 0));
			this.SPR_MINUS_CASH = new AnimatedImage(16, 16, new SprMinusCash(0, 0));
			this.SPR_RELOAD_TILE = new AnimatedImage(16, 16, new SprReload(0, 0));
			this.SPR_MONEY_BLOCK = new AnimatedImage(16, 16, new SprMoneyBlock(0, 0));
			this.SPR_FLIP_TILE = new AnimatedImage(16, 16, new SprFlipTile(0, 0));
			this.SPR_FLIP_TILE2 = new AnimatedImage(16, 16, new SprFlipTile2(0, 0));
			this.SPR_PLAYER = new AnimatedImage(16, 16, new SprPlayer(0, 0));
		}
		//Integers:
		this.NUM_LEVELS = 20;
		this.NUM_GAME_X = 32;
		this.NUM_GAME_Y = 32;
		this.NUM_GAME_W = 320;
		this.NUM_GAME_H = 400;
		//Text:
		this.STR_CREDITS = "Credits:\n\nOrigninal Idea:\nJim Turner\nChris Gluck\n\n"
			+"Programing:\nPeter Gagliardi\n\nGraphics:\nPeter Gagliardi\n\nLevels:\n"
			+"Peter Gagliardi\nJohn Barber\n\nMusic:\nKevin McClain\nBrandan Skahill\n"
			+"Peter Gagliardi\n\nTesting:\nJohn Barber\nBrandan Skahill\n\n\nThank You For Playing!";
	}
}
