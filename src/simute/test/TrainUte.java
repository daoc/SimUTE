
package simute.test;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javafx.beans.binding.Bindings;
import simute.BotUte;
import simute.CameraUte;
import simute.DriverUte;
import simute.EnvUte;
import simute.RunSimUte;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class TrainUte implements DriverUte {
    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>(20);
    
    public static void main(String[] args) {
        RunSimUte.init(
            new BotUte(0, 0, 0, new TrainUte()), 
            "mapa_path_prap1c.png", 
            EnvUte.TipoMapa.CAMINO,
            queue);
    }      
    
    @Override
    public void drive(BotUte bot, CameraUte cam) {
        String action = queue.poll();
        if(action == null) return;
        System.out.println(action);
        switch (action) {
            case "w":
                bot.velocidad(bot.getTranslationalVelocity() + 0.5);
                break;
            case "s":
                bot.velocidad(bot.getTranslationalVelocity() - 0.5);
                break;
            case "a":
                bot.giro(10);
                break;     
            case "d":
                bot.giro(-10);
                break;                
        }

    }    
}
