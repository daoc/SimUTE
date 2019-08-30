package simute.net;

public class TcpSingleConnectionServer extends Thread {
	private final int serverPort;
	private final TcpReceiveCommand getCommand;
	private final TcpSendImage sendImage;
	
	public TcpSingleConnectionServer(int serverPort, TcpReceiveCommand getCommand, TcpSendImage sendImage) {
		this.serverPort = serverPort;
		this.getCommand = getCommand;
		this.sendImage = sendImage;
	}
	
	@Override
	public void run() {
		// Aquí deben ponerse las instrucciones necesarias para crear un servidor TCP
		// que reciba un solo cliente (el conductor remoto) y que inicialice correctamente
		// las clases para compartir información (TcpReceiveCommand, TcpSendImage)
		
	}
}
