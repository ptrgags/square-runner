package gags.engine.ommelet;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to read OMMELET files
 * @author Peter Gagliardi
 */
public class OmmeletStreamReader {
	
	/** root document node */
	private Ommelet rootNode;

	/**
	 * Constructor
	 * @param ommeletFile OMMELET file as an input stream
	 */
	public OmmeletStreamReader(InputStream ommeletFile) {
		read(ommeletFile);
	}

	public Ommelet getRootNode() {
		return rootNode;
	}

	/**
	 * Read an Ommelet file into memory
	 * @param stream OMMELET file as an input stream
	 */
	public void read(InputStream ommeletFile) {
		rootNode = new Ommelet("root");
		List<String> lines = new ArrayList<String>();
		Scanner scanner = new Scanner(ommeletFile);
		while (scanner.hasNextLine())
			lines.add(scanner.nextLine());
		scanner.close();
		
		for (String line : lines) {
			if (isTag(line))
				rootNode.getLastDescendant(getTabLevel(line)).addChild(readNode(line));
			else
				rootNode.getLastDescendant(getTabLevel(line)).addText(line.trim());
		}
	}
	
	/**
	 * Check if a line is a tag or text
	 * @param line the line in the file to check
	 * @return true if the line is an object
	 * tag, false if it is text
	 */
	private boolean isTag(String line) {
		return line.trim().charAt(0) == '[';
	}
	
	/**
	 * Read a node from a tag
	 * @param line the tag to read
	 * @return an Ommelet node with the properties 
	 */
	private Ommelet readNode(String line) {
		Ommelet node = new Ommelet(getName(line));
		if (hasProperties(line)) {
			for (String[] pair : getPropertyPairs(line))
				node.setProperty(pair[0], pair[1]);
		}
		return node;
	}
	
	/**
	 * check if a tag has properties set
	 * @param line the line to parse
	 * @return if the tag contains properties
	 */
	private boolean hasProperties(String line) {
		String contents = getTagContents(line);
		return contents.contains(" ");
	}
	
	/**
	 * Get the name of a tag
	 * @param line a line with a tag to read
	 * @return the name of an Ommelet Node
	 */
	private String getName(String line) {
		String contents = getTagContents(line);
		if (contents.contains(" "))
			return contents.substring(0, contents.indexOf(" "));
		else
			return contents;
	}
	
	/**
	 * Get pairs of properties 
	 * @param line the line to parse
	 * @return a list of property pairs
	 */
	private List<String[]> getPropertyPairs(String line) {
		String contents = getTagContents(line);
		String propertyString = contents.substring(contents.indexOf(" "));
		String[] properties = propertyString.split(";");
		List<String[]> pairs = new ArrayList<String[]>();
		for (String str : properties)
			pairs.add(str.trim().split("="));
		return pairs;
		
	}

	/**
	 * Get the number of tabs at the beginning of a string
	 * @param line the line to parse
	 * @return the number of tabs at the begging of the string
	 */
	private int getTabLevel(String line) {
		int count = 0;
		for (int i = 0; line.charAt(i) == '\t'; i++)
			count++;
		return count;
	}
	
	/**
	 * Get the contents of a tag
	 * @param line the line to parse
	 * @return the contents of the tag between
	 * square brackets
	 */
	private String getTagContents(String line) {
		String str = line.trim();
		return str.substring(1, str.length() - 1);
	}
}
