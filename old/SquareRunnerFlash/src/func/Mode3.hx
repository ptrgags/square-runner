package func;
import blynkdev.Controller;
import flash.display.Bitmap;
import flash.Lib;
import Graphics;
import squarerunner.LevelState;
import squarerunner.NormalGameController;

/**
 * ...
 * @author Peter Gagliardi
 */

class Mode3 
{
	//{=M3==Normal Mode===================
	//vars[0]	background		Bitmap
	//vars[1]	lineCreator		NormalGameController
	//====================================
	public static function init(ctrl:Controller):Void
	{
		var vars = ctrl.vars;
		
		vars[0] = new Bitmap(new BacLevel());
		Lib.current.addChild(vars[0]);
		
		vars[1] = new NormalGameController(ctrl,ctrl.global[3]);
	}
	
	public static function frame(ctrl:Controller)
	{
		var vars = ctrl.vars;
		/* MOVE TO NormalGameController.hx
		this.background.y += 4;
		this.background2.y += 4;
			
		if (this.background.y == 320)
		this.background.y = -320;
		if (this.background2.y == 320)
		this.background2.y = -320;
		*/
		
		vars[1].frameEvent();
			
		/*
		if (this.grid.getObject(0,0).mode == 0)
		{
			this.sound = true;
			this.soundBoard[2].play();
		}
		else
		{
			this.sound = false;
			this.soundBoard[2].stop();
		}
		*/
	}
	public static function change(ctrl:Controller):Int
	{
		var vars = ctrl.vars;
		var global = ctrl.global;
		var nextMode:Int;
		
		if (vars[1].state == LevelState.RESTART)
			return 3;
		if (vars[1].state == LevelState.END)
		{
			//this.soundBoard[2].stop();
			
			if (vars[1].level == global[5]-1)
				nextMode = 1;//4;
			else
			{
				nextMode = 1;
				global[1][vars[1].level+1] = true;
			}
			
			if (global[7][global[3]] < vars[1].player.score)
				global[7][global[3]] = vars[1].player.score;
			
			global[6].data.lvlUnlocked[Std.int(global[3])+1] = true;
			global[6].data.highscore[global[3]] = global[7][global[3]];
			global[6].flush();
			return nextMode;
		}
		if (vars[1].state == LevelState.ESCAPE)
		{
			//Stop Sounds
			return 1;
		}
		return -1;
	}
	
	public static function end(ctrl:Controller)
	{
		var vars = ctrl.vars;
		
		Lib.current.removeChild(vars[0]);
		vars[0] = null;
		
		vars[1].delete();
		vars[1] = null;
		
		//Lib.current.removeChild(this.background2);
		//this.background2 = null;
	}
}
