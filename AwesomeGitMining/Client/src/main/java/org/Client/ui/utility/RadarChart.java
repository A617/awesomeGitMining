/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Client.ui.utility;

import javafx.scene.Group;

public class RadarChart {

    private RadarBuilder builder;
    private Double[] values;
    private String[] labels;
    private int dimensions;
    private int maxValue;

    public RadarChart(Double[] values, String[] labels,int maxValue) {
        this.values = values;
        this.labels = labels;
        this.maxValue = maxValue;
        setDimensions();
    }

    public Group build() {
        Group g = Skeleton.buildSkeleton(dimensions, labels);
        builder = new RadarBuilder(values, dimensions, maxValue);
        g.getChildren().add(builder.build());
        return g;
    }

    
    public int getMaxValue(){
    	return this.maxValue;
    }

    private void setDimensions() {
        this.dimensions = labels.length;
    }
}
