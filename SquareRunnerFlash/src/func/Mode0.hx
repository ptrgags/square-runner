package func;

import blynkdev.Button;
import blynkdev.Controller;
import flash.display.Bitmap;
import flash.Lib;
import Graphics;

/**
 * ...
 * @author Peter Gagliardi
 */

//==Variables=================
//vars[0]	background	Bitmap
//vars[1]	title		Bitmap
//============================
class Mode0
{
	public static function init(ctrl:Controller):Void
	{
		var grid = ctrl.grid;
		var const = new Const();

		//Create Background
		ctrl.vars[0] = new Bitmap(new BacMain(0, 0));
		Lib.current.addChild(ctrl.vars[0]);

		//Create Buttons
		grid.addType();
		grid.addObject(0, new Button( -176, 320, const.SPR_BUTTON.copyOf(), "Play"));
		grid.addObject(0, new Button(-240, 368, const.SPR_BUTTON.copyOf(), "Info"));
		grid.addObject(0, new Button(-304, 416, const.SPR_BUTTON.copyOf(), "Credits"));
		grid.addObject(0, new Button(640, 320, const.SPR_BUTTON.copyOf(), "Achievements",true,0,1,false));
		grid.addObject(0, new Button(704, 368, const.SPR_BUTTON.copyOf(), "Options",true,0,1,false));
		grid.addObject(0, new Button(768, 416, const.SPR_BUTTON.copyOf(), "Infinite Mode",true,0,1,false));
		for (i in grid.getType(0))
			i.label.setColors(0xFFFFFF, null);

		//Create Title
		ctrl.vars[1] = new Bitmap(new SprTitle(0, 0));
		ctrl.vars[1].x = 160;
		ctrl.vars[1].y = -320;
		Lib.current.addChild(ctrl.vars[1]);

		//Adjust Object Starting Positions
		if (ctrl.global[0])
		{
			for (i in 0...3)
				grid.getObject(0, i).x = 176;
			for (i in 3...6)
				grid.getObject(0, i).x = 336;
			ctrl.vars[1].y = 0;
		}
	}

	public static function enter(ctrl:Controller):Bool
	{
		var grid = ctrl.grid;
		grid.frameEvent();

		//End enter phase if complete
		if (ctrl.vars[1].y == 0
			&& grid.getObject(0, 0).x == 176 && grid.getObject(0, 1).x == 176 && grid.getObject(0, 2).x == 176
			&& grid.getObject(0, 3).x == 336 && grid.getObject(0, 4).x == 336 && grid.getObject(0, 5).x == 336)
		{
			ctrl.global[0] = true;
			return false;
		}

		//Move buttons to center
		for (i in 0...3)
		{
			if (grid.getObject(0, i).x != 176)
				grid.getObject(0, i).x += 16;
		}
		for (i in 3...6)
		{
			if (grid.getObject(0, i).x != 336)
				grid.getObject(0, i).x -= 16;
		}

		//Move Title downward
		if (ctrl.vars[1].y != 0)
			ctrl.vars[1].y += 16;

		return true;
	}

	public static function frame(ctrl:Controller):Void
	{
		var const:Const = new Const();
		var grid = ctrl.grid;
		var global = ctrl.global;

		//Grid frame event
		grid.frameEvent();

		if (ctrl.keys.isDown(const.KEY_SHIFT) && ctrl.keys.isDown(const.KEY_DELETE))
		{
			for (i in 0...20)
			{
				global[1][i] = false;
				global[7][i] = 0;
			}
			for (i in 0...global[5])
					global[6].data.lvlUnlocked[i] = false;
			for (i in 0...global[5])
					global[6].data.highscore[i] = 0;
			global[1][0] = true;
			global[6].data.lvlUnlocked[0] = true;
			global[6].flush();
		}

		//Play Sound
		//same for other modes
		/*
		 * if (SOUND_SHOULD_PLAY)
		 * {
		 * 		sound = true;
		 * 		soundObj.play();
		 * }
		 * else
		 * {
		 * 		sound = false;
		 * 		soundObj.stop();
		 * }
		 */
	}

	public static function change(ctrl:Controller):Int
	{
		var grid = ctrl.grid;

		//Change mode when a button is clicked
		if (grid.getObject(0, 0).isClicked())
			return 1;
		if (grid.getObject(0, 1).isClicked())
			return 2;
		if (grid.getObject(0, 2).isClicked())
			return 4;
		if (grid.getObject(0, 3).isClicked())
			return 5;
		if (grid.getObject(0, 4).isClicked())
			return 6;
		if (grid.getObject(0, 5).isClicked())
			return 7;

		return -1;
	}

	public static function end(ctrl:Controller):Void
	{

		//Delete grid
		ctrl.grid.delete();

		//Delete Title
		Lib.current.removeChild(ctrl.vars[1]);
		ctrl.vars[1] = null;

		//Delete Background
		Lib.current.removeChild(ctrl.vars[0]);
		ctrl.vars[0] = null;
	}
}
