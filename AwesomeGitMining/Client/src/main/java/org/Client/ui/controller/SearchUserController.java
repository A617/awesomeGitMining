package org.Client.ui.controller;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.UserService;
import org.Client.ui.utility.SkinConfig;
import org.Common.vo.SimpleUserVO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author tj
 * @date 2016年3月15日
 */
public class SearchUserController implements Initializable {
	private static SearchUserController instance;
	private UserService service;
	private List<SimpleUserVO> list;
	private String id;
	private VBox box = new VBox();
	@FXML
	private ScrollPane userPane;
	@FXML
	private Label page;
	private int pageNums;
	private int userPage;

	public static SearchUserController getInstance() {
		return instance;
	}

	public SearchUserController() {
		service = UserServiceImpl.getInstance();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		instance = this;

	}

	private void initPane() {
		box = new VBox();
		for (int i = 0; i < 10; i++) {
			FXMLLoader loader = new FXMLLoader();
			try {
				loader.setLocation(new URL(SkinConfig.getInstance().getFxmlResoursePath("singleReposView")));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Pane single = (Pane) loader.load();
				SingleUserController controller = loader.getController();
				if (i < list.size()) {
					SimpleUserVO vo = list.get(i);
					controller.setVO(vo);
					box.getChildren().add(single);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		userPane.setContent(box);
		box = null;
	}

	public void setSearchID(String id) {
		this.id = id;
		list = service.searchUser(id, 0);
		pageNums = service.getSearchPageNums(id);
		if (pageNums < 1) {
			pageNums = 1;
		}
		page.setText("1 / " + pageNums);
		initPane();
	}

	@FXML
	public void handlePre() {
		userPage--;
		if (userPage >= 0) {
			list = service.searchUser(id, userPage);
			initPane();
		} else {
			userPage++;
		}
		page.setText(userPage + 1 + " / " + pageNums);
	}

	@FXML
	public void handleNext() {
		userPage++;
		list = service.searchUser(id, userPage);
		if (list.size() > 0) {
			initPane();
		} else {
			userPage--;
		}
		page.setText(userPage + 1 + " / " + pageNums);
	}
}
