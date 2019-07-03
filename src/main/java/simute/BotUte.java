
package simute;

import javax.vecmath.Vector3d;
import simbad.sim.Agent;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class BotUte extends Agent {
    CameraUte camera;
    double initDir;
    DriverUte driver;

    public BotUte(double x, double z, double dir, DriverUte driver) {
        super(new Vector3d(x, 0, z), "BotUte");
        camera = BotFactory.addCamera(this);
        initDir = dir;
        this.driver = driver;
    }

    /** This method is called by the simulator engine on reset. */
    @Override
    public void initBehavior() {
        giro(initDir);
        velocidad(1);
        // progress at 1 m/s
       //setTranslationalVelocity(1);
        //setRotationalVelocity(0);
    }

    /** This method is call cyclically (20 times per second)  by the simulator engine. */
    @Override
    public void performBehavior() {
        driver.drive(this, camera);
    }
    
    public double deg2rad(double deg) {
        return deg * Math.PI / 180;
    }

    public void giro(double deg) {
        rotateY(deg2rad(deg));
    }
    
    public void velocidad(double vel) {
        setTranslationalVelocity(vel);
    }
    
}
