package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	private TextField search;
	@FXML
	private Button btn_search, btn_menu;
	@FXML
	private Label logo;

	public String getSearchId() {
		return search.getText();
	}

	@FXML
	public void handleMenu() {
		initPanel();
	}

	@FXML
	public void handleSearch() {
		if (getSearchId() != null && !getSearchId().isEmpty())
			setPanel("Ui_SearchPanel.fxml");
		// } else {
		// Dialogs.create().title("No Input").masthead("Nothing input!")
		// .message("Please input the keyword of what you want to
		// search.").showWarning();
		// }
	}

	/**
	 * when start the app, init the homePagePanel
	 */
	public void initPanel() {
		setPanel("Ui_HomePagePanel.fxml");
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
		justTest();
	}

	private void justTest() {
		logo.setText("awesomeGitmining");
		logo.setFont(Font.font("Calibri", 27));
		Image image = new Image(MainUI.class.getResourceAsStream("style/mark.png"));
		logo.setGraphic(new ImageView(image));
	}

}
