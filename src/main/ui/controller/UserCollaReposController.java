package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import main.business.impl.user.UserServiceImpl;
import main.business.service.UserService;
import main.vo.UserCollaReposNumVO;

/** 
* @author  tj 
* @date 2016年3月31日 上午9:04:20 
*/
public class UserCollaReposController implements Initializable{
	private UserService service;
	private UserCollaReposNumVO vo;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = UserServiceImpl.getInstance();
		vo = service.getUserCollaReposNum();
	}

}
