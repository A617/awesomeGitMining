package main.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
	private List<UserVO> userVO;
	private List<RepositoryVO> repositoryVO;
	private String id;

	@FXML
	private AnchorPane mainPane;
	@FXML
	private ScrollPane userPanel;
	@FXML
	private ScrollPane projectPanel;
	@FXML
	private Button btn_userPrevious;
	@FXML
	private Button btn_userNext;
	@FXML
	private Button btn_projectPrevious;
	@FXML
	private Button btn_projectNext;

	private int userPage;
	private int projectPage;

	public static SearchController getInstance() {
		if (instance == null) {
			instance = new SearchController();
		}
		return instance;
	}

	private void initUser(List<UserVO> list) {
		// pane->VBox->ScrollPane->Pane
		VBox baseBox = new VBox();
		VBox box = new VBox();
		baseBox.getChildren().add(userPanel);
		VBox.setVgrow(userPanel, Priority.ALWAYS);
		for (int i = 0; i < 5; i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainUI.class.getResource("config/Ui_SingleUserView.fxml"));
			try {
				Pane single = (Pane) loader.load();
				SingleUserController controller = loader.getController();
				if (i < list.size()) {
					UserVO vo = list.get(i);
					controller.setVO(vo);
					box.getChildren().add(single);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			userPanel.setContent(box);
		}
		mainPane.getChildren().add(userPanel);
	}

	private void initProject(List<RepositoryVO> list) {
		// pane->VBox->ScrollPane->Pane
		VBox baseBox = new VBox();
		VBox box = new VBox();
		baseBox.getChildren().add(projectPanel);
		VBox.setVgrow(projectPanel, Priority.ALWAYS);
		for (int i = 0; i < 10; i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainUI.class.getResource("config/Ui_SingleReposView.fxml"));
			try {
				Pane single = (Pane) loader.load();
				SingleRepositoryController controller = loader.getController();
				if (i < list.size()) {
					RepositoryVO vo = list.get(i);
					controller.setVO(vo);
					box.getChildren().add(single);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			projectPanel.setContent(box);
		}
		mainPane.getChildren().add(projectPanel);
	}

	@FXML
	public void handleUserPre() {
		userPage--;
		if (userPage > 0) {
			userVO = userService.searchUser(id, userPage);
			initUser(userVO);
		}

	}

	@FXML
	public void handleUserNext() {
		userPage++;
		userVO = userService.searchUser(id, userPage);
		if (userVO.size() > 0) {
			initUser(userVO);
		}
	}

	@FXML
	public void handleProjectPre() {
		projectPage--;
		if (projectPage > 0) {
			repositoryVO = repositoryService.searchRepository(id, projectPage);
			initProject(repositoryVO);
		}

	}

	@FXML
	public void handleProjectNext() {
		projectPage++;
		repositoryVO = repositoryService.searchRepository(id, projectPage);
		if (repositoryVO.size() > 0) {
			initProject(repositoryVO);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		repositoryService = RepositoryServiceImpl.getInstance();
		userService = UserServiceImpl.getInstance();
		userPage = 0;
		projectPage = 0;

		id = MainController.getInstance().getSearchId();
		userVO = userService.searchUser(id, userPage);
		repositoryVO = repositoryService.searchRepository(id, projectPage);

		if(!userVO.isEmpty())
			initUser(userVO);
		if(!repositoryVO.isEmpty())
			initProject(repositoryVO);
	}

}
