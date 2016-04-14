package org.Client.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.UserService;
import org.Common.vo.FollowersVO;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class FollowerController implements Initializable{
	@FXML
	private AnchorPane pane;
	
	private ScatterChart<Number,Number> chart;
	private NumberAxis xAxis = new NumberAxis();
	private NumberAxis yAxis = new NumberAxis();
	private XYChart.Series<Number, Number> series;
	
	private UserService service;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chart = new ScatterChart<Number,Number>(xAxis,yAxis);
		chart.setPrefSize(1166,720);
		chart.setTitle("Follower and Star");
		series = new XYChart.Series<Number, Number>();
		xAxis.setLabel("Followers");
		yAxis.setLabel("AvgStars");
		series.setName("Follower and Star");
		
		service = UserServiceImpl.getInstance();
		
		FollowersVO vo = service.getFollowerStatistics();
		int[] follower = vo.getFollowers();
		double[] stars = vo.getRepoAvgStars();
		for(int i = 0;i<follower.length;i++) {
			series.getData().add(new XYChart.Data<Number, Number>(follower[i], 0));
		}
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int i = 0;
				for (Data<Number, Number> data : series.getData()) {
					data.setYValue(stars[i]);
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
