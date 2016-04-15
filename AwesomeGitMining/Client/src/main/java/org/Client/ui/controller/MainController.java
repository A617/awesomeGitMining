package org.Client.ui.controller;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;
import java.util.ResourceBundle;

import org.Client.ui.MainUI;
import org.Client.ui.utility.fxmlLoader;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {
	private final Delta dragDelta = new Delta();
	private static MainController instance;
	private TrayIcon trayIcon;

	public static MainController getInstance() {
		return instance;
	}

	@FXML
	private AnchorPane layout;
	@FXML
	AnchorPane center_panel;
	@FXML
	private AnchorPane common;
	@FXML
	private Label min;
	@FXML
	private Label exit;
	@FXML
	private Label changeStyle;
	@FXML
	private Label repositorySta;
	@FXML
	private Label userSta;
	@FXML
	private Label repository;
	@FXML
	private Label user;
	private Label currentHead;
	private AnchorPane infoPane;

	public void initPanel() {
		setPanel("repositoryPage.fxml");
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
		addDraggableNode(common);
		infoPane = fxmlLoader.loadPanel("Ui_InfoPane.fxml");
		infoPane.setLayoutX(changeStyle.getLayoutX());
		infoPane.setLayoutY(changeStyle.getLayoutY() + changeStyle.getPrefHeight());
		addLabelListener();
	}

	public void addLabelListener() {
		repository.setOnMouseReleased((e) -> {
			setPanel("repositoryPage.fxml");
		});
		user.setOnMouseReleased((e) -> {
			setPanel("userPage.fxml");
		});
		repositorySta.setOnMouseReleased((e) -> {
			setPanel("repositoryStaPage.fxml");
		});
		userSta.setOnMouseReleased((e) -> {
			setPanel("userStaPage.fxml");
		});
		exit.setOnMouseReleased((e) -> {
			SystemTray.getSystemTray().remove(trayIcon);
			Platform.exit();
		});
		min.setOnMouseReleased((e) -> {
			MainUI.getUI().setIconified();
		});
		changeStyle.setOnMouseReleased((e) -> {
			if (layout.getChildren().contains(infoPane)) {
				layout.getChildren().remove(infoPane);
			} else {
				layout.getChildren().add(infoPane);
			}
		});
	}

	public void removeInfoPane() {
		layout.getChildren().remove(infoPane);
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
