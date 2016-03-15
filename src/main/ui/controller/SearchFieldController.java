package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *@author tj
 *@date 2016年3月15日
 */
public class SearchFieldController implements Initializable{
	@FXML
	private TextField textField;
	private MainController main;
	@FXML
	public void handleSearch() {
		if (textField.getText().isEmpty()) {
			return;
		}
		if (main.isSelectRepos()) {
			main.setPanel("Ui_SearchRepos.fxml");
			SearchController control = SearchController.getInstance();
			control.setSearchID(textField.getText());
		} else if (main.isSelectUser()) {
			main.setPanel("Ui_SearchUser.fxml");
			SearchUserController control = SearchUserController.getInstance();
			control.setSearchID(textField.getText());
		}
		textField.setText("");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		main = MainController.getInstance();
	}
}
