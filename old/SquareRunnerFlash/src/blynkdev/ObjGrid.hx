/**
 * Object Grid Class
 * Version 1.0 [SRF 1.1]
 * Last Revised on 1/1/2011
 * Copyright (c) 2010-2011 BlynkDev Gamesc
 */

package blynkdev;

/**
 * An object grid stores and references objects
 * @author BlynkDev
 */

class ObjGrid 
{
	public var objs:Array<Array<Dynamic>>;
	public var numOfTypes:Int;
	public var numOfObjs:Array<Int>;
	public var keyboard:KeyboardController;
	public var mouse:MouseController;
	
	public function new(keyboard:KeyboardController, mouse:MouseController):Void
	{
		this.objs = new Array<Array<Dynamic>>();
		this.numOfTypes = 0;
		this.numOfObjs = new Array<Int>();
		this.keyboard = keyboard;
		this.mouse = mouse;
	}
	
	public function frameEvent()
	{
		for (i in this.getAll())
			i.frameEvent();
		this.destroyObjects();
	}
	
	public function delete():Void
	{
		for (i in 0...this.numOfTypes)
		{
			for (j in 0...this.numOfObjs[i])
			{
				if (this.objs[i][j] != null)
					this.objs[i][j].delete();
			}
			this.objs[i] = null;
		}
		this.numOfTypes = 0;
		this.numOfObjs = new Array<Int>();
	}
	
	//Add a type to the grid
	public function addType():Int
	{
		this.numOfTypes++;
		this.objs[numOfTypes - 1] = new Array<Dynamic>();
		this.numOfObjs[numOfTypes - 1] = 0;
		return numOfTypes - 1;
	}
	
	//Deletes the objects in a type
	public function clearType(type:Int)
	{
		for (i in 0...this.numOfObjs[type])
		{
			this.objs[type][i].delete();
			this.objs[type][i] = null;
		}
		this.numOfObjs[type] = 0;
	}
	
	//Add an object to the grid
	public function addObject(type:Int, obj:Dynamic):Void
	{
		this.numOfObjs[type]++;
		obj.setInput(this.keyboard, this.mouse);
		this.objs[type][numOfObjs[type] - 1] = obj;
	}
	
	//Get every object of a certain type
	public function getType(type:Int):Array<Dynamic>
	{
		return objs[type];
	}
	
	//Get a specific object
	public function getObject(type:Int, index:Int):Dynamic
	{
		return this.objs[type][index];
	}
	
	//Get every object in the grid
	public function getAll():Array<Dynamic>
	{
		var all = new Array<Dynamic>();
		var count = 0;
		for (i in 0...this.objs.length)
		{
			for (j in 0...this.objs[i].length)
			{	
				if (this.objs[i][j] != null)
				{
					all[count] = this.objs[i][j];
					count++;
				}
			}
		}
		return all;
	}
	
	//Get objects from multiple types
	public function getSome(types:Array<Int>):Array<Dynamic>
	{
		var some = new Array<Dynamic>();
		var count = 0;
		for (i in 0...types.length)
		{
			for (j in 0...this.objs[types[i]].length)
			{
				some[count] = this.objs[types[i]][j];
				count++;
			}
		}
		return some;
	}
	
	//Get every object that meets a condition
	public function getByCondition(condition:Dynamic, ?types:Array<Int>):Array<Dynamic>
	{
		var obs = new Array<Dynamic>();
		var count = 0;
		if (types != null)
		{
			for (i in this.getSome(types))
			{
				if (condition(i))
				{
					obs[count] = i;
					count++;
				}
			}
		}
		else
		{
			for (i in this.getAll())
			{
				if (condition(i))
				{
					obs[count] = i;
					count++;
				}
			}
		}
		return obs;
	}
	
	//Destroy objects if necessary
	public function destroyObjects():Void
	{
		for (i in 0...this.objs.length)
		{
			for (j in 0...this.objs[i].length)
			{	
				if (this.objs[i][j] != null)
				{
					if (this.objs[i][j].destroy)
					{
						this.objs[i][j].delete();
						this.objs[i][j] = null;
					}
				}
			}
		}
	}
	
	public function objExists(type:Int):Bool
	{
		return true;
	}
	
}