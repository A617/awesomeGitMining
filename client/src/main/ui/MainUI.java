package main.ui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import main.business.impl.repository.RepositoryServiceImpl;
import main.business.impl.user.UserServiceImpl;
import main.business.service.RepositoryService;
import main.business.service.UserService;
import main.main.RMIHelper;
import main.ui.controller.MainController;
import main.ui.utility.fxmlLoader;

public class MainUI extends Application {

	private Stage stage;
	private  Scene scene;
	private AnchorPane common;
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
		stage.setWidth(1366);
		stage.setHeight(768);
		scene = new Scene(common);

		stage.setScene(scene);
		scene.getStylesheets().add(MainUI.class.getResource("style/test.css").toExternalForm());
		// 添加图标
		this.stage.getIcons().add(new Image("file:src/main/ui/style/mark.png"));
		stage.show();



		ProgressIndicator pin = new ProgressIndicator(-1);
		pin.setVisible(false);
		AnimationGroup ag = new AnimationGroup();
		ag.getChildren().add(pin);
		stage.setScene(new Scene(ag));

		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				//初始化单例
				RMIHelper.init();
				System.out.println("init");
				RepositoryService repositoryImpl = RepositoryServiceImpl.getInstance();
				UserService userImpl = UserServiceImpl.getInstance();

				updateProgress(1, 1);

				return null;
			}

		};

		pin.progressProperty().bind(task.progressProperty());
		Thread th = new Thread(task);
		th.start();

		pin.progressProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			if (new_val.intValue() == 1) {
				stage.setScene(this.scene);
				MainController.getInstance().initPanel();
			}
		});


/*		RepositoryService repositoryImpl = RepositoryServiceImpl.getInstance();
		UserService userImpl = UserServiceImpl.getInstance();
		stage.setScene(this.scene);
		MainController.getInstance().initPanel();
*/

		// stage.setFullScreen(false);
		// Rectangle2D primaryScreenBounds =
		// Screen.getPrimary().getVisualBounds();
		// stage.setX(primaryScreenBounds.getMinX());
		// stage.setY(primaryScreenBounds.getMinY());
		// stage.setWidth(primaryScreenBounds.getWidth());
		// stage.setHeight(primaryScreenBounds.getHeight());//全屏 等要用的时候再说
	}

	public static MainUI getUI() {
		return ui;
	}

	public Stage getStage() {
		return stage;
	}


	public static void main(String[] args) {
		launch(args);
	}

	public void changeStyle(String style){
		if(style=="black"){
			System.out.println("111");
			scene.getStylesheets().add(MainUI.class.getResource("style/black.css").toExternalForm());
			System.out.print("222");
		}
		else if(style=="pink"){
			scene.getStylesheets().add(MainUI.class.getResource("style/pink.css").toExternalForm());
		}

	}
	private  class AnimationGroup extends Group{

		public AnimationGroup() {
			Label sunset = new Label();
			Image img1 = new Image("file:src/main/ui/style/gradient-from-black.jpg");
			sunset.setGraphic(new ImageView(img1));
			sunset.setMaxSize(1366, 1068);

			StackPane stack1 = new StackPane();
			stack1.getChildren().addAll(sunset);
			stack1.setLayoutX(0);
			stack1.setLayoutY(-300);

			Label stars = new Label();
			Image img2 = new Image("file:src/main/ui/style/stars.png");
			stars.setGraphic(new ImageView(img2));
			stars.setMaxSize(1366, 768);

			Label logo = new Label();
			Image img3 = new Image("file:src/main/ui/style/logo.png");
			logo.setGraphic(new ImageView(img3));

			StackPane stack2 = new StackPane();
			stack2.getChildren().addAll(stars, logo);
			stack2.setOpacity(0);
			stack2.setAlignment(Pos.TOP_CENTER);

			final Timeline timeline = new Timeline();
			timeline.setCycleCount(0);
			final KeyValue kv1 = new KeyValue(stack1.layoutYProperty(), 0);
			final KeyValue kv2 = new KeyValue(stack2.opacityProperty(), 1);
			final KeyFrame kf = new KeyFrame(Duration.millis(3600), kv1, kv2);
			timeline.getKeyFrames().add(kf);
			timeline.play();


			this.getChildren().addAll(stack1, stack2);
		}

	}
}
