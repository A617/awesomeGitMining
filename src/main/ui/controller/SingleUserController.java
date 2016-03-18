package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.business.impl.user.UserServiceImpl;
import main.business.service.UserService;
import main.ui.MainUI;
import main.vo.UserVO;

public class SingleUserController implements Initializable {

	@FXML
	private Label userName;
	@FXML
	private Label location;
	@FXML
	private Label company;
	@FXML
	private Label followers;

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

//			Image image = new Image(MainUI.class.getResourceAsStream("style/place.png"));
//			location.setGraphic(new ImageView(image));
//			location.setText(vo.getLocation());
//			
//			image = new Image(MainUI.class.getResourceAsStream("style/company.png"));
//			location.setGraphic(new ImageView(image));
//			company.setText(vo.getCompany());
//			
//			followers.setText(String.valueOf(vo.getContributions_fullname().size()));
		}
	}

}
