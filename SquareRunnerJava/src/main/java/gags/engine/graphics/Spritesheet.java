package gags.engine.graphics;

/**
 * An abstract spritesheet that can
 * fetch a multi-framed sprite from
 * one image
 * @author Peter Gagliardi
 */
public abstract class Spritesheet {

	/** Source image */
	private Image<?> spritesheet;
	/** Frame width */
	private int frameWidth;
	/** Frame height */
	private int frameHeight;
	
	/**
	 * Create a new spritesheet from an image
	 * @param spritesheet the spritesheet
	 * @param frameWidth the width of one frame
	 * @param frameHeight the height of one frame
	 */
	public Spritesheet(Image<?> spritesheet, int frameWidth, int frameHeight) {
		this.spritesheet = spritesheet;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
	}
	
	public Image<?> getSpritesheet() {
		return spritesheet;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}
	
	/**
	 * Get the number of columns
	 * @return the number of columns
	 */
	public int getColumns() {
		return spritesheet.getWidth() / frameWidth;
	}

	/**
	 * Get a sprite with every frame
	 * in the spritesheet
	 * @return a sprite of all frames in
	 * the sprite sheet
	 */
	public Sprite getSprite() {
		return new Sprite(getAllFrames());
	}
	
	/**
	 * Get a sprite from an index in the
	 * sprite sheet
	 * @param index the frame index
	 * @return the sprite with frame from
	 * the index
	 */
	public Sprite getSprite(int index) {
		return new Sprite(getFrame(index));
	}
	
	/**
	 * Get a multi-framed sprite from the sprite sheet
	 * @param start the start index (inclusive)
	 * @param stop the stop index (exclusive)
	 * @return a sprite with frames from start
	 * to stop
	 */
	public Sprite getSprite(int start, int stop) {
		return new Sprite(getFrames(start, stop));
	}
	
	/**
	 * Get a sprite by coordinate in the sprite sheet
	 * @param x the x coordinate in frames
	 * @param y the y coordinate in frames
	 * @return the sprite at coordinate (x, y)
	 */
	public Sprite getSpriteXY(int x, int y) {
		return new Sprite(getFrameXY(x, y));
	}

	/**
	 * Get a single frame by index
	 * @param index the index of the frame
	 * @return the frame at index index or null
	 */
	protected abstract Image<?> getFrame(int index);

	/**
	 * Get a single frame by coordinates
	 * @param x the x coordinate in frames
	 * @param y the y coordinate in frames
	 * @return the frame at index index or null;
	 */
	private Image<?> getFrameXY(int x, int y) {
		int index = getColumns() * y + x;
		return getFrame(index);
	}

	/**
	 * Get frames from start up to but not including
	 * stop
	 * @param start the start index (inclusive
	 * @param stop the stop index (exclusive) 
	 * @return the frames from start to stop
	 */
	private Image<?>[] getFrames(int start, int stop) {
		final int NUM_FRAMES = stop - start;
		Image<?>[] frames = new Image<?>[NUM_FRAMES];
		for (int i = 0; i < NUM_FRAMES; i++)
			frames[i] = getFrame(start + i);
		return frames;
	}

	/**
	 * Get all frames in the sprite sheet
	 * @return all frames in the sprite sheet
	 */
	private Image<?>[] getAllFrames() {
		return getFrames(0, numOfFrames());
	}

	/**
	 * Get the number of frames in the sprite sheet
	 * @return the number of frames in the spritesheet
	 */
	private int numOfFrames() {
		int rows = spritesheet.getHeight() / frameHeight;
		int columns = spritesheet.getWidth() / frameWidth;
		return rows * columns;
	}
	
}
