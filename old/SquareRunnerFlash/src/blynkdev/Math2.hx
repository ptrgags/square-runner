/**
 * Math 2 Class
 * Version 1.0 [SRF 1.1]
 * Last Revised on 1/1/2011
 * Copyright (c) 2010-2011 BlynkDev Games
 */

package blynkdev;

/**
 * Math 2 provides additional math functions
 * @author Peter Gagliardi
 */

 class Math2 
{
	
	//Get distance between two points
	public static function getDistance(x1:Float, y1:Float, x2:Float, y2:Float):Float
	{
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	//Convert degrees to radians
	public static function degToRad(degrees:Float):Float
	{
		return degrees * (Math.PI / 180);
	}
	
	//Convert radians to degrees
	public static function radToDeg(radians:Float):Float
	{
		return radians * (180 / Math.PI);
	}
	
	//Get x coordinate from a circle
	public static function circleDistX(degrees:Float, dist:Int):Float
	{
		return Math.cos(Math2.degToRad(degrees)) * dist;
	}
	
	//Get y coordinate from a circle
	public static function circleDistY(degrees:Float, dist:Int):Float
	{
		return -Math.sin(Math2.degToRad(degrees)) * dist;
	}
	
	
	public static function simplifyDegrees(degrees:Float):Float
	{
		var deg = degrees;
		while (deg >= 360)
			deg -= 360;
		while (deg < 0)
			deg += 360;
		return deg;
	}
	
}