package gags.lib.util;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * A simpler interface for 
 * java.util.prefs.Preferences
 * @author Peter Gagliardi
 */
public class SaveData {

	/** Preferences object */
	private Preferences prefs;
	
	/**
	 * Create a new preferences object
	 * @param nodeName the name of the root node
	 * for the program
	 */
	public SaveData(String nodeName) {
		prefs = Preferences.userRoot();
		prefs = prefs.node(nodeName);
	}

	public String get(String key, String def) {
		return prefs.get(key, def);
	}

	public boolean getBoolean(String key, boolean def) {
		return prefs.getBoolean(key, def);
	}

	public double getDouble(String key, double def) {
		return prefs.getDouble(key, def);
	}

	public int getInt(String key, int def) {
		return prefs.getInt(key, def);
	}

	public void put(String key, String value) {
		prefs.put(key, value);
	}

	public void putBoolean(String key, boolean value) {
		prefs.putBoolean(key, value);
	}

	public void putDouble(String key, double value) {
		prefs.putDouble(key, value);
	}

	public void putInt(String key, int value) {
		prefs.putInt(key, value);
	}

	/**
	 * Clear the preferences
	 */
	public void clear() {
		try {
			prefs.clear();
		} catch (BackingStoreException e) {}
	}

	/**
	 * Remove the current node for when 
	 * uninstalling a program
	 */
	public void remove() {
		try {
			prefs.removeNode();
		} catch (BackingStoreException e) { }
	}
	
}
