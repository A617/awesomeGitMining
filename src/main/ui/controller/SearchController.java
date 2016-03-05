package main.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import main.business.impl.repository.RepositoryServiceImpl;
import main.business.impl.user.UserServiceImpl;
import main.business.service.RepositoryService;
import main.business.service.UserService;
import main.ui.MainUI;
import main.vo.RepositoryVO;
import main.vo.UserVO;

public class SearchController implements Initializable {

	private static SearchController instance;
	private RepositoryService repositoryService;
	private UserService userService;

	@FXML
	private AnchorPane mainPane;
	@FXML
	private ScrollPane userPanel;
	@FXML
	private ScrollPane projectPanel;

	private VBox user = new VBox();
	private VBox repository = new VBox();

	public static SearchController getInstance() {
		if (instance == null) {
			instance = new SearchController();
		}
		return instance;
	}

	private void initUser(String id) {
		VBox box = new VBox();
		box.getChildren().add(userPanel);
		VBox.setVgrow(userPanel, Priority.ALWAYS);

		List<UserVO> list = userService.searchUser(id);
		
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainUI.class.getResource("config/Ui_SingleUserView.fxml"));

				try {
					Pane single = (Pane) loader.load();
					SingleUserController controller = loader.getController();
					controller.setVO(list.get(i));
					user.getChildren().add(single);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				userPanel.setContent(user);
			}
			mainPane.getChildren().add(userPanel);
		}

	}

	private void initProject(String id) {
		VBox box = new VBox();
		box.getChildren().add(projectPanel);
		VBox.setVgrow(projectPanel, Priority.ALWAYS);
		
		List<RepositoryVO> list = repositoryService.searchRepository(id);

		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainUI.class.getResource("config/Ui_SingleReposView.fxml"));

				try {
					Pane single = (Pane) loader.load();
					SingleRepositoryController controller = loader.getController();
					controller.setVO(list.get(i));
					repository.getChildren().add(single);
				} catch (IOException e) {
					e.printStackTrace();
				}
				projectPanel.setContent(repository);
			}
			mainPane.getChildren().add(userPanel);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		repositoryService = RepositoryServiceImpl.getInstance();
		userService = UserServiceImpl.getInstance();
		String id = MainController.getInstance().getSearchId();
		initUser(id);
		initProject(id);
	}

}
