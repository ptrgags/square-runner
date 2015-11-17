package gags.sample.squarerunner;

import gags.engine.core.Program;
import gags.engine.swing.core.Window;
import gags.lib.program.LoadingScreen;
import gags.sample.squarerunner.modes.Menu;

/**
 * Square Runner Launcher
 * @author Peter Gagliardi
 */
public class SquareRunner {
	
	/**
	 * Launch Square Runner
	 * @param args
	 */
	public static void main(String[] args) {
		Global.init();
		Program program = new LoadingScreen("squarerunner", new Menu());
		Window window = new Window(program);
		window.setVisible(true);
	}
}
