package simute.world;

import java.awt.Color;

import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

/**
*
* @author dordonez@ute.edu.ec
*/
public interface I_Scene {
	public void setup(Color light, String groundDef);
	public void go();
	public void prepareLoop();
	public void loop();
	public void renderLoop();
	public World getWorld();
	public SimpleVector getGroundCenter();
	public long getFrameCount();
	public long getElapsedTimeMs();
}
