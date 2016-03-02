package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.ui.MainUI;

public class ProjectController implements Initializable{
	
	private MainUI app;
	
	public void setApp(MainUI app){
		this.app=app;
	}
	
	@FXML
	private void handle2Menu(){
		app.gotoHome();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
