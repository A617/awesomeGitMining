package main.ui.controller;

import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import main.vo.CollaboratorVO;
import main.vo.ContributorVO;
import main.vo.ForkVO;
import main.vo.LanguageVO;
import main.vo.RepositoryVO;

public class ProjectController implements Initializable {
	private static ProjectController instance;

	@FXML
	private Label projectNameLabel;// 用来显示项目的名字

	@FXML
	private Label profile;// 用来显示项目的介绍
	@FXML
	private Button project_back;
	@FXML
	private TableView<LanguageVO> languageTable;
	@FXML
	private TableView<ContributorVO> contributorTable;
	@FXML
	private TableView<CollaboratorVO> collaboratorTable;
	@FXML
	private TableView<ForkVO> forkTable;
	@FXML
	private TableColumn<LanguageVO, String> languageColumn;
	@FXML
	private TableColumn<ContributorVO, String> contributorColumn;
	@FXML
	private TableColumn<CollaboratorVO, String> collaboratorColumn;
	@FXML
	private TableColumn<ForkVO, String> forkColumn;

	public static ProjectController getInstance() {
		if (instance == null) {
			instance = new ProjectController();
		}
		return instance;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;
		project_back.setOnAction((e) -> {
			if (MainController.getInstance().getSearchId().equals("")) {
				MainController.getInstance().initPanel();
			} else {
				MainController.getInstance().setPanel("Ui_SearchPanel.fxml");
			}
		});
	}

	public void setVO(RepositoryVO vo) {
		if (vo != null) {
			// set description
			String str = vo.getDescription();
			int size = 100;
			int line = str.length() / size;
			String result = "";
			int i = 0;
			for (i = 0; i < line; i++) {
				result += str.substring(i * size, i * size + size) + "\n";
			}
			profile.setText(result + str.substring(i * size));
			projectNameLabel.setText(vo.getFull_name());
			// languages
			Map<String, Integer> map = vo.getLanguages();
			if (map != null) {
				ObservableList<LanguageVO> lan = FXCollections.observableArrayList();
				for (Entry<String, Integer> entry : map.entrySet()) {
					lan.add(new LanguageVO(entry.getKey() + " : " + entry.getValue() + " lines"));
				}
				languageTable.setItems(lan);
				languageColumn.setCellValueFactory(cellData -> cellData.getValue().getProperty());
			}
			// contributors
			if (vo.getContributors_login() != null) {
				ObservableList<ContributorVO> contributors = FXCollections.observableArrayList();
				for (String name : vo.getContributors_login()) {
					ContributorVO cv = new ContributorVO(name);
					contributors.add(cv);
				}
				contributorTable.setItems(contributors);
				contributorColumn.setCellValueFactory(cellData -> cellData.getValue().getProperty());
			}
			// collaborators
			if (vo.getCollaborators_login() != null) {
				ObservableList<CollaboratorVO> collaborators = FXCollections.observableArrayList();
				for (String name : vo.getCollaborators_login()) {
					CollaboratorVO cv = new CollaboratorVO(name);
					collaborators.add(cv);
				}
				collaboratorTable.setItems(collaborators);
				collaboratorColumn.setCellValueFactory(cellData -> cellData.getValue().getProperty());
			}
			// forks
			if (vo.getForks_fullname() != null) {
				ObservableList<ForkVO> forks = FXCollections.observableArrayList();
				for (String name : vo.getForks_fullname()) {
					ForkVO fv = new ForkVO(name);
					forks.add(fv);
				}
				forkTable.setItems(forks);
				forkColumn.setCellValueFactory(cellData -> cellData.getValue().getProperty());
			}
		}
	}
	private Callback<TableColumn<LanguageVO, String>, TableCell<LanguageVO, String>> getCustomCellFactory(final String color) {
        return new Callback<TableColumn<LanguageVO, String>, TableCell<LanguageVO, String>>() {
			@Override
			public TableCell<LanguageVO, String> call(TableColumn<LanguageVO, String> arg0) {
				TableCell<LanguageVO, String> cell = new TableCell<LanguageVO, String>() {

                    @Override
                    public void updateItem(final String item, boolean empty) {
                        if (item != null) {
                            setText(item);
                            setStyle("-fx-font-family:"+ "\"Microsoft YaHei\"");
                        }
                    }
                };
                return cell;
			}
        };
    }
}
