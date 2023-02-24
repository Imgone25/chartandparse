package org.example;

import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.IOException;

public class Chart {
    public static void main(String[] args) {

        double[] yData = new double[] { 2.0, 1.0, 0.0 };

        // Create Chart
        XYChart chart = new XYChart(500, 400);
        chart.setTitle("Sample Chart");
        chart.setXAxisTitle("X");
        chart.setXAxisTitle("Y");
        XYSeries series = chart.addSeries("y(x)", null, yData);
        series.setMarker(SeriesMarkers.CIRCLE);


        try {
            BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.JPG);

            VectorGraphicsEncoder.saveVectorGraphic(chart, "./Sample_Chart", VectorGraphicsEncoder.VectorGraphicsFormat.SVG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}