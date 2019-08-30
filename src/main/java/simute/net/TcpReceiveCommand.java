package simute.net;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TcpReceiveCommand extends Thread {
	private final BlockingQueue<String> commandQueue;
	private Socket socket;
	
	public TcpReceiveCommand() {
		commandQueue = new LinkedBlockingQueue<>();
	}
	
	@Override
	public void run() {
		// Aquí debe ir el código que lee del socket (del inputStream) las instrucciones
		// provenientes del conductor remoto
		
	}
	
	//Asegúrese de registrar el Socket, antes de arrancar el hilo (start())
	public void setSocket(Socket socket) {
		this.socket = socket;
	}	
	
    //Este método debe ser invocado por alguien más (VehicleLocalTcpDriver) para recuperar cada instrucción recibida del conductor remoto
    public String getCommand() {
    	return commandQueue.poll();
    }	
	
}