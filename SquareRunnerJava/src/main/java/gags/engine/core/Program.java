package gags.engine.core;

import java.util.Iterator;
import java.util.List;

import gags.engine.events.BasicEvents;
import gags.engine.events.KeyboardEvents;
import gags.engine.events.MouseEvents;
import gags.engine.graphics.Background;
import gags.engine.graphics.Color;
import gags.engine.graphics.HasBackground;
import gags.engine.graphics.Painter;
import gags.engine.io.Key;
import gags.engine.io.MouseButton;
import gags.engine.resources.HasResources;
import gags.engine.resources.ResourceManager;
import gags.engine.unit.Unit;
import gags.engine.unit.UnitHandler;

/**
 * Basic program framework
 * @author Peter Gagliardi
 */
public abstract class Program implements ProgramThreadController, BasicEvents, KeyboardEvents, MouseEvents, UnitHandler, HasResources, HasBackground {
	
	//-ProgramThreadController-----
	/** If the program is running */
	private boolean running;
	/** If the game is paused */
	private boolean paused;
	/** The next program to run */
	private Program nextProgram;

	//-HasBackground-----
	/** The background */
	private Background background;
	
	//-MouseEvents-----
	/** If the mouse has entered the program */
	private boolean mouseEntered;
	
	//-HasResources-----
	/** Resource manager for loading files, images, etc. */
	private ResourceManager resources;
	
	//-HasBackground-----
	/** The background color */
	private Color color;
	/** If the background color should be displayed */
	private boolean displayBgColor;

	//-ProgramThreadController-----
	
	@Override
	public final void start() {
		running = true;
		onCreate();
	}

	@Override
	public final boolean isRunning() {
		return running;
	}

	@Override
	public final void stop() {
		running = false;
	}
	
	@Override
	public final void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	@Override
	public final boolean isPaused() {
		return paused;
	}
	
	@Override
	public final Program getNextProgram() {
		return nextProgram;
	}
	
	@Override
	public final void setNextProgram(Program program) {
		nextProgram = program;
		stop();
	}
	
	//-BasicEvents-----

	@Override
	public void onCreate() { }

	@Override
	public void onFrame() { }

	@Override
	public void onDestroy() { }

	@Override
	public void onDraw(Painter painter) { }
	
	//-KeyboardEvents-----
	
	@Override
	public void onKeyPress(Key key) { }
	
	@Override
	public void onKeyRelease(Key key) {	}
	
	//-MouseEvents-----
	
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
	public final boolean mouseEntered() {
		return mouseEntered;
	}

	@Override
	public void onMousePress(MouseButton button, int x, int y) { }
	
	@Override
	public void onMouseRelease(MouseButton button, int x, int y) { }

	//-UnitHandler-----
	//TODO: Consolidate UnitHandler
	
	@Override
	public void onFrame(Unit unit) {
		if (unit != null)
			unit.onFrame();
	}
	
	@Override
	public void onFrame(List<? extends Unit> units) {
		for (int i = 0; i < units.size(); i++)
			onFrame(units.get(i));
	}
	
	@Override
	public void testCollision(Unit unit, Unit otherUnit) {
		if (unit != null && otherUnit != null && unit.getCollisionMask().intersects(otherUnit.getCollisionMask())) {
			unit.onCollision(otherUnit);
			otherUnit.onCollision(unit);
		}
	}
	
	@Override
	public void testCollisions(Unit unit, List<? extends Unit> otherUnits) {
		for (Unit otherUnit : otherUnits)
			testCollision(unit, otherUnit);
	}
	
	@Override
	public void testCollisions(List<? extends Unit> units, List<? extends Unit> otherUnits) {
		//LATER: Check for object equivalence
		for (int i = 0; i < units.size(); i++) {
			Unit unit = units.get(i);
			for (int j = 0; j < otherUnits.size(); j++) {
				Unit otherUnit = otherUnits.get(j);
				testCollision(unit, otherUnit);
			}
		}
	}
	
	@Override
	public void testMousePress(Unit unit, MouseButton button, int x, int y) {
		if (unit != null && unit.getCollisionMask().containsPoint(x, y))
			unit.onMousePress(button, x, y);
	}
	
	@Override
	public void testMousePress(List<? extends Unit> units, MouseButton button, int x, int y) {
		for (Unit unit : units)
			testMousePress(unit, button, x, y);
	}
	
	@Override
	public void testMouseRelease(Unit unit, MouseButton button, int x, int y) {
		if (unit != null && unit.getCollisionMask().containsPoint(x, y))
			unit.onMouseRelease(button, x, y);
	}
	
	@Override
	public void testMouseRelease(List<? extends Unit> units, MouseButton button, int x, int y) {
		for (int i = 0; i < units.size(); i++)
			testMouseRelease(units.get(i), button, x, y);
	}
	
	@Override
	public void testMouseMotion(Unit unit, int x, int y) {
		if (unit != null) {
			if (!unit.mouseEntered() && unit.getCollisionMask().containsPoint(x, y))
				unit.onMouseEnter(x, y);
			else if (unit.mouseEntered() && !unit.getCollisionMask().containsPoint(x, y))
				unit.onMouseExit(x, y);
			else if (unit.mouseEntered())
				unit.onMouseMove(x, y);
		}
	}
	
	@Override
	public void testMouseMotion(List<? extends Unit> units, int x, int y) {
		if (units != null) {
			for (int i = 0; i < units.size(); i++) {
				Unit unit = units.get(i);
				testMouseMotion(unit, x, y);
			}
		}
	}
	
	@Override
	public void testDestruction(Unit unit) {
		if (unit != null && unit.isDestroyed())
			unit.onDestroy();
	}
	
	@Override
	public void testDestruction(List<? extends Unit> units) {
		Iterator<? extends Unit> iterator = units.iterator();
		while (iterator.hasNext()) {
			Unit unit = iterator.next();
			if (unit != null && unit.isDestroyed()) {
				unit.onDestroy();
				iterator.remove();
			}
		}
	}
	
	@Override
	public final void drawUnit(Unit unit, Painter painter) {
		if (unit != null)
			unit.onDraw(painter);
	}
	
	@Override
	public final void drawUnits(List<? extends Unit> units, Painter painter) {
		if (units != null) {
			for (int i = 0; i < units.size(); i++)
				drawUnit(units.get(i), painter);
		}
	}

	//-HasResources-----
	
	@Override
	public ResourceManager getResources() {
		return resources;
	}
	
	@Override
	public void setResources(ResourceManager resources) {
		this.resources = resources;
	}
	
	//-HasBackground-----
	
	@Override
	public final int getBgX() {
		return 0;
	}
	
	@Override
	public final int getBgY() {
		return 0;
	}
	
	@Override
	public final int getBgWidth() {
		// TODO: GE - program dimensions
		return 640;
	}
	
	@Override
	public final int getBgHeight() {
		// TODO: GE - program dimensions
		return 480;
	}
	
	@Override
	public final Background getBackground() {
		return background;
	}
	
	@Override
	public final void setBackground(Background background) {
		this.background = background;
	}
	
	@Override
	public final Color getBgColor() {
		return color;
	}
	
	@Override
	public final void setBgColor(Color color) {
		this.color = color;
		displayBgColor = true;
	}
	
	@Override
	public final boolean displayBgColor() {
		return displayBgColor;
	}
	
	@Override
	public final void setDisplayBgColor(boolean display) {
		displayBgColor = display;
	}
}
