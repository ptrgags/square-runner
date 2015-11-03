package blynkdev;

import flash.net.URLLoader;
import flash.net.URLRequest;
import flash.events.EventDispatcher;
import flash.events.Event;
import flash.xml.XML;
import flash.xml.XMLList;

class XMLLoader extends EventDispatcher
{
    
	public var xml:XML;
	public var done:Bool;
	public var urlLoader:URLLoader;
    
	public function new():Void
	{
		super();
		this.done = false;
	}
	
	public function loadFile(file:String):Void
	{
		var urlRequest:URLRequest = new URLRequest(file);
		this.urlLoader = new URLLoader();
		
		this.urlLoader.addEventListener(Event.COMPLETE, this.completeListener);
		this.urlLoader.load(urlRequest);
	}
	
	public function completeListener(e:Event):Void
	{
		this.done = true;
		this.xml = new XML(this.urlLoader.data);
		this.dispatchEvent(e);
	}
}