package func;

import blynkdev.Button;
import blynkdev.Controller;
import blynkdev.OutputBox;
import flash.display.Bitmap;
import flash.Lib;
import flash.text.TextFormatAlign;
import Graphics;

/**
 * ...
 * @author Peter Gagliardi
 */

class Mode4 
{
	//{=M4==Credits=======================
	//vars[0]	background	Bitmap
	//vars[1]	txt			OutputBox
	//vars[2]	backButton	Button
	//====================================
	public static function init(ctrl:Controller)
	{
		var vars = ctrl.vars;
		var const = new Const();
		
		//Create Background
		vars[0] = new Bitmap(new BacMain());
		Lib.current.addChild(vars[0]);
		
		vars[1] = new OutputBox();
		vars[1].setDimensions(0,0,640,960);
		vars[1].setColors(0xFFFFFF, null);
		vars[1].setTextAlign(TextFormatAlign.CENTER);
		vars[1].outputText(const.STR_CREDITS);
		
		vars[2] = new Button(0, 0, const.SPR_BUTTON.copyOf(), "Back");
		vars[2].label.textColor = 0xFFFFFF;
	}
	
	public static function frame(ctrl:Controller)
	{
		var vars = ctrl.vars;
		
		vars[1].y -= 2;
		if (vars[1].y == -640)
			vars[1].y = 640;
			
		vars[2].frameEvent();
		
		
		/*this.text.y -= 1;
			if (this.text.y == -640)
				this.text.y = 320;
				
			for (i in this.grid.getAll())
				i.stepEvent();
			
			if (this.grid.getObject(0,1).mode == 0)
			{
				this.sound = true;
				this.soundBoard[0].play();
			}
			else
			{
				this.sound = false;
				this.soundBoard[0].stop();
			}
			*/
	}
	
	public static function change(ctrl:Controller)
	{
		if (ctrl.vars[2].iSClicked())
			return 0;
		return -1;
		/*
		if (ctrl.grid.getObject(0, 0).isClicked())
		{
			//Stop Sounds
			return 0;
		}
		return -1;
		*/
	}
	
	public static function end(ctrl:Controller)
	{
		ctrl.vars[0].delete();
		ctrl.vars[0] = null;
		ctrl.vars[1].delete();
		ctrl.vars[1] = null;
		ctrl.vars[2].delete();
		ctrl.vars[2] = null;
		/*
		ctrl.vars[0].delete();
		ctrl.vars[0] = null;
		ctrl.grid.delete();
		*/
	}
	//}=M4================================
}