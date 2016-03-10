package main.ui;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.ui.utility.fxmlLoader;

public class ProgressSample extends Application {

	@Override
	public void start(Stage stage) {
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Progress Controls");

		/*
		 * final Slider slider = new Slider(); slider.setMin(0);
		 * slider.setMax(50);
		 * 
		 * final ProgressBar pb = new ProgressBar(0); final ProgressIndicator pi
		 * = new ProgressIndicator(0);
		 * 
		 * slider.valueProperty().addListener( (ObservableValue<? extends
		 * Number> ov, Number old_val, Number new_val) -> {
		 * pb.setProgress(new_val.doubleValue()/50);
		 * pi.setProgress(new_val.doubleValue()/50); });
		 */
		stage.setTitle("awesomeGitmining");
		stage.setMinWidth(1024);
		stage.setMinHeight(768);
		final HBox hb = new HBox();
		hb.setSpacing(5);
		hb.setAlignment(Pos.CENTER);
		ProgressIndicator bar = new ProgressIndicator(-1);
		hb.getChildren().addAll(bar);
		scene.setRoot(hb);
		stage.show();

		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				final int max = 10000;
				for (int i = 1; i <= max; i++) {

					System.out.println(i);
				}
				updateProgress(max, max);
				
				
	//			AnchorPane common = fxmlLoader.loadPanel("Ui_CommonPart.fxml");
	//			Scene scene = new Scene(common);
				final HBox hb2 = new HBox();
				hb2.getChildren().add(new Slider());
				hb2.setSpacing(5);
				hb2.setAlignment(Pos.CENTER);
				scene.setRoot(hb2);
				stage.setScene(scene);
				stage.show();
				System.out.println("ok");
				return null;
			}

		};

		bar.progressProperty().bind(task.progressProperty());
		Thread th = new Thread(task);
		th.start();


		

	}

	public static void main(String[] args) {
		launch(args);
	}
}