package simute.exec;

/**
*
* @author dordonez@ute.edu.ec
*/
public class VehicleLocalUdpLauncher extends A_VehicleLauncher {

	public static void main(String[] args) {
		new VehicleLocalUdpLauncher().init();
	}
	
	public VehicleLocalUdpLauncher() {
		super(new VehicleLocalUdpDriver());
		((VehicleLocalUdpDriver)driver).setCanvas(canvas.getPovCanvas());
	}

	@Override
	public void loopConcreteImp() {}
	
}
