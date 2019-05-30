
package simute.test.net;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Base64;
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

    //Este método reciba una imagen como String en Base64 y la presenta en el monitor
    private void showImage(String imgBase64) {
        try {
            byte[] buffer = Base64.getDecoder().decode(imgBase64);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer, 0, buffer.length);
            BufferedImage image = ImageIO.read(bais);
            monitor.setIcon(new ImageIcon(image));
            monitor.repaint();
            System.out.println("Pintada imagen de: " + buffer.length + " bytes");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
