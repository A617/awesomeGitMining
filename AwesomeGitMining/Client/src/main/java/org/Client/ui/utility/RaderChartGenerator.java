package org.Client.ui.utility;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javafx.scene.Group;

public class RaderChartGenerator {

	private static RaderChartGenerator instance;

	public static RaderChartGenerator getInstance() {
		if (instance == null) {
			instance = new RaderChartGenerator();
		}
		return instance;
	}

	public Group createPanel(String[] labels,Double[] values,int maxValue) {
		RadarChart chart = new RadarChart(values, labels,maxValue);

		return chart.build();
	}
}
