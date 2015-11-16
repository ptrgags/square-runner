/**
 * Key State Enumeration
 * Version 1.0 [APOC2 1.0]
 * Last Revised on 1/1/2011
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package blynkdev;

enum KeyState 
{
	NORMAL;			//Key is not pressed
	PRESSED;		//Key was pressed this frame
	HELD;			//Key is being held
	RELEASED;		//Key was released this frame
}