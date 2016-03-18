package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ReposStaController implements Initializable {
	@FXML
	private AnchorPane back;
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private CategoryAxis xAxis;
	private ObservableList<String> languages = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String[] types = { "Java", "C++", "C", "Python", "Perl", "JavaScript" };
		languages.addAll(types);
		xAxis.setCategories(languages);
	}

	public void setData() {
//		int[] counter = new int[6];
//		for (int i = 0; i < 6; i++) {
//			counter[i] = i + 1;
//		}
//		XYChart.Series<String, Integer> series = new XYChart.Series<>();
//		for (int i = 0; i < counter.length; i++) {
//			series.getData().add(new XYChart.Data<>(languages.get(i), counter[i]));
//		}
//		barChart.getData().add(series);
//		Timeline tl = new Timeline();
//
//		tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//
//				for (Series<String, Integer> series : barChart.getData()) {
//
//					for (Data<String, Integer> data : series.getData()) {
//
//						data.setXValue(Math.random() * 100);
//					}
//				}
//			}
//		}));
//
//		tl.setCycleCount(Animation.INDEFINITE);
//
//		tl.play();
	}
}
