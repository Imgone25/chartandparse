package org.example;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Chart {
    public static void main(String[] args) {


        CategoryChart test1 = getChart();
        try {
            // save chart object to file
            BitmapEncoder.saveBitmap(test1, "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);

            // I don't think iText v5 supports SVG. This is the ideal format for PDF scaling.
            VectorGraphicsEncoder.saveVectorGraphic(test1, "./Sample_Chart", VectorGraphicsEncoder.VectorGraphicsFormat.SVG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static CategoryChart getChart() {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(800).title("Findings Release History").xAxisTitle("Version").yAxisTitle("Finding Count").build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setOverlapped(true);
        chart.getStyler().setSeriesColors(new Color[] { new Color(114,114,114), new Color(3,111,252)});

        String[] softwareVersions = { "Version 1.0", "Version 1.1", "Version 2.0", "Version 3.0" };
        Integer[] unresolvedFindingsArray = { 230, 215, 158, 88 };
        Integer[] newFindingsArray = { 0, 5, 3, 35 };
        // sum the unresolved and new findings to show total on bar chart
        for(int i = 0; i < newFindingsArray.length; ++i){
            newFindingsArray[i] = newFindingsArray[i] + unresolvedFindingsArray[i];
        }
        // Series

        chart.addSeries("New", Arrays.asList(softwareVersions), Arrays.asList(newFindingsArray));
        // Unresolved is added second so overlap can be seen
        chart.addSeries("Unresolved", Arrays.asList(softwareVersions), Arrays.asList(unresolvedFindingsArray));

        // manually set Y-Axis so Legend doesn't cut off bar chart
        int maxYValue = getMaxInteger(unresolvedFindingsArray, newFindingsArray); // getYAxisMax() returns null, must compare int arrays
        chart.getStyler().setYAxisMax(maxYValue*1.1);

        return chart;
    }

    public static int getMaxInteger(Integer[] a, Integer[] b) {
        int maxA = Collections.max(Arrays.asList(a));
        int maxB = Collections.max(Arrays.asList(b));

        return (maxA > maxB) ? maxA : maxB;
    }
}

