package org.Server.main;

import java.net.URL;
import java.util.ResourceBundle;

import org.Server.Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController implements Initializable{
	
	@FXML
	private Button connect;
	@FXML
	private Button disconnect;
	@FXML
	private Label min;
	@FXML
	private Label close;
	@FXML
	private Label info;
	
	private static MainController instance;
	private boolean isConnected;
	
	public static MainController getInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelInit(close, "exit_normal.png");
		labelInit(min, "min_normal.png");
		isConnected = false;
		
		connect.setOnAction((ActionEvent e) -> { 
			if(!isConnected) {
				RMIHelper.init();
				info.setText("start the server successfully!");
				isConnected = true;
			}
		});
		disconnect.setOnAction((ActionEvent e) -> {  
			System.exit(0);
		});
	}
	
	public void labelInit(Label label, String path) {
		Image image = new Image(Test.class.getResourceAsStream("ui/" + path));
		label.setGraphic(new ImageView(image));
	}
	
	@FXML
	public void enterExit() {
		labelInit(close, "exit_move.png");
	}
	@FXML
	public void exitExit() {
		labelInit(close, "exit_normal.png");
	}
	@FXML
	public void releaseExit() {
		labelInit(close, "exit_active.png");
		System.exit(0);
	}
	@FXML
	public void enterMin() {
		labelInit(min, "min_move.png");
	}
	@FXML
	public void exitMin() {
		labelInit(min, "min_normal.png");
	}
	@FXML
	public void releaseMin() {
		labelInit(min, "min_active.png");
		Main.getInstance().getStage().setIconified(true);
	}

}
