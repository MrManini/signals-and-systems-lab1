package tello;

import java.awt.event.ItemEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class TransformDiscrete extends javax.swing.JFrame {

    public TransformDiscrete() {
        initComponents();
        ComboBoxChange();
        InterpolBoxChange();
        EverythingElse(false);
    }

    public void changeLimits(boolean signal1, int limit1, int limit2){
        txtLowerLimit.setEditable(signal1);
        txtUpperLimit.setEditable(signal1);
        txtLowerLimit.setText(Integer.toString(limit1));
        txtUpperLimit.setText(Integer.toString(limit2));
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
        lblInterpol.setVisible(signal);
        comboBoxInterpol.setVisible(signal);
        
    }
    public void ComboBoxChange(){
        comboBoxSignal.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedOption = (String) e.getItem();
                switch (selectedOption) {
                    case "Ninguna" -> {
                        lblImage.setIcon(null);
                        EverythingElse(false);
                        changeLimits(false,0,0);
                    }
                    case "Señal 4" ->{                    
                        ImageIcon image = new ImageIcon("C:/Users/kevin/Downloads/imgs/discsig1.png");
                        lblImage.setIcon(image);
                        EverythingElse(true);
                        changeLimits(true,1,14);
                    }
                    case "Señal 5" ->{
                        ImageIcon image = new ImageIcon("C:/Users/kevin/Downloads/imgs/discsig2.png");
                        lblImage.setIcon(image);
                        EverythingElse(true);
                        changeLimits(false,-8,8);
                    }
                    case "Señal 6" ->{
                        ImageIcon image = new ImageIcon("C:/Users/kevin/Downloads/imgs/discsig3.png");
                        lblImage.setIcon(image);
                        EverythingElse(true);
                        changeLimits(false,-5,6);
                    }
                }
                
            }
        });
    }
    public void InterpolBoxChange(){
        comboBoxAmp.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedOption = (String) e.getItem();
                switch (selectedOption) {
                    case "2", "3", "4", "5" -> comboBoxInterpol.setEnabled(false);
                    case "1/2", "1/3", "1/4", "1/5" -> comboBoxInterpol.setEnabled(true);
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
        lblInterpol = new javax.swing.JLabel();
        comboBoxInterpol = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Harlow Solid Italic", 0, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 51, 204));
        lblTitle.setText("Transformar Señales Discretas");

        comboBoxSignal.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        comboBoxSignal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguna", "Señal 4", "Señal 5", "Señal 6" }));

        lblSignal.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblSignal.setText("Elige tu señal:");

        lblLowerLimit.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblLowerLimit.setText("Límite inferior:");

        txtLowerLimit.setEditable(false);
        txtLowerLimit.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtLowerLimit.setText("0");

        lblUpperLimit.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUpperLimit.setText("Límite superior:");

        txtUpperLimit.setEditable(false);
        txtUpperLimit.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtUpperLimit.setText("0");

        lblEquation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tello/imgs/discrete.png"))); // NOI18N

        lblAmp.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblAmp.setText("M:");

        lblDelay.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblDelay.setText("<html> n<sub>0</sub>: </html>");

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

        lblInterpol.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblInterpol.setText("Interpolación:");

        comboBoxInterpol.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        comboBoxInterpol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ceros", "Escalón", "Lineal" }));
        comboBoxInterpol.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(btnGenerate))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEquation)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblLowerLimit)
                                            .addComponent(lblUpperLimit))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtLowerLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUpperLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTransform, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblInterpol)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboBoxInterpol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(btnMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(lblSignal)
                                .addGap(18, 18, 18)
                                .addComponent(comboBoxSignal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblInterpol)
                            .addComponent(comboBoxInterpol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnMethod)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTransform)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(btnBack)
                                .addGap(17, 17, 17))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))))
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
        ImageIcon icon = new ImageIcon("C:/Users/kevin/Downloads/imgs/logo.png");
        frame.setIconImage(icon.getImage());
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        boolean pass = true;
        String signal = (String) comboBoxSignal.getSelectedItem();
        double[] limits = new double[2];
        limits[0] = Double.parseDouble(txtLowerLimit.getText());
        limits[1] = Double.parseDouble(txtUpperLimit.getText());
        if (signal.equals("Señal 4") && limits[1]-limits[0]+1 != 14){
            pass = false;
        }
        if (pass){
            dispose();
            DiscreteRegular frame = new DiscreteRegular(signal, limits);
            frame.setTitle("Primer Laboratorio Señales y Sistemas");
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            ImageIcon icon = new ImageIcon("C:/Users/kevin/Downloads/imgs/logo.png");
            frame.setIconImage(icon.getImage());
        }else{
            JOptionPane.showMessageDialog(null, "Los límites no encajan con el tamaño de la señal.");
            txtLowerLimit.setText("1");
            txtUpperLimit.setText("14");
        }

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
        boolean pass = true;
        String signal = (String) comboBoxSignal.getSelectedItem();
        double[] limits = new double[2];
        limits[0] = Double.parseDouble(txtLowerLimit.getText());
        limits[1] = Double.parseDouble(txtUpperLimit.getText());
        double[] mn0 = new double[2];
        String mstr = (String) comboBoxAmp.getSelectedItem();
        switch (mstr){
            case "1/2" -> mn0[0] = 1.0/2;
            case "1/3" -> mn0[0] = 1.0/3;
            case "1/4" -> mn0[0] = 1.0/4;
            case "1/5" -> mn0[0] = 1.0/5;
            default -> mn0[0] = Double.parseDouble(mstr);
        }
        if (btnPMamp.getText().equals("-")){
            mn0[0] *= -1;
        }
        String delaystr = (String) comboBoxDelay.getSelectedItem();
        mn0[1] = Double.parseDouble(delaystr);
        if (btnPMdelay.getText().equals("-")){
            mn0[1] *= -1;
        }
        String method = btnMethod.getText();
        String interpol = (String) comboBoxInterpol.getSelectedItem();
        
        if (signal.equals("Señal 4") && limits[1]-limits[0]+1 != 14){
            pass = false;
        }
        if (pass){
            dispose();
            DiscreteTransformed frame = new DiscreteTransformed(signal, limits, interpol, mn0, method);
            frame.setTitle("Primer Laboratorio Señales y Sistemas");
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            ImageIcon icon = new ImageIcon("C:/Users/kevin/Downloads/imgs/logo.png");
            frame.setIconImage(icon.getImage());
        }else{
            JOptionPane.showMessageDialog(null, "Los límites no encajan con el tamaño de la señal.");
            txtLowerLimit.setText("1");
            txtUpperLimit.setText("14");
        }
        
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
    private javax.swing.JComboBox<String> comboBoxInterpol;
    private javax.swing.JComboBox<String> comboBoxSignal;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblAmp;
    private javax.swing.JLabel lblDelay;
    private javax.swing.JLabel lblEquation;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblInterpol;
    private javax.swing.JLabel lblLowerLimit;
    private javax.swing.JLabel lblSignal;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUpperLimit;
    private javax.swing.JTextField txtLowerLimit;
    private javax.swing.JTextField txtUpperLimit;
    // End of variables declaration//GEN-END:variables

}
