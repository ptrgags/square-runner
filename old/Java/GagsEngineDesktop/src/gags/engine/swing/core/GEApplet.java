package gags.engine.swing.core;

import javax.swing.JApplet;
import javax.swing.JPanel;

/**
 * Applet for displaying the simulation
 * @author Peter Gagliardi
 */
public abstract class GEApplet extends JApplet {
	
	/** Serial ID */
	private static final long serialVersionUID = 1792058489081513156L;

	@Override
	public void init() {
		getContentPane().add(getProgramPanel());
		setSize(640, 480);
	}
	
	public abstract JPanel getProgramPanel();
	
}
