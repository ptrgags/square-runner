package gags.engine.swing.core;

import gags.engine.core.Program;
import gags.engine.core.ProgramThread;
import gags.engine.swing.resources.SwingResourceManager;

import javax.swing.JComponent;

/**
 * Program thread for Swing.
 * @author Peter Gagliardi
 */
public class SwingProgramThread extends ProgramThread {

	/** Component to display the graphics */
	private JComponent component;
	
	/**
	 * Create a new program thread optimized for Swing
	 * @param program the program to run
	 * @param component the component to display the
	 * graphics in
	 */
	public SwingProgramThread(Program program, ProgramPanel parent) {
		super(program, parent);
		component = parent;
	}

	@Override
	protected void loadResources() {
		Program program = getProgram();
		if (program.getResources() == null)
			program.setResources(new SwingResourceManager());
	}

	@Override
	protected void onDraw() {
		component.repaint();
	}
	
	@Override
	protected void exit() {
		System.exit(0);
	}
}