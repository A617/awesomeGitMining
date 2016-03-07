package main.ui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import main.business.impl.user.UserServiceImpl;
import main.business.service.UserService;
import main.vo.UserVO;

public class UserController implements Initializable{
	private static UserController instance;

	@FXML
	private Label userNameLabel;
	@FXML
	private Button user_back;
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
	private TableColumn Contri_Pro;//填入此用户贡献的项目
	@FXML
	private TableView Contri_Pro_View;
	@FXML
	private TableColumn Crea_Pro;//填入此用户创造的项目
	@FXML
	private TableView Crea_Pro_View;

	private UserService impl;
	private List<String> contriList;
	private List<String> creaList;

	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}
		return instance;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		impl = UserServiceImpl.getInstance();
		UserService us = UserServiceImpl.getInstance();
		instance=this;

	}

	public void setVO(UserVO vo){
		if(vo!=null){
			userNameLabel.setText(vo.getName());
			joinTime.setText(vo.getCreated_at());
			Company.setText(vo.getLocation()+"/"+vo.getEmail());
			contriList=impl.getContributeRepos(vo.getName());
			creaList=impl.getCreateRepos(vo.getName());

//			Eff_num.setText("");
//			qua_num.setText("");
//			Tot_num.setText("");
		}
	}

}
