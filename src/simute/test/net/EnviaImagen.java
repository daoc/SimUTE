
package simute.test.net;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class EnviaImagen extends Thread {       
        private final BlockingQueue<BufferedImage> imageQueue;
        private final String host;
        private final int puerto;

        public EnviaImagen(String host, int puerto) {
            this.host = host;
            this.puerto = puerto;
            imageQueue = new LinkedBlockingQueue<>();
        }
        
        @Override
        public void run() {
            //Recupere las imágenes que se encuentran en la cola (getImageData()) y envíelas al conductor remoto

        }
        
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
                Logger.getLogger(RemoteRobot.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    }    
