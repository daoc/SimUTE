/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simute.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import simute.CameraUte;

/**
 *
 * @author dordonez
 */
public class SaveImgThread extends Thread {
    final CameraUte cam;
    final String preFileName;
    final String baseDirPath = "C:\\Users\\dordonez\\Downloads\\";

    public SaveImgThread(CameraUte cam, String preFileName) {
        this.cam = cam;
        this.preFileName = preFileName;
    }
    
    @Override
    public void run() {
        String action = preFileName.substring(6, 7);
        System.out.println(action);
        try {
            String fileName = baseDirPath + "\\" + action + "\\" + preFileName + new Date().getTime() + ".png";
            ImageIO.write(cam.getSnapshot(), "PNG", new File(fileName));
        } catch (IOException ex) {
            Logger.getLogger(CameraUte.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    
    
}
