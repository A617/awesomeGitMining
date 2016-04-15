package org.Client.ui.controller;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;
import java.util.ResourceBundle;

import org.Client.ui.MainUI;
import org.Client.ui.utility.SkinConfig;
import org.Client.ui.utility.fxmlLoader;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {
	private final Delta dragDelta = new Delta();
	private static MainController instance;
	private TrayIcon trayIcon;
	private String enterColor;
	private String baseColor;

	public static MainController getInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}

	@FXML
	private AnchorPane layout;
	@FXML AnchorPane center_panel;
	@FXML
	private AnchorPane common;
	@FXML
	private Label min;
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
	@FXML
	private Label changeStyle;
	@FXML
	private Group headGroup;
	private Label currentHead;
	private AnchorPane infoPane;
	private String styleStr = "-fx-background-color: ";
	private int skinNum;
	private String[] enterColors = { "#5d9b78;", "#ff99c7;", "#cad2dd;" };
	private String[] baseColors = { "#71af8c;", "#f8aec4;", "#b4b7bb;" };
	private String[] exitPics = { "exit_normal.png", "exit_normal_pink.png", "exit_normal_dark.png" };
	private String[] minPics = { "min_normal.png", "min_normal_pink.png", "min_normal_dark.png" };
	private String[] exitEnterPics = { "exit_move.png", "exit_normal_pink.png", "exit_move_dark.png" };
	private String[] minEnterPics = { "min_move.png", "min_move_pink.png", "min_move_dark.png" };

	/**
	 * when start the app, init the homePagePanel
	 */
	public void setSkinNum(int skinNum) {
		this.skinNum = skinNum;
		this.enterColor = enterColors[skinNum];
		this.baseColor = baseColors[skinNum];
		changeLabelStyle();
	}

	public void changeLabelStyle() {
		// 退出和最小化按钮
		labelInit(exit, exitPics[skinNum]);
		labelInit(min, minPics[skinNum]);
		// 导航栏
		repository.setStyle(styleStr + baseColor);
		user.setStyle(styleStr + baseColor);
		repositorySta.setStyle(styleStr + baseColor);
		userSta.setStyle(styleStr + baseColor);
		currentHead.setStyle(styleStr + enterColor);
	}

	public void initPanel() {
		setPanel("repositoryPage.fxml");
	}

	public void setError(){
		
		AnchorPane panel = fxmlLoader.loadPanel("Ui_Error.fxml");
		panel.setLayoutX((center_panel.getWidth()-panel.getPrefWidth())/2);
		panel.setLayoutY((center_panel.getHeight()-panel.getPrefHeight())/2);
		center_panel.getChildren().clear();
		common.setDisable(true);
		center_panel.getChildren().add(panel);
	
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
		// if shows statistics
		if (name.equals("repositoryStaPage.fxml") || name.equals("userStaPage.fxml")) {
			return;
		}
		AnchorPane field = fxmlLoader.loadPanel("Ui_TextField.fxml");
		panel.getChildren().add(field);
		field.setLayoutX(10);
		field.setLayoutY(-10);
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
		// 刚开始默认显示仓库界面
		this.currentHead = repository;
		currentHead.setStyle(styleStr + enterColor);
		addHeadLabelListener();
		setSkinNum(SkinConfig.getInstance().getSkinNum());
		addDraggableNode(common);
		infoPane = fxmlLoader.loadPanel("Ui_InfoPane.fxml");
		infoPane.setLayoutX(changeStyle.getLayoutX());
		infoPane.setLayoutY(changeStyle.getLayoutY() + changeStyle.getPrefHeight());
		// 换肤按钮
		labelInit(changeStyle, "skin1.png");
	}

	public void labelInit(Label label, String path) {
		Image image = new Image(MainUI.class.getResourceAsStream("style/" + path));
		label.setGraphic(new ImageView(image));
	}

	public void addHeadLabelListener() {
		for (int i = 0; i < headGroup.getChildren().size(); i++) {
			Label label = (Label) headGroup.getChildren().get(i);
			label.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					label.setStyle(styleStr + enterColor);
				}

			});
			label.setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					label.setStyle(styleStr + enterColor);
					currentHead = label;
					setPanel(label.getId() + "Page.fxml");
				}

			});
			label.setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					label.setStyle(styleStr + baseColor);
					
				}

			});
		}
	}

	@FXML
	public void enterChange() {
		labelInit(changeStyle, "skin3.png");
	}

	@FXML
	public void exitChange() {
		labelInit(changeStyle, "skin1.png");
	}

	@FXML
	public void releaseChange() {
		labelInit(changeStyle, "skin2.png");
		if (layout.getChildren().contains(infoPane)) {
			layout.getChildren().remove(infoPane);
		} else {
			layout.getChildren().add(infoPane);
		}
	}

	public void removeInfoPane() {
		layout.getChildren().remove(infoPane);
	}

	@FXML
	public void enterExit() {
		labelInit(exit, exitEnterPics[skinNum]);
	}

	@FXML
	public void exitExit() {
		labelInit(exit, exitPics[skinNum]);
	}

	@FXML
	public void releaseExit() {
		labelInit(exit, "exit_active.png");
		SystemTray.getSystemTray().remove(trayIcon);
		Platform.exit();
		return;

	}

	@FXML
	public void enterMin() {
		labelInit(min, minEnterPics[skinNum]);
	}

	@FXML
	public void exitMin() {
		labelInit(min, minPics[skinNum]);
	}

	@FXML
	public void releaseMin() {
		labelInit(min, "min_active.png");
		MainUI.getUI().getStage().setIconified(true);

	}

	public boolean isSelectRepos() {
		return currentHead.getId().equals("repository");
	}

	public boolean isSelectUser() {
		return currentHead.getId().equals("user");
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
