package simute.exec;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import simute.Env;

/**
*
* @author dordonez@ute.edu.ec
*/
public class VehicleKeyboardDriver implements I_Driver {
	protected boolean wFlag = false;
	protected boolean sFlag = false;
	protected boolean aFlag = false;
	protected boolean dFlag = false;
	private float speed = 0;
	private float turn = 0;
	
	public void update() {
		if(wFlag && (speed < (Env.MAX_SPEED-Env.SPEED_STEP))) speed += Env.SPEED_STEP;
		if(sFlag && (speed > -(Env.MAX_SPEED-Env.SPEED_STEP))) speed -= Env.SPEED_STEP;
		if(aFlag && (turn > -(Env.MAX_TURN-Env.TURN_STEP))) turn -= Env.TURN_STEP;
		if(dFlag && (turn < (Env.MAX_TURN-Env.TURN_STEP))) turn += Env.TURN_STEP;		
		if(!aFlag && !dFlag) turn = Env.STOP;
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
		        case 'a':
		        case 'A':
		        	aFlag = true;
		    		break;                	
		        case 'd':
		        case 'D':
		        	dFlag = true;
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
		        case 'a':
		        case 'A':
		        	aFlag = false;
		    		break;                	
		        case 'd':
		        case 'D':
		        	dFlag = false;
		    		break;                	
		        case ' ':    
		    		speed = Env.STOP;
		    		break;                	
			}
		}
	};		
	
}
