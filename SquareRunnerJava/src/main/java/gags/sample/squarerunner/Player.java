package gags.sample.squarerunner;

import gags.engine.core.Program;
import gags.engine.events.Action;
import gags.engine.graphics.Painter;
import gags.engine.graphics.Sprite;
import gags.engine.io.Key;
import gags.engine.unit.Unit;
import gags.engine.unit.UnitProducer;
import gags.lib.time.CountdownTimer;
import gags.lib.time.FrameTimer;
import gags.sample.squarerunner.modes.Game;

/**
 * Player
 * @author Peter Gagliardi
 */
public class Player extends Unit implements UnitProducer<Laser> {

	/** Unit type */
	public static final String TYPE = "Player";
	
	/** Speed of the player */
	private static final int SPEED = 8;
	/** Frames in the invincibility animation */
	private static final int INVINCIBLE_FRAMES = 8;

	/** How much ammo the player has */
	private int ammo;
	/** How many keys the player has */
	private int keys;
	/** if the player is invincible */
	private boolean invincible;
	/** If the controls are flipped */
	private boolean flipped;
	/** If the player has fired to avoid repeat shots */
	private boolean fired;
	/** Timer for counting down until invincibility wears off */
	private FrameTimer invincibleTimer;
	/** Invincibility sprite sheet */
	private Sprite invincibility;

	/**
	 * Create a new player
	 * @param x the x position
	 * @param y the y position
	 */
	public Player(int x, int y, Program parent) {
		super(x, y);
		setParent(parent);
		setSprite(getResources().getSprite("shipColors"));
		setSpriteFrame(Global.getColorIndex());
		invincibility = getResources().getSprite("invincibility");
		setDimensionsBySprite();
		updateMask();
		
		ammo = 0;
		keys = 0;
		invincible = false;
		flipped = false;
		fired = false;
		
		invincibleTimer = new CountdownTimer(new Action() {
			@Override
			public void performAction() {
				setInvincible(false);
			}
		}, 70);
	}
	
	public int getAmmo() {
		return ammo;
	}
	
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	/**
	 * Increase Ammo by a certain amount
	 * @param amount
	 */
	public void increaseAmmo(int amount) {
		ammo += amount;
	}
	
	public int getKeys() {
		return keys;
	}
	
	/**
	 * Get a key
	 */
	public void addKey() {
		keys++;
	}
	
	/**
	 * Use a key on a lock
	 */
	public void useKey() {
		if (keys > 0)
			keys--;
	}
	
	public boolean isInvincible() {
		return invincible;
	}
	
	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
		if (invincible)
			invincibleTimer.restart();
	}
	
	public boolean isFlipped() {
		return flipped;
	}
	
	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}
	
	public boolean hasFired() {
		return fired;
	}
	
	public void setFired(boolean fired) {
		this.fired = fired;
	}
	
	@Override
	public void onFrame() {
		move();
		if (getX() < Game.GAME_X) {
			setX(Game.GAME_X);
			stop();
		}
		if (getX() > Game.GAME_X + Game.GAME_WIDTH - getWidth()) {
			setX(Game.GAME_X + Game.GAME_WIDTH - getWidth());
			stop();
		}
		updateMask();
		
		if (invincible)
			invincibleTimer.onFrame();
	}
	
	@Override
	public void onKeyPress(Key key) {
		if (key == Key.LEFT) {
			if (flipped)
				setHSpeed(SPEED);
			else
				setHSpeed(-SPEED);
		}
		else if (key == Key.RIGHT) {
			if (flipped)
				setHSpeed(-SPEED);
			else
				setHSpeed(SPEED);
		}
		else if (key == Key.SPACEBAR) {
			if (ammo > 0 && !fired) {
				((Game) getParent()).fire();
				ammo--;
				fired = true;
			}
		}
	}
	
	@Override
	public void onKeyRelease(Key key) {
		if (key == Key.LEFT) {
			if (isFlipped() && getHSpeed() > 0 || !isFlipped() && getHSpeed() < 0)
				stop();
		}
		else if (key == Key.RIGHT) {
			if (isFlipped() && getHSpeed() < 0 || !isFlipped() && getHSpeed() > 0)
				stop();
		}
		else if (key == Key.SPACEBAR)
			fired = false;
	}
	
	@Override
	public void onDraw(Painter painter) {
		painter.drawSprite(this);
		if (invincible)
			painter.drawSprite(invincibility, invincibleTimer.getElapsedFrames() % (INVINCIBLE_FRAMES * 2) / 2, getX(), getY());
	}
	
	@Override
	public String getType() {
		return TYPE;
	}
	
	@Override
	public Laser produceUnit() {
		return new Laser(getX() + getWidth() / 4, getY(), getParent());
	}
}
