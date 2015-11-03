package gags.engine.swing.io;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import gags.engine.io.Key;
import gags.engine.io.MouseButton;

/**
 * Class that handles converting input codes
 * to enum constants
 * @author Peter Gagliardi
 */
public class SwingInput {

	/**
	 * Get a Key constant from an AWT
	 * keycode
	 * @param keyCode the key code to parse
	 * @return the Key constant equivalent or null
	 * LATER: Add more keys
	 */
	public static Key toKey(int keyCode) {
		switch (keyCode) {
			case KeyEvent.VK_A:
				return Key.A;
			case KeyEvent.VK_B:
				return Key.B;
			case KeyEvent.VK_C:
				return Key.C;
			case KeyEvent.VK_D:
				return Key.D;
			case KeyEvent.VK_E:
				return Key.E;
			case KeyEvent.VK_F:
				return Key.F;
			case KeyEvent.VK_G:
				return Key.G;
			case KeyEvent.VK_H:
				return Key.H;
			case KeyEvent.VK_I:
				return Key.I;
			case KeyEvent.VK_J:
				return Key.J;
			case KeyEvent.VK_K:
				return Key.K;
			case KeyEvent.VK_L:
				return Key.L;
			case KeyEvent.VK_M:
				return Key.M;
			case KeyEvent.VK_N:
				return Key.N;
			case KeyEvent.VK_O:
				return Key.O;
			case KeyEvent.VK_P:
				return Key.P;
			case KeyEvent.VK_Q:
				return Key.Q;
			case KeyEvent.VK_R:
				return Key.R;
			case KeyEvent.VK_S:
				return Key.S;
			case KeyEvent.VK_T:
				return Key.T;
			case KeyEvent.VK_U:
				return Key.U;
			case KeyEvent.VK_V:
				return Key.V;
			case KeyEvent.VK_W:
				return Key.W;
			case KeyEvent.VK_X:
				return Key.X;
			case KeyEvent.VK_Y:
				return Key.Y;
			case KeyEvent.VK_Z:
				return Key.Z;
			case KeyEvent.VK_RIGHT:
				return Key.RIGHT;
			case KeyEvent.VK_LEFT:
				return Key.LEFT;
			case KeyEvent.VK_UP:
				return Key.UP;
			case KeyEvent.VK_DOWN:
				return Key.DOWN;
			case KeyEvent.VK_ESCAPE:
				return Key.ESC;
			case KeyEvent.VK_SPACE:
				return Key.SPACEBAR;
			case KeyEvent.VK_SHIFT:
				return Key.SHIFT;
			case KeyEvent.VK_CONTROL:
				return Key.CTRL;
			case KeyEvent.VK_ALT:
				return Key.ALT;
			default:
				return null;
		}
	}

	/**
	 * Get a MouseButton constant from an AWT keycode
	 * @param button the button code to parse
	 * @return the MouseButton constant equivalent or null
	 * LATER: Add more buttons
	 */
	public static MouseButton toMouseButton(int button) {
		switch (button) {
			case MouseEvent.BUTTON1:
				return MouseButton.LEFT;
			case MouseEvent.BUTTON2:
				return MouseButton.RIGHT;
			default:
				return null;
		}
	}
}
