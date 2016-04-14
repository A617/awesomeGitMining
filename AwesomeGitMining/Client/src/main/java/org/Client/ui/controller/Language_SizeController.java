package org.Client.ui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Common.vo.Language_SizeVO;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Language_SizeController implements Initializable{

	@FXML
	private AnchorPane pane;
	
	private ScatterChart<String,Number> chart;
	private CategoryAxis xAxis = new CategoryAxis();
	private NumberAxis yAxis = new NumberAxis();
	private XYChart.Series<String, Number> series;
	
	private RepositoryService service;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		chart = new ScatterChart<String,Number>(xAxis,yAxis);
		chart.setPrefSize(1166,720);
		chart.setTitle("Language and Size");
		series = new XYChart.Series<String, Number>();
		xAxis.setLabel("Languages");
		yAxis.setLabel("Sizes");
		series.setName("language and size");
		
		service = RepositoryServiceImpl.getInstance();
		
		Language_SizeVO vo =service.getlanguage2sizeStatistics();
		List<String> languages = vo.getLanguages();
		List<Integer> sizes = vo.getSizes();
		
		for(int i=0;i<languages.size();i++){
			series.getData().add(new XYChart.Data<String,Number>(languages.get(i),0));
		}
		
	
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int i = 0;
				for (Data<String, Number> data : series.getData()) {
					data.setYValue(sizes.get(i));
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
		label.setStyle("-fx-font: 11 arial;" + "-fx-opacity:0.5");
		
	    for (final XYChart.Data<String, Number> dt : series.getData()) {
	        final Node n = dt.getNode();
	        n.setEffect(null);
	        n.setOnMouseEntered(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent e) {
	            	n.setCursor(Cursor.HAND);
	            	label.setLayoutX(n.getLayoutX()+40);
	            	label.setTranslateY(n.getLayoutY() + 10);
	            	label.setText("("+dt.getXValue()+","+String.valueOf(dt.getYValue())+")");
	            }
	        });
	    }
	    pane.getChildren().add(label);
	}

}
