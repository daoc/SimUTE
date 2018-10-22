
package simute.test.net;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class ObtieneImagen extends Thread {
    private final JLabel monitor;
    private final int puerto;
    
    public ObtieneImagen(JLabel monitor, int puerto) {
        this.monitor = monitor;
        this.puerto = puerto;
    }

    @Override
    public void run() {
        //Capture las imagenes enviadas por el robot remoto y muéstrelas en el monitor (showImage(...))

    }

    private void showImage(DatagramPacket packet) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
            BufferedImage image = ImageIO.read(bais);
            monitor.setIcon(new ImageIcon(image));
            monitor.repaint();
            System.out.println("Pintada imagen: " + packet.getLength());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
