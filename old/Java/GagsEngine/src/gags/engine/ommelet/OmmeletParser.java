package gags.engine.ommelet;

/**
 * Class to parse an OMMELET node
 * after it is read from a file
 * @author Peter Gagliardi
 */
public abstract class OmmeletParser {
	
	/** Root node */
	private Ommelet rootNode;
	
	/**
	 * Create an Ommelet parser
	 * @param rootNode the root OMMELET node
	 */
	public OmmeletParser(Ommelet rootNode) {
		this.rootNode = rootNode;
	}
	
	public Ommelet getRootNode() {
		return rootNode;
	}

	/**
	 * Check if an Ommelet has a child
	 * with a certain tag
	 * @param node the node to test
	 * @param tagName the desired tag name
	 * @return if the Ommelet has a certain tag
	 */
	public static boolean hasTag(Ommelet node, String tagName) {
		return node.getFirstChild(tagName) != null;
	}
	
	/** 
	 * read data from the Ommelet
	 */
	public abstract void read();
}
