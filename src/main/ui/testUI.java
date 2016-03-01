package main.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class testUI extends Application {

	private Stage primaryStage;
    private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AwesomeGitming");

//        initRootLayout();
//
//        test();
	}
	
//	public void initRootLayout(){
//		try {
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(testUI.class.getResource("config/Ui_CommonPart.fxml"));
//			rootLayout = (AnchorPane)loader.load();
//			
//			Scene scene = new Scene(rootLayout);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//	}
//	
//	public void test(){
//		try {
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(testUI.class.getResource("config/Ui_Searchview.fxml"));
//			AnchorPane test = (AnchorPane) loader.load();
//			
//			rootLayout.getChildren().addAll(test);
//			AnchorPane.setTopAnchor(test,50.0);
//			
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//	}
	
	public Stage getPrimaryStage() {
        return primaryStage;
    }

	public static void main(String[] args) {
		launch(args);
	}
}
