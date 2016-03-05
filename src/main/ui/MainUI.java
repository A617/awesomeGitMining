package main.ui;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.ui.controller.MainController;
import main.ui.utility.fxmlLoader;

public class MainUI extends Application{

	private Stage stage;
	private AnchorPane common ;
	public static AnchorPane homePanel;

	private static MainUI ui;

	@Override
	/**
	 * initialize all the fxml document
	 */
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		ui = this;

		common = fxmlLoader.loadPanel("Ui_CommonPart.fxml");
		homePanel = fxmlLoader.loadPanel("Ui_ProjectPanel.fxml");
		stage.setTitle("awesomeGitmining");
		stage.setMinWidth(1024);
		stage.setMinHeight(768);
		Scene scene = new Scene(common);
		stage.setScene(scene);
//		scene.getStylesheets().add(MainUI.class.getResource("style/test.css").toExternalForm());
//		//添加图标
//		this.stage.getIcons().add(new Image("file:src/main/ui/style/mark.png"));
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
