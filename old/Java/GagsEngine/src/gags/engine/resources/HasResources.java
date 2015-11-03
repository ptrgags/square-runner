package gags.engine.resources;

/**
 * Defines an object that contains
 * a reference to a Resource Manager
 * @author Peter Gagliardi
 */
public interface HasResources {

	/**
	 * Get the resources
	 * @return the resources
	 */
	ResourceManager getResources();
	
	/**
	 * Set the resources
	 * @param resources the resources
	 */
	void setResources(ResourceManager resources);
	
}
