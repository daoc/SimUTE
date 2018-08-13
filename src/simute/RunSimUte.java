
package simute;

import simbad.gui.Simbad;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class RunSimUte {
    
    public static void init(BotUte robot, String mapa, EnvUte.TipoMapa tipo) {
        //System.setProperty("j3d.implicitAntialiasing", "true");
        new Simbad(new EnvUte(robot, mapa, tipo), false);
    }  
    
}
