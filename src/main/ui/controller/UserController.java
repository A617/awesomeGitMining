package main.ui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import main.ui.MainUI;
import main.vo.RepositoryVO;
import main.vo.UserVO;

public class UserController implements Initializable{
	private static UserController instance;
	private MainUI app;

	@FXML
	private Label userNameLabel;
	@FXML
	private Label joinTime;
	@FXML
	private Label Company;
	@FXML
	private Label Eff_num;
	@FXML
	private Label qua_num;
	@FXML
	private Label Tot_num;
	@FXML
	private TreeTableColumn Contri_Pro;
	@FXML
	private TreeTableColumn Crea_Pro;

	private List<UserVO> contriList;
	private List<UserVO> creaList;

	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}
		return instance;
	}

	public void setApp(MainUI app) {
		this.app = app;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		instance=this;
	}

	public void setVO(UserVO vo){
		if(vo!=null){
			userNameLabel.setText(vo.getName());
			joinTime.setText(vo.getCreated_at());
			Company.setText(vo.getLocation()+"/"+vo.getEmail());
//			Eff_num.setText("");
//			qua_num.setText("");
//			Tot_num.setText("");
		}
	}

}
