package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.ui.MainUI;

public class ProjectController implements Initializable{

	private MainUI app;
	@FXML
	private Label projectNameLabel;//用来显示项目的名字
	@FXML
	private Label creatorNameLabel;//用来显示创建者的名字

	public void setApp(MainUI app){
		this.app=app;
	}

	@FXML
	private void handle2Menu(){
		app.gotoHome();
	}
	public void setProjectLabel(){
		projectNameLabel.setText("aaa");
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub


	}

}
