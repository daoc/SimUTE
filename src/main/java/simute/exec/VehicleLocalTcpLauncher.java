package simute.exec;

/**
*
* @author dordonez@ute.edu.ec
*/
public class VehicleLocalTcpLauncher extends A_VehicleLauncher {

	public static void main(String[] args) {
		new VehicleLocalTcpLauncher().init();
	}
	
	public VehicleLocalTcpLauncher() {
		super(new VehicleLocalTcpDriver());
		((VehicleLocalTcpDriver)driver).setCanvas(canvas.getPovCanvas());
	}

	@Override
	public void loopConcreteImp() {}
	
}
