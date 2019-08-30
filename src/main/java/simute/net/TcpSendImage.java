package simute.net;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.imageio.ImageIO;

public class TcpSendImage extends Thread {
	private final BlockingQueue<BufferedImage> imageQueue;
	private Socket socket;
	
	public TcpSendImage() {
		imageQueue = new LinkedBlockingQueue<>();
	}
	
	@Override
	public void run() {
		// Aquí deben ir las instrucciones que envían las imágenes recolectadas del vehículo
		// mediante getImageData(), hacia el conductor remoto, mediante el socket (outputStream)
		
	}
	
	//Asegúrese de registrar el Socket, antes de arrancar el hilo (start())
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
    //Alguien más debe llamar este método y poner las imágenes en la cola
    public void addImage(BufferedImage image) {
        try {
            imageQueue.put(image);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }		
    
    private byte[] getImageData() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage image = imageQueue.take();
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            byte[] buffer = baos.toByteArray();
            baos.close();
            return buffer;
        } catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }	
	
}