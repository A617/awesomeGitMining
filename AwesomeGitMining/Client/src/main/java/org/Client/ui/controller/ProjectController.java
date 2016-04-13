package org.Client.ui.controller;
import java.awt.Dimension;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Client.business.service.UserService;
import org.Client.ui.MainUI;
import org.Client.ui.utility.BackHandler;
import org.Client.ui.utility.BackObject;
import org.Client.ui.utility.BackType;
import org.Client.ui.utility.PieChartGenerator;
import org.Client.ui.utility.RaderChartGenerator;
import org.Common.po.Statistics;
import org.Common.vo.CodeFrequencyVO;
import org.Common.vo.CollaboratorVO;
import org.Common.vo.ContributorVO;
import org.Common.vo.RepositoryRateVO;
import org.Common.vo.RepositoryVO;
import org.Common.vo.UserVO;
import org.jfree.data.category.DefaultCategoryDataset;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

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
	private Label stars;
	@FXML
	private Label starLabel;
	@FXML
	private Label forkLabel;
	@FXML
	private Label subLabel;
	@FXML
	private Label conLabel;
	@FXML
	private Label collaLabel;
	@FXML
	private Label forks;
	@FXML
	private Label subs;
	@FXML
	private Label cons;
	@FXML
	private Label collas;
	@FXML
	private StackPane piePane;
	@FXML
	private StackPane raderPane;
	@FXML
	private AnchorPane pane;
	@FXML
	private TableView<ContributorVO> contributorTable;
	@FXML
	private TableView<CollaboratorVO> collaboratorTable;
	@FXML
	private TableColumn<ContributorVO, String> contributorColumn;
	@FXML
	private TableColumn<CollaboratorVO, String> collaboratorColumn;
	@FXML
	private AreaChart<String, Integer> areaChart;

	private Clipboard clipboard;// 获取系统剪贴板
	private ClipboardContent content;
	private PieChart piechart;
	private DefaultCategoryDataset dataset;
	private SwingNode swingNode;
	private UserVO fullVO;
	private RepositoryService repositoryImpl;
	private UserService userImpl;
	private JPanel panel;
	private final XYChart.Series<String, Integer> series = new XYChart.Series<>();
	private RepositoryVO vo;


	@FXML
	public void enterStar(){
		starLabel.setVisible(true);
	}
	@FXML
	public void exitStar(){
		starLabel.setVisible(false);
	}
	@FXML
	public void enterFork(){
		forkLabel.setVisible(true);
	}
	@FXML
	public void exitFork(){
		forkLabel.setVisible(false);
	}
	@FXML
	public void enterCon(){
		conLabel.setVisible(true);
	}
	@FXML
	public void exitCon(){
		conLabel.setVisible(false);
	}
	@FXML
	public void enterSub(){
		subLabel.setVisible(true);
	}
	@FXML
	public void exitSub(){
		subLabel.setVisible(false);
	}
	@FXML
	public void enterColla(){
		collaLabel.setVisible(true);
	}
	@FXML
	public void exitColla(){
		collaLabel.setVisible(false);
	}
	public static ProjectController getInstance() {
		if (instance == null) {
			instance = new ProjectController();
		}
		return instance;
	}

	public void labelInit(Label label, String path) {
		Image image = new Image(MainUI.class.getResourceAsStream("style/" + path));
		label.setGraphic(new ImageView(image));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;
		starLabel.setVisible(false);
		forkLabel.setVisible(false);
		subLabel.setVisible(false);
		conLabel.setVisible(false);
		collaLabel.setVisible(false);
		repositoryImpl = RepositoryServiceImpl.getInstance();
		userImpl = UserServiceImpl.getInstance();

		labelInit(stars, "Star_32.png");
		labelInit(forks, "forks.png");
		labelInit(subs, "subs.png");
		labelInit(cons, "cons.png");
		labelInit(collas, "collas.png");

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
			BackHandler.getInstance().handleRepoBack();
		});
	}

	public void setVO(RepositoryVO vo) {
		if (vo != null) {
			this.vo = vo;
			// set description
			String str = vo.getDescription();
			int size = 90;
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
			piechart.setMaxSize(240, 340);
			piechart.setPrefSize(240, 340);
			piechart.autosize();
			piePane.getChildren().add(piechart);
			piePane.setAlignment(Pos.TOP_CENTER);

			// raderchart
			RepositoryRateVO ratevo = repositoryImpl.showReposRate(vo.getFull_name());
			if (ratevo != null) {
				createRader(ratevo.getRates());
			}

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
			// areaChart
			addAreaChart();
		}
	}

	private void addAreaChart() {
		areaChart.setAnimated(false);
		areaChart.setHorizontalGridLinesVisible(false);
		areaChart.setVerticalGridLinesVisible(false);
		ProgressIndicator pin = new ProgressIndicator(-1);
		pin.setLayoutX(areaChart.getLayoutX() + areaChart.getPrefWidth() / 2);
		pin.setLayoutY(areaChart.getLayoutY() + areaChart.getPrefHeight() / 2);
		pane.getChildren().add(pin);
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				CodeFrequencyVO cv = repositoryImpl.getCodeFrequency(vo.getFull_name());

				int[] data = cv.getData();
				String[] time = cv.getTime();

				for (int j = 0; j < data.length; j++) {
					series.getData().add(new XYChart.Data<String, Integer>(time[j], data[j]));
				}
				updateProgress(1, 1);
				return null;
			}
		};
		pin.progressProperty().bind(task.progressProperty());
		new Thread(task).start();

		pin.progressProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			if (new_val.intValue() == 1) {
				areaChart.getData().add(series);
				series.setName("Addition");
				pane.getChildren().remove(pin);
			}
		});
	}

	private void createRader(Map<String, Double> map) {
		dataset = new DefaultCategoryDataset();
		String group1 = "score";
		for(String s :Statistics.repoRader){
			dataset.addValue(map.get(s), group1, s);
		}
		swingNode = new SwingNode();

		ProgressIndicator pin = new ProgressIndicator(-1);
		pin.setMaxSize(70, 70);

		raderPane.getChildren().clear();
		raderPane.getChildren().add(pin);

		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				panel = RaderChartGenerator.getInstance().createPanel(dataset);
				panel.validate();
				panel.setPreferredSize(new Dimension(330, 330));

				updateProgress(1, 1);
				return null;
			}
		};
		pin.progressProperty().bind(task.progressProperty());
		new Thread(task).start();

		pin.progressProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			if (new_val.intValue() == 1) {
				if (panel != null) {
					swingNode.setContent(panel);
					raderPane.getChildren().clear();
					raderPane.getChildren().add(swingNode);
				}
			}
		});
	}

	private class ContributorCellFactory
			implements Callback<TableColumn<ContributorVO, String>, TableCell<ContributorVO, String>> {

		@Override
		public TableCell<ContributorVO, String> call(TableColumn<ContributorVO, String> arg0) {
			TextFieldTableCell<ContributorVO, String> cell = new TextFieldTableCell<>();
			cell.setOnMouseReleased((MouseEvent t) -> {
				String temp = cell.getText();
				if (temp != null) {
					BackHandler.getInstance().setUserBack(new BackObject(BackType.REPO, projectNameLabel.getText(),0));
					MainController.getInstance().setGroup("Ui_UserPanel.fxml");
					fullVO = userImpl.getUser(temp);
					if (fullVO != null)
						UserController.getInstance().setVO(fullVO);
				}
			});
			cell.setOnMouseEntered((MouseEvent t) -> {
				cell.setCursor(Cursor.HAND);
				cell.setUnderline(true);
			});
			cell.setOnMouseExited((MouseEvent t) -> {
				cell.setCursor(Cursor.DEFAULT);
				cell.setUnderline(false);
			});
			return cell;
		}
	}

	private class CollaboratorCellFactory
			implements Callback<TableColumn<CollaboratorVO, String>, TableCell<CollaboratorVO, String>> {

		@Override
		public TableCell<CollaboratorVO, String> call(TableColumn<CollaboratorVO, String> arg0) {
			TextFieldTableCell<CollaboratorVO, String> cell = new TextFieldTableCell<>();
			cell.setOnMouseReleased((MouseEvent t) -> {
				String temp = cell.getText();
				if (temp != null) {
					BackHandler.getInstance().setUserBack(new BackObject(BackType.REPO, projectNameLabel.getText(),0));
					MainController.getInstance().setGroup("Ui_UserPanel.fxml");
					fullVO = userImpl.getUser(temp);
					if (fullVO != null)
						UserController.getInstance().setVO(fullVO);
				}
			});
			cell.setOnMouseEntered((MouseEvent t) -> {
				cell.setCursor(Cursor.HAND);
				cell.setUnderline(true);
			});
			cell.setOnMouseExited((MouseEvent t) -> {
				cell.setCursor(Cursor.DEFAULT);
				cell.setUnderline(false);
			});
			return cell;
		}
	}
}
