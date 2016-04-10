package org.Client.ui.utility;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class PieChartGenerator {
	
	private static PieChartGenerator instance;
	
	public static PieChartGenerator getInstance() {
		if(instance==null) {
			instance=new PieChartGenerator();
		}
		return instance;
	}
	
	public PieChart generateChart(Map<String,Integer> map){
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
		}
		
		PieChart chart = new PieChart(pieChartData);
		chart.setClockwise(false);
		chart.setStartAngle(90);
		chart.setLabelsVisible(false);

		return chart;
	}

}
