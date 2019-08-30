package simute.gui;

import java.awt.event.KeyListener;

/**
*
* @author dordonez@ute.edu.ec
*/
public interface I_Gui {
	public I_Canvas getPovCanvas();
	public I_Canvas getDroneCanvas();
	public ControllsPanel getControllsPanel();
	public void setKeyListener(KeyListener listener);
}
