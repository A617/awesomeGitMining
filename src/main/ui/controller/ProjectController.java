package main.ui.controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import org.jfree.data.category.DefaultCategoryDataset;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import main.business.impl.user.UserServiceImpl;
import main.ui.utility.PieChartGenerator;
import main.ui.utility.RaderChartGenerator;
import main.vo.CollaboratorVO;
import main.vo.ContributorVO;
import main.vo.LanguageVO;
import main.vo.RepositoryVO;
import main.vo.UserVO;

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
	private TableView<ContributorVO> contributorTable;
	@FXML
	private TableView<CollaboratorVO> collaboratorTable;
	@FXML
	private TableColumn<ContributorVO, String> contributorColumn;
	@FXML
	private TableColumn<CollaboratorVO, String> collaboratorColumn;

	private Clipboard clipboard;// 获取系统剪贴板
	private ClipboardContent content;
	private PieChart piechart;
	private DefaultCategoryDataset dataset;
	private SwingNode swingNode;
	private UserVO fullVO;

	public static ProjectController getInstance() {
		if (instance == null) {
			instance = new ProjectController();
		}
		return instance;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;
		dataset = new DefaultCategoryDataset();
		clipboard = Clipboard.getSystemClipboard();
		content = new ClipboardContent();
		btn_clone.setOnAction((e) -> {
			content.putString(url.getText());
			clipboard.setContent(content);
			// 复制成功之后的反馈
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("copy successfully!");
			alert.showAndWait();
		});

		btn_back.setOnAction((e) -> {
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

			// piechart
			piechart = PieChartGenerator.getInstance().generateChart(vo.getLanguages());
			piechart.setTitle("Languages");
			piechart.setMaxWidth(340);
			piechart.setMaxHeight(340);
			piePane.getChildren().add(piechart);

			// raderchart
			createRader(vo.getLanguages());

			// contributors
			if (vo.getContributors_login() != null) {
				ObservableList<ContributorVO> contributors = FXCollections.observableArrayList();
				for (String name : vo.getContributors_login()) {
					ContributorVO cv = new ContributorVO(name);
					contributors.add(cv);
				}
				contributorTable.setItems(contributors);
				contributorColumn.setCellFactory(new ContributorCellFactory());
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
				collaboratorColumn.setCellFactory(new CollaboratorCellFactory());
				collaboratorColumn.setCellValueFactory(cellData -> cellData.getValue().getProperty());
			}
		}
	}

	private Callback<TableColumn<LanguageVO, String>, TableCell<LanguageVO, String>> getCustomCellFactory(
			final String color) {
		return new Callback<TableColumn<LanguageVO, String>, TableCell<LanguageVO, String>>() {
			@Override
			public TableCell<LanguageVO, String> call(TableColumn<LanguageVO, String> arg0) {
				TableCell<LanguageVO, String> cell = new TableCell<LanguageVO, String>() {
					@Override
					public void updateItem(final String item, boolean empty) {
						if (item != null) {
							setText(item);
							setStyle("-fx-font-family:" + "\"Microsoft YaHei\"");
						}
					}
				};
				return cell;
			}
		};
	}

	private void createRader(Map<String, Integer> map) {
		dataset = new DefaultCategoryDataset();
		String group1 = "score";
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			dataset.addValue(entry.getValue(), group1, entry.getKey());
		}
		swingNode = new SwingNode();

		ProgressIndicator pin = new ProgressIndicator(-1);

		raderPane.getChildren().clear();
		raderPane.getChildren().add(pin);

		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				swingNode.setContent(RaderChartGenerator.getInstance().createPanel(dataset));

				updateProgress(1, 1);
				return null;
			}
		};
		pin.progressProperty().bind(task.progressProperty());
		new Thread(task).start();

		pin.progressProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			if (new_val.intValue() == 1) {
				raderPane.getChildren().clear();
				raderPane.getChildren().add(swingNode);
			}
		});
	}

	private class ContributorCellFactory implements Callback<TableColumn<ContributorVO, String>, TableCell<ContributorVO, String>> {

		@Override
		public TableCell<ContributorVO, String> call(TableColumn<ContributorVO, String> arg0) {
			 TextFieldTableCell<ContributorVO, String> cell = new TextFieldTableCell<>();
		        cell.setOnMouseClicked((MouseEvent t) -> {
		            if (t.getClickCount() == 2) {
		            	String temp = cell.getText();
		            	MainController.getInstance().setGroup("Ui_UserPanel.fxml");
		    			fullVO = UserServiceImpl.getInstance().getUser(temp);
		    			
		    			if(fullVO!=null)
		    				UserController.getInstance().setVO(fullVO);
		            }
		        });
		        return cell;
		}	
	}
	
	private class CollaboratorCellFactory implements Callback<TableColumn<CollaboratorVO, String>, TableCell<CollaboratorVO, String>> {

		@Override
		public TableCell<CollaboratorVO, String> call(TableColumn<CollaboratorVO, String> arg0) {
			 TextFieldTableCell<CollaboratorVO, String> cell = new TextFieldTableCell<>();
		        cell.setOnMouseClicked((MouseEvent t) -> {
		            if (t.getClickCount() == 2) {
		            	String temp = cell.getText();
		            	MainController.getInstance().setGroup("Ui_UserPanel.fxml");
		    			fullVO = UserServiceImpl.getInstance().getUser(temp);
		    			
		    			if(fullVO!=null)
		    				UserController.getInstance().setVO(fullVO);
		            }
		        });
		        return cell;
		}	
	}
}
