
package simute.test.net;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
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
        
        //Este método entrega la imagen tomada por la cámara como String en Base64
        private String getImageData() {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedImage image = imageQueue.take();
                ImageIO.write(image, "jpg", baos);
                baos.flush();
                byte[] buffer = baos.toByteArray();
                baos.close();
                return Base64.getEncoder().encodeToString(buffer);
            } catch (InterruptedException | IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }    
