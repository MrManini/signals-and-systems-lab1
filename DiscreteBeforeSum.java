package tello;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYDataset;

/**
 *
 * @author kevin
 */
public class DiscreteBeforeSum extends javax.swing.JFrame {

    private static String type;
    private static double[] limits;
    private static String interpol;
    
    public DiscreteBeforeSum(String type, double[] limits, String interpol) {
        initComponents();
        DiscreteBeforeSum.type = type;
        DiscreteBeforeSum.limits = limits;
        DiscreteBeforeSum.interpol = interpol;
        
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
        XYPlot[] subplots = new XYPlot[3];
        CombinedDomainXYPlot combinedPlot = new CombinedDomainXYPlot();
        
        Color[] lineColors = new Color[3];
        switch (type){
            case "Señal 4" ->{
                lineColors[0] = Color.decode("#FF00FF");
                lineColors[1] = Color.decode("#9908FF");
                lineColors[2] = Color.decode("#FF0074");
            }
            case "Señal 5" ->{
                lineColors[0] = Color.decode("#1d47ab");
                lineColors[1] = Color.decode("#1d9aab");
                lineColors[2] = Color.decode("#1dab85");
            }
        }
        
        
        for (int i = 0; i < 3; i++) {
            // Create the scatter plot for each subplot
            JFreeChart chart = ChartFactory.createScatterPlot(
                    "Subplot " + (i + 1),
                    "n",
                    "x",
                    datasets[i],
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            // Customize the plot and renderer for scatter points (optional)
            XYPlot plot = (XYPlot) chart.getPlot();
            XYItemRenderer renderer = plot.getRenderer();

            // Customize the renderer for scatter points (e.g., change the shape and color of points)
            Shape pointShape = new Ellipse2D.Double(-3, -3, 6, 6); // Example shape
            renderer.setSeriesShape(0, pointShape);
            renderer.setSeriesPaint(0, lineColors[i]);

            // Add the subplot to the CombinedDomainXYPlot
            subplots[i] = plot;
            combinedPlot.add(subplots[i]);
            
            plot.setBackgroundPaint(Color.white);
            plot.setDomainGridlinesVisible(true);
            plot.setRangeGridlinesVisible(true);
            plot.setDomainGridlinePaint(Color.lightGray);
            plot.setRangeGridlinePaint(Color.lightGray);

            
            DefaultXYDataset lineDataset = createLineDataset(datasets[i]);
            XYItemRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
            lineRenderer.setSeriesPaint(0, lineColors[i]);
            subplots[i].setDataset(1, lineDataset);
            subplots[i].setRenderer(1, lineRenderer);
            lineRenderer.setSeriesVisibleInLegend(0, false);
            
        }
        
        String title = "Transformaciones de x[n]: x[3 - n/3] y x[n/4 - 4]";
        
        //Full plot
        JFreeChart finalChart = new JFreeChart(
                title,
                JFreeChart.DEFAULT_TITLE_FONT,
                combinedPlot,
                true
        );
        
        Color pink = Color.decode("#fceaf1");
        finalChart.setBackgroundPaint(pink);
        finalChart.getLegend().setBackgroundPaint(pink);

        return finalChart;
    }
    
    private static XYSeriesCollection[] allDatasets(){
        XYSeriesCollection dataset1 = originalDataset();
        XYSeriesCollection dataset2 = finalDataset(3, -1.0/3, "x[3 - n/3]");
        XYSeriesCollection dataset3 = finalDataset(-4, 1.0/4, "x[n/4 - 4]");
        
        XYSeriesCollection[] datasets = {dataset1, dataset2, dataset3};
        
        return datasets;
    }
    private static XYSeriesCollection originalDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries(type);
        double[] signal4Array = {-6,0,1,2,6,5,1,0,4,7,3,-2,3,6};
        // Generate data points for the function
        double[] t = arrayer(limits[0],limits[1],1);
        for (int i = 0; i < t.length; i++) {
            double x;
            switch (type) {
                case "Señal 4" -> x = signal4Array[i];
                case "Señal 5" -> {
                    if (t[i] <= -4){
                        x = -2;
                    }else if (t[i] <= 2){
                        x = 2*t[i];
                    }else{
                        x = 9.0/t[i];
                    }
                }
                default -> x = 0;
            }
            series.add(t[i], x);
        }   
        dataset.addSeries(series);
        return dataset;
    }  
    private static XYSeriesCollection methodDataset(XYSeriesCollection originalData, 
            String method, String interpol, double delay, double amp){
        XYSeries originalSeries = originalData.getSeries(0);
        XYSeriesCollection dataset = new XYSeriesCollection();
        if (method.equals("Método 1")){
            //Primero desplazar, luego escalar
            XYSeries series = new XYSeries(type+" desplazada");
            for (int i = 0; i < originalSeries.getItemCount(); i++) {
                double originalN = originalSeries.getX(i).doubleValue();
                double newN = originalN - delay;
                double originalX = originalSeries.getY(i).doubleValue();
                series.add(newN, originalX);
            }
            dataset.addSeries(series);
        }else{
            //Primero escalar, luego desplazar
            XYSeries series = new XYSeries(type+" escalada");
            if (Math.abs(amp) > 1){
                //Diezmación
                for (int i = 0; i < originalSeries.getItemCount(); i++) {
                    double originalN = originalSeries.getX(i).doubleValue();
                    if (originalN % amp == 0){
                        double newN = originalN / amp;
                        double originalX = originalSeries.getY(i).doubleValue();
                        series.add(newN, originalX);
                    }
                }
            }else{
                //Interpolación
                int count = originalSeries.getItemCount();
                switch (interpol){                   
                    case "Ceros" -> {
                        for (int i = 0; i < count - 1; i++) {
                            double originalN = originalSeries.getX(i).doubleValue();
                            double newN = originalN / amp;
                            double originalX = originalSeries.getY(i).doubleValue();
                            series.add(newN, originalX);
                            int k = 1;
                            if (amp < 0) k = -1;
                            for (int j = 1; j < 1/Math.abs(amp); j++){
                                series.add(newN+j*k,0);
                            }
                        }
                        double originalN = originalSeries.getX(count-1).doubleValue();
                        double newN = originalN / amp;
                        double originalX = originalSeries.getY(count-1).doubleValue();
                        series.add(newN, originalX);
                    }
                    case "Escalón" -> {
                        for (int i = 0; i < count; i++) {
                            double originalN = originalSeries.getX(i).doubleValue();
                            double newN = originalN / amp;
                            double originalX = originalSeries.getY(i).doubleValue();
                            series.add(newN, originalX);
                            int k = 1;
                            if (amp < 0) k = -1;
                            for (int j = 1; j < 1/Math.abs(amp); j++){
                                series.add(newN+j*k,originalX);
                            }
                        }
                        /*double originalN = originalSeries.getX(count-1).doubleValue();
                        double newN = originalN / amp;
                        double originalX = originalSeries.getY(count-1).doubleValue();
                        series.add(newN, originalX);*/
                    }
                    case "Lineal" -> {
                        for (int i = 0; i < count - 1; i++) {
                            double originalN = originalSeries.getX(i).doubleValue();
                            double newN = originalN / amp;
                            double originalX = originalSeries.getY(i).doubleValue();
                            series.add(newN, originalX);
                            double nextN = originalSeries.getX(i+1).doubleValue();
                            double newNextN = nextN/ amp;
                            double nextX = originalSeries.getY(i+1).doubleValue();
                            int size = (int) Math.abs(1/amp);
                            double[][] values = linearInterpolation(newN, originalX, newNextN, nextX, size);
                            for (int j = 0; j < size-1; j++){
                                series.add(values[0][j], values[1][j]);
                            }
                        }
                        double originalN = originalSeries.getX(count-1).doubleValue();
                        double newN = originalN / amp;
                        double originalX = originalSeries.getY(count-1).doubleValue();
                        series.add(newN, originalX);
                    }
                }
            }
            dataset.addSeries(series); 
        }
        return dataset;
    }
    private static XYSeriesCollection finalDataset(double delay, double amp, String title){
        XYSeriesCollection delayedData = methodDataset(originalDataset(),"Método 1","", delay, amp);
        XYSeriesCollection finalData = methodDataset(delayedData, "Método 2", interpol, delay, amp);
        XYSeries series = finalData.getSeries(0);
        series.setKey(title);
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        
        
        return dataset;
    }
    
    
    private DefaultXYDataset createLineDataset(XYSeriesCollection scatterDataset) {
        DefaultXYDataset lineDataset = new DefaultXYDataset();

        // Create lines connecting data points to the x-axis
        for (int i = 0; i < scatterDataset.getSeriesCount(); i++) {
            XYSeries scatterSeries = scatterDataset.getSeries(i);
            double[][] lineData = new double[2][scatterSeries.getItemCount() * 3];

            for (int j = 0; j < scatterSeries.getItemCount(); j++) {
                double x = scatterSeries.getX(j).doubleValue();
                double y = scatterSeries.getY(j).doubleValue();

                // Add the data point
                lineData[0][j * 3] = x;
                lineData[1][j * 3] = 0;

                // Connect to the x-axis before the data point
                lineData[0][j * 3 + 1] = x;
                lineData[1][j * 3 + 1] = y;

                // Connect to the x-axis after the data point
                lineData[0][j * 3 + 2] = x;
                lineData[1][j * 3 + 2] = 0;
            }

            lineDataset.addSeries("Lines", lineData);
        }

        return lineDataset;
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
    private static double[][] linearInterpolation(double x1, double y1, double x2, double y2, double N){
        double[][] xyValues = new double[2][(int) N-1];
        for (int i = 1; i < N; i++) {
            double x, y;
            if (x1 < x2){
                x = x1 + i;
                y = y1 + (x - x1) * ((y2 - y1) / (x2 - x1));
            }else{
                x = x2 + i;
                y = y2 + (x - x2) * ((y2 - y1) / (x2 - x1));
            }
            xyValues[0][i-1] = x;
            xyValues[1][i-1] = y;
        }
        return xyValues;
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
        frame.setResizable(false);
    }//GEN-LAST:event_btnBackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel1;
    private javax.swing.JButton btnBack;
    // End of variables declaration//GEN-END:variables
}
