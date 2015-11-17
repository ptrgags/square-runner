package gags.engine.unit;

import gags.engine.collision.CollisionMask;
import gags.engine.collision.CollisionRectangle;
import gags.engine.core.Program;
import gags.engine.events.BasicEvents;
import gags.engine.events.CollisionEvents;
import gags.engine.events.KeyboardEvents;
import gags.engine.events.MouseEvents;
import gags.engine.geom.Coords;
import gags.engine.geom.Dimensions;
import gags.engine.graphics.Painter;
import gags.engine.graphics.Sprite;
import gags.engine.io.Key;
import gags.engine.io.MouseButton;
import gags.engine.motion.HVMovement;
import gags.engine.resources.ResourceManager;

/**
 * The basic program unit
 * @author Peter Gagliardi
 */
public abstract class Unit implements Coords, Dimensions, HVMovement, BasicEvents, KeyboardEvents, MouseEvents, CollisionEvents {

	/** The type name of a unit */
	public static final String TYPE = "unit";
	
	/** x coordinate */
	private int x;
	/** y coordinate */
	private int y;
	/** width */
	private int width;
	/** height */
	private int height;
	/** horizontal speed */
	private int hSpeed;
	/** vertical speed */
	private int vSpeed;
	/** The unit's sprite */
	private Sprite sprite;
	/** Current frame of the sprite*/
	private int spriteFrame;
	/** The collision mask */
	private CollisionMask collisionMask;
	/** 
	 * If the mouse has entered the unit's 
	 * bounding box
	 */
	private boolean mouseEntered;
	/** parent Program */
	private Program parent;
	/** If the unit should be destroyed */
	private boolean destroyed;

	/**
	 * Create a unit at position (x, y)
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Unit(int x, int y) {
		this.x = x;
		this.y = y;
		height = 0;
		width = 0;
		hSpeed = 0;
		vSpeed = 0;
		mouseEntered = false;
		updateMask();
	}
	
	public int getHSpeed() {
		return hSpeed;
	}

	public void setHSpeed(int hSpeed) {
		this.hSpeed = hSpeed;
	}

	public int getVSpeed() {
		return vSpeed;
	}

	public void setVSpeed(int vSpeed) {
		this.vSpeed = vSpeed;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public int getSpriteFrame() {
		return spriteFrame;
	}

	public void setSpriteFrame(int spriteFrame) {
		this.spriteFrame = spriteFrame;
	}

	public CollisionMask getCollisionMask() {
		return collisionMask;
	}

	public void setCollisionMask(CollisionMask collisionMask) {
		this.collisionMask = collisionMask;
	}

	public Program getParent() {
		return parent;
	}	

	public void setParent(Program parent) {
		this.parent = parent;
	}

	public boolean isDestroyed() {
		return destroyed;
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Set dimensions based on the current sprite
	 */
	public void setDimensionsBySprite() {
		if (sprite != null)
			setDimensions(sprite.getWidth(), sprite.getHeight());
	}

	@Override
	public void setVelocity(int hSpeed, int vSpeed) {
		this.hSpeed = hSpeed;
		this.vSpeed = vSpeed;
	}

	@Override
	public void move() {
		x += hSpeed;
		y += vSpeed;
	}

	@Override
	public void move(int deltaX, int deltaY) {
		x += deltaX;
		y += deltaY;
	}
	
	@Override
	public void stop() {
		hSpeed = 0;
		vSpeed = 0;
	}

	@Override
	public void onCreate() { }

	@Override
	public void onFrame() { }

	@Override
	public void onDestroy() { }

	@Override
	public void onDraw(Painter painter) { }
	
	@Override
	public void onKeyPress(Key key) { }
	
	@Override
	public void onKeyRelease(Key key) { }
	
	@Override
	public void onMouseEnter(int x, int y) { 
		mouseEntered = true;
	}
	
	@Override
	public void onMouseMove(int x, int y) { }

	@Override
	public void onMouseExit(int x, int y) {	
		mouseEntered = false;
	}
	
	@Override
	public boolean mouseEntered() {
		return mouseEntered;
	}

	@Override
	public void onMousePress(MouseButton button, int x, int y) { }
	
	@Override
	public void onMouseRelease(MouseButton button, int x, int y) { }
	
	@Override
	public void onCollision(Unit otherUnit) { }
	
	@Override
	public String toString() {
		return getType() + ": (" + x + ", " + y + ")";
	}
	
	/**
	 * Update the collision mask
	 */
	public void updateMask() { 
		collisionMask = new CollisionRectangle(this);
	}
	
	/**
	 * Get the program resources
	 * @return the program resources
	 */
	public ResourceManager getResources() {
		return getParent().getResources();
	}
	
	/**
	 * Get the type of the unit
	 * @return the type name of the unit
	 */
	public String getType() {
		return TYPE;
	}
	
	/**
	 * mark the object for destruction
	 */
	public void destroy() {
		destroyed = true;
	}

	//TODO: GE - Organize methods
}
