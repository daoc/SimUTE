package simute.world;

import java.awt.Color;

import com.threed.jpct.Camera;
import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;

import simute.Env;

/**
*
* @author dordonez@ute.edu.ec
*/
public class PovGroundObject {
	
	protected final I_Scene scene;
	private final Camera cam;
	private Object3D obj;
	
	public PovGroundObject(I_Scene scene) {
		this.scene = scene;
		this.obj = Primitives.getSphere(1f);
		this.cam = new Camera();
	}
	
	protected Object3D setup() {
		cam.moveCamera(Camera.CAMERA_MOVEOUT, Env.CAM_INIT_OUT);
		cam.lookAt(scene.getGroundCenter());
		cam.rotateX(Env.CAM_INIT_ROT_X);
		cam.setFOV(Env.CAM_INIT_FOV);
		
		TextureManager.getInstance().addTexture("carBox", new Texture("box.jpg"));
		obj = Primitives.getSphere(1f);
		obj.translate(0, 0, -0.5f);
		obj.setTexture("carBox");
		obj.setEnvmapped(Object3D.ENVMAP_ENABLED);
		obj.setAdditionalColor(Color.RED);
		obj.build();
		
		return obj;
	}
	
	public void prepareLoop() {
        cam.rotateX(Env.CAM_ROT_X_FIX);
	}
	
	public void renderLoop() {
        cam.rotateX(-Env.CAM_ROT_X_FIX);
        obj.clearTranslation();
        obj.translate(cam.getPosition().x, cam.getPosition().y, 0);
	}
	
	public Camera getPov() {
		return cam;
	}
	
    public void speed(float speed) {
    	cam.moveCamera(Camera.CAMERA_MOVEIN, speed);
    }
	
    public void turn(float turn) {
    	//El giro real es: - hacia la izquierda; + hacia la derecha.
    	//dado que es contra-intuitivo, se cambia el signo (por eso el - antes de turn)
    	cam.rotateY(-turn);
    }
    
}
