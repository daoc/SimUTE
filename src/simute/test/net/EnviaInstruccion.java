
package simute.test.net;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class EnviaInstruccion extends Thread {       
        private final BlockingQueue<String> commandQueue;
        private final String host;
        private final int puerto;

        public EnviaInstruccion(String host, int puerto) {
            this.host = host;
            this.puerto = puerto;
            commandQueue = new LinkedBlockingQueue<>();
        }
        
        @Override
        public void run() {
            //Recupere las instrucciones que se encuentran en la cola (getInstruccion()) y envíelas al robot remoto

        }
        
        public void addInstruccion(String instruccion) {
            try {
                commandQueue.put(instruccion);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        private String getInstruccion() {
            try {
                String instruccion = commandQueue.take();
                return instruccion;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }    
