package simute.exec;

import simute.gui.I_Gui;
import simute.gui.MainGui;
import simute.world.GroundScene;

/**
*
* @author dordonez@ute.edu.ec
*/
public class GroundLauncher implements I_Launcher {
	final I_Gui canvas;
	final GroundScene terrain;
	
	public static void main(String[] args) {
		new GroundLauncher().init();
	}
	
	public GroundLauncher() {
		canvas = new MainGui();
		terrain = new GroundScene(canvas, this);
	}
	
	@Override
	public void init() {
		terrain.setup();
		terrain.go();
	}
	@Override
	public void loop() {
	}

}
