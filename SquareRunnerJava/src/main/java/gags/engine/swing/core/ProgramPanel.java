package gags.engine.swing.core;

import gags.engine.core.Program;
import gags.engine.core.ProgramThread.HasProgramThread;
import gags.engine.swing.graphics.SwingPainter;
import gags.engine.swing.io.SwingInput;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Basic program JPanel that handles the game logic
 * @author Peter Gagliardi
 */
public class ProgramPanel extends JPanel implements HasProgramThread, KeyListener, MouseListener, MouseMotionListener {

	/** Serial ID */
	private static final long serialVersionUID = 1447280357862119445L;

	/** Thread to run game logic */
	private Thread thread;
	/** Offscreen image for double buffering */
	private Image offscreen;
	/** Program to run */
	private Program program;
	
	/**
	 * Create the panel.
	 */
	public ProgramPanel(Program program) {
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setProgramThread(program);
	}
	
	//-JPanel-----
	
	@Override
	protected final void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (offscreen == null)
			offscreen = createImage(640, 480);
		offscreen.getGraphics().clearRect(0, 0, 640, 480);
		program.onDraw(new SwingPainter(offscreen.getGraphics()));
		g.drawImage(offscreen, 0, 0, null);
	}

	@Override
	public final void update(Graphics g) {
		paintComponent(g);
	}

	//-HasProgramThread-----
	
	@Override
	public final void setProgramThread(Program program) {
		this.program = program;
		thread = new SwingProgramThread(program, this);
		thread.start();
	}
	
	//-KeyListener-----

	@Override
	public final void keyPressed(KeyEvent e) {
		program.onKeyPress(SwingInput.toKey(e.getKeyCode()));
	}

	@Override
	public final void keyReleased(KeyEvent e) {
		program.onKeyRelease(SwingInput.toKey(e.getKeyCode()));
	}

	@Override
	public final void keyTyped(KeyEvent e) { }

	//-MouseListener-----
	
	@Override
	public final void mouseClicked(MouseEvent e) { }

	@Override
	public final void mouseEntered(MouseEvent e) {
		program.onMouseEnter(e.getX(), e.getY());
	}

	@Override
	public final void mouseExited(MouseEvent e) {
		program.onMouseExit(e.getX(), e.getY());
	}

	@Override
	public final void mousePressed(MouseEvent e) {
		program.onMousePress(SwingInput.toMouseButton(e.getButton()), e.getX(), e.getY());
	}

	@Override
	public final void mouseReleased(MouseEvent e) {
		program.onMouseRelease(SwingInput.toMouseButton(e.getButton()), e.getX(), e.getY());
		
	}
	
	//-MouseMotionListener-----

	@Override
	public final void mouseDragged(MouseEvent e) { 
		program.onMouseMove(e.getX(), e.getY());
	}

	@Override
	public final void mouseMoved(MouseEvent e) {
		program.onMouseMove(e.getX(), e.getY());
	}
}
