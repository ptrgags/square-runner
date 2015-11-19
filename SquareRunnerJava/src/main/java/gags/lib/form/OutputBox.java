package gags.lib.form;

import java.util.ArrayList;
import java.util.List;

import gags.engine.graphics.Color;
import gags.engine.graphics.Painter;
import gags.engine.unit.Unit;

/**
 * Output box for displaying text
 * @author Peter Gagliardi
 */
public class OutputBox extends Unit {

	/** text to display */
	private List<String> lines;
	/** Color of the text */
	private Color textColor;
	
	/**
	 * Constructor
	 * @param x position
	 * @param y position
	 */
	public OutputBox(int x, int y) {
		super(x, y);
		lines = new ArrayList<String>();
		textColor = Color.BLACK;
	}
	
	public void setLines(List<String> lines) {
		this.lines = lines;
	}
	
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
	
	/**
	 * Set the text based on a string.
	 * The text will be split upon newlines or
	 * "\n"
	 * @param text the text to display
	 */
	public void setText(String text) {
		text.replaceAll("\\n", "\n");
		String[] textLines = text.split("\n");
		
		lines = new ArrayList<String>();
		for (String string : textLines)
			lines.add(string);
	}
	
	/**
	 * Add a line of text to the output box
	 * @param line the line of text
	 */
	public void addLine(String line) {
		lines.add(line);
	}
	
	/**
	 * clear the text box, leaving a new
	 * empty ArrayList of text
	 */
	public void clear() {
		lines = new ArrayList<String>();
	}
	
	@Override
	public void onDraw(Painter painter) {
		//LATER: GE - Account for font metrics
		painter.setColor(textColor);
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			painter.drawString(line, getX(), getY() + i * 16);
		}
		painter.resetColor();
	}
}
