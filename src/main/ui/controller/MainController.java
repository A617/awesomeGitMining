package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import main.ui.MainUI;
import main.ui.utility.fxmlLoader;

public class MainController implements Initializable {

	private static MainController instance;

	public static MainController getInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}

	@FXML
	private AnchorPane center_panel, common;
	@FXML
	private Label minimize;
	@FXML
	private Label exit;

	/**
	 * when start the app, init the homePagePanel
	 */
	public void initPanel() {
		setPanel("Ui_HomePagePanel2.fxml");
	}

	/**
	 * the common method to change the current panel
	 *
	 * @param panel
	 */
	public void setPanel(String name) {
		AnchorPane panel = fxmlLoader.loadPanel(name);
		center_panel.getChildren().clear();
		center_panel.getChildren().add(panel);
		// otherwise the searchButton cannot use
		common.toFront();
	}

	public void setGroup(String name) {
		Group group = fxmlLoader.loadGroup(name);
		center_panel.getChildren().clear();
		center_panel.getChildren().add(group);
		// otherwise the searchButton cannot use
		common.toFront();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		instance = this;
	}
		

	public void labelInit(Label label,String path) {
		Image image = new Image(MainUI.class.getResourceAsStream("style/"+path));
		label.setGraphic(new ImageView(image));
	}


}
