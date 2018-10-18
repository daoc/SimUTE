
package simute;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import simbad.sim.CameraSensor;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class CameraUte {
    private CameraSensor cam;
    
    public CameraUte(CameraSensor cam) {
        //cam.setUpdateOnEachFrame(true);
        this.cam = cam;
    }
    
    public void saveImageFile(String preFileName) {
        try {
            String fileName = preFileName + new Date().getTime() + ".png";
            ImageIO.write(getSnapshot(), "PNG", new File(fileName));
        } catch (IOException ex) {
            Logger.getLogger(CameraUte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BufferedImage getSnapshot() {
        BufferedImage img = cam.createCompatibleImage();
        cam.copyVisionImage(img);
        return img;
    }
    
    public Raster getRaster() {
        return getSnapshot().getData();
    }
    
    /*
        Excess G <- 2*G-(R+B);
        Columna (Horizontal)
        int[0][] -> Excess Red
        int[1][] -> Excess Green
        int[2][] -> Excess Blue
        Fila (Vertical)
        int[3][] -> Excess Red
        int[4][] -> Excess Green
        int[5][] -> Excess Blue    
     */
    public int[][] getHistAvgExcessRgb() {
        Raster raster = getRaster();
        int[][] histAvgRGB = new int[6][raster.getWidth()];
        //Histograma por columna (Horizontal)
        for(int x = 0; x <raster.getWidth(); x++) {
            for(int y = 0; y < raster.getHeight(); y++) {
                int r = raster.getSample(x, y, 0);
                int g = raster.getSample(x, y, 1);
                int b = raster.getSample(x, y, 2);
                int exR = 2*r-(g+b);
                int exG = 2*g-(r+b);
                int exB = 2*b-(r+g);
                histAvgRGB[0][x] += (exR > 0 ? exR : 0) / raster.getHeight();
                histAvgRGB[1][x] += (exG > 0 ? exG : 0) / raster.getHeight();
                histAvgRGB[2][x] += (exB > 0 ? exB : 0) / raster.getHeight();
            }
        }
        //Histograma por fila (vertical)
        for(int y = 0; y <raster.getHeight(); y++) {
            for(int x = 0; x < raster.getWidth(); x++) {
                int r = raster.getSample(x, y, 0);
                int g = raster.getSample(x, y, 1);
                int b = raster.getSample(x, y, 2);
                int exR = 2*r-(g+b);
                int exG = 2*g-(r+b);
                int exB = 2*b-(r+g);
                histAvgRGB[3][y] += (exR > 0 ? exR : 0) / raster.getWidth();
                histAvgRGB[4][y] += (exG > 0 ? exG : 0) / raster.getWidth();
                histAvgRGB[5][y] += (exB > 0 ? exB : 0) / raster.getWidth();
            }
        }        
        return histAvgRGB;
    }
    
    /*
        Columna(Horizontal)
        cog[0] -> Center of Gravity Red
        cog[1] -> Center of Gravity Green
        cog[2] -> Center of Gravity Blue   
        cog = 0 -> peso en la mitad horizontal de la imagen
        cog < 0 -> peso hacia la izquierda de la imagen
        cog > 0 -> peso hacia la derecha de la imagen
    
        Fila(Vertical)
        cog[3] -> Center of Gravity Red
        cog[4] -> Center of Gravity Green
        cog[5] -> Center of Gravity Blue   
        cog = 0 -> peso en la mitad vertical de la imagen
        cog < 0 -> peso hacia arriba de la imagen
        cog > 0 -> peso hacia abajo de la imagen    
    */
    public int[] getCogRgb() {
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
