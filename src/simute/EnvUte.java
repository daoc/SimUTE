
package simute;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import simbad.sim.Box;
import simbad.sim.EnvironmentDescription;
import simbad.sim.MyPath;
import simbad.sim.Wall;

/**
 *
 * @author dordonez@ute.edu.ec
 */

public class EnvUte extends EnvironmentDescription {
    public static enum TipoMapa {PARED, CAMINO}
    
    public EnvUte(BotUte robot, String mapa, TipoMapa tipo) {
        add(robot);        
        buildBorders();
        buildFromMap(mapa, tipo);
    }

    public final void buildBorders() {
        //Bordes
        Wall w1 = new Wall(new Vector3d(0, 0, 10), 20, 1, this);
        add(w1);
        Wall w2 = new Wall(new Vector3d(0, 0, -10), 20, 1, this);
        add(w2);
        Wall w3 = new Wall(new Vector3d(10, 0, 0), 20, 1, this);
        w3.rotate90(1);
        add(w3);
        Wall w4 = new Wall(new Vector3d(-10, 0, 0), 20, 1, this);
        w4.rotate90(1);
        add(w4);
    }

    public final void buildFromMap(String mapfilename, TipoMapa tipo) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(mapfilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int x = 0; x < 20; x++) {
            for (int z = 0; z < 20; z++) {
                int p = img.getRGB(x, z);
                System.out.print(p);
                if (p != -1) {
                    if (tipo.equals(TipoMapa.CAMINO)) {
                        add(new MyPath(new Vector3d(x - 10, 0, z - 10), this));
                    } else {
                        add(new Box(new Vector3d(x - 10, 0, z - 10), new Vector3f(1, 1, 1), this));
                    }
                }
            }
            System.out.println();
        }
    }

}
