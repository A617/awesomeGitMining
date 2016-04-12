package org.Client.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.Client.ui.MainUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * @author tj
 * @date 2016年4月11日 下午3:27:03
 */
public class InfoPaneController implements Initializable {
	@FXML
	private Label yellow;
	@FXML
	private Label pink;
	@FXML
	private Label dark;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	@FXML
	public void setYellow() {
		MainUI.getUI().changeStyle("yellow");
	}

	@FXML
	public void setPink() {
		MainUI.getUI().changeStyle("pink");
	}

	@FXML
	public void setDark() {
		MainUI.getUI().changeStyle("black");
	}
}
