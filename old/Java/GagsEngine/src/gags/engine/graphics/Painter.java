package gags.engine.graphics;

import java.util.List;

import gags.engine.geom.Shape;
import gags.engine.unit.Unit;

/**
 * Object used for drawing
 * functions
 * @author Peter Gagliardi
 */
public interface Painter {
	
	/**
	 * Set the drawing color
	 * @param color
	 */
	void setColor(Color color);
	
	/**
	 * Set the drawing color back 
	 * to the previous color
	 */
	void resetColor();
	
	/**
	 * Draw a string on the screen
	 * @param string the string to draw
	 * @param the x coordinate of the string
	 * @param the y coordinate of the top of the string
	 */
	void drawString(String string, int x, int y);
	
	/**
	 * Draw text aligned center within
	 * @param string the text to center
	 * @param x the x position of the center of the text
	 * @param y the y position of the top of the text
	 */
	void drawCenteredString(String string, int x, int y);

	/**
	 * Draw a point on the screen
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	void drawPoint(int x, int y);
	
	/**
	 * Draw a line
	 * @param x the first x coordinate
	 * @param y the first y coordinate
	 * @param x2 the second x coordinate
	 * @param y2 the second y coordinate
	 */
	void drawLine(int x, int y, int x2, int y2);
	
	/**
	 * Draw an oval on the screen
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param width the width
	 * @param height the height
	 */
	void drawOval(int x, int y, int width, int height);
	
	/**
	 * Draw a filled oval on the screen
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param width the width
	 * @param height the height
	 */
	void fillOval(int x, int y, int width, int height);
	
	/**
	 * Draw a circle
	 * @param x the center x coordinate
	 * @param y the center y coordinate
	 * @param radius the radius of the coordinate
	 */
	void drawCircle(int x, int y, int radius);
	
	/**
	 * Draw a filled circle
	 * @param x the center x coordinate
	 * @param y the center y coordinate
	 * @param radius the radius of the coordinate
	 */
	void fillCircle(int x, int y, int radius);
	
	/**
	 * draw a rectangle
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param width the width of the rectangle
	 * @param height the height of the rectangle
	 */
	void drawRect(int x, int y, int width, int height);
	
	/**
	 * Draw a filled rectangle
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param width the width of the rectangle
	 * @param height the height of the rectangle
	 */
	void fillRect(int x, int y, int width, int height);
	
	/**
	 * Draw a shape on the screen
	 * @param shape the shape to draw
	 */
	void drawShape(Shape shape);
	
	/**
	 * Draw a filled shape on the screen
	 * @param shape the shape to draw
	 */
	void fillShape(Shape shape);

	/**
	 * Draw the bounding box of a unit for debugging
	 * @param unit the unit to get the bounding box from
	 */
	void drawBoundingBox(Unit unit);
	
	/**
	 * Draw a filled collision mask for debugging
	 * @param unit the unit to get the mask from
	 */
	void drawCollisionMask(Unit unit);
	
	/**
	 * Draw a grid with square cells on the screen at position (0, 0)
	 * @param cellSize the size of one cell's width and height
	 * @param width the width of the grid
	 * @param height the height of the grid
	 */
	void drawSquareGrid(int cellSize, int width, int height);

	/**
	 * Draw a grid with square cells on the screen
	 * @param x the x position of the left of the grid
	 * @param y the y position of the left of the grid
	 * @param cellSize the size of one cell's width and height
	 * @param width the width of the grid
	 * @param height the height of the grid
	 */
	void drawSquareGrid(int x, int y, int cellSize, int width, int height);

	/**
	 * Draw a grid on the screen at position (0,0);
	 * @param cellWidth the width of one cell
	 * @param cellHeight the height of one cell
	 * @param width the width of the grid
	 * @param height the height of the grid
	 */
	void drawGrid(int cellWidth, int cellHeight, int width, int height);

	/**
	 * Draw a grid on the screen
	 * @param x the x position of the left of the grid
	 * @param y the y position of the left of the grid
	 * @param cellWidth the width of one cell
	 * @param cellHeight the height of one cell
	 * @param width the width of the grid
	 * @param height the height of the grid
	 */
	void drawGrid(int x, int y, int cellWidth, int cellHeight, int width, int height);

	/**
	 * Draw an image on the screen
	 * @param image the image to draw
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	void drawImage(Image<?> image, int x, int y);

	/**
	 * Draw the first frame of a sprite
	 * @param sprite the sprite to draw
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	void drawSprite(Sprite sprite, int x, int y);
	
	/**
	 * Draw a frame from a sprite
	 * @param sprite the sprite to draw
	 * @param frame the frame of the sprite to draw
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	void drawSprite(Sprite sprite, int frame, int x, int y);

	/**
	 * Draw the sprite of a unit
	 * @param unit the unit to draw
	 */
	void drawSprite(Unit unit);
	
	/**
	 * Draw the sprites for multiple units
	 * @param units the units to draw sprites for
	 */
	void drawSprites(List<? extends Unit> units);

	/**
	 * Draw a background at position (0, 0)
	 * @param background the background to draw
	 */
	void drawBackground(Background background);
	
	/**
	 * Draw a background on the screen
	 * @param background the background to draw
	 * @param x the x position
	 * @param y the y position
	 */
	void drawBackground(Background background, int x, int y);
	
	/**
	 * Draw the program's background
	 * @param program the program to draw the background of
	 */
	void drawBackground(HasBackground background);
	
	/**
	 * Fill a rectangle that acts as the program's background
	 * @param background the background to display
	 */
	void drawBackgroundColor(HasBackground background);
}
