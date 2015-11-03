/**
 * Audio Object Class
 * Version 1.0 [SRF 1.0]
 * Last Revised on 1/1/2011
 * Copyright (c) 2010-2011 BlynkDev Games
 */
package blynkdev;
//
import flash.events.Event;
import flash.media.Sound;
import flash.media.SoundChannel;
import flash.media.SoundTransform;

/**
 * An audio object represents a
 * @author Peter Gagliardi
 */

class AudioObj 
{
	public var snd:Sound;						//The sound the audio object controls
	public var sndChannel:SoundChannel;			//The sound channel for the sound
	public var position:Float;					//The position of the sound
	public var volume:Float;					//The sound's volume

	public function new(snd:Sound):Void
	{
		this.snd = snd;
		this.sndChannel = new SoundChannel();
		this.position = this.sndChannel.position;
		this.volume = 3;
	}
	
	//Play the sound
	public function play():Void
	{
		if (this.position == 0)
		{
			this.sndChannel = this.snd.play(0,0,new SoundTransform(this.volume)); 
			this.position = 1;
		}
		this.sndChannel.addEventListener(Event.SOUND_COMPLETE, this.sndComplete);
	}
	
	//When sound is complete, rewind sound to the beginning
	public function sndComplete(event:Event):Void
	{
		this.position = 0;
	}
	
	//Stop playing the current sound
	public function stop():Void
	{
		this.sndChannel.stop();
		this.position = 0;
	}
	
	//Set Volume to a specific value
	public function setVolume(volume:Float)
	{
		this.volume = volume;
	}
	
	//Increase the volume by one
	public function incrementVolume()
	{
		this.volume++;
	}
	
	//Decrease the volume by one
	public function decrementVolume()
	{
		this.volume--;
	}
}