package org.Client.ui.utility;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Client.business.service.UserService;
import org.Client.ui.controller.MainController;
import org.Client.ui.controller.ProjectController;
import org.Client.ui.controller.SearchController;
import org.Client.ui.controller.SearchUserController;
import org.Client.ui.controller.UserController;
import org.Common.vo.RepositoryVO;
import org.Common.vo.UserVO;

public class HandleBack {
	private static HandleBack instance;
	private UserService userImpl;
	private UserVO userVO;
	private RepositoryService repositoryImpl;
	private RepositoryVO repositoryVO;
	
	private BackType userType;
	private String userId;
	private BackType repoType;
	private String repoId;
	
	public HandleBack() {
		this.repositoryImpl = RepositoryServiceImpl.getInstance();
		this.userImpl = UserServiceImpl.getInstance();
		this.userType = BackType.HOME_USER;
		this.userId = null;
		this.repoType = BackType.HOME_PROJECT;
		this.repoId = null;
	}
	
	
	public static HandleBack getInstance() {
		if(instance == null) {
			instance = new HandleBack();
		}
		return instance;
	}
	
	public BackType getUserType() {
		return userType;
	}
	public String getUserID() {
		return userId;
	}
	
	public BackType getRepoType() {
		return repoType;
	}
	public String getRepoID() {
		return repoId;
	}
	
	public void setUserBack(BackType type, String id) {
		this.userType = type;
		this.userId = id;
	}
	
	public void setRepoBack(BackType type, String id) {
		this.repoType = type;
		this.repoId = id;
	}
	
	public void handleUserBack() {
		switch (userType) {
		case HOME_USER:
			MainController.getInstance().setPanel("Ui_UserPagePanel.fxml");
			break;
		case SEARCH_USER:
			MainController.getInstance().setPanel("Ui_SearchUser.fxml");
			SearchUserController controlU = SearchUserController.getInstance();
			controlU.setSearchID(userId);
			break;
		case PROJECT:
			MainController.getInstance().setGroup(SkinConfig.getInstance().getFxmlResoursePath("projectPanel"));
			repositoryVO = repositoryImpl.searchRepositoryInfo(userId);
			ProjectController.getInstance().setVO(repositoryVO);
			break;
		default:
			break;
		}
	}
	
	public void handleRepoBack() {
		switch (repoType) {
		case HOME_PROJECT:
			MainController.getInstance().setPanel("main.fxml");
			break;
		case SEARCH_PROJECT:
			MainController.getInstance().setPanel("Ui_SearchRepos.fxml");
			SearchController controlP = SearchController.getInstance();
			controlP.setSearchID(repoId);
			break;
		case USER:
			MainController.getInstance().setGroup(SkinConfig.getInstance().getFxmlResoursePath("userPanel"));
			userVO = userImpl.getUser(repoId);
			UserController.getInstance().setVO(userVO);
			break;
		default:
			break;
		}
	}

}
