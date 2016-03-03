package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import main.ui.MainUI;

public class HomeController implements Initializable{
	
	private MainUI app;
	@FXML
	private ScrollPane generalPane;
	@FXML
	private Pane single;
	public void setApp(MainUI app){
		this.app=app;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.single = MainUI.getPane();
		generalPane.setContent(single);
	}

}
