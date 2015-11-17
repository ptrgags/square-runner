package gags.engine.swing.graphics;

import java.awt.Graphics;
import java.util.List;

import gags.engine.geom.Circle;
import gags.engine.geom.Line;
import gags.engine.geom.Point;
import gags.engine.geom.Rectangle;
import gags.engine.geom.Shape;
import gags.engine.graphics.Background;
import gags.engine.graphics.Color;
import gags.engine.graphics.HasBackground;
import gags.engine.graphics.Image;
import gags.engine.graphics.Painter;
import gags.engine.graphics.Sprite;
import gags.engine.unit.Unit;

/**
 * Painter for graphics in Swing
 * @author Peter Gagliardi
 */
public final class SwingPainter implements Painter {

	/** Graphics to draw with */
	private Graphics graphics;
	/** Old drawing color */
	private java.awt.Color oldColor;
	
	/**
	 * Constructor
	 * @param g Graphics to draw on
	 */
	public SwingPainter(Graphics g) {
		this.graphics = g;
		oldColor = java.awt.Color.BLACK;
	}
	
	@Override
	public void setColor(Color color) {
		oldColor = graphics.getColor();
		graphics.setColor(new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue()));
	}
	
	@Override
	public void resetColor() {
		graphics.setColor(oldColor);
		oldColor = java.awt.Color.BLACK;
	}

	@Override
	public void drawString(String string, int x, int y) {
		graphics.drawString(string, x, y + graphics.getFontMetrics().getAscent());
	}
	
	@Override
	public void drawCenteredString(String string, int x, int y) {
		int textWidth = graphics.getFontMetrics().stringWidth(string);
		int textX = x - textWidth / 2;
		drawString(string, textX, y);
	}

	@Override
	public void drawPoint(int x, int y) {
		graphics.drawLine(x, y, x, y);
	}

	@Override
	public void drawLine(int x, int y, int x2, int y2) {
		graphics.drawLine(x, y, x2, y2);
	}

	@Override
	public void drawOval(int x, int y, int width, int height) {
		graphics.drawOval(x, y, width, height);
	}

	@Override
	public void fillOval(int x, int y, int width, int height) {
		graphics.fillOval(x, y, width, height);
	}

	@Override
	public void drawCircle(int x, int y, int radius) {
		graphics.drawOval(x - radius, y - radius, 2 * radius + 1, 2 * radius + 1);
	}

	@Override
	public void fillCircle(int x, int y, int radius) {
		graphics.fillOval(x - radius, y - radius, 2 * radius + 1, 2 * radius + 1);
	}

	@Override
	public void drawRect(int x, int y, int width, int height) {
		graphics.drawRect(x, y, width - 1, height - 1);
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		graphics.fillRect(x, y, width, height);
	}

	@Override
	public void drawShape(Shape shape) {
		switch (shape.getType()) {
		case CIRCLE:
			Circle circle = (Circle) shape;
			drawCircle(circle.getX(), circle.getY(), circle.getRadius());
			break;
		case LINE:
			Line line = (Line) shape;
			drawLine(line.getX(), line.getY(), line.getX2(), line.getY2());
			break;
		case POINT:
			Point point = (Point) shape;
			drawPoint(point.getX(), point.getY());
			break;
		case POLYGON:
			//LATER: Draw Polygon
			break;
		case RECTANGLE:
			Rectangle rect = (Rectangle) shape;
			drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
			break;
		default:
			break;
		}
	}
	
	@Override
	public void fillShape(Shape shape) {
		switch (shape.getType()) {
		case CIRCLE:
			Circle circle = (Circle) shape;
			fillCircle(circle.getX(), circle.getY(), circle.getRadius());
			break;
		case LINE:
			Line line = (Line) shape;
			drawLine(line.getX(), line.getY(), line.getX2(), line.getX2());
			break;
		case POINT:
			Point point = (Point) shape;
			drawPoint(point.getX(), point.getY());
			break;
		case POLYGON:
			//LATER: Fill Polygon
			break;
		case RECTANGLE:
			Rectangle rect = (Rectangle) shape;
			fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
			break;
		default:
			break;
		}
	}

	@Override
	public void drawBoundingBox(Unit unit) {
		if (unit != null)
			drawShape(unit.getCollisionMask().getBoundingBox());
	}

	@Override
	public void drawCollisionMask(Unit unit) {
		if (unit != null)
			fillShape(unit.getCollisionMask().getMask());
	}

	@Override
	public void drawSquareGrid(int cellSize, int width, int height) {
		drawGrid(0, 0, cellSize, cellSize, width, height);
	}

	@Override
	public void drawSquareGrid(int x, int y, int cellSize, int width, int height) {
		drawGrid(x, y, cellSize, cellSize, width, height);
	}

	@Override
	public void drawGrid(int cellWidth, int cellHeight, int width, int height) {
		drawGrid(0, 0, cellWidth, cellHeight, width, height);
	}

	@Override
	public void drawGrid(int x, int y, int cellWidth, int cellHeight, int width, int height) {
		for (int i = 0; i * cellWidth < width; i++)
			drawLine(x + i * cellWidth, y, x + i * cellWidth, y + height);
		for (int i = 0; i * cellHeight < height; i++)
			drawLine(x, y + i * cellHeight, x + width, y + i * cellHeight);
	}

	@Override
	public void drawImage(Image<?> image, int x, int y) {
		SwingImage sImage = (SwingImage) image;
		graphics.drawImage(sImage.getImage(), x, y, null);
	}

	@Override
	public void drawSprite(Sprite sprite, int x, int y) {
		if (sprite != null)
			drawImage(sprite.getFrame(), x, y);
	}

	@Override
	public void drawSprite(Sprite sprite, int frame, int x, int y) {
		if (sprite != null)
			drawImage(sprite.getFrame(frame), x, y);
	}
	
	@Override
	public void drawSprite(Unit unit) {
		if (unit != null)
			drawSprite(unit.getSprite(), unit.getSpriteFrame(), unit.getX(), unit.getY());
	}
	
	@Override
	public void drawSprites(List<? extends Unit> units) {
		if (units != null) {
			for (int i = 0; i < units.size(); i++)
				drawSprite(units.get(i));
		}
	}

	@Override
	public void drawBackground(Background background) {
		if (background != null)
			drawBackground(background, 0, 0);
	}

	@Override
	public void drawBackground(Background background, int x, int y) {
		if (background != null)
			drawImage(background.getImage(), x, y);
	}

	@Override
	public void drawBackground(HasBackground background) {
		if (background != null) {
			if (background.displayBgColor()) {
				setColor(background.getBgColor());
					fillRect(background.getBgX(), background.getBgY(), background.getBgWidth(), background.getBgHeight());
				resetColor();
			}
			if (background.getBackground() != null)
				drawBackground(background.getBackground());
		}
	}
	
	@Override
	public void drawBackgroundColor(HasBackground background) {
		if (background != null && background.getBgColor() != null) {
			setColor(background.getBgColor());
				fillRect(background.getBgX(), background.getBgY(), background.getBgWidth(), background.getBgHeight());
			resetColor();
		}
	}
}
