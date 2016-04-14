package org.Client.ui.utility;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class LineChartGenerator {
	
	private LineChart<String, Number> lineChart;
	private CategoryAxis xAxis = new CategoryAxis();
	private NumberAxis yAxis = new NumberAxis();
	private XYChart.Series<String, Number> series;
	
	private AnchorPane pane;
	
	
	public LineChartGenerator(AnchorPane pane,String name) {
		this.pane = pane;
		lineChart = new LineChart<String,Number>(xAxis,yAxis);
		lineChart.setPrefSize(1166,720);
		lineChart.setTitle(name);
		series = new XYChart.Series<String, Number>();
		series.setName(name);
		
	}

	public void setData(String[] x,int[] y) {
		for (int i = 0; i < x.length; i++) {
			series.getData().add(new XYChart.Data<String, Number>(x[i], 0));
		}
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int i = 0;
				for (Data<String, Number> data : series.getData()) {
					data.setYValue(y[i]);
					i++;
				}
			}
		}));
		yAxis.setAnimated(false);
		tl.play();
		lineChart.getData().add(series);
		lineChart.setCreateSymbols(true);
		pane.getChildren().add(lineChart);
		
		setupHover();
	}

	private void setupHover() {
		Label label = new Label();
		label.setTextFill(Color.DARKCYAN);
		label.setStyle("-fx-font: 18 arial;" + "-fx-opacity:0.5");
		
	    for (final XYChart.Data<String, Number> dt : series.getData()) {
	        final Node n = dt.getNode();
	        n.setEffect(null);
	        n.setOnMouseEntered(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent e) {
	            	n.setCursor(Cursor.HAND);
	            	label.setLayoutX(n.getLayoutX()+40);
	            	label.setTranslateY(n.getLayoutY() + 10);
	            	label.setText(String.valueOf(dt.getYValue()));
	            }
	        });
	    }
	    pane.getChildren().add(label);
	}
}
