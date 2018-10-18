/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class HistCog {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(HistCog.getHistAvgExcessRgb()[4]));
    }
    
    public static Raster readImgFile(String fileName) {
        try {
            return ImageIO.read(new File(fileName)).getData();
        } catch (IOException ex) {
            Logger.getLogger(HistCog.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static int[][] getHistAvgExcessRgb() {
        Raster raster = readImgFile("MiImg_A_1539873428981.png");
        int[][] histAvgRGB = new int[6][raster.getWidth()];
        //Histograma por columna (Horizontal)
        for (int x = 0; x < raster.getWidth(); x++) {
            for (int y = 0; y < raster.getHeight(); y++) {
                int r = raster.getSample(x, y, 0);
                int g = raster.getSample(x, y, 1);
                int b = raster.getSample(x, y, 2);
                int exR = 2 * r - (g + b);
                int exG = 2 * g - (r + b);
                int exB = 2 * b - (r + g);
                histAvgRGB[0][x] += (exR > 0 ? exR : 0) / raster.getHeight();
                histAvgRGB[1][x] += (exG > 0 ? exG : 0) / raster.getHeight();
                histAvgRGB[2][x] += (exB > 0 ? exB : 0) / raster.getHeight();
            }
        }
        //Histograma por fila (vertical)
        for (int y = 0; y < raster.getHeight(); y++) {
            for (int x = 0; x < raster.getWidth(); x++) {
                int r = raster.getSample(x, y, 0);
                int g = raster.getSample(x, y, 1);
                int b = raster.getSample(x, y, 2);
                int exR = 2 * r - (g + b);
                int exG = 2 * g - (r + b);
                int exB = 2 * b - (r + g);
                histAvgRGB[3][y] += (exR > 0 ? exR : 0) / raster.getWidth();
                histAvgRGB[4][y] += (exG > 0 ? exG : 0) / raster.getWidth();
                histAvgRGB[5][y] += (exB > 0 ? exB : 0) / raster.getWidth();
            }
        }
        return histAvgRGB;
    }
}
