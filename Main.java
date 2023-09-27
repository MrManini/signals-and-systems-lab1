package tello;

import com.formdev.flatlaf.IntelliJTheme;
import javax.swing.ImageIcon;
/**
 *
 * @author kevin
 */
public class Main extends javax.swing.JFrame {
    public Main() {
        initComponents();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnContinuous = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnDiscrete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnSignalSum = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Harlow Solid Italic", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 204));
        jLabel1.setText("Mi primer laboratorio de señales en Java");

        btnContinuous.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        btnContinuous.setForeground(new java.awt.Color(102, 0, 102));
        btnContinuous.setText("Señales continuas");
        btnContinuous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuousActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tello/imgs/sparkle.png"))); // NOI18N
        jLabel4.setText("jLabel4");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tello/imgs/hearts.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        btnDiscrete.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        btnDiscrete.setForeground(new java.awt.Color(102, 0, 102));
        btnDiscrete.setText("Señales discretas");
        btnDiscrete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscreteActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tello/imgs/rainbow.png"))); // NOI18N
        jLabel3.setText("jLabel3");

        btnSignalSum.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        btnSignalSum.setForeground(new java.awt.Color(102, 0, 102));
        btnSignalSum.setText("Suma de señales");
        btnSignalSum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignalSumActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lucida Handwriting", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 204));
        jLabel5.setText("<html> <b>Integrantes:</b><br> Belissa Briceño<br> Isabella Orozco<br> Kevin Torregrosa<br> </html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnContinuous))
                        .addGap(45, 45, 45)
                        .addComponent(btnDiscrete)
                        .addGap(61, 61, 61)
                        .addComponent(btnSignalSum)
                        .addGap(56, 56, 56))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuous)
                    .addComponent(btnDiscrete)
                    .addComponent(btnSignalSum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSignalSumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignalSumActionPerformed
        SignalSum frame = new SignalSum();
        frame.setTitle("Primer Laboratorio Señales y Sistemas");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon((this.getClass().getResource("/tello/imgs/logo.png")));
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
        dispose();
    }//GEN-LAST:event_btnSignalSumActionPerformed

    private void btnDiscreteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscreteActionPerformed
        TransformDiscrete frame = new TransformDiscrete();
        frame.setTitle("Primer Laboratorio Señales y Sistemas");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon((this.getClass().getResource("/tello/imgs/logo.png")));
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
        dispose();
    }//GEN-LAST:event_btnDiscreteActionPerformed

    private void btnContinuousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuousActionPerformed
        TransformContinuous frame = new TransformContinuous();
        frame.setTitle("Primer Laboratorio Señales y Sistemas");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon((this.getClass().getResource("/tello/imgs/logo.png")));
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
        dispose();
    }//GEN-LAST:event_btnContinuousActionPerformed

    public static void main(String args[]) {
        try {
            IntelliJTheme.setup(Main.class.getResourceAsStream("/cute_pink_light.theme.json"));
            //FlatGradiantoDarkFuchsiaIJTheme.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main frame = new Main();
                frame.setTitle("Primer Laboratorio Señales y Sistemas");
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ImageIcon icon = new ImageIcon((this.getClass().getResource("/tello/imgs/logo.png")));
                frame.setIconImage(icon.getImage());
                frame.setResizable(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuous;
    private javax.swing.JButton btnDiscrete;
    private javax.swing.JButton btnSignalSum;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
