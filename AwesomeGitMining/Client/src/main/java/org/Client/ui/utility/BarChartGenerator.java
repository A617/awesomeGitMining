package org.Client.ui.utility;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BarChartGenerator {
	private BarChart<String, Integer> barChart;
	private CategoryAxis xAxis;
	private NumberAxis yAxis;
	private AnchorPane pane;
	private double columnWidth;
	private XYChart.Series<String, Integer> series;
	
	public BarChartGenerator(AnchorPane pane,BarChart<String, Integer> barChart,CategoryAxis xAxis,NumberAxis yAxis) {
		this.pane = pane;
		this.barChart = barChart;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setData(ObservableList<String> xData ,int[] yData, String name) {
		series = new XYChart.Series();
		int typeNum = xData.size();
		for (int i = 0; i < typeNum; i++) {
			XYChart.Data<String, Integer> data = new XYChart.Data(xData.get(i), 0);
			series.getData().add(data);
		}
		barChart.getData().add(series);
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
		double catSpace = xAxis.getCategorySpacing();
		double avilableBarSpace = catSpace - (barChart.getCategoryGap() + barChart.getBarGap());
		columnWidth = (avilableBarSpace / barChart.getData().size()) - barChart.getBarGap();
		yAxis.setAnimated(false);
		tl.play();
		setupHover(series);
	}
	
	public void setMaxBarWidth(double maxBarWidth, double minCategoryGap) {
		double barWidth = 0;
		do {
			double catSpace = xAxis.getCategorySpacing();
			double avilableBarSpace = catSpace - (barChart.getCategoryGap() + barChart.getBarGap());
			barWidth = (avilableBarSpace / barChart.getData().size()) - barChart.getBarGap();
			if (barWidth > maxBarWidth) {
				avilableBarSpace = (maxBarWidth + barChart.getBarGap()) * barChart.getData().size();
				barChart.setCategoryGap(catSpace - avilableBarSpace - barChart.getBarGap());
			}
		} while (barWidth > maxBarWidth);

		do {
			double catSpace = xAxis.getCategorySpacing();
			double avilableBarSpace = catSpace - (minCategoryGap + barChart.getBarGap());
			barWidth = Math.min(maxBarWidth, (avilableBarSpace / barChart.getData().size()) - barChart.getBarGap());
			avilableBarSpace = (barWidth + barChart.getBarGap()) * barChart.getData().size();
			barChart.setCategoryGap(catSpace - avilableBarSpace - barChart.getBarGap());
		} while (barWidth < maxBarWidth && barChart.getCategoryGap() > minCategoryGap);
	}
	
	/**
	 * 鼠标移进一个柱子可以显示对应的数据
	 * @param series
	 */
	private void setupHover(XYChart.Series<String, Integer> series) {
		Label label = new Label();
		label.setTextFill(Color.DARKCYAN);
		label.setStyle("-fx-font: 18 arial;"
				+ "-fx-opacity:0.5");
		
	    for (final XYChart.Data<String, Integer> dt : series.getData()) {
	        final Node n = dt.getNode();
	        n.setEffect(null);
	        n.setOnMouseEntered(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent e) {
	            	label.setLayoutX(n.getLayoutX() +columnWidth+90);
	            	label.setTranslateY(n.getLayoutY());
	            	label.setText(String.valueOf(dt.getYValue()));
	            }
	        });
	    }
	    pane.getChildren().add(label);
	}

}
