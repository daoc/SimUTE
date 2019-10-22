package simute;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;

import simute.gui.I_Canvas;

/**
*
* @author dordonez@ute.edu.ec
*/
public class ImageRenderer {

	@SuppressWarnings("unused")
	public static void saveSceneFrame(I_Canvas canvas, long frameCount, float speed, float turn) {
		File folder = new File(Env.IMG_FRAMES_FOLDER);
		folder.mkdirs();
		if (Env.SAVE_SCENE_FRAMES && (frameCount % Env.SAVE_EACH_X_FRAMES == 0)) {
			String fileName = String.format("s_%f-t_%f-%d.png", speed, turn, new Date().getTime());
			try {
				File f = new File(folder, fileName);
				f.createNewFile();
				ImageIO.write(canvas.getFrameImage(), "png", f);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
	public static int[][] getHistAvgExcessRgb(BufferedImage img) {
		Raster raster = img.getData();
		int[][] histAvgRGB = new int[6][raster.getWidth()];
		// Histograma por columna (Horizontal)
		for (int x = 0; x < raster.getWidth(); x++) {
			for (int y = 0; y < raster.getHeight(); y++) {
				int r = raster.getSample(x, y, 0);
				int g = raster.getSample(x, y, 1);
				int b = raster.getSample(x, y, 2);
				int exR = 2 * r - (g + b);
				int exG = 2 * g - (r + b);
				int exB = 2 * b - (r + g);
				histAvgRGB[0][x] += (exR > 0 ? 1 : 0);
				histAvgRGB[1][x] += (exG > 0 ? 1 : 0);
				histAvgRGB[2][x] += (exB > 0 ? 1 : 0);
			}
		}
		// Histograma por fila (vertical)
		for (int y = 0; y < raster.getHeight(); y++) {
			for (int x = 0; x < raster.getWidth(); x++) {
				int r = raster.getSample(x, y, 0);
				int g = raster.getSample(x, y, 1);
				int b = raster.getSample(x, y, 2);
				int exR = 2 * r - (g + b);
				int exG = 2 * g - (r + b);
				int exB = 2 * b - (r + g);
				histAvgRGB[3][y] += (exR > 0 ? 1 : 0);
				histAvgRGB[4][y] += (exG > 0 ? 1 : 0);
				histAvgRGB[5][y] += (exB > 0 ? 1 : 0);
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
    cog > 0 -> peso hacia la derecha de la imagen
    cog < 0 -> peso hacia la izquierda de la imagen
    

    Fila(Vertical)
    cog[3] -> Center of Gravity Red
    cog[4] -> Center of Gravity Green
    cog[5] -> Center of Gravity Blue   
    cog = 0 -> peso en la mitad vertical de la imagen
    cog > 0 -> peso hacia arriba de la imagen
    cog < 0 -> peso hacia abajo de la imagen
    */
	public static int[] getCogRgb(BufferedImage img) {
		int[][] hist = getHistAvgExcessRgb(img);
		int[] cog = new int[6];
		int ncol = hist[0].length;
		int nrow = hist[3].length;
		int EfxR, EfxG, EfxB, EfR, EfG, EfB;

		// Center of Gravity por columna (horizontal)
		EfxR = EfxG = EfxB = EfR = EfG = EfB = 0;
		for (int x = 0; x < ncol; x++) {
			EfxR += hist[0][x] * (x + 1);
			EfR += hist[0][x];
			EfxG += hist[1][x] * (x + 1);
			EfG += hist[1][x];
			EfxB += hist[2][x] * (x + 1);
			EfB += hist[2][x];
		}
		cog[0] = EfR > 0 ? (EfxR / EfR) - (ncol / 2) : 0;
		cog[1] = EfG > 0 ? (EfxG / EfG) - (ncol / 2) : 0;
		cog[2] = EfB > 0 ? (EfxB / EfB) - (ncol / 2) : 0;
		// Center of Gravity por fila (vertical)
		EfxR = EfxG = EfxB = EfR = EfG = EfB = 0;
		for (int x = 0; x < nrow; x++) {
			EfxR += hist[3][x] * (x + 1);
			EfR += hist[3][x];
			EfxG += hist[4][x] * (x + 1);
			EfG += hist[4][x];
			EfxB += hist[5][x] * (x + 1);
			EfB += hist[5][x];
		}
		cog[3] = EfR > 0 ? (nrow / 2) - (EfxR / EfR) : 0;
		cog[4] = EfG > 0 ? (nrow / 2) - (EfxG / EfG) : 0;
		cog[5] = EfB > 0 ? (nrow / 2) - (EfxB / EfB) : 0;
		return cog;
	}

    public static BufferedImage getResizedImg(BufferedImage src, int newW, int newH) { 
        Image tmp = src.getScaledInstance(newW, newH, Image.SCALE_FAST);
        BufferedImage destimg = new BufferedImage(newW, newH, src.getType());

        Graphics2D g2d = destimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return destimg;
    } 	
	
}
