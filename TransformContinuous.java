package tello;

import java.awt.event.ItemEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author kevin
 */
public class TransformContinuous extends javax.swing.JFrame {

    public TransformContinuous() {
        initComponents();
        ComboBoxChange();
        appearSignalParameters(false, 0);
        EverythingElse(false);
    }

    public void appearSignalParameters(boolean signal1, int limit){
        lblA.setVisible(signal1);
        lblB.setVisible(signal1);
        lblC.setVisible(signal1);
        txtA.setVisible(signal1);
        txtB.setVisible(signal1);
        txtC.setVisible(signal1);
        txtLowerLimit.setEditable(signal1);
        txtUpperLimit.setEditable(signal1);
        txtLowerLimit.setText(Integer.toString(-limit));
        txtUpperLimit.setText(Integer.toString(limit));
    }
    public void EverythingElse(boolean signal){
        btnGenerate.setEnabled(signal);
        lblEquation.setVisible(signal);
        lblAmp.setVisible(signal);
        btnPMamp.setVisible(signal);
        comboBoxAmp.setVisible(signal);
        lblDelay.setVisible(signal);
        btnPMdelay.setVisible(signal);
        comboBoxDelay.setVisible(signal);
        btnMethod.setVisible(signal);
        btnTransform.setVisible(signal);
        
    }
    public void ComboBoxChange(){
        comboBoxSignal.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedOption = (String) e.getItem();
                ImageIcon image;
                switch (selectedOption) {
                    case "Ninguna":
                        lblImage.setIcon(null);
                        appearSignalParameters(false, 0);
                        EverythingElse(false);
                        break;
                    case "Señal 1":                   
                        appearSignalParameters(true, 5);
                        image = new ImageIcon((this.getClass().getResource("/tello/imgs/parabola.png")));
                        lblImage.setIcon(image);
                        EverythingElse(true);
                        break;
                    case "Señal 2":
                        appearSignalParameters(false, 1);
                        image = new ImageIcon((this.getClass().getResource("/tello/imgs/triangle.png")));
                        lblImage.setIcon(image);
                        EverythingElse(true);
                        break;
                    case "Señal 3":
                        appearSignalParameters(false, 10);
                        image = new ImageIcon((this.getClass().getResource("/tello/imgs/exp.png")));
                        lblImage.setIcon(image);
                        EverythingElse(true);
                        break;
                }
                
            }
        });
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        comboBoxSignal = new javax.swing.JComboBox<>();
        lblSignal = new javax.swing.JLabel();
        lblA = new javax.swing.JLabel();
        txtA = new javax.swing.JTextField();
        txtB = new javax.swing.JTextField();
        lblB = new javax.swing.JLabel();
        txtC = new javax.swing.JTextField();
        lblC = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        lblLowerLimit = new javax.swing.JLabel();
        txtLowerLimit = new javax.swing.JTextField();
        lblUpperLimit = new javax.swing.JLabel();
        txtUpperLimit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblEquation = new javax.swing.JLabel();
        lblAmp = new javax.swing.JLabel();
        lblDelay = new javax.swing.JLabel();
        comboBoxAmp = new javax.swing.JComboBox<>();
        comboBoxDelay = new javax.swing.JComboBox<>();
        btnPMamp = new javax.swing.JToggleButton();
        btnPMdelay = new javax.swing.JToggleButton();
        btnGenerate = new javax.swing.JButton();
        btnTransform = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnMethod = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Harlow Solid Italic", 0, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 51, 204));
        lblTitle.setText("Transformar Señales Continuas");

        comboBoxSignal.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        comboBoxSignal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguna", "Señal 1", "Señal 2", "Señal 3" }));

        lblSignal.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblSignal.setText("Elige tu señal:");

        lblA.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblA.setText("a:");

        txtA.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtA.setText("1");

        txtB.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtB.setText("0");

        lblB.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblB.setText("b:");

        txtC.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtC.setText("0");

        lblC.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblC.setText("c:");

        lblLowerLimit.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblLowerLimit.setText("Límite inferior:");

        txtLowerLimit.setEditable(false);
        txtLowerLimit.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N

        lblUpperLimit.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUpperLimit.setText("Límite superior:");

        txtUpperLimit.setEditable(false);
        txtUpperLimit.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N

        lblEquation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tello/imgs/continuous.png"))); // NOI18N

        lblAmp.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblAmp.setText("a:");

        lblDelay.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblDelay.setText("<html> t<sub>0</sub>: </html>");

        comboBoxAmp.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        comboBoxAmp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5", "1/2", "1/3", "1/4", "1/5" }));

        comboBoxDelay.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        comboBoxDelay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));

        btnPMamp.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        btnPMamp.setText("+");
        btnPMamp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPMampActionPerformed(evt);
            }
        });

        btnPMdelay.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        btnPMdelay.setText("+");
        btnPMdelay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPMdelayActionPerformed(evt);
            }
        });

        btnGenerate.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        btnGenerate.setText("Generar");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        btnTransform.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        btnTransform.setText("Transformar");
        btnTransform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransformActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        btnBack.setText("Volver");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnMethod.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        btnMethod.setText("Método 1");
        btnMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMethodActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblLowerLimit)
                            .addComponent(lblUpperLimit))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtLowerLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUpperLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(btnGenerate))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblSignal)
                        .addGap(18, 18, 18)
                        .addComponent(comboBoxSignal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnTransform, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEquation, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnBack))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAmp, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPMdelay, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPMamp, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboBoxAmp, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxDelay, 0, 1, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(btnMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblA, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblB, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblC, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 46, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSignal)
                            .addComponent(comboBoxSignal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLowerLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLowerLimit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUpperLimit)
                            .addComponent(txtUpperLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(btnGenerate)
                        .addGap(18, 18, 18)
                        .addComponent(lblEquation)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxAmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPMamp)
                            .addComponent(lblAmp))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPMdelay)
                            .addComponent(lblDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMethod)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTransform)
                            .addComponent(jLabel2)))
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblA)
                            .addComponent(lblB)
                            .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblC)
                            .addComponent(txtC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(btnBack)
                        .addGap(18, 18, 18))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPMampActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPMampActionPerformed
        String plusorminus = btnPMamp.getText();
        if (plusorminus.equals("+")){
            btnPMamp.setText("-");
        }else{
            btnPMamp.setText("+");
        }
    }//GEN-LAST:event_btnPMampActionPerformed

    private void btnPMdelayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPMdelayActionPerformed
        String plusorminus = btnPMdelay.getText();
        if (plusorminus.equals("+")){
            btnPMdelay.setText("-");
        }else{
            btnPMdelay.setText("+");
        }
    }//GEN-LAST:event_btnPMdelayActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        dispose();
        Main frame = new Main();
        frame.setTitle("Primer Laboratorio Señales y Sistemas");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon((this.getClass().getResource("/tello/imgs/logo.png")));
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        String signal = (String) comboBoxSignal.getSelectedItem();
        double[] limits = new double[2];
        limits[0] = Double.parseDouble(txtLowerLimit.getText());
        limits[1] = Double.parseDouble(txtUpperLimit.getText());
        double[] abc = new double[3];
        abc[0] = Double.parseDouble(txtA.getText());
        abc[1] = Double.parseDouble(txtB.getText());
        abc[2] = Double.parseDouble(txtC.getText());

        ContinuousRegular frame = new ContinuousRegular(signal, limits, abc);
        frame.setTitle("Primer Laboratorio Señales y Sistemas");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon((this.getClass().getResource("/tello/imgs/logo.png")));
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void btnMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMethodActionPerformed
        String method = btnMethod.getText();
        if (method.equals("Método 1")){
            btnMethod.setText("Método 2");
        }else{
            btnMethod.setText("Método 1");
        }
    }//GEN-LAST:event_btnMethodActionPerformed

    private void btnTransformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransformActionPerformed
        String signal = (String) comboBoxSignal.getSelectedItem();
        double[] limits = new double[2];
        limits[0] = Double.parseDouble(txtLowerLimit.getText());
        limits[1] = Double.parseDouble(txtUpperLimit.getText());
        double[] abc = new double[3];
        abc[0] = Double.parseDouble(txtA.getText());
        abc[1] = Double.parseDouble(txtB.getText());
        abc[2] = Double.parseDouble(txtC.getText());
        double[] at0 = new double[2];
        String astr = (String) comboBoxAmp.getSelectedItem();
        switch (astr){
            case "1/2": at0[0] = 1.0/2; break;
            case "1/3": at0[0] = 1.0/3; break;
            case "1/4": at0[0] = 1.0/4; break;
            case "1/5": at0[0] = 1.0/5; break;
            default: at0[0] = Double.parseDouble(astr); break;
        }
        if (btnPMamp.getText().equals("-")){
            at0[0] *= -1;
        }
        String delaystr = (String) comboBoxDelay.getSelectedItem();
        at0[1] = Double.parseDouble(delaystr);
        if (btnPMdelay.getText().equals("-")){
            at0[1] *= -1;
        }
        String method = btnMethod.getText();
        
        ContinuousTransformed frame = new ContinuousTransformed(signal, limits, abc, at0, method);
        frame.setTitle("Primer Laboratorio Señales y Sistemas");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon((this.getClass().getResource("/tello/imgs/logo.png")));
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
    }//GEN-LAST:event_btnTransformActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JToggleButton btnMethod;
    private javax.swing.JToggleButton btnPMamp;
    private javax.swing.JToggleButton btnPMdelay;
    private javax.swing.JButton btnTransform;
    private javax.swing.JComboBox<String> comboBoxAmp;
    private javax.swing.JComboBox<String> comboBoxDelay;
    private javax.swing.JComboBox<String> comboBoxSignal;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblA;
    private javax.swing.JLabel lblAmp;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblC;
    private javax.swing.JLabel lblDelay;
    private javax.swing.JLabel lblEquation;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLowerLimit;
    private javax.swing.JLabel lblSignal;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUpperLimit;
    private javax.swing.JTextField txtA;
    private javax.swing.JTextField txtB;
    private javax.swing.JTextField txtC;
    private javax.swing.JTextField txtLowerLimit;
    private javax.swing.JTextField txtUpperLimit;
    // End of variables declaration//GEN-END:variables

}
