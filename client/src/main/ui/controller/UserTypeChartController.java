package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import main.business.impl.user.UserServiceImpl;
import main.business.service.UserService;

public class UserTypeChartController implements Initializable {
	private UserService service;
	@FXML
	private PieChart pieChart;
	@FXML
	private AnchorPane pane;
	final Label caption = new Label("");
	private ObservableList<PieChart.Data> pieChartData;
	private double division;
	private int[] types;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = UserServiceImpl.getInstance();
		types = service.getTypeStatistic();
		pieChartData = FXCollections.observableArrayList();
		division = 100 * types[0] / ((types[1] + types[0]) * 1.0);
		pieChartData.add(new PieChart.Data("organization", 0));
		pieChartData.add(new PieChart.Data("individual", 0));
		pieChart.setAnimated(true);
		pieChart.setData(pieChartData);
		setLabel();
		setAnimation();
	}

	private void setLabel() {
		caption.setTextFill(Color.DARKORANGE);
		caption.setStyle("-fx-font: 24 arial;");
		for (final PieChart.Data data : pieChart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					caption.setTranslateX(e.getSceneX());
					caption.setTranslateY(e.getSceneY());
					caption.setText(String.valueOf(division) + "%");
				}
			});
		}
		pane.getChildren().add(caption);
	}

	private void setAnimation() {
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				pieChart.getData().get(1).setPieValue(division);
			}
		}));
		tl.play();

	}
}
