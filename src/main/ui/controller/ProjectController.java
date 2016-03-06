package main.ui.controller;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.vo.RepositoryVO;

public class ProjectController implements Initializable {
	private static ProjectController instance;

	@FXML
	private Label projectNameLabel;// 用来显示项目的名字

	@FXML
	private Label profile;// 用来显示项目的介绍
	@FXML
	private Label languageLabel;
	@FXML
	private Label contributorLabel;
	@FXML
	private Label collaboratorLabel;
	@FXML
	private Label forkLabel;

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
		if (vo != null) {
			profile.setText(vo.getDescription());
			projectNameLabel.setText(vo.getFull_name());
			Map<String, Integer> map = vo.getLanguages();
			String languageInfo = "";
//			for (Map.Entry<String, Integer> entry : map.entrySet()) {
//				languageInfo += entry.getKey() +" "+entry.getValue()+"\n";
//			}
			languageLabel.setText(languageInfo);
			String contributorInfo = "";
			for(String name : vo.getContributors_login()){
				contributorInfo+=name+"\n";
			}
			contributorLabel.setText(contributorInfo);
			String collaboratorInfo = "";
			for(String name : vo.getCollaborators_login()){
				collaboratorInfo+=name+"\n";
			}
			collaboratorLabel.setText(collaboratorInfo);
			String forksInfo = "";
//			for(String name : vo.getForks_fullname()){
//				forksInfo+=name+"\n";
//			}
			forkLabel.setText(forksInfo);
		}
	}
}
