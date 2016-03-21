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

public class ReposStaPaneController implements Initializable {
	@FXML
	private Label language;
	@FXML
	private Label createTime;
	@FXML
	private Label star;
	@FXML
	private Label fork;
	@FXML
	private AnchorPane center;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		selectLanguage();
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

	@FXML
	public void selectLanguage() {
		setChart("Ui_ReposSta.fxml");
	}

	@FXML
	public void selectFork() {
		setChart("Ui_ForkStatistics.fxml");
	}

	@FXML
	public void selectStar() {
		setChart("Ui_StarStatistics.fxml");
	}

	@FXML
	public void selectCreateTime() {
		setChart("Ui_CreateTimeChar.fxml");
	}
}
