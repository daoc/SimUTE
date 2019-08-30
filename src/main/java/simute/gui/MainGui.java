package simute.gui;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
*
* @author dordonez@ute.edu.ec
*/
public class MainGui extends JFrame implements I_Gui {
	private static final long serialVersionUID = 1L;

	private final JPanel leftPanel;
	
	private final PointOfViewPanel povView;
	private final DroneViewPanel droneView;
	private final ControllsPanel controlls;
	//private final InfoPanel info;

	public static void main(String[] args) {
		new MainGui();
	}
		
	public MainGui() {
		setTitle("SimUTE Main GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		povView = new PointOfViewPanel();
		droneView = new DroneViewPanel();
		controlls = new ControllsPanel();
		//info = new InfoPanel();

		add(povView, BorderLayout.CENTER);
		add(leftPanel, BorderLayout.WEST);
		
		leftPanel.add(droneView);
		//add(info, BorderLayout.SOUTH);
		leftPanel.add(controlls);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public I_Canvas getPovCanvas() {
		return povView;
	}
	
	public I_Canvas getDroneCanvas() {
		return droneView;
	}	
	
	public void setKeyListener(KeyListener listener) {
		addKeyListener(listener);
	}

	public ControllsPanel getControllsPanel() {
		return controlls;
	}
}
