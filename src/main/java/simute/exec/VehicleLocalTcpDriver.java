package simute.exec;

import java.awt.event.KeyListener;

import simute.Env;
import simute.ImageRenderer;
import simute.gui.I_Canvas;
import simute.net.TcpReceiveCommand;
import simute.net.TcpSendImage;
import simute.net.TcpSingleConnectionServer;

/**
*
* @author dordonez@ute.edu.ec
*/
public class VehicleLocalTcpDriver implements I_Driver {
	protected boolean wFlag = false;
	protected boolean sFlag = false;
	protected boolean aFlag = false;
	protected boolean dFlag = false;	
	private float speed = 0;
	private float turn = 0;
	private I_Canvas canvas;
	
	private static final int PORT = 8888;
	
	private final TcpReceiveCommand getCommand;
	private final TcpSendImage sendImage;
	
	public VehicleLocalTcpDriver() {
		getCommand = new TcpReceiveCommand();
		sendImage = new TcpSendImage();
		new TcpSingleConnectionServer(PORT, getCommand, sendImage).start();
	}
	
	public void update() {
		if(getCommand.isAlive() && sendImage.isAlive()) {
			sendImage.addImage(ImageRenderer.getResizedImg(canvas.getFrameImage(), 400, 300));
			updateDrivingFlags(getCommand.getCommand());
			if(wFlag && (speed < (Env.MAX_SPEED-Env.SPEED_STEP))) speed += Env.SPEED_STEP;
			if(sFlag && (speed > -(Env.MAX_SPEED-Env.SPEED_STEP))) speed -= Env.SPEED_STEP;
			if(aFlag && (turn > -(Env.MAX_TURN-Env.TURN_STEP))) turn -= Env.TURN_STEP;
			if(dFlag && (turn < (Env.MAX_TURN-Env.TURN_STEP))) turn += Env.TURN_STEP;		
			if(!aFlag && !dFlag) turn = Env.STOP;
		}
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
	
	public void setCanvas(I_Canvas canvas) {
		this.canvas = canvas;
	}
	
	public KeyListener getKeyListener() {
		return null;
	}
		
	private void updateDrivingFlags(String action) {
		if(action == null) return;
		switch(action) {
	        case "w":
	        case "W":
	        	wFlag = true;
	        	break;
	        case "s":
	        case "S":
	        	sFlag = true;
	        	break;                	
	        case "a":
	        case "A":
	        	aFlag = true;
	    		break;                	
	        case "d":
	        case "D":
	        	dFlag = true;
	    		break; 
	        case "!w":
	        case "!W":
	        	wFlag = false;
	        	break;
	        case "!s":
	        case "!S":
	        	sFlag = false;
	        	break;                	
	        case "!a":
	        case "!A":
	        	aFlag = false;
	    		break;                	
	        case "!d":
	        case "!D":
	        	dFlag = false;
	    		break; 	    		
	        case " ":
	        case "! ":	
	    		speed = Env.STOP;
	    		break;	
		}
	}		
	
}
