package org.Client.ui.controller;

import java.awt.SystemTray;
import java.net.URL;
import java.util.ResourceBundle;

import org.Client.ui.MainUI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ErrorController implements Initializable{
	@FXML
	private AnchorPane pane;
	@FXML
	private Button btn_ok;
	@FXML
	private Label info;
	@FXML
	private Label error;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		info.setText("Server connection failed!");
		labelInit(error,"error.png");
		
		btn_ok.setOnAction((e) -> {
			System.exit(0);
		});
	}
	
	
	public void labelInit(Label label, String path) {
		Image image = new Image(MainUI.class.getResourceAsStream("style/" + path));
		label.setGraphic(new ImageView(image));
	}

	
}
