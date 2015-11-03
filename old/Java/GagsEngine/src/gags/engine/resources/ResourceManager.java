package gags.engine.resources;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import gags.engine.graphics.Background;
import gags.engine.graphics.Sprite;

/**
 * Class for loading and storing resources
 * @author Peter Gagliardi
 */
public abstract class ResourceManager implements ResourceLoader {

	/** Backgrounds */
	private Map<String, Background> backgrounds;
	/** Sprites */
	private Map<String, Sprite> sprites;
	/** If resources have already been loaded */
	private boolean resourcesLoaded;
	
	/**
	 * Constructor
	 */
	public ResourceManager() {
		backgrounds = new HashMap<String, Background>();
		sprites = new HashMap<String, Sprite>();
		resourcesLoaded = false;
	}
	
	/**
	 * Add a new background
	 * @param id the id of the background
	 * @param background the background
	 */
	public void addBackground(String id, Background background) {
		backgrounds.put(id, background);
	}
	
	/**
	 * get a background from the resources
	 * @param id the id of the background
	 * @return the background or null
	 */
	public Background getBackground(String id) {
		return backgrounds.get(id);
	}
	
	/**
	 * Add a new sprite
	 * @param id the id of the sprite
	 * @param sprite the sprite
	 */
	public void addSprite(String id, Sprite sprite) {
		sprites.put(id, sprite);
	}
	
	/**
	 * Get a sprite from the resources
	 * @param id the id of the sprite
	 * @return the sprite or null
	 */
	public Sprite getSprite(String id) {
		return sprites.get(id);
	}
	
	/**
	 * Load resources from an ommelet file. This can only be done
	 * once
	 * @param ommeletFileName the name of an Ommelet file
	 */
	public void loadResources(String ommeletName) {
		if (!resourcesLoaded) {
			ResourceOmmeletParser parser = new ResourceOmmeletParser(loadResourceOmmelet(ommeletName), this);
			parser.read();
			resourcesLoaded = true;
		}
	}
	
	/**
	 * Get an input stream of a resource file
	 * @param fname the file and file path to look for
	 * @return a resource stream or null
	 */
	public abstract InputStream getResourceStream(String fname);
}
