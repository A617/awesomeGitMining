package org.Client.ui.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.Client.ui.MainUI;
import org.Client.ui.utility.SkinConfig;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class UserStaPaneController implements Initializable {
	private static UserStaPaneController instance;
	@FXML
	private Label type;
	@FXML
	private Label registerTime;
	@FXML
	private Label collaborate;
	@FXML
	private Label create;
	@FXML
	private Label company;
	@FXML
	private Label follower;
	@FXML
	private Label blank;
	@FXML
	private AnchorPane center;

	private String styleStr = "-fx-background-color: ";
	private String enterColor;
	private String baseColor;
	private boolean selectType;
	private boolean selectRegisterTime;
	private boolean selectCollaborate;
	private boolean selectCreate;
	private boolean selectCompany;
	private boolean selectFollower;
	private final String configPath = "file:src/main/java/org/Client/ui/config/";
	private String[] enterColors = { "#5d9b78;", "#bdc9e7;", "#c9cacc;" };
	private String[] baseColors = { "#71af8c;", "#d4dfff;", "#d5d8dd;" };
	private int skinNum;

	public void setSkinNum(int skinNum) {
		this.skinNum = skinNum;
		this.enterColor = enterColors[skinNum];
		this.baseColor = baseColors[skinNum];
		type.setStyle(styleStr + baseColor);
		registerTime.setStyle(styleStr + baseColor);
		create.setStyle(styleStr + baseColor);
		company.setStyle(styleStr + baseColor);
		follower.setStyle(styleStr + baseColor);
		blank.setStyle(styleStr + baseColor);
		collaborate.setStyle(styleStr + baseColor);
	}
	public static UserStaPaneController getInstance(){
		return instance;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		instance = this;
		//setChart("Ui_UserTypeChar.fxml");
		setSkinNum(SkinConfig.getInstance().getSkinNum());
		blank.setStyle(styleStr + baseColor);
		selectCompany();

	}

	private void setChart(String name) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainUI.class.getResource("config/" + name));
		AnchorPane chart = null;
		try {
			chart = (AnchorPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		center.getChildren().clear();
		if (chart != null)
			center.getChildren().add(chart);
	}

	@FXML
	public void enterType(){
		type.setStyle(styleStr + enterColor);
	}

	@FXML
	public void enterRegisterTime(){
		registerTime.setStyle(styleStr + enterColor);
	}

	@FXML
	public void enterCollaborate(){
		collaborate.setStyle(styleStr + enterColor);
	}

	@FXML
	public void enterCreate(){
		create.setStyle(styleStr + enterColor);
	}
	@FXML
	public void enterCompany(){
		company.setStyle(styleStr + enterColor);
	}
	@FXML
	public void enterFollower(){
		follower.setStyle(styleStr + enterColor);
	}


	@FXML
	public void exitType(){
		if (!selectType) {
			type.setStyle(styleStr + baseColor);
		}
	}

	@FXML
	public void exitRegisterTime(){
		if (!selectRegisterTime) {
			registerTime.setStyle(styleStr + baseColor);
		}
	}
	@FXML
	public void exitCollaborate(){
		if (!selectCollaborate) {
			collaborate.setStyle(styleStr + baseColor);
		}
	}
	@FXML
	public void exitCreate(){
		if (!selectCreate) {
			create.setStyle(styleStr + baseColor);
		}
	}
	@FXML
	public void exitCompany(){
		if (!selectCompany) {
			company.setStyle(styleStr + baseColor);
		}
	}
	@FXML
	public void exitFollower(){
		if (!selectFollower) {
			follower.setStyle(styleStr + baseColor);
		}
	}
	
	@FXML
	public void selectType() {
		selectType = true;
		selectRegisterTime = false;
		selectCreate = false;
		selectCompany = false;
		selectCollaborate=false;
		selectFollower=false;
		enterType();
		registerTime.setStyle(styleStr + baseColor);
		create.setStyle(styleStr + baseColor);
		company.setStyle(styleStr + baseColor);
		collaborate.setStyle(styleStr + baseColor);
		follower.setStyle(styleStr + baseColor);
		setChart("Ui_UserTypeChar.fxml");
	}
	@FXML
	public void selectRegisterTime() {
		selectType = false;
		selectRegisterTime = true;
		selectCreate = false;
		selectCompany = false;
		selectCollaborate=false;
		selectFollower=false;
		enterRegisterTime();
		type.setStyle(styleStr + baseColor);
		create.setStyle(styleStr + baseColor);
		company.setStyle(styleStr + baseColor);
		collaborate.setStyle(styleStr + baseColor);
		follower.setStyle(styleStr + baseColor);
		setChart("Ui_UserRegisTime.fxml");
	}
	@FXML
	public void selectCreate() {
		selectType = false;
		selectRegisterTime = false;
		selectCreate = true;
		selectCompany = false;
		selectCollaborate=false;
		selectFollower=false;
		enterCreate();
		registerTime.setStyle(styleStr + baseColor);
		type.setStyle(styleStr + baseColor);
		company.setStyle(styleStr + baseColor);
		collaborate.setStyle(styleStr + baseColor);
		follower.setStyle(styleStr + baseColor);
		setChart("Ui_UserCreateChart.fxml");
	}

	@FXML
	public void selectCompany() {
		selectType = false;
		selectRegisterTime = false;
		selectCreate = false;
		selectCompany = true;
		selectCollaborate=false;
		selectFollower=false;
		enterCompany();
		registerTime.setStyle(styleStr + baseColor);
		create.setStyle(styleStr + baseColor);
		type.setStyle(styleStr + baseColor);
		collaborate.setStyle(styleStr + baseColor);
		follower.setStyle(styleStr + baseColor);
		setChart("Ui_UserCompanyChart.fxml");
	}

	@FXML
	public void selectCollaborate() {
		selectType = false;
		selectRegisterTime = false;
		selectCreate = false;
		selectCompany = false;
		selectCollaborate=true;
		selectFollower=false;
		enterCollaborate();
		registerTime.setStyle(styleStr + baseColor);
		create.setStyle(styleStr + baseColor);
		company.setStyle(styleStr + baseColor);
		type.setStyle(styleStr + baseColor);
		follower.setStyle(styleStr + baseColor);
		setChart("Ui_UserCollaChart.fxml");
	}
	
	@FXML
	public void selectFollower() {
		selectType = false;
		selectRegisterTime = false;
		selectCreate = false;
		selectCompany = false;
		selectCollaborate=false;
		selectFollower=true;
		enterFollower();
		registerTime.setStyle(styleStr + baseColor);
		create.setStyle(styleStr + baseColor);
		company.setStyle(styleStr + baseColor);
		type.setStyle(styleStr + baseColor);
		collaborate.setStyle(styleStr + baseColor);
		setChart("Ui_Follower.fxml");
	}
}
