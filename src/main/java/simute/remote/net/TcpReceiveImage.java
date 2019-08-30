package simute.remote.net;

import java.net.Socket;

import javax.swing.JLabel;

public class TcpReceiveImage extends Thread {
	private Socket socket;
	private final JLabel monitor;
	
	public TcpReceiveImage(JLabel monitor) {
		this.monitor = monitor;
	}
	
	@Override
	public void run() {
		// Aquí debe ir el código que lee del socket (del inputStream) las imágenes
		// provenientes del vehículo remoto, y que luego las presenta en el monitor (JLabel)
		
	}
	
	//Asegúrese de registrar el Socket, antes de arrancar el hilo (start())
	public void setSocket(Socket socket) {
		this.socket = socket;
	}	
}