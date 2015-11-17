package gags.engine.swing.graphics;

import java.awt.image.BufferedImage;

import gags.engine.graphics.Image;

/**
 * Image wrapper for a BufferedImage
 * @author Peter Gagliardi
 */
public class SwingImage implements Image<BufferedImage> {

	/** Image */
	private BufferedImage image;

	public SwingImage(BufferedImage image) {
		this.image = image;
	}
	
	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public int getWidth() {
		return image.getWidth();
	}

	@Override
	public int getHeight() {
		return image.getHeight();
	}

}
