package simute.gui;

/**
*
* @author dordonez@ute.edu.ec
*/
import java.awt.image.BufferedImage;

import com.threed.jpct.World;

public interface I_Canvas {
	public void renderScene(World world);
	public BufferedImage getFrameImage();
}
