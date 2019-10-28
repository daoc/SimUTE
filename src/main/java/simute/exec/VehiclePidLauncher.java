package simute.exec;

import simute.PidController;

/**
*
* @author dordonez@ute.edu.ec
*/
public class VehiclePidLauncher extends A_VehicleLauncher {

	public static void main(String[] args) {
		new VehiclePidLauncher().init();
	}
	
	public VehiclePidLauncher() {
		super(new VehiclePidDriver());

		PidController pidCtrl = new PidController(true, false, false);
		pidCtrl.setConstants(0f, 0f, 0f);
		pidCtrl.setExpectedValue(0);
		
		((VehiclePidDriver)driver).setPidController(pidCtrl);
		((VehiclePidDriver)driver).setCanvas(canvas.getPovCanvas());
	}

	@Override
	public void loopConcreteImp() {}
	
}
