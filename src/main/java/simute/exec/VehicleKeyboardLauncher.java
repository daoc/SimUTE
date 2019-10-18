package simute.exec;

import simute.ImageRenderer;

/**
* Utiliza, de A_VehicleLauncher:
*   init()
*	I_Gui canvas
*	VehicleScene car
*	I_Driver driver
* @author dordonez@ute.edu.ec
*/
public class VehicleKeyboardLauncher extends A_VehicleLauncher {
	
	public static void main(String[] args) {
		//init() está en super
		new VehicleKeyboardLauncher().init();
	}
	
	public VehicleKeyboardLauncher() {
		super(new VehicleKeyboardDriver());
	}

	@Override
	public void loopConcreteImp() {
        ImageRenderer.saveSceneFrame(
            	canvas.getPovCanvas()
            	, car.getFrameCount()
            	, driver.getLoopParam("speed")
            	, driver.getLoopParam("turn"));
	}	
	
}
