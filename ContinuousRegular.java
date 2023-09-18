package tello;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import java.awt.*;
import javax.swing.ImageIcon;



/**
 *
 * @author kevin
 */
public class ContinuousRegular extends javax.swing.JFrame {

    private static String type;
    private static double[] limits;
    private static double[]  abc;
    
    public ContinuousRegular(String type, double[] limits, double[] abc) {
        initComponents();
        ContinuousRegular.type = type;
        ContinuousRegular.limits = limits;
        ContinuousRegular.abc = abc;
        
        JFreeChart chart = showPlot();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        JPanel1.setPreferredSize(new Dimension(800, 600));
        JPanel1.add(chartPanel);
        JPanel1.repaint();
        pack();  
    }
    
    private JFreeChart showPlot(){
        // Create the JFreeChart
        JFreeChart chart = ChartFactory.createXYLineChart(
                type.toUpperCase(),  // chart title
                "t",            // x-axis label
                "x",            // y-axis label
                createFunctionDataset()
        );
        
        Color pink = Color.decode("#fceaf1");
        Color lineColor;
        switch (type){
            case "Señal 1" -> lineColor = Color.MAGENTA;
            case "Señal 2" -> lineColor = Color.decode("#7F00FF");
            case "Señal 3" -> lineColor = Color.decode("#0099FF");
            default -> lineColor = Color.MAGENTA;
        }
        
        chart.setBackgroundPaint(pink);
        chart.getLegend().setBackgroundPaint(pink);
        
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);
        
        
        renderer.setSeriesPaint(0, lineColor);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesStroke(0, new BasicStroke(3f));
        
        plot.setRenderer(renderer);

        return chart;   
    }
    
    
    private static XYSeriesCollection createFunctionDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries(type);
        // Generate data points for the function
        // abc = {a, b, c} a = abc[0]
        double[] t = arrayer(limits[0],limits[1],0.01);
        for (int i = 0; i < t.length; i++) {
            double x;
            switch (type) {
                case "Señal 1" -> x = abc[0]*t[i]*t[i] + abc[1]*t[i] + abc[2];
                case "Señal 2" -> {
                    if (t[i] <= 0){
                        x = t[i] + 1;
                    }else{
                        x = -t[i] + 1;
                    }
                }
                case "Señal 3" -> x = Math.exp(-Math.abs(t[i]));
                default -> x = 0;
            }
            series.add(t[i], x);
        }   
        dataset.addSeries(series);
        return dataset;
    }   
    
    private static double[] arrayer(double start, double end, double step ){
        //t = 0:Delta:100
        int size = (int) ((end - start)/step + 1);
        double[] array = new double[size];
        for (int i=0; i<size; i++){
            array[i] = start + step*i;
        }
        return array;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanel1 = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBack.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        btnBack.setText("Volver");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBack)
                    .addComponent(JPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        dispose();
        TransformContinuous frame = new TransformContinuous();
        frame.setTitle("Primer Laboratorio Señales y Sistemas");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon("C:/Users/kevin/Downloads/imgs/logo.png");
        frame.setIconImage(icon.getImage());
    }//GEN-LAST:event_btnBackActionPerformed

    //public static void main(String args[]) {
        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(new Runnable() {
            //public void run() {
                //new ContinuousRegular(type, limits, abc).setVisible(true);
            //}
        //});
    //}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel1;
    private javax.swing.JButton btnBack;
    // End of variables declaration//GEN-END:variables
}
