package simute.exec;

import simute.PidController;

/**
*
* @author dordonez@ute.edu.ec
*/
public class VehicleCogLauncher extends A_VehicleLauncher {

	public static void main(String[] args) {
		new VehicleCogLauncher().init();
	}
	
	public VehicleCogLauncher() {
		super(new VehicleCogDriver());

		PidController pidCtrl = new PidController(true, false, false);
		pidCtrl.setConstants(0.001f, 0, 0);
		pidCtrl.setExpectedValue(0);
		
		((VehicleCogDriver)driver).setPidController(pidCtrl);
		((VehicleCogDriver)driver).setCanvas(canvas.getPovCanvas());
	}

	@Override
	public void loopConcreteImp() {}
	
}
