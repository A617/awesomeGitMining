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
import main.ui.utility.SkinConfig;
import main.vo.SimpleUserVO;
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
	private SimpleUserVO vo;
	private UserVO fullVO;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		userImpl = UserServiceImpl.getInstance();
		userName.setOnMouseClicked((e) -> {
			MainController.getInstance().setGroup(SkinConfig.getInstance().getFxmlResoursePath("userPanel"));
			
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
