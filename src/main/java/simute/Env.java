package simute;

import java.awt.Color;

import java.awt.Dimension;
/**
*
* @author dordonez@ute.edu.ec
*/
public class Env {
	public static final Dimension WORLD_SIZE = new Dimension(800, 600);
	public static final Dimension TOP_CAMERA_SIZE = new Dimension(300, 300);
	public static final Dimension CONTROLLS_SIZE = new Dimension(300, 300);
	public static final Dimension INFO_SIZE = new Dimension(WORLD_SIZE.width, 100);
	public static final int FRAME_DELAY = 10;
	public static final Color OVERCAST_SKY = new Color(235, 245, 255);
	public static final Color BACKGROUND = Color.LIGHT_GRAY;
	public static final String GROUND_SURFACE = "GroundGreen02.png";
	public static final float PI = (float) Math.PI;
	public static final float GROUND_SCALE = 50f;
	public static final float CAM_INIT_OUT = 0.35f;
	public static final float CAM_LOOK_STRAIGHT = PI/2;
	public static final float CAM_ROT_X_FIX = 0.15f;
	public static final float CAM_INIT_ROT_X = PI/2 - CAM_ROT_X_FIX;
	public static final float CAM_INIT_FOV = 0.5f;//minfov
	public static final float MAX_SPEED = 0.05f;
	public static final float SPEED_STEP = 0.001f;
	public static final float MAX_TURN = 0.03f;
	public static final float TURN_STEP = 0.001f;
	public static final float STOP = 0f;
	
	public static final boolean SAVE_SCENE_FRAMES = false;
	public static final int SAVE_EACH_X_FRAMES = 30;
	public static final String IMG_FRAMES_FOLDER = "sceneFrames";
}
