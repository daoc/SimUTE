package simute.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;

import simute.Env;

/**
*
* @author dordonez@ute.edu.ec
*/
public class ControllsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final JLabel lblFps;
	private final JLabel lblSecs;
	private final JLabel lblSpeed;
	private final JLabel lblTurn;
	private final JSlider sldrSpeed;
	private final JSlider sldrTurn;
	
	public ControllsPanel() {
		setPreferredSize(Env.CONTROLLS_SIZE);
		setSize(Env.CONTROLLS_SIZE);
		setBackground(Color.LIGHT_GRAY);
		
		setLayout(new GridLayout(0, 2));
		
		lblFps = new JLabel("lblFps");
		lblSecs = new JLabel("lblSecs");
		lblSpeed = new JLabel("lblSpeed");
		lblTurn = new JLabel("lblTurn");
		sldrSpeed = new JSlider(-50, 50, 0);
		sldrSpeed.setFocusable(false);
		sldrSpeed.setPaintTicks(true);
		sldrSpeed.setMajorTickSpacing(25);
		sldrTurn = new JSlider(-30, 30, 0);
		sldrTurn.setFocusable(false);
		sldrTurn.setPaintTicks(true);
		sldrTurn.setMajorTickSpacing(15);
		
		lblFps.setFont(new Font("Courier New", Font.BOLD, 16));
		lblSecs.setFont(new Font("Courier New", Font.BOLD, 16));
		lblSpeed.setFont(new Font("Courier New", Font.BOLD, 16));
		lblTurn.setFont(new Font("Courier New", Font.BOLD, 16));
		
		add(new JLabel("FPS: "));
		add(lblFps);
		add(new JLabel("Secs: "));
		add(lblSecs);
		add(new JLabel("Speed: "));
		add(lblSpeed);
		add(new JSeparator());
		add(sldrSpeed);
		add(new JLabel("Turn: "));
		add(lblTurn);
		add(new JSeparator());
		add(sldrTurn);
	}

	public JLabel getLblFps() {
		return lblFps;
	}
	public JLabel getLblSecs() {
		return lblSecs;
	}
	public JLabel getLblSpeed() {
		return lblSpeed;
	}
	public JLabel getLblTurn() {
		return lblTurn;
	}
	public JSlider getSliderSpeed() {
		return sldrSpeed;
	}	
	public JSlider getSliderTurn() {
		return sldrTurn;
	}		
}
