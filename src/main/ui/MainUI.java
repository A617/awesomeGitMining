package main.ui;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.ui.controller.MainController;
import main.ui.utility.fxmlLoader;

public class MainUI extends Application{

	private Stage stage;
	private Scene scene;
	private AnchorPane common ;
	public static Group test;
	private static MainUI ui;

	@Override
	/**
	 * initialize all the fxml document
	 */
	public void start(Stage primaryStage) throws Exception {
		this.stage = primaryStage;
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		ui = this;

		common = fxmlLoader.loadPanel("Ui_CommonPart.fxml");
		stage.setTitle("awesomeGitmining");
		stage.setMinWidth(1024);
		stage.setMinHeight(768);
		scene = new Scene(common);

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
	
	public Stage getStage() {
		return stage;
	}
	
	public void test1() {
		Group root = new Group();
		Scene scene1 = new Scene(root, 300, 250);
		scene1.getStylesheets().add(MainUI.class.getResource("style/test.css").toExternalForm());
		Label label = new Label();
		label.setText("Please wait for a while ~");

		ProgressBar pb = new ProgressBar();
		pb.setProgress(-1.0f);

		ProgressIndicator pin = new ProgressIndicator();
		pin.setProgress(-1.0f);

		HBox hb = new HBox();
		hb.setSpacing(5);
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(pin, label);

		scene1.setRoot(hb);
		stage.setScene(scene1);
	}

	public void test2() {
		stage.setScene(scene);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
