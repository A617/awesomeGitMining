package main.ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.vo.LanguageVO;
import main.vo.RepositoryVO;

public class ProjectController implements Initializable {
	private static ProjectController instance;

	@FXML
	private Label projectNameLabel;// 用来显示项目的名字

	@FXML
	private Label profile;// 用来显示项目的介绍

	@FXML
	private TableView<LanguageVO> languageTable;
	@FXML
	private TableView<String> contributorTable;
	@FXML
	private TableView<String> collaboratorTable;
	@FXML
	private TableView<String> forkTable;
	@FXML
	private TableColumn<LanguageVO, String> languageColumn;
	@FXML
	private TableColumn<RepositoryVO, List<String>> contributorColumn;
	@FXML
	private TableColumn<RepositoryVO, List<String>> collaboratorColumn;
	@FXML
	private TableColumn<RepositoryVO, List<String>> forkColumn;

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
			if (map != null) {
				ObservableList<LanguageVO> lan = FXCollections.observableArrayList();
				for (Entry<String, Integer> entry : map.entrySet()) {
					lan.add(new LanguageVO(entry.getKey() + ":" + entry.getValue()));
				}
				languageTable.setItems(lan);
				languageColumn.setCellValueFactory(cellData -> cellData.getValue().getLanguage());
			}
			if (vo.getContributors_login() != null) {
				ObservableList<String> contributors = FXCollections.observableArrayList();
				for (String name : vo.getContributors_login()) {
					contributors.add(name);
				}
				contributorTable.setItems(contributors);
			}
			if (vo.getCollaborators_login() != null) {
				ObservableList<String> collaborators = FXCollections.observableArrayList();
				for (String name : vo.getCollaborators_login()) {
					collaborators.add(name);
				}
				collaboratorTable.setItems(collaborators);
			}
			if (vo.getForks_fullname() != null) {
				ObservableList<String> forks = FXCollections.observableArrayList();
				for (String name : vo.getForks_fullname()) {
					forks.add(name);
				}
				forkTable.setItems(forks);
			}
		}
	}
}
