package org.Client.ui.utility;

import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ScatterChartGenerator {
	private ScatterChart<Number,Number> chart;
	private NumberAxis xAxis = new NumberAxis();
	private NumberAxis yAxis = new NumberAxis();
	private XYChart.Series<Number, Number> series;

	private AnchorPane pane;
	
	public ScatterChartGenerator(AnchorPane pane,String name) {
		this.pane = pane;
		chart = new ScatterChart<Number,Number>(xAxis,yAxis);
		chart.setPrefSize(1166,720);
		chart.setTitle(name);
		series = new XYChart.Series<Number, Number>();
	}
	
	public void setData(int[] x,int[] y) {
		for(int i = 0;i<x.length;i++) {
			series.getData().add(new XYChart.Data<Number, Number>(x[i], 0));
		}
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int i = 0;
				for (Data<Number, Number> data : series.getData()) {
					data.setYValue(y[i]);
					i++;
				}
			}
		}));
		yAxis.setAnimated(false);
		tl.play();
		chart.getData().add(series);
		pane.getChildren().add(chart);
		setupHover();
	}
	
	public void setData(List<Integer> x,List<Double> y) {
		for(int i = 0;i<x.size();i++) {
			series.getData().add(new XYChart.Data<Number, Number>(x.get(i), 0));
		}
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int i = 0;
				for (Data<Number, Number> data : series.getData()) {
					data.setYValue(y.get(i));
					i++;
				}
			}
		}));
		yAxis.setAnimated(false);
		tl.play();
		chart.getData().add(series);
		pane.getChildren().add(chart);
		setupHover();
	}
	
	public void setupHover(){
		Label label = new Label();
		label.setTextFill(Color.DARKCYAN);
		label.setStyle("-fx-font: 13 arial;" + "-fx-opacity:0.5");
		
	    for (final XYChart.Data<Number, Number> dt : series.getData()) {
	        final Node n = dt.getNode();
	        n.setEffect(null);
	        n.setOnMouseEntered(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent e) {
	            	n.setCursor(Cursor.HAND);
	            	label.setLayoutX(n.getLayoutX()+40);
	            	label.setTranslateY(n.getLayoutY() + 10);
	            	label.setText("("+String.valueOf(dt.getXValue())+","+String.valueOf(dt.getYValue())+")");
	            }
	        });
	    }
	    pane.getChildren().add(label);
	}

}
