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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYDataset;



/**
 *
 * @author kevin
 */
public class DiscreteRegular extends javax.swing.JFrame {

    private static String type;
    private static double[] limits;
    
    public DiscreteRegular(String type, double[] limits) {
        initComponents();
        DiscreteRegular.type = type;
        DiscreteRegular.limits = limits;
        
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
        XYSeriesCollection dataset = createFunctionDataset();
        JFreeChart chart = ChartFactory.createScatterPlot(
                type.toUpperCase(),  // chart title
                "n",            // x-axis label
                "x",            // y-axis label
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        
        Color pink = Color.decode("#fceaf1");
        Color lineColor;
        switch (type){
            case "Señal 4": lineColor = Color.MAGENTA; break;
            case "Señal 5": lineColor = Color.decode("#7F00FF"); break;
            case "Señal 6": lineColor = Color.decode("#0099FF"); break;
            default: lineColor = Color.MAGENTA; break;
        }
        
        // Customize the plot and renderer for scatter points (optional)
        XYPlot plot = (XYPlot) chart.getPlot();
        XYItemRenderer renderer = plot.getRenderer();

        // Customize the renderer for scatter points (e.g., change the shape and color of points)
        Shape pointShape = new Ellipse2D.Double(-4, -4, 8, 8); // Example shape
        renderer.setSeriesShape(0, pointShape);
        renderer.setSeriesPaint(0, lineColor);
        
        // Create a dataset for lines connecting the data points to the x-axis
        DefaultXYDataset lineDataset = createLineDataset(dataset);

        // Create a renderer for the lines
        chart.setBackgroundPaint(pink);
        chart.getLegend().setBackgroundPaint(pink);
        XYItemRenderer lineRenderer = new XYLineAndShapeRenderer(true, false);
        lineRenderer.setSeriesPaint(0, lineColor); // Line color
        lineRenderer.setSeriesVisibleInLegend(0, false);
        lineRenderer.setSeriesStroke(0, new BasicStroke(2f));

        plot.setDataset(1, lineDataset); // Add the line dataset to the plot
        plot.setRenderer(1, lineRenderer); // Set the line renderer for the new dataset

        // Set margin for the plot
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);
        
        if (type.equals("Señal 4")){
            NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
            domainAxis.setAutoRangeIncludesZero(false); // Disable automatic zero inclusion
            domainAxis.setRange(domainAxis.getLowerBound()-0.5, domainAxis.getUpperBound());
        }
        
        if (type.equals("Señal 6")){
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setAutoRangeIncludesZero(false); // Disable automatic zero inclusion
            rangeAxis.setRange(rangeAxis.getLowerBound()-0.5, rangeAxis.getUpperBound());
        }
        
        return chart;   
    }
    
    private static XYSeriesCollection createFunctionDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries(type);
        double[] signal4Array = {-6,0,1,2,6,5,1,0,4,7,3,-2,3,6};
        // Generate data points for the function
        double[] n = arrayer(limits[0],limits[1],1);
        for (int i = 0; i < n.length; i++) {
            double x;
            switch (type) {
                case "Señal 4": x = signal4Array[i]; break;
                case "Señal 5":
                    if (n[i] <= -4){
                        x = -2;
                    }else if (n[i] <= 2){
                        x = 2*n[i];
                    }else{
                        x = 9.0/n[i];
                    }
                    break;
                case "Señal 6":
                    if (n[i] <= 0){
                        x = Math.pow(2.0,n[i]);
                    }else{
                        x = Math.pow(1.5,n[i]);
                    }
                    break;
                default: x = 0; break;
            }
            series.add(n[i], x);
        }   
        dataset.addSeries(series);
        return dataset;
    }   
    private DefaultXYDataset createLineDataset(XYSeriesCollection pointsDataset) {
        DefaultXYDataset lineDataset = new DefaultXYDataset();
        XYSeries lineSeries = new XYSeries("líneas");

        // Create lines connecting data points to the x-axis
        for (int i = 0; i < pointsDataset.getSeriesCount(); i++) {
            XYSeries pointsSeries = pointsDataset.getSeries(i);
            for (int j = 0; j < pointsSeries.getItemCount(); j++) {
                double x = pointsSeries.getX(j).doubleValue();
                double y = pointsSeries.getY(j).doubleValue();
                lineSeries.add(x,0); // Llegar
                lineSeries.add(x, y); // Subir
                lineSeries.add(x, 0); // Bajar
            }
        }

        double[][] lineData = new double[2][lineSeries.getItemCount()];
        for (int i = 0; i < lineSeries.getItemCount(); i++) {
            lineData[0][i] = (double) lineSeries.getX(i);
            lineData[1][i] = (double) lineSeries.getY(i);
        }

        lineDataset.addSeries("Lines",lineData);

        return lineDataset;
    }
    
    
    private static double[] arrayer(double start, double end, double step ){
        //n = 0:Delta:100
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
