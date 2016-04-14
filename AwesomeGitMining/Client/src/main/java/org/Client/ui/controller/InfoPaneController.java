package org.Client.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.Client.ui.MainUI;

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
	String[] pics = { "back_dark_icon.png", "back_pink_icon.png", "back_icon.png" };
	String[] enterPics = { "back_dark_icon_flash.png", "back_pink_icon_flash.png", "back_icon_flash.png" };
	private String[] labelIds = { "dark", "pink", "yellow" };

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (int i = 0; i < group.getChildren().size(); i++) {
			Label label = (Label) group.getChildren().get(i);
			labelInit(label, pics[i]);
			label.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					enterLabel(label, label.getId());
				}

			});
			label.setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					enterLabel(label, label.getId());
					MainUI.getUI().changeStyle(label.getId());
					MainController.getInstance().removeInfoPane();
				}

			});
			label.setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					exitLabel(label, label.getId());
				}

			});
		}
	}

	public void labelInit(Label label, String path) {
		Image image = new Image(MainUI.class.getResourceAsStream("style/" + path));
		label.setGraphic(new ImageView(image));
	}

	public void enterLabel(Label label, String id) {
		int index = getIndexById(id);
		String path = enterPics[index];
		labelInit(label,path);
	}
	public int getIndexById(String id){
		int index = -1;
		for (int i = 0; i < labelIds.length; i++) {
			if (id.equals(labelIds[i])) {
				index = i;
				break;
			}
		}
		return index;
	}
	public void exitLabel(Label label,String id){
		int index = getIndexById(id);
		String path = pics[index];
		labelInit(label,path);
	}
}
