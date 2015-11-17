package gags.sample.squarerunner.tiles;

import gags.engine.core.Program;
import gags.engine.graphics.Sprite;
import gags.sample.squarerunner.Player;

/**
 * A flip tile reverses the user's controls
 * @author Peter Gagliardi
 */
public class FlipTile extends Tile {

	/** If the controls should be reversed */
	private boolean reverse;
	/** Sprite for the flip tile*/
	private Sprite sprite;
	/** The player object */
	private Player player;
	
	/**
	 * Constructor
	 * @param x the x position
	 * @param y the y position
	 * @param reverse if the tile reverses the
	 * controls (true) or sets them back to normal
	 * (false)
	 * @param parent the parent program
	 */
	public FlipTile(int x, int y, boolean reverse, Player player, Program parent) {
		super(x, y, parent);
		this.reverse = reverse;
		this.player = player;
		sprite = getResources().getSprite(reverse ? "flipTile_flipped" : "flipTile_normal");
		setSprite(sprite);
		setDimensionsBySprite();
		updateMask();
	}
	
	@Override
	public void onFrame() {
		super.onFrame();
		if (player.isFlipped() == reverse)
			setSprite(null);
		else
			setSprite(sprite);
	}

	@Override
	public void onHover(Player player) {
		player.setFlipped(reverse);
	}

}
