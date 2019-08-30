package simute.world;

import java.awt.Color;

import com.threed.jpct.Camera;
import com.threed.jpct.Config;
import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;

import simute.Env;
import simute.exec.I_Launcher;
import simute.gui.I_Gui;

/**
*
* @author dordonez@ute.edu.ec
*/
public class GroundScene implements I_Scene {
	protected final I_Gui render;
	protected final I_Launcher launcher;
	
	protected final World world;
	protected Object3D ground;
	
	private final Camera droneCam;

	private long frameCounter = 0;
	private long startTimeMs = 0;
	
	public GroundScene(I_Gui render, I_Launcher launcher) {
		this.render = render;
		this.launcher = launcher;
		
		Config.useMultipleThreads = true;
		Config.loadBalancingStrategy = 1;
		World.setDefaultThread(Thread.currentThread());		
		
		world = new World();
		droneCam = world.getCamera();
	}

	public void setup() {
		setup(Env.OVERCAST_SKY, Env.GROUND_SURFACE);
	}
	
	public void setup(Color light, String groundDef) {
		world.setAmbientLight(light.getRed(), light.getGreen(), light.getBlue());
		TextureManager.getInstance().addTexture("ground", new Texture(groundDef));
		ground = Primitives.getPlane(1, Env.GROUND_SCALE);
		ground.setTexture("ground");
		ground.build();	
		world.addObject(ground);
		
		droneCam.setPositionToCenter(ground);
		droneCam.moveCamera(Camera.CAMERA_MOVEOUT, 40);
		droneCam.lookAt(getGroundCenter());
	}
	
	public void go() {
		startTimeMs = System.currentTimeMillis();
		while (true) {
			prepareLoop();	        
			loop();	        
			renderLoop();
			try {
				Thread.sleep(Env.FRAME_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
    
	public void prepareLoop() {
		frameCounter++;
	}
	
	public void loop() {
//        Código personalizado del Launcher
		launcher.loop();
	}
	
	public void renderLoop() {
        world.setCameraTo(droneCam);
        render.getDroneCanvas().renderScene(world);
	}

	public World getWorld() {
		return world;
	}
	
	public SimpleVector getGroundCenter() {
		return ground.getTransformedCenter();
	}
	
	public long getFrameCount() {
		return frameCounter;
	}
	
	public long getElapsedTimeMs() {
		return System.currentTimeMillis() - startTimeMs;
	}
}
