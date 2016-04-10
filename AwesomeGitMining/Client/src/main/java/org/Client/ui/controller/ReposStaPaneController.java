package org.Client.ui.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.Client.ui.MainUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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
	private Label blank;
	@FXML
	private AnchorPane center;

	private String styleStr = "-fx-background-color: ";
	private String enterColor = "#69b589;";
	private String baseColor = "#74c996;";
	private boolean selectLanguage;
	private boolean selectFork;
	private boolean selectStar;
	private boolean selectCreateTime;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		blank.setStyle(styleStr + baseColor);
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
	public void enterLanguage(){
		language.setStyle(styleStr + enterColor);
	}
	@FXML
	public void exitLanguage(){
		if (!selectLanguage) {
			language.setStyle(styleStr + baseColor);
		}
	}

	@FXML
	public void selectLanguage() {
		selectLanguage = true;
		selectFork = false;
		selectCreateTime = false;
		selectStar = false;
		enterLanguage();
		star.setStyle(styleStr + baseColor);
		fork.setStyle(styleStr + baseColor);
		createTime.setStyle(styleStr + baseColor);
		setChart("Ui_ReposSta.fxml");
	}

	@FXML
	public void enterFork(){
		fork.setStyle(styleStr + enterColor);
	}
	@FXML
	public void exitFork(){
		if (!selectFork) {
			fork.setStyle(styleStr + baseColor);
		}
	}
	@FXML
	public void selectFork() {
		selectLanguage = false;
		selectFork = true;
		selectCreateTime = false;
		selectStar = false;
		enterFork();
		language.setStyle(styleStr + baseColor);
		star.setStyle(styleStr + baseColor);
		createTime.setStyle(styleStr + baseColor);
		setChart("Ui_ForkStatistics.fxml");
	}

	@FXML
	public void enterStar(){
		star.setStyle(styleStr + enterColor);
	}
	@FXML
	public void exitStar(){
		if (!selectStar) {
			star.setStyle(styleStr + baseColor);
		}
	}

	@FXML
	public void selectStar() {
		selectLanguage = false;
		selectFork = false;
		selectCreateTime = false;
		selectStar = true;
		enterStar();
		language.setStyle(styleStr + baseColor);
		fork.setStyle(styleStr + baseColor);
		createTime.setStyle(styleStr + baseColor);
		setChart("Ui_StarStatistics.fxml");
	}

	@FXML
	public void enterCreateTime(){
		createTime.setStyle(styleStr + enterColor);
	}
	@FXML
	public void exitCreateTime(){
		if (!selectCreateTime) {
			createTime.setStyle(styleStr + baseColor);
		}
	}
	@FXML
	public void selectCreateTime() {
		selectLanguage = false;
		selectFork = false;
		selectCreateTime = true;
		selectStar = false;
		enterCreateTime();
		language.setStyle(styleStr + baseColor);
		fork.setStyle(styleStr + baseColor);
		star.setStyle(styleStr + baseColor);
		setChart("Ui_CreateTimeChar.fxml");
	}
}
