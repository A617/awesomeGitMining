package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.vo.UserVO;

public class SingleUserController implements Initializable {
	
	@FXML
	private Label userImage;
	@FXML
	private Label userName;
	@FXML
	private Label company;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setVO(UserVO user) {
		if(user!=null) {
			//TODO
			userName.setText(user.getLogin());
			company.setText(user.getLocation());
		}
	}

}
