package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.ui.MainUI;
import main.ui.utility.fxmlLoader;

public class MainController implements Initializable {
	private final Delta dragDelta = new Delta();
	private static MainController instance;

	public static MainController getInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}

	@FXML
	private AnchorPane layout;
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
	private boolean selectRepos;
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
		if (panel != null) {
			center_panel.getChildren().clear();
			center_panel.getChildren().add(panel);
			AnchorPane field = fxmlLoader.loadPanel("Ui_TextField.fxml");
			panel.getChildren().add(field);
			field.setLayoutX(10);
			field.setLayoutY(-10);
			// otherwise the searchButton cannot use
			common.toFront();
		}
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
		selectRepos = true;
		enterRepos();
		addDraggableNode(common);
	}

	public void labelInit(Label label, String path) {
		Image image = new Image(MainUI.class.getResourceAsStream("style/" + path));
		label.setGraphic(new ImageView(image));
	}

	@FXML
	public void enterRepos() {
		repository.setStyle(styleStr + enterColor);
	}

	@FXML
	public void enterUser() {
		user.setStyle(styleStr + enterColor);
	}

	@FXML
	public void enterReposSta() {
		repositorySta.setStyle(styleStr + enterColor);
	}

	@FXML
	public void enterUserSta() {
		userSta.setStyle(styleStr + enterColor);
	}

	@FXML
	public void releaseRepos() {
		selectUser = false;
		selectReposSta = false;
		selectUserSta = false;
		selectRepos = true;
		enterRepos();
		user.setStyle(styleStr + baseColor);
		repositorySta.setStyle(styleStr + baseColor);
		userSta.setStyle(styleStr + baseColor);
		setPanel("test.fxml");
	}

	@FXML
	public void releaseUser() {
		selectUser = true;
		selectRepos = false;
		selectReposSta = false;
		selectUserSta = false;
		enterUser();
		repository.setStyle(styleStr + baseColor);
		repositorySta.setStyle(styleStr + baseColor);
		userSta.setStyle(styleStr + baseColor);
		setPanel("Ui_UserPagePanel.fxml");
	}

	@FXML
	public void releaseReposSta() {
		selectReposSta = true;
		selectRepos = false;
		selectUser = false;
		selectUserSta = false;
		enterReposSta();
		user.setStyle(styleStr + baseColor);
		repository.setStyle(styleStr + baseColor);
		userSta.setStyle(styleStr + baseColor);
		setPanel("Ui_ReposSta.fxml");
	}

	@FXML
	public void releaseUserSta() {
		selectUserSta = true;
		selectRepos = false;
		selectUser = false;
		selectReposSta = false;
		enterUserSta();
		user.setStyle(styleStr + baseColor);
		repositorySta.setStyle(styleStr + baseColor);
		repository.setStyle(styleStr + baseColor);
		setPanel("Ui_UserSta.fxml");
	}

	@FXML
	public void exitRepos() {
		if (!selectRepos) {
			repository.setStyle(styleStr + baseColor);
		}
	}

	@FXML
	public void exitUser() {
		if (!selectUser) {
			user.setStyle(styleStr + baseColor);
		}
	}

	@FXML
	public void exitReposSta() {
		if (!selectReposSta) {
			repositorySta.setStyle(styleStr + baseColor);
		}
	}

	@FXML
	public void exitUserSta() {
		if (!selectUserSta) {
			userSta.setStyle(styleStr + baseColor);
		}
	}

	public boolean isSelectRepos() {
		return selectRepos;
	}

	public boolean isSelectUser() {
		return selectUser;
	}

	class Delta {
		double x, y;
	}

	private void addDraggableNode(final Node node) {

		node.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (me.getButton() != MouseButton.MIDDLE) {
					dragDelta.x = me.getSceneX();
					dragDelta.y = me.getSceneY();
				}
			}
		});

		node.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (me.getButton() != MouseButton.MIDDLE) {
					node.getScene().getWindow().setX(me.getScreenX() - dragDelta.x);
					node.getScene().getWindow().setY(me.getScreenY() - dragDelta.y);
				}
			}
		});
	}
}
