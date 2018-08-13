
package simute;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public interface DriverUte {
    /**
        Este método será invocado 20 veces por segundo, por el simulador
    */
    public void drive(BotUte bot, CameraUte cam);
}
