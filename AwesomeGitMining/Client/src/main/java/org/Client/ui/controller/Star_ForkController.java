package org.Client.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Common.vo.Star_ForkVO;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Star_ForkController implements Initializable{

	@FXML
	private AnchorPane pane;
	
	private ScatterChart<Number,Number> chart;
	private NumberAxis xAxis = new NumberAxis();
	private NumberAxis yAxis = new NumberAxis();
	private XYChart.Series<Number, Number> series;
	
	private RepositoryService service;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chart = new ScatterChart<Number,Number>(xAxis,yAxis);
		chart.setPrefSize(1166,720);
		chart.setTitle("Star and Fork");
		series = new XYChart.Series<Number, Number>();
		xAxis.setLabel("Stars");
		yAxis.setLabel("Forks");
		series.setName("star and fork");
		
		service = RepositoryServiceImpl.getInstance();
		
		Star_ForkVO vo = service.getstar_forkStatistics();
		int[] stars = vo.getStar();
		int[] forks = vo.getFork();
		for(int i = 0;i<stars.length;i++) {
			series.getData().add(new XYChart.Data<Number, Number>(stars[i], 0));
		}
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int i = 0;
				for (Data<Number, Number> data : series.getData()) {
					data.setYValue(forks[i]);
					i++;
				}
			}
		}));
		yAxis.setAnimated(false);
		tl.play();
		chart.getData().add(series);
		pane.getChildren().add(chart);
//		setupHover();
	}
}
