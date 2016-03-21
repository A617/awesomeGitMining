package main.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.business.impl.user.UserServiceImpl;
import main.business.service.UserService;
import main.ui.MainUI;
import main.vo.SimpleUserVO;

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
			loader.setLocation(MainUI.class.getResource("config/Ui_SingleUserView.fxml"));
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
	}
}
