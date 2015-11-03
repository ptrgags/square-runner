package func;
import blynkdev.Button;
import blynkdev.Controller;
import blynkdev.OutputBox;
import flash.display.Bitmap;
import flash.display.Shape;
import flash.Lib;
import flash.text.TextFormatAlign;
import Graphics;

/**
 * ...
 * @author Peter Gagliardi
 */

class Mode1 
{
	//{=M1==Level Select==================
	//vars[0]	background		Bitmap
	//vars[1]	txt				OutputBox
	//vars[2]	highlight		Shape
	//vars[3]	panelStart		Int
	//vars[4]	selectIndex		Int
	//vars[5]	selPanelIndex	Int
	//===================================
	public static function init(ctrl:Controller):Void
	{
		var grid = ctrl.grid;
		var vars = ctrl.vars;
		var const = new Const();
		
		//Initialize variables
		ctrl.vars[3] = 0;
		ctrl.vars[4] = 0;
		ctrl.vars[5] = 0;
	
		//Create Background
		vars[0] = new Bitmap(new BacMain());
		Lib.current.addChild(ctrl.vars[0]);
	
		//Create Level Buttons
		grid.addType();
		for (i in 0...10)
		{
			ctrl.grid.addObject(0, new Button(32,32*i+32, const.SPR_LEVEL_BUTTON.copyOf()));
			ctrl.grid.getObject(0, i).label.setColors(0xFFFFFF, null);
			ctrl.grid.getObject(0, i).label.setTextAlign(TextFormatAlign.LEFT);
		}

		//Create Text Box
		vars[1] = new OutputBox();
		vars[1].setDimensions(352, 32, 256, 320);
		vars[1].setColors(0xFFFFFF,null);

		//{Create Up/Down Buttons
		grid.addType();
		grid.addObject(1, new Button(288, 32,const.SPR_UP_BUTTON.copyOf()));
		grid.addObject(1, new Button(288, 320, const.SPR_DOWN_BUTTON.copyOf()));

		//Create Infinity Mode, Play, and Back Buttons
		grid.addType();
		grid.addObject(2, new Button(208,368,const.SPR_BUTTON.copyOf(),"Infinity Mode",true,0,1,false));
		grid.addObject(2, new Button(368,368,const.SPR_BUTTON.copyOf(), "Play"));
		grid.addObject(2, new Button(48,368,const.SPR_BUTTON.copyOf(), "Back"));
		for (i in grid.getType(2))
			i.label.setColors(0xFFFFFF, null);
			
		//Create Highlight
		vars[2] = new Shape();
		vars[2].graphics.beginFill(0x00FFFF,0.5);
		vars[2].graphics.drawRect(32,32,256,32);
		vars[2].graphics.endFill();
		Lib.current.addChild(vars[2]);
	}

	public static function frame(ctrl:Controller):Void
	{
		var const = new Const();
		var grid = ctrl.grid;
		var global = ctrl.global;
		var vars = ctrl.vars;
		
		//Step Events
		grid.frameEvent();

		//Adjust Up Button
		if (vars[4] == 0)
			grid.getObject(1, 0).enabled = false;
		else
			grid.getObject(1, 0).enabled = true;
			
		//Adjust Down Button
		if (vars[4] == global[5] - 1)
			grid.getObject(1, 1).enabled = false;
		else
			grid.getObject(1, 1).enabled = true;
		
		//Adjust Level Buttons
		for (i in 0...10)
		{
			if (i + vars[3] >= global[5])
			{
				grid.getObject(0, i).enabled = false;
				grid.getObject(0, i).labelText = "";
			}
			else
			{
				grid.getObject(0, i).enabled = true;
				grid.getObject(0, i).labelText = (i + vars[3] + 1) + ". " + global[4][Std.int(i + vars[3])].title;
			}	
			if (!global[1][Std.int(i + vars[3])])
			{
				grid.getObject(0, i).enabled = false;
				grid.getObject(0, i).labelText = (i+vars[3]+1) +". --- Locked ---";
			}
		}
		
		//Adjust Play Button
		if (global[1][vars[4]])
			grid.getObject(2,1).enabled = true;
		else
			grid.getObject(2,1).enabled = false;
		
		//Adjust Highlight and bring to front
		vars[2].graphics.clear();
		vars[2].graphics.beginFill(0x00FFFF, 0.5);
		vars[2].graphics.drawRect(32, 32 +(vars[5] * 32), 256, 32);
		vars[2].graphics.endFill();
		Lib.current.removeChild(vars[2]);
		Lib.current.addChild(vars[2]);
		
		//Update Text Box
		ctrl.vars[1].outputText(global[4][vars[4]].title + "\n" + global[4][vars[4]].desc);
		
		//Check if Up Button or Arrow was clicked/released
		if (grid.getObject(1, 0).isClicked() || ctrl.keys.isReleased(const.KEY_UP))
		{
			if (vars[4] != 0)
				vars[4]--;
			if (vars[5] != 0)
				vars[5]--;
			else
			{
				if (vars[3] != 0)
					vars[3]--;
			}
		}
		
		//Check if Down Button or Arrow was clicked/released
		if (grid.getObject(1, 1).isClicked() || ctrl.keys.isReleased(const.KEY_DOWN))
		{
			if (vars[4] != global[5] - 1)
				vars[4]++;
			if (vars[5] != 9)
				vars[5]++;
			else
			{
				if (vars[3] != global[5] - 10)
					vars[3]++;
			}
		}
		
		//Check if a Level Button was clicked
		for (i in 0...10)
		{
			if (grid.getObject(0, i).isClicked())
			{
				vars[4] = vars[3] + i;
				vars[5] = i;
			}
		}
	}

	public static function change(ctrl:Controller):Int
	{
		//If Back Button is clicked, go to 0
		if (ctrl.grid.getObject(2, 2).isClicked())
			return 0;

		//If Play is clicked, save selection and go to 3
		if (ctrl.grid.getObject(2, 1).isClicked())
		{
			ctrl.global[3] = ctrl.vars[4];
			return 3;
		}

		//If Infinity Button is clicked, go to 7
		if (ctrl.grid.getObject(2, 0).isClicked())
			return 7;
	
		return -1;
	}

	public static function end(ctrl:Controller):Void
	{
		//Delete Background
		Lib.current.removeChild(ctrl.vars[0]);
		ctrl.vars[0] = null;
	
		//Delete Grid
		ctrl.grid.delete();

		//Delete text box
		ctrl.vars[1].delete();
		ctrl.vars[1] = null;

		//Delete Highlight
		Lib.current.removeChild(ctrl.vars[2]);
		ctrl.vars[2] = null;
	}
	//}=M1================================
}
