package func;
import blynkdev.Controller;
import blynkdev.XMLLoader;
import flash.events.Event;
import flash.Lib;
import haxe.Log;
import flash.net.SharedObject;
import flash.xml.XML;
import squarerunner.SRFLevel;

/**
 * ...
 * @author Peter Gagliardi
 */

//==Variables==================================
//global[0]		initComplete	Bool
//global[1]		lvlUnlocked		Array<Bool>
//global[2]		levelData		XMLLoader
//global[3]		currentLevel	Int
//global[4]		levels			Array<SRFLevel>
//global[5]		numLevels		Int
//global[6]		objShare		SharedObject
//global[7]		highscore		Array<Int>
//global[8]		flashvars		Array<String>
//=============================================
class Init 
{
	public static function start(ctrl:Controller):Void
	{
		var global = ctrl.global;
		var const = new Const();
		var xmlLoaded:Dynamic;
		var params:Dynamic<String> = Lib.current.loaderInfo.parameters;
		
		//Initialize variables
		global[0] = false;
		global[1] = new Array<Bool>();
		global[2] = new XMLLoader();
		global[3] = 0;
		global[4] = new Array<SRFLevel>();
		global[5] = 0;
		global[6] = SharedObject.getLocal("saveData");
		global[7] = new Array<Int>();
		global[8] = params.fname;
		
		//Load XML level data
		xmlLoaded = function(e:Event) { 
			var x:XML = global[2].xml;
			global[5] = Std.parseInt(x.attributes()[0].toString());
			for (i in 0...global[5])
			{
				global[4][i] = new SRFLevel();
				global[4][i].title = x.children()[i].children()[0].toString();
				global[4][i].desc = x.children()[i].children()[1].toString();
				global[4][i].text = x.children()[i].children()[2].toString();
				global[4][i].numOfLines = Std.parseInt(x.children()[i].children()[3].attributes()[0].toString());
				for (j in 0...global[4][i].numOfLines)
					global[4][i].line[j] = x.children()[i].children()[3].children()[j].toString();
			}
			
			//Load Save Data or create new save data
			if (global[6].data.lvlUnlocked == null)
			{
				global[6].data.lvlUnlocked = new Array<Bool>();
				for (i in 0...global[5])
					global[6].data.lvlUnlocked[i] = false;
				global[6].data.lvlUnlocked[0] = true;
			}
			if (global[6].data.highscore == null)
			{
				global[6].data.highscore = new Array<Int>();
				for (i in 0...global[5])
					global[6].data.highscore[i] = 0;
			}
			global[6].flush();
			
			//Copy save data into RAM
			for (i in 0...global[5])
				global[1][i] = global[6].data.lvlUnlocked[i];
			for (i in 0...global[5])
				global[7][i] = global[6].data.highscore[i];
		};
		global[2].addEventListener(Event.COMPLETE, xmlLoaded);
		global[2].loadFile(global[8]); //global[2].loadFile('levels.xml');
		
		//Create Sound Toggle
		//ctrl.global.soundToggle = new Button(/*0-X_OFFSET*/,/*Y_OFFSET*/, /*IMG2.copyOf()*/, "", false));
	}
}