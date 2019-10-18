package simute.exec;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import simute.Env;
import simute.ImageRenderer;
import simute.PidController;
import simute.gui.I_Canvas;

/**
*
* @author dordonez@ute.edu.ec
*/
public class VehiclePidDriver implements I_Driver {
	protected boolean wFlag = false;
	protected boolean sFlag = false;
	private float speed = 0;
	private float turn = 0;
	private I_Canvas canvas;
	private PidController pidCtrl;
	
	public void update() {
		int[] cog = ImageRenderer.getCogRgb(canvas.getFrameImage());
		if(wFlag && (speed < (Env.MAX_SPEED-Env.SPEED_STEP))) speed += Env.SPEED_STEP;
		if(sFlag && (speed > -(Env.MAX_SPEED-Env.SPEED_STEP))) speed -= Env.SPEED_STEP;
		
		turn = pidCtrl.loop(cog[1]);
		
		System.out.println("CogG: " + cog[1] + " / Turn: " + turn);
	}

	public float getLoopParam(String par) {
		if(par.equals("speed")) {
			return speed;
		} else if(par.equals("turn")) {
			return turn;
		}
		throw new RuntimeException("Parameter: '" + par + "' does not exist !!!");
	}
	
	public void reset() {
	}
	
	public void setPidController(PidController pidCtrl) {
		this.pidCtrl = pidCtrl;
	}
	
	public void setCanvas(I_Canvas canvas) {
		this.canvas = canvas;
	}
	
	public KeyListener getKeyListener() {
		return keyL;
	}
	
	private final KeyListener keyL = new KeyAdapter() {

		public void keyPressed(KeyEvent e) {
			char key = e.getKeyChar();
			System.out.println("keyPressed: " + key);
			switch(key) {
		        case 'w':
		        case 'W':
		        	wFlag = true;
		        	break;
		        case 's':
		        case 'S':
		        	sFlag = true;
		        	break;                	              	
		        case ' ':    
		    		speed = Env.STOP;
		    		break;                	
			}
		}

		public void keyReleased(KeyEvent e) {
			char key = e.getKeyChar();
			System.out.println("keyReleased: " + key);
			switch(key) {
		        case 'w':
		        case 'W':
		        	wFlag = false;
		        	break;
		        case 's':
		        case 'S':
		        	sFlag = false;
		        	break;                	              	
		        case ' ':    
		    		speed = Env.STOP;
		    		break;                	
			}
		}
	};		
	
}
