package org.Client.ui.utility;

import java.util.ArrayList;
import java.util.List;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Client.business.service.UserService;
import org.Client.ui.controller.MainController;
import org.Client.ui.controller.ProjectController;
import org.Client.ui.controller.SearchController;
import org.Client.ui.controller.SearchUserController;
import org.Client.ui.controller.UserController;
import org.Client.ui.controller.UserPageController;
import org.Common.vo.RepositoryVO;
import org.Common.vo.SimpleUserVO;
import org.Common.vo.UserVO;

/**
 * 解决back的问题
 * @author user
 *
 */
public class BackHandler {
	private static BackHandler instance;
	private UserService userImpl;
	private RepositoryService repositoryImpl;
	private UserVO userVO;
	private RepositoryVO repositoryVO;
	private List<SimpleUserVO> userList;
	private List<RepositoryVO> repositoryList;
	
	private ArrayList<BackObject> repoBackList;
	private ArrayList<BackObject> userBackList;
	
	public static BackHandler getInstance() {
		if(instance == null)
			instance = new BackHandler();
		return instance;
	}
	
	public BackHandler() {
		this.repositoryImpl = RepositoryServiceImpl.getInstance();
		this.userImpl = UserServiceImpl.getInstance();
		repoBackList = new ArrayList<BackObject>();
		userBackList = new ArrayList<BackObject>();
	}
	
	public void handleRepoBack() {
		int length = repoBackList.size();
		BackObject repo = repoBackList.get(length-1);
		switch (repo.getType()) {
		case HOME_REPO:
			//TODO
			MainController.getInstance().setPanel(SkinConfig.getInstance().getFxmlResoursePath("main"));
//			repositoryList = repositoryImpl.showRepositories(repo.getPage());
//			HomeController.getInstance().initTabPane(repositoryList);
//			HomeController.getInstance().setPage(repo.getPage());
			break;
		case SEARCH_REPO:
			MainController.getInstance().setPanel("Ui_SearchRepos.fxml");
			SearchController controlP = SearchController.getInstance();
			controlP.setSearchID(repo.getId());
			repositoryList = repositoryImpl.searchRepository(repo.getId(), repo.getPage());
			SearchController.getInstance().initProject(repositoryList);
			SearchController.getInstance().setPage(repo.getPage());
			break;
			//TODO
		case USER:
			MainController.getInstance().setGroup("Ui_UserPanel.fxml");
			userVO = userImpl.getUser(repo.getId());
			UserController.getInstance().setVO(userVO);
			break;
		default:
			break;
		}
		
		if(length > 1) {
			repoBackList.remove(length-1);
		}
	}
	
	public void handleUserBack() {
		int length = userBackList.size();
		BackObject user = userBackList.get(length-1);
		switch (user.getType()) {
		case HOME_USER:
			MainController.getInstance().setPanel("Ui_UserPagePanel.fxml");
//			userList = userImpl.showUsers(user.getPage());
//			UserPageController.getInstance().initUser(userList);
//			UserPageController.getInstance().setPage(user.getPage());
			break;
		case SEARCH_USER:
			MainController.getInstance().setPanel("Ui_SearchUser.fxml");
			SearchUserController controlU = SearchUserController.getInstance();
			controlU.setSearchID(user.getId());
			userList = userImpl.searchUser(user.getId(), user.getPage());
			SearchUserController.getInstance().initPane(userList);
			SearchUserController.getInstance().setPage(user.getPage());
			break;
//			//TODO
		case REPO:
			MainController.getInstance().setGroup("Ui_ProjectPanel.fxml");
			repositoryVO = repositoryImpl.searchRepositoryInfo(user.getId());
			ProjectController.getInstance().setVO(repositoryVO);
			break;
		default:
			break;
		}
		
		if(length > 1) {
			userBackList.remove(length-1);
		}
	}

	public ArrayList<BackObject> getRepoBackList() {
		return repoBackList;
	}

	public void setRepoBack(BackObject object) {
		repoBackList.add(object);
	}

	public ArrayList<BackObject> getUserBackList() {
		return userBackList;
	}

	public void setUserBack(BackObject object) {
		userBackList.add(object);
	}
	
}