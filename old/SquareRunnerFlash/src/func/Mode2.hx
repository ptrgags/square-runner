package func;
import blynkdev.Button;
import blynkdev.Controller;
import blynkdev.InfoPanel;
import flash.display.Bitmap;
import flash.Lib;
import Graphics;

/**
 * ...
 * @author Peter Gagliardi
 */

class Mode2
{
	//{=M2==Info==========================
	//vars[0]	background	Bitmap
	//vars[1]	backButton	Button
	//vars[2]	info		InfoPanel
	//====================================
	public static function init(ctrl:Controller)
	{
		var vars = ctrl.vars;
		var const = new Const();
		var backButton = vars[1];

		vars[0] = new Bitmap(new BacMain(0, 0));
		Lib.current.addChild(vars[0]);

		vars[1] = new Button(256,272,const.SPR_BUTTON.copyOf(), "Back");
		vars[1].label.setColors(0xFFFFFF,null);
		vars[1].setInput(ctrl.keys, ctrl.mouse);

		vars[2] = new InfoPanel(ctrl.keys);
		vars[2].titleBar.setDimensions(0,0,640,32);
		vars[2].titleBar.setColors(0xFFFFFF, null);
		vars[2].textBox.setDimensions(0,64,640, 480);
		vars[2].textBox.setColors(0xFFFFFF, null);
		vars[2].addPage("<\tSquare Runner Flash \t>","Game by:\n BlynkDev Games\n_______________________\nObjective:\n"
			+"Avoid obstacles, collect powerups, and reach the goal of each level. this version also supports local high scores.");
		vars[2].addPage("<\tControls\t>","Left Arrow: Move left\nRight Arrow: Move right\nSpacebar: Fire lasers (if you "
			+"have enough ammo)\nShift + Delete on Main Menu: Erase level data\nP: Pause Game\nEsc: Quit Level");
		vars[2].addPage("<\tBlocks\t>","\nBlocks are the obstacles to avoid\nTypes:\nNormal Block - Moves toward you\n"
			+"Moving Block - Moves left or right\nBreakable Block - You can pass through these unharmed\nLock - You "
			+"can only pass these if you have a key\nMoney Block - Shoot this to add to your score");
		vars[2].addPage("<\tPowerups\t>", "\nTypes:\nInvincible - Makes you invincible for a short time\nSlow - Slows down the falling"
			+" blocks\nAmmo - Increases your ammuniton\nMoney Bag - Adds to your score\nKey - lets you pass through locks\n");
		vars[2].addPage("<\tTiles\t>", "\nTiles Act like powerups when you pass over them\nTypes:\nFinish Tile - Pass over"
			+" this to win the level\nReload Tile - Adds to Ammo and Score\nGold Tile - Adds to Score\nMinus Cash Tile -"
			+" Subtracts from Score\nFlip Tile - Flips Controls");
		vars[2].addPic(new SprBlock(0, 0), 			2, 248,240);
		vars[2].addPic(new SprMovingBlock(0, 0),	2, 280,240);
		vars[2].addPic(new SprBreakBlock(0, 0), 	2, 312,240);
		vars[2].addPic(new SprLock(0, 0), 			2, 344,240);
		vars[2].addPic(new SprMoneyBlock(0, 0), 	2, 376,240);
		vars[2].addPic(new SprInvincible(0, 0), 	3, 248,240);
		vars[2].addPic(new SprSlow(0, 0), 			3, 280,240);
		vars[2].addPic(new SprAmmo(0, 0), 			3, 312,240);
		vars[2].addPic(new SprMoneybag(0, 0), 		3, 344,240);
		vars[2].addPic(new SprKey(0, 0), 			3, 376,240);
		vars[2].addPic(new SprFinish(0, 0), 		4, 248,240);
		vars[2].addPic(new SprReload(0, 0), 		4, 280,240);
		vars[2].addPic(new SprGoldTile(0, 0), 		4, 312,240);
		vars[2].addPic(new SprMinusCash(0, 0), 		4, 344,240);
		vars[2].addPic(new SprFlipTile(0, 0), 		4, 376,240);
		vars[2].initPage();

		/*
			if (this.sound)
				this.grid.getObject(0, 1).mode = 0;
			else
				this.grid.getObject(0, 1).mode = 1;
		*/
	}
	public static function frame(ctrl:Controller)
	{
		var vars = ctrl.vars;

		vars[1].frameEvent();
		vars[2].frameEvent();
		/*
			if (this.grid.getObject(0,1).mode == 0)
			{
				this.sound = true;
				this.soundBoard[1].play();
			}
			else
			{
				this.sound = false;
				this.soundBoard[1].stop();
			}
		 */
	}

	public static function change(ctrl:Controller):Int
	{
		var vars = ctrl.vars;

		if (vars[1].isClicked())
			return 0;
		return -1;
	}

	public static function end(ctrl:Controller):Void
	{
		var vars = ctrl.vars;

		Lib.current.removeChild(vars[0]);
		vars[0] = null;
		vars[1].delete();
		vars[1] = null;
		vars[2].delete();
		vars[2] = null;
	}
	//}=M2================================
}
