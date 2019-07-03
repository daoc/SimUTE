
package simute.test;

import simute.BotUte;
import simute.CameraUte;
import simute.DriverUte;
import simute.EnvUte;
import simute.RunSimUte;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class MyDriverUte implements DriverUte {

    
    
    public static void main(String[] args) {
        RunSimUte.init(
            new BotUte(0, 0, 0, new MyDriverUte()), 
            "mapa_path_prap1c.png", 
            EnvUte.TipoMapa.CAMINO);
    }      
    
    @Override
    public void drive(BotUte bot, CameraUte cam) {
        // actualiza dirección cada 10 ciclos
        if ((bot.getCounter() % 10) == 0) {
            int cogG = cam.getCogRgb()[1];
            bot.giro(-cogG*1.5);
            System.out.println(cogG);
        }
    }
    
}
