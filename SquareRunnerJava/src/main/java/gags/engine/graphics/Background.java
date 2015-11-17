package gags.engine.graphics;

/**
 * Background wrapper to store
 * an Image without needing generics
 * @author Peter Gagliardi
 */
public class Background {

	/** Background image */
	private Image<?> image;
	
	/**
	 * Constructor
	 * @param image image to use
	 */
	public Background(Image<?> image) {
		this.image = image;
	}

	public Image<?> getImage() {
		return image;
	}
	
}
