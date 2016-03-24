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
import main.vo.UserCompanyVO;

public class UserCompanyChartController implements Initializable {
	private UserService service;
	@FXML
	private PieChart pieChart;
	@FXML
	private AnchorPane pane;

	private UserCompanyVO vo;
	private ObservableList<PieChart.Data> pieChartData;
	private int[] company;
	private int sum = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = UserServiceImpl.getInstance();
		vo = service.getUserCompanyStatistics();
		company = vo.getNums();
		pieChartData = FXCollections.observableArrayList();
		setData();
		pieChart.setAnimated(true);
		setLabel();
		setAnimation();
	}

	private void setData() {
		for (int i = 0; i < company.length; i++) {
			pieChartData.add(new PieChart.Data(vo.getCompany()[i], 0));
		}
		pieChart.setData(pieChartData);
	}

	private void setLabel() {
		for (int i = 0; i < company.length; i++) {
			sum += company[i];
		}
		Label caption = new Label();
		caption.setTextFill(Color.DARKORANGE);
		caption.setStyle("-fx-font: 24 arial;");
		for (final PieChart.Data data : pieChart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					double division = 100 * data.getPieValue() * 1.0 / sum;
					caption.setText(String.format("%.2f", division) + "%");
					caption.setTranslateX(e.getSceneX() - 250);
					caption.setTranslateY(e.getSceneY() - 30);
				}
			});
		}
		pane.getChildren().add(caption);
	}

	private void setAnimation() {
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int j = 0;
				for (PieChart.Data data : pieChart.getData()) {
					int nums = company[j];
					for (int i = 0; i < nums; i++) {
						data.setPieValue(i);
					}
					j++;
				}
			}
		}));
		tl.play();

	}
}
