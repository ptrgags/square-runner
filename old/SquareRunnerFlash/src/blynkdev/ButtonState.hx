/**
 * Button State Enumeration
 * Version 1.0 [SRF 1.0]
 * Last Revised on 1/1/2011
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package blynkdev;
//
enum ButtonState
{
	NORMAL;   //Mouse is not on button
	HOVERING; //Mouse is hovering over button
	PRESSED;  //Mouse is hovering over button and the left mouse button is pressed
	RELEASED; //Left mouse button was just released
}