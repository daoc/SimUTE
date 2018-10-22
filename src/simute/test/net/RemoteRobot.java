package simute.test.net;

import simute.BotUte;
import simute.CameraUte;
import simute.DriverUte;
import simute.EnvUte;
import simute.RunSimUte;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class RemoteRobot implements DriverUte {
    private static final String host = "localhost";
    private static final int remotePort = 8889;
    private static final int localPort = 8888;
    private static final EnviaImagen enviaImagen = new EnviaImagen(host, remotePort);
    private static final ObtieneInstruccion obtieneInstruccion = new ObtieneInstruccion(localPort);
    
    public static void main(String[] args) {
        RunSimUte.init(new BotUte(0, 0, 0, new RemoteRobot()),
            "mapa_path_prap1c.png",
            EnvUte.TipoMapa.CAMINO);
        obtieneInstruccion.start();
        enviaImagen.start();
    }

    @Override
    public void drive(BotUte bot, CameraUte cam) {
        enviaImagen.addImage(cam.getSnapshot());
        String action = obtieneInstruccion.getInstruccion();
        if (action == null) return;
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
