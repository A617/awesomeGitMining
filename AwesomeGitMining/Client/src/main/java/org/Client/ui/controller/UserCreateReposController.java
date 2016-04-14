package org.Client.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.UserService;
import org.Common.vo.UserCreateReposNumVO;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * @author tj
 * @date 2016年3月26日 下午5:20:46
 */
public class UserCreateReposController implements Initializable {
	private UserService service;
	private UserCreateReposNumVO vo;
	@FXML
	private PieChart pieChart;
	@FXML
	private AnchorPane pane;
	final Label caption = new Label("");
	private ObservableList<PieChart.Data> pieChartData;
	private int[] data;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = UserServiceImpl.getInstance();
		vo = service.getUserCreateReposNum();
		data = vo.getNums();
		String[] types = vo.getRanges();
		pieChartData = FXCollections.observableArrayList();
		// set data
		for (int i = 0; i < data.length; i++) {
			pieChartData.add(new PieChart.Data(types[i], data[i]));
		}
		//pieChart.setAnimated(true);
		pieChart.setData(pieChartData);
		setLabel();
		//setAnimation();
	}

	private void setLabel() {
		caption.setTextFill(Color.DARKORANGE);
		caption.setStyle("-fx-font: 24 arial;");
		for (final PieChart.Data data : pieChart.getData()) {
			Node n = data.getNode();
			n.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					n.setCursor(Cursor.HAND);
					caption.setTranslateX(e.getSceneX());
					caption.setTranslateY(e.getSceneY());
					caption.setText(data.getPieValue() + "");
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
				for (int i = 0; i < data.length; i++) {
					pieChart.getData().get(i).setPieValue(data[i]);
				}
			}
		}));
		tl.play();

	}
}
