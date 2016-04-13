package org.Client.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.Client.ui.MainUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	@FXML
	private AnchorPane infoPane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelInit(yellow, "back_icon.png");
		labelInit(pink, "back_pink_icon.png");
		labelInit(dark, "back_dark_icon.png");
	}

	public void labelInit(Label label, String path) {
		Image image = new Image(MainUI.class.getResourceAsStream("style/" + path));
		label.setGraphic(new ImageView(image));
	}

	@FXML
	public void enterYellow() {
		labelInit(yellow, "back_icon_flash.png");
	}

	@FXML
	public void exitYellow() {
		labelInit(yellow, "back_icon.png");
	}

	@FXML
	public void enterPink() {
		labelInit(pink, "back_pink_icon_flash.png");
	}

	@FXML
	public void exitPink() {
		labelInit(pink, "back_pink_icon.png");
	}

	@FXML
	public void enterDark() {
		labelInit(dark, "back_dark_icon_flash.png");
	}

	@FXML
	public void exitDark() {
		labelInit(dark, "back_dark_icon.png");
	}

	@FXML
	public void selectYellow() {
		labelInit(yellow, "back_icon_flash.png");
		MainUI.getUI().changeStyle("yellow");
		MainController.getInstance().removeInfoPane();
	}

	@FXML
	public void selectPink() {
		labelInit(pink, "back_pink_icon_flash.png");
		MainUI.getUI().changeStyle("pink");
		MainController.getInstance().removeInfoPane();
	}

	@FXML
	public void selectDark() {
		labelInit(dark, "back_dark_icon_flash.png");
		MainUI.getUI().changeStyle("black");
		MainController.getInstance().removeInfoPane();
	}
}
