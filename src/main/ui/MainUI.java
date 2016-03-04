package main.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.ui.controller.MainController;
import main.ui.utility.fxmlLoader;

public class MainUI extends Application{

	private Stage stage;
	private AnchorPane common;
	public static AnchorPane homePanel,projectPanel,searchPanel,userPanel;
	
	private static MainUI ui;
	
	@Override
	/**
	 * initialize all the fxml document
	 */
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		ui = this;
		
		common = fxmlLoader.loadPanel("Ui_CommonPart.fxml");
		homePanel = fxmlLoader.loadPanel("Ui_HomePagePanel.fxml");
		projectPanel = fxmlLoader.loadPanel("Ui_ProjectPanel.fxml");
		searchPanel = fxmlLoader.loadPanel("Ui_SearchPanel.fxml");
		userPanel = fxmlLoader.loadPanel("Ui_UserPanel.fxml");
		
		stage.setTitle("awesomeGitmining");
		stage.setMinWidth(1024);
		stage.setMinHeight(768);
		stage.setScene(new Scene(common));
		MainController.getInstance().initPanel();
		stage.show();
	}
	
	public static MainUI getUI(){
		return ui;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
