package org.Client.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.Client.ui.MainUI;
import org.Client.ui.utility.SkinHandler;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author tj
 * @date 2016年4月11日 下午3:27:03
 */
public class InfoPaneController implements Initializable {
	@FXML
	private Label yellow;
	@FXML
	private Label pink;
	@FXML
	private Label dark;
	@FXML
	private AnchorPane infoPane;
	@FXML
	private Group group;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (int i = 0; i < group.getChildren().size(); i++) {
			Label label = (Label) group.getChildren().get(i);
			label.setOnMouseReleased((e) -> {
				SkinHandler.changeStyle(label.getId());
				MainController.getInstance().removeInfoPane();
			});
		}
	}
}
