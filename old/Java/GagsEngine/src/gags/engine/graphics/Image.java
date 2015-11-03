package gags.engine.graphics;

/**
 * Generic image wrapper for drawing 
 * functions
 * @author Peter Gagliardi
 * @param <T> Image class type.
 * Typically BufferedImage for Swing
 * and Bitmap for Android
 */
public interface Image<T> {

	/**
	 * Get the platform-specific
	 * image stored in the Image subclass
	 * @return the image
	 */
	T getImage();
	
	/**
	 * Get the width of the image
	 * @return the width of the image
	 */
	int getWidth();
	
	/**
	 * Get the height of the image
	 * @return the height of the image
	 */
	int getHeight();
}
