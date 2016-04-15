package org.Client.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Client.business.service.UserService;
import org.Client.main.RMIHelper;
import org.Client.ui.controller.MainController;
import org.Client.ui.utility.SkinConfig;
import org.Client.ui.utility.SkinHandler;
import org.Client.ui.utility.fxmlLoader;

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

public class MainUI extends Application {

	private Stage stage;
	private Scene scene;
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

		scene.getStylesheets().add(new URL(SkinConfig.getInstance().getCssResoursePath()).toExternalForm());

		// 添加图标
		this.stage.getIcons().add(new Image("file:src/main/java/org/Client/ui/style/mark.png"));
		stage.show();

		ProgressIndicator pin = new ProgressIndicator(-1);
		pin.setVisible(false);
		AnimationGroup ag = new AnimationGroup();
		ag.getChildren().add(pin);
		stage.setScene(new Scene(ag));

		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() {

				// 初始化单例
				try {
					RMIHelper.init();
				} catch (MalformedURLException | RemoteException | NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					setError();
				}
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

	}

	private void setError(){
		
		AnchorPane panel = fxmlLoader.loadPanel("Ui_Error.fxml");
		panel.setLayoutX((common.getWidth()-panel.getPrefWidth())/2);
		panel.setLayoutY((common.getHeight()-panel.getPrefHeight())/2);
		common.getChildren().clear();
		common.getChildren().add(panel);
		panel.toFront();
		
	}

	public static MainUI getUI() {
		return ui;
	}

	public void setIconified() {
		stage.setIconified(true);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void changeStyle() {
		scene.getStylesheets().clear();
		try {
			scene.getStylesheets().add(new URL(SkinConfig.getInstance().getCssResoursePath()).toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private class AnimationGroup extends Group {

		public AnimationGroup() {
			Label sunset = new Label();
			Image img1 = new Image("file:src/main/java/org/Client/ui/style/gradient-from-black.jpg");
			sunset.setGraphic(new ImageView(img1));
			sunset.setMaxSize(1366, 1068);

			StackPane stack1 = new StackPane();
			stack1.getChildren().addAll(sunset);
			stack1.setLayoutX(0);
			stack1.setLayoutY(-300);

			Label stars = new Label();
			Image img2 = new Image("file:src/main/java/org/Client/ui/style/stars.png");
			stars.setGraphic(new ImageView(img2));
			stars.setMaxSize(1366, 768);

			Label logo = new Label();
			Image img3 = new Image("file:src/main/java/org/Client/ui/style/logo.png");
			logo.setGraphic(new ImageView(img3));

			StackPane stack2 = new StackPane();
			stack2.getChildren().addAll(stars, logo);
			stack2.setOpacity(0);
			stack2.setAlignment(Pos.TOP_CENTER);

			final Timeline timeline = new Timeline();
			timeline.setCycleCount(0);
			final KeyValue kv1 = new KeyValue(stack1.layoutYProperty(), 0);
			final KeyValue kv2 = new KeyValue(stack2.opacityProperty(), 1);
			final KeyFrame kf = new KeyFrame(Duration.millis(1500), kv1, kv2);
			timeline.getKeyFrames().add(kf);
			timeline.play();

			this.getChildren().addAll(stack1, stack2);
		}

	}
}
