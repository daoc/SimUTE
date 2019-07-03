
package simute.test.net;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class ObtieneInstruccion extends Thread {
    private final BlockingQueue<String> commandQueue;
    private final int puerto;

    public ObtieneInstruccion(int puerto) {
        this.puerto = puerto;
        commandQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        //Capture las instrucciones enviadas por el conductor remoto y póngalas en la cola (addInstruccion(...))

    }

    private void addInstruccion(String instruccion) {
        try {
            commandQueue.put(instruccion);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public String getInstruccion() {
        return commandQueue.poll();
    }        
}