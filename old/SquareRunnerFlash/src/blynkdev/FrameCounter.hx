package blynkdev;

/**
 * ...
 * @author Peter Gagliardi
 */

class FrameCounter 
{
	public var frame:Int;	//Current frame

	public function new():Void 
	{
		frame = 0;
	}
	
	public function frameEvent():Void
	{
		//Count up from the current time
		time++;
	}
	
}