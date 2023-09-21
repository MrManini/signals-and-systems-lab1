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
import java.awt.geom.Point2D;
import java.util.ArrayList;
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
public class DiscreteAfterSum extends javax.swing.JFrame {

    private static String type;
    private static double[] limits;
    private static String interpol;
    
    public DiscreteAfterSum(String type, double[] limits, String interpol) {
        initComponents();
        DiscreteAfterSum.type = type;
        DiscreteAfterSum.limits = limits;
        DiscreteAfterSum.interpol = interpol;
        
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
        XYSeriesCollection totalDataset = sumOfDatasets();
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Señal sumada completa x[3 - n/3] + x[n/4 - 4]",  // chart title
                "n",            // x-axis label
                "x",            // y-axis label
                totalDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        
        Color pink = Color.decode("#fceaf1");
        
        chart.setBackgroundPaint(pink);
        chart.getLegend().setBackgroundPaint(pink);
        
        XYPlot plot = (XYPlot) chart.getPlot();
        XYItemRenderer renderer = plot.getRenderer();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);
        
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
        
        Point2D.Float point1 = new Point2D.Float(0, 0);
        Point2D.Float point2 = new Point2D.Float(600, 0);
        float[] fractions = {0f, 0.5f, 1f};
        Color[] colors = lineColors;
        MultipleGradientPaint gradientPaint = new LinearGradientPaint(point1, point2, fractions, colors);

        // Create a custom stroke with a gradient paint and make the line thicker
        Stroke customStroke = new BasicStroke(
                4.0f, // Adjust the line thickness as needed
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND
        );
        
        Shape pointShape = new Ellipse2D.Double(-4, -4, 8, 8); // Example shape
        renderer.setSeriesShape(0, pointShape);
        
        // Create a gradient paint for the points with multiple color stops
        LinearGradientPaint gradientDotPaint = new LinearGradientPaint(point1, point2, fractions, colors);

        // Set the gradient paint as the fill paint for the renderer
        renderer.setSeriesPaint(0, gradientDotPaint);
        
        plot.setRenderer(0,renderer);  
        
        // Create a dataset for lines connecting the data points to the x-axis
        DefaultXYDataset lineDataset = createLineDataset(totalDataset);

        // Create a renderer for the lines
        XYItemRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
        lineRenderer.setSeriesPaint(0, gradientPaint); // Set the gradient paint as the line color
        lineRenderer.setSeriesStroke(0, customStroke);  // Set the custom stroke
        lineRenderer.setSeriesVisibleInLegend(0, false);
        
        plot.setDataset(1, lineDataset); // Add the line dataset to the plot
        plot.setRenderer(1, lineRenderer); // Set the line renderer for the new dataset
        
        return chart;   
    }
    
    private static XYSeriesCollection sumOfDatasets(){
        XYSeriesCollection dataset1 = finalDataset(3, -1.0/3, "x[3 - n/3]");
        XYSeriesCollection dataset2 = finalDataset(-4, 1.0/4, "x[n/4 - 4]");
        XYSeriesCollection totalDataset = new XYSeriesCollection();
        
        XYSeries series1 = dataset1.getSeries(0);
        XYSeries series2 = dataset2.getSeries(0);
        XYSeries totalSeries = new XYSeries("Suma de las señales");
        
        ArrayList nValues = new ArrayList<Double>();
        double lowestNumber = Double.MAX_VALUE;
        double highestNumber = Double.MIN_VALUE;
        for (int i = 0; i < series1.getItemCount(); i++){
            double n1 = series1.getX(i).doubleValue();
            double x1 = series1.getY(i).doubleValue();
            double x2 = findYValueForX(series2, n1);
            if (!Double.isNaN(x2)){
                totalSeries.add(n1, x1+x2);
            }else{
                totalSeries.add(n1, x1);
            }
            nValues.add(n1);
            if (n1 < lowestNumber) lowestNumber = n1;
            if (n1 > highestNumber) highestNumber = n1;
        }
        
        for (int i = 0; i < series2.getItemCount(); i++){
            double n2 = series2.getX(i).doubleValue();
            if (!nValues.contains(n2)){
                double x2 = series2.getY(i).doubleValue();
                double x1 = findYValueForX(series1, n2);
                if (!Double.isNaN(x1)){
                    totalSeries.add(n2, x1+x2);
                }else{
                    totalSeries.add(n2, x2);
                }
                nValues.add(n2);
                if (n2 < lowestNumber) lowestNumber = n2;
                if (n2 > highestNumber) highestNumber = n2;
            }
        }
        
        double[] array = arrayer(lowestNumber, highestNumber, 1);
        for (int i = 0; i < array.length; i++){
            if (!nValues.contains(array[i])){
                totalSeries.add(array[i],0);
            }
        }
        
        
        totalDataset.addSeries(totalSeries);
        return totalDataset;
    }
    
    private static double findYValueForX(XYSeries series, double x) {
        for (int itemIndex = 0; itemIndex < series.getItemCount(); itemIndex++) {
            double xValue = series.getX(itemIndex).doubleValue();
            if (x == xValue) {
                return series.getY(itemIndex).doubleValue();
            }
        }
        return Double.NaN; // Return NaN if the x-value is not found in the series
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
