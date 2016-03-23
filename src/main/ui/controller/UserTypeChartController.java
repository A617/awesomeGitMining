package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
		pieChartData.add(new PieChart.Data("individual", division));
		setAnimation();
		pieChart.setData(pieChartData);
		pieChart.setAnimated(true);
		setLabel();
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
		for (int i = 0; i < types[1]; i++) {
			try {
				Thread.sleep(5);
				pieChart.getData().get(1).setPieValue(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
