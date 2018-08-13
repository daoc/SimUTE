/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simute;

import simbad.sim.RobotFactory;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class BotFactory {
    
    public static CameraUte addCamera(BotUte bot) {
        return new CameraUte(RobotFactory.addCameraSensor(bot));
    }
    
}
