package gags.engine.swing.graphics;

import java.awt.image.BufferedImage;

import gags.engine.graphics.Image;
import gags.engine.graphics.Spritesheet;

/**
 * Swing implementation of a Spritesheet
 * @author Peter Gagliardi
 */
public class SwingSpritesheet extends Spritesheet{

	/**
	 * Create a Swing spritesheet
	 * @param spritesheet the spritesheet
	 * @param frameWidth the width of one frame
	 * @param frameHeight the height of one frame
	 */
	public SwingSpritesheet(SwingImage spritesheet, int frameWidth, int frameHeight) {
		super(spritesheet, frameWidth, frameHeight);
	}

	@Override
	protected Image<?> getFrame(int index) {
		int x = index % getColumns() * getFrameWidth();
		int y = index / getColumns() * getFrameHeight();
		BufferedImage image = ((SwingImage) getSpritesheet()).getImage();
		return new SwingImage(image.getSubimage(x, y, getFrameWidth(), getFrameHeight()));
	}
}
