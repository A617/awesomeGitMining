package main.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import main.ui.MainUI;

public class ReposStaPaneController implements Initializable {
	@FXML
	private ScrollPane scrollPane;
	private VBox box;
	private String[] fxmls = { "Ui_ReposSta.fxml", "Ui_CreateTimeChar.fxml", "Ui_ContributorRange.fxml",
			"Ui_Collaborator.fxml" };

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		box = new VBox();
		VBox.setVgrow(scrollPane, Priority.ALWAYS);
		box.setSpacing(2);
		for (int i = 0; i < fxmls.length; i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainUI.class.getResource("config/"+fxmls[i]));
			try {
				AnchorPane chart = (AnchorPane) loader.load();
				box.getChildren().add(chart);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		scrollPane.setContent(box);
		box = null;
	}

}
