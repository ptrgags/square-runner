package gags.engine.resources;

import gags.engine.graphics.Background;
import gags.engine.graphics.Image;
import gags.engine.graphics.Sprite;
import gags.engine.graphics.Spritesheet;
import gags.engine.ommelet.Ommelet;

/**
 * Class for handling loading and storing
 * resources
 * @author Peter Gagliardi
 */
public interface ResourceLoader {

	/**
	 * Load an image from the resources
	 * @param id the id of the resource
	 * @return the image or null if it does not exist
	 */
	Image<?> loadImage(String id);
	
	/**
	 * Load an image from a different folder
	 * @param folder the subfolder of resources/res/ with trailing slash
	 * @param id the id of the image to get
	 * @return the image or null
	 */
	Image<?> loadImage(String folder, String id);
	
	/**
	 * Load a single-framed sprite from the resources
	 * @param id the id of the image
	 * @return the sprite or null if it does not exist
	 */
	Sprite loadSprite(String id);
	
	/**
	 * Get a spritesheet from the resources
	 * @param id the id of the spritesheet image
	 * @param frameWidth the width of 1 frame
	 * @param frameHeight the height of one frame
	 * @return the spritesheet or null if it does not exist
	 */
	Spritesheet loadSpritesheet(String id, int frameWidth, int frameHeight);
	
	/**
	 * Load a background from the resources
	 * @param id the id of the background
	 * @return the background or null if it does not exist
	 */
	Background loadBackground(String id);
	
	/**
	 * Load a resource OMMELET file
	 * @param fname the filename of the ommelet file
	 * @return the ommelet
	 */
	Ommelet loadResourceOmmelet(String id);
	
	/**
	 * Load an OMMELETE file
	 * @param folder the folder of the ommelet with a trailing slash
	 * @param id the id of the ommelet file
	 * @return the ommelet
	 */
	Ommelet loadOmmelet(String folder, String id);
}
