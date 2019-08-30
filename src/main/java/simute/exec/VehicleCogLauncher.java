package simute.exec;

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
		((VehicleCogDriver)driver).setCanvas(canvas.getPovCanvas());
	}

	@Override
	public void loopConcreteImp() {}
	
}
