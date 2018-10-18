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

    public static String FILENAME = "C:\\Users\\dordonez\\Desktop\\_tmp\\SimUTE_imgs\\MiImg_A_1539873428981.png";
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(HistCog.getCogRgb()));
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
        Raster raster = readImgFile(FILENAME);
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
    
 public static int[] getCogRgb() {
        int[][] hist = getHistAvgExcessRgb();
        int[] cog = new int[6];
        int ncol = hist[0].length;
        int nrow = hist[3].length;
        int EfxR, EfxG, EfxB, EfR, EfG, EfB;
        EfxR = EfxG = EfxB = EfR = EfG = EfB = 0;
        
        //Center of Gravity por columna (horizontal)
        for(int x = 0; x < ncol; x++) {
            EfxR += hist[0][x] * (x+1);
            EfR += hist[0][x];
            EfxG += hist[1][x] * (x+1);
            EfG += hist[1][x];
            EfxB += hist[2][x] * (x+1);
            EfB += hist[2][x];            
        }      
        cog[0] = EfR > 0 ? (ncol / 2) - (EfxR / EfR) : 0;
        cog[1] = EfG > 0 ? (ncol / 2) - (EfxG / EfG) : 0;
        cog[2] = EfB > 0 ? (ncol / 2) - (EfxB / EfB) : 0;
        //Center of Gravity por fila (vertical)
        for(int x = 0; x < nrow; x++) {
            EfxR += hist[3][x] * (x+1);
            EfR += hist[3][x];
            EfxG += hist[4][x] * (x+1);
            EfG += hist[4][x];
            EfxB += hist[5][x] * (x+1);
            EfB += hist[5][x];            
        }      
        cog[3] = EfR > 0 ? (nrow / 2) - (EfxR / EfR) : 0;
        cog[4] = EfG > 0 ? (nrow / 2) - (EfxG / EfG) : 0;
        cog[5] = EfB > 0 ? (nrow / 2) - (EfxB / EfB) : 0;
        return cog;
    }    
}
