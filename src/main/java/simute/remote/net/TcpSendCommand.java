package simute.remote.net;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TcpSendCommand extends Thread {
	private final BlockingQueue<String> commandQueue;
	private Socket socket;
	
	public TcpSendCommand() {
		commandQueue = new LinkedBlockingQueue<>();
	}
	
	@Override
	public void run() {
		// Aquí debe ir el código que envía las instrucciones recolectadas del conductor, que se
		// encuentran en la cola (commandQueue), hacia el vehículo remoto, mediante el socket (outputStream)

	}
	
	//Asegúrese de registrar el Socket, antes de arrancar el hilo (start())
	public void setSocket(Socket socket) {
		this.socket = socket;
	}	
	
	//Alguien más debe llamar este método y poner las instrucciones en la cola
    public void sendCommand(String command) {
    	if(!this.isAlive()) return;
        try {
            commandQueue.put(command);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }	
	
}