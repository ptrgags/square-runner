package gags.engine.unit;

import gags.engine.graphics.Painter;
import gags.engine.io.MouseButton;

import java.util.List;

/**
 * Interface for an object
 * that calls events on Units
 * @author Peter Gagliardi
 */
public interface UnitHandler {

	//LATER: setParent(Unit) and setParent(List<Unit>)
	
	/**
	 * Execute a null-safe onFrame() for a unit
	 * @param unit the unit
	 */
	void onFrame(Unit unit);
	
	/**
	 * Execute onFrame() for a list of units
	 * @param units the list of units
	 */
	void onFrame(List<? extends Unit> units);
	
	/**
	 * Test a collision between two units
	 * and call onCollision() for each unit if 
	 * a collision occurs
	 * @param unit the first unit
	 * @param otherUnit the other unit
	 */
	void testCollision(Unit unit, Unit otherUnit);
	
	/**
	 * Test a unit for collisions against a list of units,
	 * calling onCollision() when collisions occur
	 * @param unit the unit
	 * @param otherUnits the other units
	 */
	void testCollisions(Unit unit, List<? extends Unit> otherUnits);
	
	/**
	 * Test a collision between two lists of units, calling
	 * onCollision() when collisions occur
	 * @param units the first list of units
	 * @param otherUnits the second list of units
	 */
	void testCollisions(List<? extends Unit> units, List<? extends Unit> otherUnits);
	
	/**
	 * Test if a mouse press event should be called on a unit
	 * @param unit the unit to test
	 * @param button the mouse button
	 * @param x the x position of the cursor
	 * @param y the y position of the cursor
	 */
	void testMousePress(Unit unit, MouseButton button, int x, int y);
	
	/**
	 * Test if a mouse press event should be called
	 * on a list of units
	 * @param units the units to test
	 * @param button the mouse button
	 * @param x the x position of the cursor
	 * @param y the y position of the cursor
	 */
	void testMousePress(List<? extends Unit> units, MouseButton button, int x, int y);
	
	/**
	 * Test if a mouse release event should be called
	 * on a unit
	 * @param unit the unit to test
	 * @param button the mouse button
	 * @param x the x position of the cursor
	 * @param y the y position of the cursor
	 */
	void testMouseRelease(Unit unit, MouseButton button, int x, int y);
	
	/**
	 * Test if a mouse release event should be called on
	 * a list of units
	 * @param units the units to test
	 * @param button the mouse button
	 * @param x the x position of the cursor
	 * @param y the y position of the cursor
	 */
	void testMouseRelease(List<? extends Unit> units, MouseButton button, int x, int y);
	
	/**
	 * Determine what mouse motion event
	 * to perform on a unit
	 * @param unit the unit to test
	 * @param x the x position of the mouse
	 * @param y the y position of the mouse
	 */
	void testMouseMotion(Unit unit, int x, int y);
	
	/**
	 * Determine what mouse motion events to perform
	 * for a list of units
	 * @param units the units to test
	 * @param x the x position of the mouse
	 * @param y the y position of the mouse
	 */
	void testMouseMotion(List<? extends Unit> units, int x, int y);
	
	/**
	 * Check if a unit should be destroyed,
	 * if so, destroy the unit
	 * @param unit the unit to test
	 */
	void testDestruction(Unit unit);

	/**
	 * Check if any of a list of units
	 * should be destroyed, if so, destroy them
	 * @param units the units to test
	 */
	void testDestruction(List<? extends Unit> units);

	/**
	 * Call a unit's onDraw() event
	 * @param unit the unit
	 * @param painter the painter to draw with
	 */
	void drawUnit(Unit unit, Painter painter);
	
	/**
	 * Call multiple units' onDraw() events
	 * @param units the units to draw
	 * @param painter the painter to draw with
	 */
	void drawUnits(List<? extends Unit> units, Painter painter);
	
}
