package gags.engine.resources;

import java.util.List;

import gags.engine.graphics.Background;
import gags.engine.graphics.Sprite;
import gags.engine.graphics.Spritesheet;
import gags.engine.ommelet.Ommelet;
import gags.engine.ommelet.OmmeletParser;

/**
 * Ommelet Parser for a resource Ommelet
 * @author Peter Gagliardi
 */
public class ResourceOmmeletParser extends OmmeletParser {

	/** tag name for backgrounds */
	private static final String TAG_BACKGROUNDS = "backgrounds";
	/** tag name for tilesets */
	//private static final String TAG_TILESETS = "tilesets";
	/** tag name for sprites */
	private static final String TAG_SPRITES = "sprites";
	/** tag name for a sprite */
	private static final String TAG_SPRITE = "sprite";
	/** tag name for a spritesheet */
	private static final String TAG_SPRITESHEET = "spritesheet";
	/** tag name for strings */
	//private static final String TAG_STRINGS = "strings";
	/** filename property */
	private static final String PROP_FNAME = "fname";
	/** ID property */
	private static final String PROP_ID = "id";
	/** frame width property */
	private static final String PROP_FRAME_WIDTH = "framewidth";
	/** frame height property */
	private static final String PROP_FRAME_HEIGHT = "frameheight";
	/** frame index property */
	private static final String PROP_INDEX = "index";
	/** frame start index property */
	private static final String PROP_START_INDEX = "start";
	/** frame end index property */
	private static final String PROP_END_INDEX = "end";
	
	/** Resource manager to store resources */
	private ResourceManager resources;
	/** Number of completed steps */
	private int stepsComplete;
	
	/**
	 * Create a resource Ommelet parser
	 * @param rootNode the root node of the resource Ommelet
	 * @param resources the resource manager to store the resources
	 */
	public ResourceOmmeletParser(Ommelet rootNode, ResourceManager resources) {
		super(rootNode);
		this.resources = resources;
		stepsComplete = 0;
	}
	
	/**
	 * Get the number of steps complete
	 * @return the number of steps complete
	 */
	public int getStepsComplete() {
		return stepsComplete;
	}
	
	@Override
	public void read() {
		if (hasTag(getRootNode(), TAG_BACKGROUNDS))
			readBackgrounds();
		
		//if (hasTag(getRootNode(), TAG_TILESETS))
		//	readTilesets();
		
		if (hasTag(getRootNode(), TAG_SPRITES))
			readSprites();
		
		//if (hasTag(getRootNode(), TAG_STRINGS))
		//	readStrings();
	}
	
	/**
	 * Read a single resource 
	 * @param frame the frame to 
	 */
	public void readStep(int frame) {
		if (stepsComplete < numOfSteps(TAG_BACKGROUNDS))
			readBackground(getRootNode().getFirstChild(TAG_BACKGROUNDS).getChild(stepsComplete));
		else if (stepsComplete - numOfSteps(TAG_BACKGROUNDS) < numOfSteps(TAG_SPRITES))
			readSprite(getRootNode().getFirstChild(TAG_SPRITES).getChild(stepsComplete - numOfSteps(TAG_BACKGROUNDS)));
		stepsComplete++;
	}
	
	/**
	 * get the number of steps required to load the resources
	 * @return the number of steps needed
	 */
	public int numOfSteps() {
		int count = 0;
		count += numOfSteps(TAG_BACKGROUNDS);
		//LATER: GE - Tilesets
		count += numOfSteps(TAG_SPRITES);
		//LATER: GE - Strings
		return count;
	}
	
	/**
	 * Get the number of steps needed for a given type of 
	 * @param tag the tag for the type of resources to check
	 * @return the number of steps needed for the given resource type
	 */
	private int numOfSteps(String tag) {
		if (hasTag(getRootNode(), tag))
			return getRootNode().getFirstChild(tag).getChildren().size();
		else
			return 0;
	}
	
	/**
	 * Read backgrounds from the Ommelet and store
	 * them in the resources
	 */
	private void readBackgrounds() {
		List<Ommelet> backgrounds = getRootNode().getFirstChild(TAG_BACKGROUNDS).getChildren();
		for (Ommelet background : backgrounds)
			readBackground(background);
	}
	
	/**
	 * Read a single background into memory
	 * @param backgroundNode the background OMMELET node
	 */
	private void readBackground(Ommelet backgroundNode) {
		String filename = backgroundNode.getPropertyValue(PROP_FNAME);
		String id = backgroundNode.getPropertyValue(PROP_ID);
		Background bg = resources.loadBackground(filename);
		resources.addBackground(id, bg);
	}
	
	//LATER: read tilesets
	
	/**
	 * Read sprites from the Ommelet
	 */
	private void readSprites() {
		List<Ommelet> sprites = getRootNode().getFirstChild(TAG_SPRITES).getChildren();
		for (Ommelet sprite : sprites)
			readSprite(sprite);
	}
	
	/**
	 * Read a single sprite
	 * @param spriteNode
	 */
	private void readSprite(Ommelet spriteNode) {
		if (spriteNode.getName().equals(TAG_SPRITE)) {
			String fname = spriteNode.getPropertyValue(PROP_FNAME);
			String id = spriteNode.getPropertyValue(PROP_ID);
			Sprite spr = resources.loadSprite(fname);
			resources.addSprite(id, spr);
		}
		else if (spriteNode.getName().equals(TAG_SPRITESHEET)) {
			String fname = spriteNode.getPropertyValue(PROP_FNAME);
			int frameWidth = Integer.parseInt(spriteNode.getPropertyValue(PROP_FRAME_WIDTH));
			int frameHeight = Integer.parseInt(spriteNode.getPropertyValue(PROP_FRAME_HEIGHT));
			Spritesheet spritesheet = resources.loadSpritesheet(fname, frameWidth, frameHeight);
			List<Ommelet> frames = spriteNode.getChildren();
			for (Ommelet frame : frames) {
				String id = frame.getPropertyValue(PROP_ID);
				boolean singleFrame = frame.getPropertyValue(PROP_INDEX) != null;
				Sprite spr;
				if (singleFrame) {
					int index = Integer.parseInt(frame.getPropertyValue(PROP_INDEX));
					spr = spritesheet.getSprite(index);
				}
				else {
					int startIndex = Integer.parseInt(frame.getPropertyValue(PROP_START_INDEX));
					int endIndex = Integer.parseInt(frame.getPropertyValue(PROP_END_INDEX));
					spr = spritesheet.getSprite(startIndex, endIndex);
					resources.addSprite(id, spr);
				}
				resources.addSprite(id, spr);
			}
		}
	}
	
	//LATER: read strings
}
