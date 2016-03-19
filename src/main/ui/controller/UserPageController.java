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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import main.business.impl.user.UserServiceImpl;
import main.business.service.UserService;
import main.ui.MainUI;
import main.vo.SimpleUserVO;
import main.vo.UserVO;

public class UserPageController implements Initializable{

	private int userPage;
	
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private Button btn_previous;
	@FXML
	private Button btn_next;
	
	private UserService userImpl;
	private static UserPageController instance;
	private List<SimpleUserVO> userVO;
	
	public static UserPageController getInstance() {
		if(instance == null) {
			instance = new UserPageController();
		}
		return instance;
	}
	
	public void initUser(List<SimpleUserVO> user) {
		VBox box = new VBox();
		VBox.setVgrow(scrollPane, Priority.ALWAYS);
		box.setSpacing(4);
		
		for (int i = 0; i < 10; i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainUI.class.getResource("config/Ui_SingleUserView.fxml"));
			try {
				Pane single = (Pane) loader.load();
				SingleUserController controller = loader.getController();
				if (i < user.size()) {
					SimpleUserVO vo = user.get(i);
					controller.setVO(vo);

					box.getChildren().add(single);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		scrollPane.setContent(box);
	}
	
	@FXML
	public void handleUserPre() {
		userPage--;
		if (userPage >= 0) {
			userVO = userImpl.showUsers(userPage);
			initUser(userVO);
		}else {
			userPage++;
		}

	}

	@FXML
	public void handleUserNext() {
		userPage++;
		userVO = userImpl.showUsers(userPage);
		if (userVO.size() > 0) {
			initUser(userVO);
		}else {
			userPage--;
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		userImpl = UserServiceImpl.getInstance();
		userPage = 0;
		userVO = userImpl.showUsers(userPage);
		initUser(userVO);
	}

}
