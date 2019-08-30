package simute.net;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
*
* @author dordonez@ute.edu.ec
*/
public class UdpReceiveCommand extends Thread {
	private static final int PACKET_SIZE = 32;
	private final BlockingQueue<String> commandQueue;
    private final int puerto;

    public UdpReceiveCommand(int puerto) {
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

    //Alguien más debe llamar este método y recuperar las instrucciones de la cola
    public String getInstruccion() {
        return commandQueue.poll();
    }  
}
