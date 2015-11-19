package gags.engine.unit;

/**
 * Defines an object (typically a Unit) that produces
 * units
 * @author Peter Gagliardi
 * @param <T> the type of unit
 */
public interface UnitProducer<T extends Unit> {
	
	/**
	 * Produce a unit
	 * @return the new unit
	 */
	public T produceUnit();
}
