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
import org.jfree.chart.renderer.xy.XYSplineRenderer;

/**
 *
 * @author kevin
 */
public class ContinuousTransformed extends javax.swing.JFrame {

    private static String type;
    private static double[] limits;
    private static double[] abc;
    private static double[] at0;
    private static String method;
    
    public ContinuousTransformed(String type, double[] limits, double[] abc, double[] at0, String method) {
        initComponents();
        ContinuousTransformed.type = type;
        ContinuousTransformed.limits = limits;
        ContinuousTransformed.abc = abc;
        ContinuousTransformed.at0 = at0;
        ContinuousTransformed.method = method;
        
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
                type.toUpperCase(),  // chart title
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
        switch (type){
            case "Señal 1" ->{
                lineColors[0] = Color.decode("#FF00FF");
                lineColors[1] = Color.decode("#9908FF");
                lineColors[2] = Color.decode("#FF0074");
            }
            case "Señal 2" ->{
                lineColors[0] = Color.decode("#7F00FF");
                lineColors[1] = Color.decode("#9F10C0");
                lineColors[2] = Color.decode("#BF2080");
            }
            case "Señal 3" ->{
                lineColors[0] = Color.decode("#0099FF");
                lineColors[1] = Color.decode("#00BBFF");
                lineColors[2] = Color.decode("#00CC88");
            }
        }
        

        
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

        int mnValueAsInt = (int) (Math.abs(at0[0])* 100); // Convert to an integer (e.g., 0.5 -> 50)
        String ampName;

        ampName = switch (mnValueAsInt) {
            case 50 -> "1/2";
            case 33 -> "1/3";
            case 25 -> "1/4";
            case 20 -> "1/5";
            default -> String.valueOf(at0[0]);
        };
        if (at0[0] < 0) ampName = "-" + ampName; 
        
        String delayName = String.valueOf(Math.abs(at0[1]));
        String delaySign = "+";
        if (at0[1] < 0) delaySign = "-";
        String title = "Transformación de "+type+": x("+ampName+"t "+delaySign+" "+delayName+")";
        
        
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
        XYSeriesCollection dataset2 = methodDataset(method);
        XYSeriesCollection dataset3 = finalDataset();
        
        XYSeriesCollection[] datasets = {dataset1, dataset2, dataset3};
        
        return datasets;
    }
    private static XYSeriesCollection originalDataset() {
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
    private static XYSeriesCollection methodDataset(String method){
        XYSeriesCollection originalData = originalDataset();
        XYSeries originalSeries = originalData.getSeries(0);
        XYSeriesCollection dataset = new XYSeriesCollection();
        if (method.equals("Método 1")){
            //Primero desplazar, luego escalar
            XYSeries series = new XYSeries(type+" desplazada");
            for (int i = 0; i < originalSeries.getItemCount(); i++) {
                double originalT = originalSeries.getX(i).doubleValue();
                double newT = originalT - at0[1];
                double originalX = originalSeries.getY(i).doubleValue();
                series.add(newT, originalX);
            }
            dataset.addSeries(series);
        }else{
            //Primero escalar, luego desplazar
            XYSeries series = new XYSeries(type+" escalada");
            for (int i = 0; i < originalSeries.getItemCount(); i++) {
                double originalT = originalSeries.getX(i).doubleValue();
                double newT = originalT / at0[0];
                double originalX = originalSeries.getY(i).doubleValue();
                series.add(newT, originalX);
            }
            dataset.addSeries(series); 
        }
        return dataset;
    }
    private static XYSeriesCollection finalDataset(){
        XYSeriesCollection originalData = originalDataset();
        XYSeries originalSeries = originalData.getSeries(0);
        XYSeriesCollection dataset = new XYSeriesCollection();
            XYSeries series = new XYSeries(type+" transformada");
        for (int i = 0; i < originalSeries.getItemCount(); i++) {
            double originalT = originalSeries.getX(i).doubleValue();
            double newT = (originalT - at0[1])/at0[0];
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
        TransformContinuous frame = new TransformContinuous();
        frame.setTitle("Primer Laboratorio Señales y Sistemas");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon("C:/Users/kevin/Downloads/imgs/logo.png");
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel1;
    private javax.swing.JButton btnBack;
    // End of variables declaration//GEN-END:variables
}
