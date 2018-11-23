
package simute.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
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
    private static final boolean SAVE_IMAGES = false;
    
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
                if(SAVE_IMAGES) cam.saveImageFile("Train_W_");
                bot.velocidad(bot.getTranslationalVelocity() + 0.5);
                break;
            case "s":
                if(SAVE_IMAGES) cam.saveImageFile("Train_S_");
                bot.velocidad(bot.getTranslationalVelocity() - 0.5);
                break;
            case "a":
                if(SAVE_IMAGES) cam.saveImageFile("Train_A_");
                bot.giro(10);
                break;     
            case "d":
                if(SAVE_IMAGES) cam.saveImageFile("Train_D_");
                bot.giro(-10);
                break;                
        }

    }    
}
