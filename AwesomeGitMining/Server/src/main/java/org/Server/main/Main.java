package org.Server.main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		RMIHelper.init();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
