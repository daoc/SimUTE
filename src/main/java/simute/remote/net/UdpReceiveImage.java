
package simute.remote.net;

import javax.swing.JLabel;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class UdpReceiveImage extends Thread {
    private final JLabel monitor;
    private final int puerto;
    
    public UdpReceiveImage(JLabel monitor, int puerto) {
        this.monitor = monitor;
        this.puerto = puerto;
    }

    @Override
    public void run() {
        //Capture las imagenes enviadas por el vehículo remoto y muéstrelas en el monitor (JLabel)

    }

}
