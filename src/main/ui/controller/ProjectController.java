package main.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import main.ui.MainUI;
import main.vo.RepositoryVO;

public class ProjectController implements Initializable{
	private static ProjectController instance;
	private MainUI app;
	@FXML
	private ScrollPane scrollPane;//所有组件放在这个滚动面板上
	@FXML
	private Label projectNameLabel;//用来显示项目的名字
	@FXML
	private Label creatorNameLabel;//用来显示创建者的名字
	@FXML
	private Label profile;//用来显示项目的介绍
	@FXML
	private Label star_num;//用来显示star数
	@FXML
	private Label fork_num;
	@FXML
	private Label colla_num;
	@FXML
	private Label sub_num;
	@FXML
	private Label contri_num;
	public void setApp(MainUI app){
		this.app=app;
	}
	public static ProjectController getInstance() {
		return instance;
	}

	@FXML
	private void handleSearch(){
		setProjectLabel();
	}

	@FXML
	public void setProjectLabel(){
		projectNameLabel.setText("aaa");
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initPane(scrollPane);

	}
	private void initPane(ScrollPane sp){
//
//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(MainUI.class.getResource("config/Ui_SingleReposView.fxml"));
//		try {
//			Pane single = (Pane) loader.load();
//			SingleRepositoryController controller = loader.getController();
//			RepositoryVO vo = new RepositoryVO();
//			vo.setName("awesome");
//			controller.setVO(vo);
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		projectNameLabel.setText("awesome");
		creatorNameLabel.setText("617");
		star_num.setText("666");
		fork_num.setText("666");
		colla_num.setText("444");
		sub_num.setText("4");
		contri_num.setText("88");

	}
}
