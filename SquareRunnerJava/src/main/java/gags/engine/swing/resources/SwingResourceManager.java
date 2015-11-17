package gags.engine.swing.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import gags.engine.graphics.Background;
import gags.engine.graphics.Image;
import gags.engine.graphics.Sprite;
import gags.engine.graphics.Spritesheet;
import gags.engine.ommelet.Ommelet;
import gags.engine.ommelet.OmmeletStreamReader;
import gags.engine.resources.ResourceManager;
import gags.engine.swing.graphics.SwingImage;
import gags.engine.swing.graphics.SwingSpritesheet;

/**
 * Resource manager for Swing
 * @author Peter Gagliardi
 */
public class SwingResourceManager extends ResourceManager {

	@Override
	public Image<?> loadImage(String id) {
		BufferedImage image = getResourceImage("img/", id);
		return new SwingImage(image);
	}
	
	@Override
	public Image<?> loadImage(String folder, String id) {
		BufferedImage image = getResourceImage(folder, id);
		return new SwingImage(image);
	}

	@Override
	public Sprite loadSprite(String id) {
		return new Sprite(loadImage("spr/", id));
	}

	@Override
	public Spritesheet loadSpritesheet(String id, int frameWidth, int frameHeight) {
		BufferedImage spritesheet = getResourceImage("spr/", id);
		return new SwingSpritesheet(new SwingImage(spritesheet), frameWidth, frameHeight);
	}

	@Override
	public Background loadBackground(String id) {
		return new Background(loadImage("bg/", id));
	}
	
	@Override
	public Ommelet loadResourceOmmelet(String id) {
		OmmeletStreamReader reader = new OmmeletStreamReader(getResourceStream("res/" + id + ".omml"));
		return reader.getRootNode();
	}
	
	@Override
	public Ommelet loadOmmelet(String folder, String id) {
		OmmeletStreamReader reader = new OmmeletStreamReader(getResourceStream("res/" + folder + id + ".omml"));
		return reader.getRootNode();
	}

	@Override
	public InputStream getResourceStream(String fname) {
		return SwingResourceManager.class.getClassLoader().getResourceAsStream(fname);
	}

	/**
	 * Get a PNG image from the resources
	 * @param id the id of the image
	 * @return The image or null
	 */
	private BufferedImage getResourceImage(String folder, String id) {
		try {
			return ImageIO.read(getResourceStream("res/" + folder + id + ".png"));
		} catch (IOException e) {
			return null;
		}
	}

}
