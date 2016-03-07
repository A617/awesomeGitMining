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

	private void initUser(String id) {
		
	}

	private void initProject(String id) {
		
	}
	
	@FXML
	public void handleUserPre() {
		
	}
	@FXML
	public void handleUserNext() {
		
	}
	@FXML
	public void handleProjectPre() {
		
	}
	@FXML
	public void handleProjectNext() {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		repositoryService = RepositoryServiceImpl.getInstance();
		userService = UserServiceImpl.getInstance();
		userPage = 0;
		projectPage = 0;
		
		id = MainController.getInstance().getSearchId();
		initUser(id);
		initProject(id);
	}

}
