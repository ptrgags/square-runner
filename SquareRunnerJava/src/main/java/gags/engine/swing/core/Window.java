package gags.engine.swing.core;

import gags.engine.core.Program;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Window for displaying a program
 * @author Peter Gagliardi
 */
public class Window extends JFrame {
	
	/** Serial ID */
	private static final long serialVersionUID = -1294281737755833679L;
	
	/** Content Pane */
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Window(Program program) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(640, 480));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(new ProgramPanel(program));
		setContentPane(contentPane);
		pack();
	}
}
