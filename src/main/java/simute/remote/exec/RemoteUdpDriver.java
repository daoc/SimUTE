package simute.remote.exec;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simute.remote.net.UdpReceiveImage;
import simute.remote.net.UdpSendCommand;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class RemoteUdpDriver {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8888;
    private static final int LOCAL_PORT = 8889;    
    
    public static void main(String[] args) {
    	new RemoteUdpDriver();
    }
    
    public RemoteUdpDriver() {
        JFrame frame = new JFrame("Conduciendo el BotUTE");
        frame.add(new PanelCamara(), BorderLayout.WEST);
        frame.add(new PanelControles(), BorderLayout.CENTER);
        frame.pack();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);    	
    }
    
    private static class PanelCamara extends JPanel {
		private static final long serialVersionUID = 1L;
		private final JLabel monitor;

        public PanelCamara() {
            monitor = new JLabel(new ImageIcon("noimg_400_X_300.png"));
            add(monitor);
            new UdpReceiveImage(monitor, LOCAL_PORT).start();
        } 
    }    
  
    /*
     * Los botones son solo informativos (de adorno) y NO funcionan
     */
    private static class PanelControles extends JPanel {
		private static final long serialVersionUID = 1L;
		private final UdpSendCommand enviaInstruccion;
        
        public PanelControles() {
            setLayout(new BorderLayout());
            JButton bW = new JButton("W");
            bW.addKeyListener(keyL);
            JButton bS = new JButton("S");
            bS.addKeyListener(keyL);
            JButton bA = new JButton("A");
            bA.addKeyListener(keyL);
            JButton bD = new JButton("D");
            bD.addKeyListener(keyL);        
            add(bW, BorderLayout.NORTH);
            add(bS, BorderLayout.SOUTH);
            add(bA, BorderLayout.WEST);
            add(bD, BorderLayout.EAST);
            enviaInstruccion = new UdpSendCommand(SERVER_HOST, SERVER_PORT);
            enviaInstruccion.start();
        }

        private final KeyListener keyL = new KeyAdapter() {

    		public void keyPressed(KeyEvent e) {
	          char key = e.getKeyChar();
	          switch(key) {
	              case 'w':
	              case 'W':
	              case 's':
	              case 'S':
	              case 'a':
	              case 'A':
	              case 'd':
	              case 'D':
	              case ' ':
	                  enviaInstruccion.addInstruccion(Character.toString(key)); 
	                  break;
	          }
    		}

    		public void keyReleased(KeyEvent e) {
  	          char key = e.getKeyChar();
  	          switch(key) {
  	              case 'w':
  	              case 'W':
  	              case 's':
  	              case 'S':
  	              case 'a':
  	              case 'A':
  	              case 'd':
  	              case 'D':
  	              case ' ':
  	                  enviaInstruccion.addInstruccion("!" + Character.toString(key)); 
  	                  break;
  	          }
    		}
    	};		        
    }

}
