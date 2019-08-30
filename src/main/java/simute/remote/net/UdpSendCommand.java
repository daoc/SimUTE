
package simute.remote.net;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class UdpSendCommand extends Thread {       
        private final BlockingQueue<String> commandQueue;
        private final String host;
        private final int puerto;

        public UdpSendCommand(String host, int puerto) {
            this.host = host;
            this.puerto = puerto;
            commandQueue = new LinkedBlockingQueue<>();
        }
        
        @Override
        public void run() {
            //Recupere las instrucciones que se encuentran en la cola (getInstruccion()) y envíelas al vehículo remoto

        }
        
        //Alguien más debe llamar este método y poner las instrucciones en la cola        
        public void addInstruccion(String instruccion) {
            try {
                commandQueue.put(instruccion);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        private byte[] getInstruccion() {
            try {
                String instruccion = commandQueue.take();
                return instruccion.getBytes();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }    
