package gags.engine.graphics;


import java.util.ArrayList;
import java.util.List;

/**
 * A sprite is an image resource
 * that can have a number of Image
 * "frames" for animation and other
 * purposes
 * @author Peter Gagliardi
 */
public class Sprite {

	/** Frames in the sprite */
	private List<Image<?>> frames;
	
	/**
	 * Constructor
	 * @param frames the images in the sprite
	 */
	public Sprite(Image<?>... frames) {
		this.frames = new ArrayList<Image<?>>();
		for (Image<?> img : frames)
			this.frames.add(img);
	}
	
	/**
	 * Get the first frame in the sprite
	 * @return the first frame in the sprite
	 */
	public Image<?> getFrame() {
		return frames.get(0);
	}
	
	/**
	 * Get a frame by an index number
	 * @param frame the frame index
	 * @return the image at index frame
	 */
	public Image<?> getFrame(int frame) {
		return frames.get(frame);
	}
	
	/**
	 * get the width of the sprite
	 * @return the width of the first
	 * frame of the sprite
	 */
	public int getWidth() {
		return frames.get(0).getWidth();
	}
	
	/**
	 * Get the height of the sprite
	 * @return the height of the first frame
	 * of the sprite
	 */
	public int getHeight() {
		return frames.get(0).getHeight();
	}
}
