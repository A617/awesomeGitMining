package main.ui;

import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.ui.controller.HomeController;
import main.ui.controller.ProjectController;
import main.ui.controller.SearchController;
import main.ui.controller.UserController;

public class MainUI extends Application{

	private Stage stage;
	private static final double WIDTH = 1024;
	private static final double HEIGHT = 768;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		stage.setTitle("AwesomeGitming");
		stage.setMinWidth(WIDTH);
		stage.setMinHeight(HEIGHT);

		gotoHome();
		stage.show();
	}

	public void gotoHome(){
		HomeController homeController;
		try {
			homeController = (HomeController)changeSceneContent("config/Ui_HomePageView.fxml");
			homeController.setApp(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gotoProject(){
		ProjectController projectController;
		try {
			projectController = (ProjectController)changeSceneContent("config/Ui_ProjectDetail.fxml");
			projectController.setApp(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gotoSearch(){
		SearchController searchController;
		try {
			searchController = (SearchController) changeSceneContent("config/Ui_SearchView.fxml");
			searchController.setApp(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gotoUser(){
		UserController userController;
		try {
			userController = (UserController) changeSceneContent("config/Ui_UserDetail.fxml");
			userController.setApp(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Initializable changeSceneContent(String fxml) throws Exception{
		FXMLLoader loader = new FXMLLoader();
		InputStream in = MainUI.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(MainUI.class.getResource(fxml));

		AnchorPane pane;
		try {
			pane = (AnchorPane) loader.load();
		} finally {
			in.close();
		}

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.sizeToScene();
		return (Initializable) loader.getController();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
