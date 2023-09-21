package tello;

import java.awt.event.ItemEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class SignalSum extends javax.swing.JFrame {

    public SignalSum() {
        initComponents();
        ComboBoxChange();
        appearSignalParameters(false, 0, 0);
        EverythingElse(false);
    }

    public void appearSignalParameters(boolean signal4, int limit1, int limit2){
        txtLowerLimit.setEditable(signal4);
        txtUpperLimit.setEditable(signal4);
        txtLowerLimit.setText(Integer.toString(limit1));
        txtUpperLimit.setText(Integer.toString(limit2));
    }
    public void EverythingElse(boolean signal){
        btnTransform.setEnabled(signal);
        lblEquation.setVisible(signal);
        btnAdd.setVisible(signal);
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
                        appearSignalParameters(false, 0, 0);
                        EverythingElse(false);
                    }
                    case "Señal 2" ->{                    
                        appearSignalParameters(false, -1, 1);
                        ImageIcon image = new ImageIcon("C:/Users/kevin/Downloads/imgs/triangle.png");
                        lblImage.setIcon(image);
                        ImageIcon equation = new ImageIcon("C:/Users/kevin/Downloads/imgs/sumContinuous.png");
                        lblEquation.setIcon(equation);
                        EverythingElse(true);
                        lblInterpol.setVisible(false);
                        comboBoxInterpol.setVisible(false);
                    }
                    case "Señal 4" ->{
                        appearSignalParameters(true, 0, 13);
                        ImageIcon image = new ImageIcon("C:/Users/kevin/Downloads/imgs/discsig1.png");
                        lblImage.setIcon(image);
                        ImageIcon equation = new ImageIcon("C:/Users/kevin/Downloads/imgs/sumDiscrete.png");
                        lblEquation.setIcon(equation);
                        EverythingElse(true);
                        lblInterpol.setVisible(true);
                        comboBoxInterpol.setVisible(true);
                    }
                    case "Señal 5" ->{
                        appearSignalParameters(false, -8, 8);
                        ImageIcon image = new ImageIcon("C:/Users/kevin/Downloads/imgs/discsig2flip.png");
                        lblImage.setIcon(image);
                        ImageIcon equation = new ImageIcon("C:/Users/kevin/Downloads/imgs/sumDiscrete.png");
                        lblEquation.setIcon(equation);
                        EverythingElse(true);
                        lblInterpol.setVisible(true);
                        comboBoxInterpol.setVisible(true);
                    }
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
        btnTransform = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblInterpol = new javax.swing.JLabel();
        comboBoxInterpol = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setFont(new java.awt.Font("Harlow Solid Italic", 0, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 51, 204));
        lblTitle.setText("Suma de Señales");

        comboBoxSignal.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        comboBoxSignal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguna", "Señal 2", "Señal 4", "Señal 5" }));

        lblSignal.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblSignal.setText("Elige tu señal:");

        lblLowerLimit.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblLowerLimit.setText("Límite inferior:");

        txtLowerLimit.setEditable(false);
        txtLowerLimit.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N

        lblUpperLimit.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblUpperLimit.setText("Límite superior:");

        txtUpperLimit.setEditable(false);
        txtUpperLimit.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N

        btnTransform.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        btnTransform.setText("Transformar");
        btnTransform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransformActionPerformed(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        btnAdd.setText("Sumar");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        btnBack.setText("Volver");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblInterpol.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        lblInterpol.setText("Interpolación:");

        comboBoxInterpol.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        comboBoxInterpol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ceros", "Escalón", "Lineal" }));
        comboBoxInterpol.setFocusCycleRoot(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(btnBack))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblUpperLimit)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtUpperLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblLowerLimit)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtLowerLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblSignal)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboBoxSignal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEquation, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(btnTransform))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(lblInterpol)
                                .addGap(18, 18, 18)
                                .addComponent(comboBoxInterpol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(btnAdd)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSignal)
                            .addComponent(comboBoxSignal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLowerLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLowerLimit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUpperLimit)
                            .addComponent(txtUpperLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTransform)
                        .addGap(18, 18, 18)
                        .addComponent(lblEquation, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblInterpol)
                            .addComponent(comboBoxInterpol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdd)
                        .addGap(36, 36, 36)))
                .addComponent(jLabel2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        dispose();
        Main frame = new Main();
        frame.setTitle("Primer Laboratorio Señales y Sistemas");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon("C:/Users/kevin/Downloads/imgs/logo.png");
        frame.setIconImage(icon.getImage());
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnTransformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransformActionPerformed
        String signal = (String) comboBoxSignal.getSelectedItem();
        double[] limits = new double[2];
        limits[0] = Double.parseDouble(txtLowerLimit.getText());
        limits[1] = Double.parseDouble(txtUpperLimit.getText());
        String interpol = (String) comboBoxInterpol.getSelectedItem(); 
        if (signal.equals("Señal 2")){
            dispose();
            ContinuousBeforeSum frame = new ContinuousBeforeSum();
            frame.setTitle("Primer Laboratorio Señales y Sistemas");
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            ImageIcon icon = new ImageIcon("C:/Users/kevin/Downloads/imgs/logo.png");
            frame.setIconImage(icon.getImage());
            frame.setResizable(false);
        }else if (signal.equals("Señal 4") && limits[1]-limits[0]+1 != 14){
            JOptionPane.showMessageDialog(null, 
                    "Los límites no encajan con el tamaño de la señal.",
                    "Error: límites erróneos",
                    JOptionPane.ERROR_MESSAGE
                );
            txtLowerLimit.setText("0");
            txtUpperLimit.setText("13");
        }else{
            dispose();
            DiscreteBeforeSum frame = new DiscreteBeforeSum(signal, limits, interpol);
            frame.setTitle("Primer Laboratorio Señales y Sistemas");
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            ImageIcon icon = new ImageIcon("C:/Users/kevin/Downloads/imgs/logo.png");
            frame.setIconImage(icon.getImage());
            frame.setResizable(false);
        }
    }//GEN-LAST:event_btnTransformActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String signal = (String) comboBoxSignal.getSelectedItem();
        double[] limits = new double[2];
        limits[0] = Double.parseDouble(txtLowerLimit.getText());
        limits[1] = Double.parseDouble(txtUpperLimit.getText());
        String interpol = (String) comboBoxInterpol.getSelectedItem(); 
        if (signal.equals("Señal 2")){
            dispose();
            ContinuousAfterSum frame = new ContinuousAfterSum();
            frame.setTitle("Primer Laboratorio Señales y Sistemas");
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            ImageIcon icon = new ImageIcon("C:/Users/kevin/Downloads/imgs/logo.png");
            frame.setIconImage(icon.getImage());
            frame.setResizable(false);
        }else if (signal.equals("Señal 4") && limits[1]-limits[0]+1 != 14){
            JOptionPane.showMessageDialog(null, 
                    "Los límites no encajan con el tamaño de la señal.",
                    "Error: límites erróneos",
                    JOptionPane.ERROR_MESSAGE
                );
            txtLowerLimit.setText("0");
            txtUpperLimit.setText("13");
        }else{
            dispose();
            DiscreteAfterSum frame = new DiscreteAfterSum(signal, limits, interpol);
            frame.setTitle("Primer Laboratorio Señales y Sistemas");
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            ImageIcon icon = new ImageIcon("C:/Users/kevin/Downloads/imgs/logo.png");
            frame.setIconImage(icon.getImage());
            frame.setResizable(false);
        }
    }//GEN-LAST:event_btnAddActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnTransform;
    private javax.swing.JComboBox<String> comboBoxInterpol;
    private javax.swing.JComboBox<String> comboBoxSignal;
    private javax.swing.JLabel jLabel2;
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
