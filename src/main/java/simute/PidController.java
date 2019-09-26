package simute;

public class PidController {
	
	public final boolean P, I, D; 
	private float kP, kI, kD;
	private float setPoint;
	private float integral, derivada, prevError, prevTime;

	public PidController(boolean P, boolean I, boolean D) {
		this.P = P;
		this.I = I;
		this.D = D;
	}
	
	public void setConstants(float kP, float kI, float kD) {
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
	}
	
	public void setExpectedValue(float setPoint) {
		this.setPoint = setPoint;
	}
	
	public float getExpectedValue() {
		return setPoint;
	}
	
	public float loop(float input) {
		float output = 0;
		float error = input - setPoint;
		float now = System.currentTimeMillis();
		float gap = now - prevTime;
		
		integral += error * gap;
		derivada = (error - prevError) / gap;
		
		if(P) output += kP * error;
		if(I) output += kI * integral;
		if(D) output += kD * derivada;
		
		prevError = error;
		prevTime = now;
		return output;
	}
	
	
	
}
