/**
 * Level State Enumeration
 * Last Revised on 5/17/2011 2:29 PM
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package squarerunner;

enum LevelState 
{
	START;		//Level is initializing
	NORMAL;		//Level is being played
	COMPLETE;	//Level has been completed, victory message is displayed
	END;		//Level has been completed, move on to the next mode
	RESTART;	//Level has been failed, set to restart
	ESCAPE;		//Level has been terminated by user
}