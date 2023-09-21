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
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author kevin
 */
public class ContinuousBeforeSum extends javax.swing.JFrame {

    
    public ContinuousBeforeSum() {
        initComponents();
        
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
        XYSeriesCollection[] datasets = allDatasets();
        JFreeChart chart = ChartFactory.createXYLineChart(
                "",  // chart title
                "t",            // x-axis label
                "x",            // y-axis label
                datasets[0],
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        
        CombinedDomainXYPlot combinedPlot = new CombinedDomainXYPlot();
        Color[] lineColors = new Color[3];
        lineColors[0] = Color.decode("#7F00FF");
        lineColors[1] = Color.decode("#9F10C0");
        lineColors[2] = Color.decode("#BF2080");
        
        for (int i = 0; i < datasets.length; i++) {
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
            renderer.setSeriesPaint(0, lineColors[i]);
            renderer.setSeriesStroke(0, new BasicStroke(3f));
            XYPlot subplot = new XYPlot(datasets[i], new org.jfree.chart.axis.NumberAxis("n"),
                    new org.jfree.chart.axis.NumberAxis("x"), renderer);
            combinedPlot.add(subplot);
        }
        // Set the domain and range axes of the main plot to invisible
        chart.getXYPlot().getDomainAxis().setVisible(false);
        chart.getXYPlot().getRangeAxis().setVisible(false);

        String title = "Transformaciones de x(t): x(3-t/3) y x(t/2-2)";
        
        
        //Full plot
        chart.getXYPlot().setParent(combinedPlot);
        chart = new JFreeChart(
                title,
                JFreeChart.DEFAULT_TITLE_FONT,
                combinedPlot,
                true
        );
        
        Color pink = Color.decode("#fceaf1");
        
        chart.setBackgroundPaint(pink);
        chart.getLegend().setBackgroundPaint(pink);
        
        XYPlot plot = (XYPlot) chart.getPlot();
        
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);

        return chart;
    }
    private static XYSeriesCollection[] allDatasets(){
        XYSeriesCollection dataset1 = originalDataset();
        XYSeriesCollection dataset2 = transformedDataset(-1.0/3, 3, "x(3-t/3)");
        XYSeriesCollection dataset3 = transformedDataset(1.0/2, -2, "x(t/2-2)");
        
        XYSeriesCollection[] datasets = {dataset1, dataset2, dataset3};
        
        return datasets;
    }
    private static XYSeriesCollection originalDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("x(t)");
        // Generate data points for the function
        double[] t = arrayer(-1,1,0.01);
        for (int i = 0; i < t.length; i++) {
            double x;
                if (t[i] <= 0){
                    x = t[i] + 1;
                }else{
                    x = -t[i] + 1;
                }
            series.add(t[i], x);
        }   
        dataset.addSeries(series);
        return dataset;
    }
    private static XYSeriesCollection transformedDataset(double amp, double delay, String title){
        XYSeriesCollection originalData = originalDataset();
        XYSeries originalSeries = originalData.getSeries(0);
        XYSeriesCollection dataset = new XYSeriesCollection();
            XYSeries series = new XYSeries(title);
        for (int i = 0; i < originalSeries.getItemCount(); i++) {
            double originalT = originalSeries.getX(i).doubleValue();
            double newT = (originalT - delay)/amp;
            double originalX = originalSeries.getY(i).doubleValue();
            series.add(newT, originalX);
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
        SignalSum frame = new SignalSum();
        frame.setTitle("Primer Laboratorio Señales y Sistemas");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon("C:/Users/kevin/Downloads/imgs/logo.png");
        frame.setIconImage(icon.getImage());
    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel1;
    private javax.swing.JButton btnBack;
    // End of variables declaration//GEN-END:variables
}
