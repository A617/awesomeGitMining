package main.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainUI extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub·	
		Application.launch(MainUI.class,args);
	}

	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("config/Ui_HomePageView.fxml"));
		Scene scene = new Scene(root, 800, 600);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle("JavaFXRange");
        stage.show();

	}

}
