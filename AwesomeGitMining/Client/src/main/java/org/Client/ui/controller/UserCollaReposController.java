package org.Client.ui.controller;
import java.net.URL;
import java.util.ResourceBundle;

import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.UserService;
import org.Common.vo.UserCollaReposNumVO;

import javafx.fxml.Initializable;

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
