package simute.exec;

import java.awt.event.KeyListener;

/**
*
* @author dordonez@ute.edu.ec
*/
public interface I_Driver {
	public KeyListener getKeyListener();
	public void update();
	public float getLoopParam(String par);
	public void reset();
}
