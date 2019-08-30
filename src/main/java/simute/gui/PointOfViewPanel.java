package simute.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.World;

import simute.Env;

/**
*
* @author dordonez@ute.edu.ec
*/
public class PointOfViewPanel extends JPanel implements I_Canvas {
	private static final long serialVersionUID = 1L;

	private FrameBuffer frameBuffer;
	
	public PointOfViewPanel() {
		setPreferredSize(Env.WORLD_SIZE);
		setSize(Env.WORLD_SIZE);
		setBackground(Color.BLACK);
		
		frameBuffer = new FrameBuffer(Env.WORLD_SIZE.width, Env.WORLD_SIZE.height, FrameBuffer.SAMPLINGMODE_NORMAL);
		frameBuffer.optimizeBufferAccess();
	}
	
	public void renderScene(World world) {
		frameBuffer.clear(Env.BACKGROUND);
		world.renderScene(frameBuffer);
		world.draw(frameBuffer);
		frameBuffer.update();
		frameBuffer.display(getGraphics());
	}
	
	public BufferedImage getFrameImage() {
		BufferedImage buffImg = new BufferedImage(Env.WORLD_SIZE.width, Env.WORLD_SIZE.height, BufferedImage.TYPE_INT_RGB);
		Graphics buffImgGraphics = buffImg.createGraphics();
		
		frameBuffer.display(buffImgGraphics);
		return buffImg;
	}
	
}
