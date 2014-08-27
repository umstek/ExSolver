/*
 * About.java v0.1.0.0 alpha
 * Contains the code used to display the About window.
 * mailto:umstek@gmail.com
 */
package mathexsolver;

/**
 *
 * @author Wickramaranga
 * @version 0.1.0.0 alpha
 */
public class About extends javax.swing.JFrame {

    /**
     * Creates new form About
     */
    public About() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAbout = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About mathex™ Solver");
        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        setName("About"); // NOI18N
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        lblAbout.setFont(new java.awt.Font("Segoe UI Semibold", 0, 11)); // NOI18N
        lblAbout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAbout.setText("<html>\n<body>\n\n<font color = \"#990066\" size=\"4\">Thanks to:</font><br/>\n<font color = \"#009999\">Everyone who liked (and commented on) the post about this software in the Facebook (crewOCJP) page. ;-) Please take a minute to put a like if you didn't.</font><br/><br/>\n\n<font color = \"#990066\" size=\"5\">Special thanks to:</font><br/>\n<font color = \"#009999\">Isuru, Buddhima, Sumudu and the people who invented java and html. :-)</font><br/><br/>\n\n<font color = \"#990066\" size=\"5\">Very special thanks to:</font><br/>\n<font color = \"#009999\">Shiran Dinuka sir and the teachers who taught me Math.</font><br/><br/>\n\n<font color = \"#990066\" size=\"2\">No thanks to: </font>\n<font color = \"#009999\" size=\"2\">The one who invented RegEx. :-( :-(</font><br/><br/>\n<font color = \"#ff0066\">CAUTION: </font>ALPHA VERSION. EVALUATION AND TESTING PURPOSES ONLY.\n</body>\n</html>");

        lblTitle.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("<html>\n<font size = \"6\" color = \"#ff0066\">math</font><font size = \"7\" color=\"#00ccff\" >ex</font>™ <font size=\"5\">Solver</font> v0.1.0.0 <i>alpha</i>\n<br />\n<a href= \"mailto:umstek@gmail.com\">umstek@gmail.com</a>\n</html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(lblAbout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAbout, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAbout;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}