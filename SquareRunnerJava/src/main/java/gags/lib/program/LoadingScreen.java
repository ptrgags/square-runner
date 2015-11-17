package gags.lib.program;

import gags.engine.core.Program;
import gags.engine.graphics.Color;
import gags.engine.graphics.Painter;
import gags.engine.resources.ResourceOmmeletParser;

/**
 * A loading screen
 * @author Peter Gagliardi
 */
public class LoadingScreen extends Program {

	/** Resource OmmeletParser parser */
	private ResourceOmmeletParser parser;
	/** The next program to run */
	private Program nextProgram;
	/** Id of the resource ommelet */
	private String resourceId;
	/** The foreground color for drawing */
	private Color fgColor;
	
	/**
	 * Create a loading screen.
	 * @param resourceId the id of the resource OMMELET
	 */
	public LoadingScreen(String resourceId, Program nextProgram) {
		this.resourceId = resourceId;
		this.nextProgram = nextProgram;
		fgColor = Color.WHITE;
	}
	
	@Override
	public void onCreate() {
		parser = new ResourceOmmeletParser(getResources().loadOmmelet("", resourceId), getResources());
		setBgColor(Color.BLACK);
	}
	
	@Override
	public void onFrame() {
		if (parser.getStepsComplete() < parser.numOfSteps())
			parser.readStep(parser.getStepsComplete());
		else
			setNextProgram(nextProgram);
	}
	
	@Override
	public void onDraw(Painter painter) {
		painter.drawBackgroundColor(this);
		painter.setColor(fgColor);
			painter.drawRect(0, 0, 640, 32);
			painter.fillRect(0, 0, 640 * (int)((float) parser.getStepsComplete())/parser.numOfSteps(), 32);
		painter.resetColor();
	}
	
	public void setColors(Color bgColor, Color fgColor) {
		setBgColor(bgColor);
		this.fgColor = fgColor;
	}
	
}
