package main.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import main.ui.MainUI;
import main.vo.RepositoryVO;

public class HomeController implements Initializable {

	private static HomeController instance;
	@FXML
	private ScrollPane generalPane;
	@FXML
	private ScrollPane starPane;
	@FXML
	private ScrollPane forkPane;
	@FXML
	private ScrollPane contriPane;
	
	private VBox generalVB = new VBox();
	private VBox starVB = new VBox();
	private VBox forkVB = new VBox();
	private VBox contriVB = new VBox();
	private HomeController(){
		
	}
	public static HomeController getInstance() {
		return instance;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTabPane(generalPane,generalVB);
		initTabPane(starPane,starVB);
		initTabPane(forkPane,forkVB);
		initTabPane(contriPane,contriVB);
	}

	private void initTabPane(ScrollPane sp,VBox vb) {
		// Pane->VBox->ScrollPane->VBox
		VBox box = new VBox();
		box.getChildren().add(sp);
		VBox.setVgrow(sp, Priority.ALWAYS);
		for (int i = 0; i < 20; i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainUI.class.getResource("config/Ui_SingleReposView.fxml"));
			try {
				Pane single = (Pane) loader.load();
				SingleRepositoryController controller = loader.getController();
				RepositoryVO vo = new RepositoryVO();
				vo.setName("awesome");
				controller.setVO(vo);
				vb.getChildren().add(single);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sp.setContent(vb);
		}
	}
}
