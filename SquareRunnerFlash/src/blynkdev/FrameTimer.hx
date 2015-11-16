/**
 * Frame Timer Class
 * Last Revised on 5/16/2011 2:28 PM
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package blynkdev;

/**
 * A frame timer counts down frames from the set time
 * @author Peter Gagliardi
 */

class FrameTimer 
{
	public var time:Int;	//Current time

	public function new():Void
	{
		time = -1;
	}
	
	public function frameEvent():Void
	{
		//Count down from the current time
		if (time > -1)
		time--;
	}
}