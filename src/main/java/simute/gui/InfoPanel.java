package simute.gui;

import java.awt.Color;

import javax.swing.JPanel;

import simute.Env;

/**
*
* @author dordonez@ute.edu.ec
*/
public class InfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public InfoPanel() {
		setPreferredSize(Env.INFO_SIZE);
		setSize(Env.INFO_SIZE);
		setBackground(Color.LIGHT_GRAY);
	}
	
}
