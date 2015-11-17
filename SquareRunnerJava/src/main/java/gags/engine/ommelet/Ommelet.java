/**
 * Object Markup Made Easy for Lightweight 
 * Editing of Text (OMMELET)<br/>
 * <br/>
 * OMMELET is a lightweight markup language
 * that provides a simpler alternative to
 * XML for programmers.
 */
package gags.engine.ommelet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines the basic OMMELET
 * node.
 * @author Peter Gagliardi
 */
public class Ommelet {
	/** Name of the node*/
	private String name;
	/** Properties in key, value pairs */
	private Map<String, String> properties;
	/** Text stored in the node */
	private List<String> text;
	/** Child nodes */
	private List<Ommelet> children;
	
	/**
	 * Constructor
	 * @param name the name of the node
	 * @post
	 * - name is set<br/>
	 * - properties, text, and children
	 * are initialized
	 */
	public Ommelet(String name) {
		this.name = name;
		properties = new HashMap<String, String>();
		text = new ArrayList<String>();
		children = new ArrayList<Ommelet>();
	}
	
	public String getName() {
		return name;
	}

	/**
	 * Set a property
	 * @param key the key of the property
	 * @param value the value of the property
	 */
	public void setProperty(String key, String value) {
		properties.put(key, value);
	}
	
	/**
	 * Get the value of a property
	 * @param property the property to look for
	 * @return the value of the property or null
	 */
	public String getPropertyValue(String property) {
		return properties.get(property);
	}
	
	/**
	 * Add a line of text to the node
	 * @param line the line of text to add
	 */
	public void addText(String line) {
		text.add(line);
	}
	
	public List<String> getText() {
		return text;
	}
	
	/**
	 * Get a line of text
	 * @param line the line of text to get
	 * @return a line of text or null
	 */
	public String getText(int line) {
		return text.get(line);
	}

	/**
	 * Add a child node to the list
	 * @param child the node to add
	 */
	public void addChild(Ommelet child) {
		children.add(child);
	}
	
	/**
	 * Get a child node
	 * @param index the index of the child node
	 * @return the child node at the given index
	 */
	public Ommelet getChild(int index) {
		return children.get(index);
	}
	
	/**
	 * Get the first child node
	 * @return the first child node
	 * or null if there are no child nodes
	 */
	public Ommelet getFirstChild() {
		return children.get(0);
	}
	
	/**
	 * Get the first child with a given name
	 * @param name the name of the child
	 * @return the first child with name <code>name</code>
	 * or null
	 */
	public Ommelet getFirstChild(String name) {
		for (Ommelet child : children) {
			if (child.getName().equals(name))
				return child;
		}
		return null;
	}
	
	/**
	 * Get the most recently added node
	 * @return the last child in the list
	 */
	public Ommelet getLastChild() {
		return children.get(children.size() - 1);
	}
	
	/**
	 * Get a descendant a variable number
	 * of levels down the hierarchy 
	 * @param level the level to get the descendant from
	 * @return the last descendent <code>level</code> 
	 * levels down the hierarchy
	 */
	public Ommelet getLastDescendant(int level) {
		if (level == 0)
			return this;
		Ommelet node = this;
		for (int i = 0; i < level; i++) {
			node = node.getLastChild();
		}
		return node;
	}
	
	public List<Ommelet> getChildren() {
		return children;
	}
	
	/**
	 * Get all children of a given name
	 * @param name the children to get
	 * @return an array of all children with the
	 * specified name
	 */
	public List<Ommelet> getChildren(String name) {
		List<Ommelet> childList = new ArrayList<Ommelet>();
		for (Ommelet omelet : children) {
			if (omelet.getName().equals(name))
				childList.add(omelet);
		}
		return childList;
	}

	/**
	 * Get a a string representation of the
	 * node for writing to a file, treating
	 * the current node as the root node<br/>
	 * <b>Note:</b> the text and properties of the
	 * current node will not be retained 
	 * @return a string of the nodes
	 */
	public String toFileString() {
		String str = "";
		for (Ommelet child : children) {
			str += child.toString(0);
			str += "\n";
		}
		return str;
	}
	
	@Override
	public String toString() {
		return toString(0);
	}
	
	/**
	 * Get a string representation of the object
	 * tabbed in by the proper amount to account
	 * for children
	 * @param tabs the number of tabs the string
	 * should be tabbed in by
	 * @return a string representation of the object
	 * to be used by toString
	 */
	private String toString(int tabs) {
		String str = "";
		for (int i = 0; i < tabs; i++)
			str += "\t";
		str += "[" + name;
		if (properties.size() > 0)
			str += " " + getPropertyString();
		str += "]";
		for (String line : text) {
			str += "\n";
			for (int i = 0; i < tabs; i++)
				str+= "\t";
			str += "\t" + line;
		}
		for (Ommelet child : children) {
			str += "\n";
			str += child.toString(tabs + 1);
		}
		return str;
	}
	
	/**
	 * Get a string of all the properties
	 * @return the properties as a string in the format
	 * key=value; key=value[; key=value]
	 */
	private String getPropertyString() {
		String str = "";
		List<String> propertyList = formatProperties();
		for (int i = 0; i < propertyList.size() - 1; i++)
			str += propertyList.get(i) + "; ";
		str+= propertyList.get(propertyList.size() - 1);
		return str;
	}
	
	/**
	 * Format properties into a list of values in the form
	 * key=value
	 * @return a list of properties
	 */
	private List<String> formatProperties() {
		List<String> propertyList = new ArrayList<String>();
		for (String key : properties.keySet())
			propertyList.add(key + "=" + properties.get(key));
		return propertyList;
	}
}
