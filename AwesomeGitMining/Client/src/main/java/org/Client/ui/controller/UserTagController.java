package org.Client.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class UserTagController implements Initializable{
	private static UserTagController instance;
	
	public UserTagController getInstance() {
		if(instance == null)
			instance = new UserTagController();
		return instance;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setText(String text) {
		
	}

}
