package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.ui.MainUI;
import main.ui.utility.fxmlLoader;

public class MainController implements Initializable {

	private static MainController instance;

	public static MainController getInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}

	@FXML
	private AnchorPane center_panel, common;
	@FXML
	private Label minimize;
	@FXML
	private Label exit;
	@FXML
	private Label repository;
	@FXML
	private Label user;
	@FXML
	private Label repositorySta;
	@FXML
	private Label userSta;
	private boolean selectRepos = true;
	private boolean selectUser;
	private boolean selectReposSta;
	private boolean selectUserSta;
	private String styleStr = "-fx-background-color: ";
	private String enterColor = "#5d9b78;";
	private String baseColor = "#67a582;";

	/**
	 * when start the app, init the homePagePanel
	 */
	public void initPanel() {
		setPanel("test.fxml");
	}

	/**
	 * the common method to change the current panel
	 *
	 * @param panel
	 */
	public void setPanel(String name) {
		AnchorPane panel = fxmlLoader.loadPanel(name);
		center_panel.getChildren().clear();
		center_panel.getChildren().add(panel);
		// otherwise the searchButton cannot use
		common.toFront();
	}

	public void setGroup(String name) {
		Group group = fxmlLoader.loadGroup(name);
		center_panel.getChildren().clear();
		center_panel.getChildren().add(group);
		// otherwise the searchButton cannot use
		common.toFront();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		instance = this;
	}

	public void labelInit(Label label, String path) {
		Image image = new Image(MainUI.class.getResourceAsStream("style/" + path));
		label.setGraphic(new ImageView(image));
	}

	@FXML
	public void enterRepos() {
		repository.setStyle(styleStr + enterColor);
		user.setStyle(styleStr + baseColor);
		repositorySta.setStyle(styleStr + baseColor);
		userSta.setStyle(styleStr + baseColor);
	}

	@FXML
	public void enterUser() {
		user.setStyle(styleStr + enterColor);
		repositorySta.setStyle(styleStr + baseColor);
		userSta.setStyle(styleStr + baseColor);
		repository.setStyle(styleStr + baseColor);
	}

	@FXML
	public void enterReposSta() {
		repositorySta.setStyle(styleStr + enterColor);
		user.setStyle(styleStr + baseColor);
		userSta.setStyle(styleStr + baseColor);
		repository.setStyle(styleStr + baseColor);
	}

	@FXML
	public void enterUserSta() {
		userSta.setStyle(styleStr + enterColor);
		repositorySta.setStyle(styleStr + baseColor);
		user.setStyle(styleStr + baseColor);
		repository.setStyle(styleStr + baseColor);
	}

	@FXML
	public void releaseRepos() {
		selectRepos = true;
		selectUser = false;
		selectReposSta = false;
		selectUserSta = false;
		enterRepos();
		setPanel("test.fxml");
	}

	@FXML
	public void releaseUser() {
		selectRepos = false;
		selectUser = true;
		selectReposSta = false;
		selectUserSta = false;
		enterUser();
		setPanel("Ui_UserListPanel.fxml");
	}

	@FXML
	public void releaseReposSta() {
		selectRepos = false;
		selectUser = false;
		selectReposSta = true;
		selectUserSta = false;
		enterReposSta();
		setPanel("Ui_ReposSta.fxml");
	}

	@FXML
	public void releaseUserSta() {
		selectRepos = false;
		selectUser = false;
		selectReposSta = false;
		selectUserSta = true;
		enterUserSta();
		setPanel("Ui_UserSta.fxml");
	}

	@FXML
	public void exitRepos() {
		if(!selectRepos){
			repository.setStyle(styleStr + baseColor);
		}
	}

	@FXML
	public void exitUser() {
		if(!selectUser){
			user.setStyle(styleStr + baseColor);
		}
	}
	@FXML
	public void exitReposSta(){
		if(!selectReposSta){
			repositorySta.setStyle(styleStr + baseColor);
		}
	}
	@FXML
	public void exitUserSta(){
		if(!selectUserSta){
			userSta.setStyle(styleStr + baseColor);
		}
	}
	public boolean isSelectRepos() {
		return selectRepos;
	}

	public boolean isSelectUser() {
		return selectUser;
	}

}
