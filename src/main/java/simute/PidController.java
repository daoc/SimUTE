package simute;

public class PidController {
	
	public final boolean P, I, D; 
	private float kP, kI, kD;
	private float setPoint;
	private float integral, prevError;
	private long prevTime;

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
		float error = setPoint - input;
		float derivada = 0;
		long now = System.currentTimeMillis();
		long gap = now - prevTime;

		if(P) {
			output += kP * error;
		}
		if(I) {
			integral += error * gap;
			output += kI * integral;
		}
		if(D) {
			if(gap > 0) {
				derivada = (error - prevError) / gap;
			}
			output += kD * derivada;
		}
		
		prevError = error;
		prevTime = now;
		return output;
	}
	
	
	
}
