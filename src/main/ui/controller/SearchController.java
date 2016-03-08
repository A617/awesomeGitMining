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
	private ScrollPane userPane;
	@FXML
	private ScrollPane projectPane;
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
		baseBox.getChildren().add(userPane);
		VBox.setVgrow(userPane, Priority.ALWAYS);
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
			userPane.setContent(box);
		}
		mainPane.getChildren().add(userPane);
	}

	private void initProject(List<RepositoryVO> list) {
		// pane->VBox->ScrollPane->Pane
		VBox baseBox = new VBox();
		VBox box = new VBox();
		baseBox.getChildren().add(projectPane);
		VBox.setVgrow(projectPane, Priority.ALWAYS);
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
			projectPane.setContent(box);
		}
		mainPane.getChildren().add(projectPane);
	}

	@FXML
	public void handleUserPre() {
		userPage--;
		if (userPage >= 0) {
			userVO = userService.searchUser(id, userPage);
			initUser(userVO);
		}else {
			userPage++;
		}

	}

	@FXML
	public void handleUserNext() {
		userPage++;
		userVO = userService.searchUser(id, userPage);
		if (userVO.size() > 0) {
			initUser(userVO);
		}else {
			userPage--;
		}
	}

	@FXML
	public void handleProjectPre() {
		projectPage--;
		if (projectPage >= 0) {
			repositoryVO = repositoryService.searchRepository(id, projectPage);
			initProject(repositoryVO);
		}else {
			projectPage++;
		}

	}

	@FXML
	public void handleProjectNext() {
		projectPage++;
		repositoryVO = repositoryService.searchRepository(id, projectPage);
		if (repositoryVO.size() > 0) {
			initProject(repositoryVO);
		}else {
			projectPage--;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MainUI.getUI().test1();
		repositoryService = RepositoryServiceImpl.getInstance();
		userService = UserServiceImpl.getInstance();
		userPage = 0;
		projectPage = 0;

		id = MainController.getInstance().getSearchId();
		userVO = userService.searchUser(id, userPage);
		repositoryVO = repositoryService.searchRepository(id, projectPage);

		initUser(userVO);
		initProject(repositoryVO);
		MainUI.getUI().test2();
	}

}
