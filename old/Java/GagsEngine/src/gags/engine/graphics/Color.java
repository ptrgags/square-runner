package gags.engine.graphics;

/**
 * Portable, extended version of the
 * Color Class
 * @author Peter Gagliardi
 */
public class Color {

	/** White HTML color name */
	public static final Color WHITE = new Color(0xFFFFFF);
	/** Black HTML color name */
	public static final Color BLACK = new Color(0x000000);
	/** Red HTML color name */
	public static final Color RED = new Color(0xFF0000);
	/** Lime HTML color name */
	public static final Color LIME = new Color(0x00FF00);
	/** Blue HTML color name */
	public static final Color BLUE = new Color(0x0000FF);
	/** Cyan HTML color name */
	public static final Color CYAN = new Color(0x00FFFF);
	/** Yellow HTML color name */
	public static final Color YELLOW = new Color(0xFFFF00);
	/** Magenta HTML color name */
	public static final Color MAGENTA = new Color(0xFF00FF);
	/** TARDIS Blue color name */
	public static final Color TARDIS_BLUE = new Color(0x272D70);
	/** Green HTML color name */
	public static final Color GREEN = new Color(0x008000);
	/** Brown HTML color name */
	public static final Color BROWN = new Color(0xA52A2A);
	
	/** red value */
	private int red;
	/** green value */
	private int green;
	/** blue value */
	private int blue;
	
	/**
	 * Create a color from an integer value,
	 * typically in hex notation (0xRRGGBB)
	 * @param value the value to parse
	 */
	public Color(int value) {
		blue = value % 256;
		value /= 256;
		green = value % 256;
		value /= 256;
		red = value;
	}
	
	/**
	 * Create a color from an HTML
	 * color string (#RRGGBB); 
	 * @param colorString the string to parse
	 */
	public Color(String colorString) {
		red = Integer.parseInt(colorString.substring(1, 3), 16);
		green = Integer.parseInt(colorString.substring(3, 5), 16);
		blue = Integer.parseInt(colorString.substring(5), 16);
	}
	
	/**
	 * Constructs a new color with the red,
	 * blue, and green 
	 * @param red red value
	 * @param green green value
	 * @param blue blue value
	 */
	public Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	@Override
	public String toString() {
		return "RGB: (" + red + ", " + green + ", " + blue + ")";
	}
}
