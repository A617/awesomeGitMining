package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import main.ui.MainUI;

public class ProjectController implements Initializable{
	private static ProjectController instance;
	private MainUI app;
	
	@FXML
	private Label projectNameLabel;//用来显示项目的名字
	
	@FXML
	private Label profile;//用来显示项目的介绍
	@FXML
	private Label star_num;//用来显示star数
	@FXML
	private Label fork_num;
	@FXML
	private Label colla_num;
	@FXML
	private Label sub_num;
	@FXML
	private Label contri_num;
	public void setApp(MainUI app){
		this.app=app;
	}
	public static ProjectController getInstance() {
		return instance;
	}

	@FXML
	private void handleSearch(){
		setProjectLabel();
	}

	@FXML
	public void setProjectLabel(){
		projectNameLabel.setText("aaa");
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		projectNameLabel.setText("awesome");
		star_num.setText("666");
		fork_num.setText("666");
		colla_num.setText("444");
		sub_num.setText("4");
		contri_num.setText("88");

	}

}
