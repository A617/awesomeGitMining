package main.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.ui.MainUI;

public class UserStaPaneController implements Initializable {
	@FXML
	private Label type;
	@FXML
	private Label registerTime;
	@FXML
	private Label collaborate;
	@FXML
	private Label create;
	@FXML
	private Label company;
	@FXML
	private AnchorPane center;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setChart("Ui_UserTypeChar.fxml");

	}

	private void setChart(String name) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainUI.class.getResource("config/" + name));
		AnchorPane chart = null;
		try {
			chart = (AnchorPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		center.getChildren().clear();
		if (chart != null)
			center.getChildren().add(chart);
	}
}
