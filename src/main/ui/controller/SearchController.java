package main.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
<<<<<<< HEAD
=======
	private List<UserVO> userVO;
	private List<RepositoryVO> repositoryVO;
	private String id;

>>>>>>> 99eb6b0b1f26a89f1f6307c243d3667a72f31220
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
	private int maxUserPage;
	private int projectPage;
	private int maxProjectPage;

	public static SearchController getInstance() {
		if (instance == null) {
			instance = new SearchController();
		}
		return instance;
	}

<<<<<<< HEAD
	private void initUser(String id) {
		
=======
	private void initUser(String id,int pageIndex) {
		// Pane->VBox->ScrollPane->AnchorPane
		VBox user = new VBox();
>>>>>>> 99eb6b0b1f26a89f1f6307c243d3667a72f31220
		VBox box = new VBox();
		box.getChildren().add(userPanel);
		VBox.setVgrow(userPanel, Priority.ALWAYS);
		int temp = userVO.size() - pageIndex*5;
		
		for(int i=1;i<=5;i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainUI.class.getResource("config/Ui_SingleUserView.fxml"));

			try {
				Pane single = (Pane) loader.load();
				SingleUserController controller = loader.getController();
				temp--;
				if(temp>=0){
					controller.setVO(userVO.get(5*pageIndex+i-1));
					user.getChildren().add(single);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			userPanel.setContent(user);
		}
		mainPane.getChildren().add(userPanel);

	}

	private void initProject(String id,int pageIndex) {
		VBox project = new VBox();
		VBox box = new VBox();
		box.getChildren().add(projectPanel);
		VBox.setVgrow(projectPanel, Priority.ALWAYS);
		int temp = repositoryVO.size() - pageIndex*5;
		
		for(int i=1;i<=10;i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainUI.class.getResource("config/Ui_SingleReposView.fxml"));

			try {
				Pane single = (Pane) loader.load();
				SingleRepositoryController controller = loader.getController();
				temp--;
				if(temp>=0){
					controller.setVO(repositoryVO.get(10*pageIndex+i-1));
					project.getChildren().add(single);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			projectPanel.setContent(project);
		}
		mainPane.getChildren().add(projectPanel);

	}
	
	@FXML
	public void handleUserPre() {
		userPage--;
		if(userPage>=0){
			initUser(id,userPage);
		}
	}
	@FXML
	public void handleUserNext() {
		userPage++;
		if(userPage<maxUserPage){
			System.out.println(userPage);
			initUser(id,userPage);
		}
	}
	@FXML
	public void handleProjectPre() {
		projectPage--;
		if(projectPage>=0){
			initProject(id,projectPage);
		}
	}
	@FXML
	public void handleProjectNext() {
		projectPage++;
		if(projectPage<maxProjectPage){
			initProject(id,projectPage);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		repositoryService = RepositoryServiceImpl.getInstance();
		userService = UserServiceImpl.getInstance();
		userPage = 0;
		projectPage = 0;
		
		id = MainController.getInstance().getSearchId();
		userVO = userService.searchUser(id);
		repositoryVO = repositoryService.searchRepository(id);
		maxUserPage = (userVO.size()+1)/5;
		maxProjectPage = (repositoryVO.size()+1)/10;
		
		initUser(id,userPage);
		initProject(id,projectPage);
	}

}
