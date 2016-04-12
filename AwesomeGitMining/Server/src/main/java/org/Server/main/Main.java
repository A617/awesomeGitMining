package org.Server.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.Server.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
	
	private static Main instance;
	private Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		instance = this;
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		
		AnchorPane panel = loadPanel();
		stage.setTitle("AwesomeGitmining Server");
		stage.setWidth(600);
		stage.setHeight(400);
		
		Scene scene = new Scene(panel);
		scene.getStylesheets().add(Test.class.getResource("ui/server.css").toExternalForm());
		stage.setScene(scene);
		
		stage.show();
	}
	
	public AnchorPane loadPanel() {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(new URL("file:src/main/java/org/Server/ui/server_ui.fxml"));
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		AnchorPane result = null;
		try {
			result = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println("加载失败");
			e.printStackTrace();
		}
		return result;
	}
	
	public static Main getInstance() {
		return instance;
	}
	public Stage getStage() {
		return stage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
