package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.business.impl.user.UserServiceImpl;
import main.business.service.UserService;
import main.vo.UserVO;

public class SingleUserController implements Initializable {

	@FXML
	private Label userName;
	@FXML
	private Label company;

	private UserService userImpl;
	private UserVO vo;
	private UserVO fullVO;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		userImpl = UserServiceImpl.getInstance();
		userName.setOnMouseClicked((e) -> {
			MainController.getInstance().setGroup("Ui_UserPanel.fxml");
			fullVO = userImpl.getUser(vo.getLogin());
			
			if(fullVO!=null)
				UserController.getInstance().setVO(fullVO);
		});
	}

	public void setVO(UserVO vo) {
		this.vo=vo;
		if(vo!=null) {
			userName.setText(vo.getLogin());
			MainController.getInstance().labelInit(company,"place.png");
			company.setText(vo.getLocation());
		}
	}

}
