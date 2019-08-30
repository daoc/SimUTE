package simute.exec;

import simute.gui.I_Gui;
import simute.gui.MainGui;
import simute.world.VehicleScene;

/**
*
* @author dordonez@ute.edu.ec
*/
public abstract class A_VehicleLauncher implements I_Launcher {
	protected final I_Gui canvas;
	protected final VehicleScene car;
	protected final I_Driver driver;
	
	public A_VehicleLauncher(I_Driver driver) {
		canvas = new MainGui();
		car = new VehicleScene(canvas, this);
		this.driver = driver;
	}

	public void init() {
		canvas.setKeyListener(driver.getKeyListener());
		car.setup();
		car.go();
	}

	public void loop() {
		driver.update();
        car.speed(driver.getLoopParam("speed"));
        car.turn(driver.getLoopParam("turn"));
        updateGauges();
        loopConcreteImp();//Proporcionado por la implementación concreta
        driver.reset();
	}

	public abstract void loopConcreteImp();
	
	private void updateGauges() {
		long secs = car.getElapsedTimeMs()/1000;
		long fps = secs==0 ? 0 : car.getFrameCount()/secs;
        canvas.getControllsPanel().getLblFps().setText(String.valueOf(fps));
        canvas.getControllsPanel().getLblSecs().setText(String.valueOf(secs));
        canvas.getControllsPanel().getLblSpeed().setText(String.format("%+.3f", driver.getLoopParam("speed")));
        canvas.getControllsPanel().getSliderSpeed().setValue((int)(driver.getLoopParam("speed")*1000)); 
        canvas.getControllsPanel().getLblTurn().setText(String.format("%+.3f", driver.getLoopParam("turn")));
        canvas.getControllsPanel().getSliderTurn().setValue((int)(driver.getLoopParam("turn")*1000));
	}
	
}
