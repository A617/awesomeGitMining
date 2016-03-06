package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.vo.RepositoryVO;

public class ProjectController implements Initializable {
	private static ProjectController instance;
	

	@FXML
	private Label projectNameLabel;// 用来显示项目的名字

	@FXML
	private Label profile;// 用来显示项目的介绍
	@FXML
	private Label star_num;// 用来显示star数
	@FXML
	private Label fork_num;
	@FXML
	private Label colla_num;
	@FXML
	private Label sub_num;
	@FXML
	private Label contri_num;

	


	public static ProjectController getInstance() {
		if (instance == null) {
			instance = new ProjectController();
		}
		return instance;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;

	}

	public void setVO(RepositoryVO vo) {
		if(vo!=null){
			profile.setText(vo.getDescription());
			projectNameLabel.setText(vo.getName()+"/");
			star_num.setText(vo.getSubscribers_count()+"");
			fork_num.setText(vo.getForks_count()+"");
			colla_num.setText(vo.getCollaborators_login().size()+"");
			sub_num.setText(vo.getSubscribers_count()+"");
			contri_num.setText(vo.getContributors_login().size()+"");
		}
	}
}
