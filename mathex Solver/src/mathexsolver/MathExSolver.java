/*
 * MathExSolver.java v0.1.0.0 alpha
 * Contains the logic needed to execute the program.
 * mailto:umstek@gmail.com
 */
package mathexsolver;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Wickramaranga
 * @version 0.1.0.0 alpha
 */
 public class MathExSolver {

    static SolverGUI gui;
    static About about;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        setTheme("Windows");
    }

    //Initialize GUIs
    static {
        gui = new SolverGUI();
        about = new About();
    }

    //Set a new theme.
    //TODO: Support multiple themes,
    //TODO: Preserve window contents while changing theme.
    static void setTheme(String theme) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (theme.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;  
                }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SolverGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        gui.setVisible(false);
        gui = new SolverGUI();
        gui.setVisible(true);

        if (about.isVisible()) {
            about.setVisible(false);
            about = new About();
            about.setVisible(true);
        } else {
            about = new About();
        }

    }

}
