
package simute;

import java.awt.event.ActionEvent;
import java.util.concurrent.BlockingQueue;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import simbad.gui.Simbad;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class RunSimUte {
    
    public static void init(BotUte robot, String mapa, EnvUte.TipoMapa tipo) {
        //System.setProperty("j3d.implicitAntialiasing", "true");
        new Simbad(new EnvUte(robot, mapa, tipo), false);   
    }  
    
    public static void init(BotUte robot, String mapa, EnvUte.TipoMapa tipo, BlockingQueue<String> queue) {
        Simbad s = new Simbad(new EnvUte(robot, mapa, tipo), false);
        
        Action myAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queue.add(e.getActionCommand());
            }
        };
        
        JRootPane rootPane = s.getRootPane();
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "mas");
        rootPane.getActionMap().put("mas", myAction);
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "izquierda");
        rootPane.getActionMap().put("izquierda", myAction);
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "menos");
        rootPane.getActionMap().put("menos", myAction);
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "derecha");
        rootPane.getActionMap().put("derecha", myAction);        
    }
}
