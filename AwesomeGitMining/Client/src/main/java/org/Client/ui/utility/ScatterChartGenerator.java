package org.Client.ui.utility;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ScatterChartGenerator {

	private ScatterChart<String,Integer> scatterChart;
	private CategoryAxis xAxis;
	private NumberAxis yAxis;
	private AnchorPane pane;
	private XYChart.Series<String, Integer> series;
	
	public ScatterChartGenerator(AnchorPane pane,ScatterChart<String,Integer> scatterChart,CategoryAxis xAxis,NumberAxis yAxis) {
		this.pane = pane;
		this.scatterChart = scatterChart;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}
	
	public void setData(ObservableList<String> xData ,int[] yData, String name) {
		series = new XYChart.Series();
		
		int typeNum = xData.size();
		for (int i = 0; i < typeNum; i++) {
			XYChart.Data<String, Integer> data = new XYChart.Data(xData.get(i), 0);
			series.getData().add(data);
		}
		
		scatterChart.getData().add(series);
		series.setName(name);
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int i = 0;
				for (Data<String, Integer> data : series.getData()) {
					data.setYValue(yData[i]);
					i++;
				}
			}
		}));
		yAxis.setAnimated(false);
		tl.play();
//		setupHover(series);
	}
	
	public static void main(String[] args) {
		
	}
}
