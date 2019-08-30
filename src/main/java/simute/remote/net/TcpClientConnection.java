package simute.remote.net;

public class TcpClientConnection extends Thread {
	private final String host;
	private final int port;
	private final TcpSendCommand sendCommand;
	private final TcpReceiveImage getImage;
	
	public TcpClientConnection(String host, int port, TcpSendCommand sendCommand, TcpReceiveImage getImage) {
		this.host = host;
		this.port = port;
		this.sendCommand = sendCommand;
		this.getImage = getImage;
	}
	
	@Override
	public void run() {	
		// Aquí deben ponerse las instrucciones necesarias para conectarse con el servidor TCP
		// y para inicializar correctamente las clases que compartirán la información
		// (TcpSendCommand, TcpReceiveImage)	
		
	}
	
}
