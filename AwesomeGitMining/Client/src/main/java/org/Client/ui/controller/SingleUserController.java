package org.Client.ui.controller;
import java.net.URL;
import java.util.ResourceBundle;

import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.UserService;
import org.Client.ui.MainUI;
import org.Common.vo.SimpleUserVO;
import org.Common.vo.UserVO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	private SimpleUserVO vo;
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

	public void setVO(SimpleUserVO vo) {
		this.vo=vo;
		if(vo!=null) {
			userName.setText(vo.getLogin());

			Image image1 = new Image(MainUI.class.getResourceAsStream("style/place.png"));
			location.setGraphic(new ImageView(image1));
			location.setText(vo.getLocation());
			
			Image image2 = new Image(MainUI.class.getResourceAsStream("style/company.png"));
			company.setGraphic(new ImageView(image2));
			company.setText(vo.getCompany());
			
			followers.setText(String.valueOf(vo.getFollowers()));
		}
	}

}
