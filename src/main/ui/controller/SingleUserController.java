package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.vo.UserVO;

public class SingleUserController implements Initializable {

	@FXML
	private Label userName;
	@FXML
	private Label company;

	private UserVO vo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		userName.setOnMouseClicked((e) -> {
			MainController.getInstance().setGroup("Ui_UserPanel.fxml");
			UserController.getInstance().setVO(vo);
		});
	}

	public void setVO(UserVO vo) {
		this.vo=vo;
		if(vo!=null) {
			userName.setText(vo.getLogin());
			company.setText(vo.getLocation());
		}
	}

}
