package simute.world;

import java.awt.Color;

import simute.Env;
import simute.exec.I_Launcher;
import simute.gui.I_Gui;

/**
*
* @author dordonez@ute.edu.ec
*/
public class VehicleScene extends GroundScene {

	private PovGroundObject carBox;

	public VehicleScene(I_Gui render, I_Launcher launcher) {
		super(render, launcher);
	}
	
	public void setup() {
		setup(Env.OVERCAST_SKY, Env.GROUND_SURFACE);
	}
	
	public void setup(Color light, String groundDef) {
		super.setup(light, groundDef);

		carBox = new PovGroundObject(this);
		getWorld().addObject(carBox.setup());
	}

	public void prepareLoop() {
		super.prepareLoop();
        carBox.prepareLoop();
	}
	
	public void renderLoop() {
		super.renderLoop();
		carBox.renderLoop();
		
		getWorld().setCameraTo(carBox.getPov());
		render.getPovCanvas().renderScene(getWorld());
	}
	
    public void speed(float speed) {
    	carBox.speed(speed);
    }
	
    public void turn(float turn) {
    	carBox.turn(turn);
    }
    
}
