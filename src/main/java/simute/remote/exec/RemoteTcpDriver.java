package simute.remote.exec;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simute.remote.net.TcpClientConnection;
import simute.remote.net.TcpReceiveImage;
import simute.remote.net.TcpSendCommand;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class RemoteTcpDriver {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8888; 
    private final TcpSendCommand sendCommand;
    private final TcpReceiveImage getImage;
    
    public static void main(String[] args) {
    	new RemoteTcpDriver();
    }
    
    public RemoteTcpDriver() {   	
        JFrame frame = new JFrame("Conduciendo el BotUTE");
        PanelCamara canvas = new PanelCamara();
        PanelControles controles = new PanelControles();
        frame.add(canvas, BorderLayout.WEST);
        frame.add(controles, BorderLayout.CENTER);
        frame.pack();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(keyL);
        frame.setVisible(true);

        sendCommand = new TcpSendCommand();
        getImage = new TcpReceiveImage(canvas.getMonitor());
        
        new TcpClientConnection(SERVER_HOST, SERVER_PORT, sendCommand, getImage).start();
    }

    private static class PanelCamara extends JPanel {
		private static final long serialVersionUID = 1L;
		private final JLabel monitor;

        public PanelCamara() {
            monitor = new JLabel(new ImageIcon("noimg_400_X_300.png"));
            add(monitor);
        }
        
        public JLabel getMonitor() {
        	return monitor;
        }
    }    
  
    /*
     * Los botones son solo informativos (de adorno) y NO funcionan
     */
    private static class PanelControles extends JPanel {
		private static final long serialVersionUID = 1L;
        
        public PanelControles() {
            setLayout(new BorderLayout());
            JButton bW = new JButton("W");
            JButton bS = new JButton("S");
            JButton bA = new JButton("A");
            JButton bD = new JButton("D");
            bW.setFocusable(false);
            bS.setFocusable(false);
            bA.setFocusable(false);
            bD.setFocusable(false);
            add(bW, BorderLayout.NORTH);
            add(bS, BorderLayout.SOUTH);
            add(bA, BorderLayout.WEST);
            add(bD, BorderLayout.EAST);
        }
	        
    }

	private final KeyListener keyL = new KeyAdapter() {

		public void keyPressed(KeyEvent ke) {
			char key = ke.getKeyChar();
			switch (key) {
			case 'w':
			case 'W':
			case 's':
			case 'S':
			case 'a':
			case 'A':
			case 'd':
			case 'D':
			case ' ':
				sendCommand.sendCommand(Character.toString(key));
				break;
			}
		}

		public void keyReleased(KeyEvent ke) {
			char key = ke.getKeyChar();
			switch (key) {
			case 'w':
			case 'W':
			case 's':
			case 'S':
			case 'a':
			case 'A':
			case 'd':
			case 'D':
			case ' ':
				sendCommand.sendCommand("!" + Character.toString(key));
				break;
			}
		}
	};    
    
}
