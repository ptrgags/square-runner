package squarerunner;

/**
 * ...
 * @author Peter Gagliardi
 */

class SRFLevel 
{

	public var title:		String;
	public var desc:		String;
	public var text:		String;
	public var line:		Array<String>;
	public var numOfLines:	Int;
	
	public function new() 
	{
		this.title = "";
		this.desc = "";
		this.text = "";
		this.line = new Array<String>();
		this.numOfLines = 0;
	}
	
	public function addLine(line:String)
	{
		this.line[numOfLines] = line;
		this.numOfLines++;
	}
	
}