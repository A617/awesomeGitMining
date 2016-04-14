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
	@FXML
	private Label close;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		info.setText("Server connection failed!");
		labelInit(error,"error.png");
		labelInit(close, "exit_normal.png");
		
		btn_ok.setOnAction((e) -> {
			System.exit(0);
		});
	}
	
	@FXML
	public void enterClose() {
		labelInit(close, "exit_move.png");
	}

	@FXML
	public void exitClose() {
		labelInit(close, "exit_normal.png");
	}

	@FXML
	public void releaseClose() {
		labelInit(close, "exit_active.png");
		MainController.getInstance().center_panel.getChildren().remove(pane);
	}
	
	public void labelInit(Label label, String path) {
		Image image = new Image(MainUI.class.getResourceAsStream("style/" + path));
		label.setGraphic(new ImageView(image));
	}

	
}
