package simute.test.net;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class RemoteDriver {
    private static final String host = "localhost";
    private static final int remotePort = 8888;
    private static final int localPort = 8889;    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Conduciendo el BotUTE");
        frame.add(new PanelCamara(), BorderLayout.WEST);
        frame.add(new PanelControles(), BorderLayout.CENTER);
        frame.pack();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private static class PanelCamara extends JPanel {
        private final JLabel monitor;

        public PanelCamara() {
            monitor = new JLabel(new ImageIcon("noimg.png"));
            add(monitor);
            new ObtieneImagen(monitor, localPort).start();
        } 
    }    
  
    private static class PanelControles extends JPanel {
        private final EnviaInstruccion enviaInstruccion;
        
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
            enviaInstruccion = new EnviaInstruccion(host, remotePort);
            enviaInstruccion.start();
        }

        private final KeyListener keyL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                switch(key) {
                    case 'w':
                    case 's':
                    case 'a':
                    case 'd':
                        enviaInstruccion.addInstruccion(Character.toString(key)); 
                        break;
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {}      
            @Override
            public void keyReleased(KeyEvent e) {}
        };  

    }
    
}
