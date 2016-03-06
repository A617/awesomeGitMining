package main.ui;


import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.ui.controller.MainController;
import main.ui.utility.fxmlLoader;

public class MainUI extends Application{

	private Stage stage;
	private AnchorPane common ;
	public static Group test;
	private static MainUI ui;

	@Override
	/**
	 * initialize all the fxml document
	 */
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		ui = this;

		common = fxmlLoader.loadPanel("Ui_CommonPart.fxml");
		stage.setTitle("awesomeGitmining");
		stage.setMinWidth(1024);
		stage.setMinHeight(768);
		//stage.initStyle(StageStyle.TRANSPARENT);
		Scene scene = new Scene(common);

		stage.setScene(scene);
		scene.getStylesheets().add(MainUI.class.getResource("style/test.css").toExternalForm());
		//添加图标
		this.stage.getIcons().add(new Image("file:src/main/ui/style/mark.png"));
		MainController.getInstance().initPanel();
//		stage.setFullScreen(false);
//		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//		stage.setX(primaryScreenBounds.getMinX());
//		stage.setY(primaryScreenBounds.getMinY());
//		stage.setWidth(primaryScreenBounds.getWidth());
//		stage.setHeight(primaryScreenBounds.getHeight());//全屏 等要用的时候再说
		stage.show();
	}

	public static MainUI getUI(){
		return ui;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
