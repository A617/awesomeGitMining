package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import main.ui.utility.PieChartGenerator;
import main.vo.CollaboratorVO;
import main.vo.ContributorVO;
import main.vo.LanguageVO;
import main.vo.RepositoryVO;

public class ProjectController implements Initializable {
	private static ProjectController instance;

	@FXML
	private Button btn_back;
	@FXML
	private Label projectNameLabel;// 用来显示项目的名字
	@FXML
	private Label profile;// 用来显示项目的介绍
	@FXML
	private Button btn_clone;
	@FXML
	private TextField url;
	@FXML
	private Label starNum;
	@FXML
	private Label forkNum;
	@FXML
	private Label subNum;
	@FXML
	private Label conNum;
	@FXML
	private Label coNum;
	@FXML
	private AnchorPane piePane;
	@FXML
	private AnchorPane raderPane;
	@FXML
	private Label chart;
	@FXML
	private TableView<ContributorVO> contributorTable;
	@FXML
	private TableView<CollaboratorVO> collaboratorTable;
	@FXML
	private TableColumn<ContributorVO, String> contributorColumn;
	@FXML
	private TableColumn<CollaboratorVO, String> collaboratorColumn;
	
	private Clipboard clipboard;//获取系统剪贴板
	private ClipboardContent content;
	private PieChart piechart;

	public static ProjectController getInstance() {
		if (instance == null) {
			instance = new ProjectController();
		}
		return instance;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;
		clipboard = Clipboard.getSystemClipboard();
		content = new ClipboardContent();
		btn_clone.setOnAction((e) -> {
			content.putString(url.getText());
			clipboard.setContent(content);
			//复制成功之后的反馈
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("copy successfully!");
			alert.showAndWait();
		});
		btn_back.setOnAction((e) -> {
			//TODO
			MainController.getInstance().setPanel("test.fxml");
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
			url.setText(vo.getClone_url());
			starNum.setText(String.valueOf(vo.getStargazers_count()));
			forkNum.setText(String.valueOf(vo.getForks_count()));
			subNum.setText(String.valueOf(vo.getSubscribers_count()));
			conNum.setText(String.valueOf(vo.getContributors_login().size()));
			coNum.setText(String.valueOf(vo.getCollaborators_login().size()));
			
			//piechart
			piechart = PieChartGenerator.getInstance().generateChart(vo.getLanguages());
			piechart.setTitle("Languages");
			piechart.setMaxWidth(340);
			piechart.setMaxHeight(320);
			piePane.getChildren().add(piechart);
			//TODO
			//raderchart
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
	
	//TODO
	//create CategoryDataset
}
