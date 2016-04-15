package org.Client.ui.utility;

import org.Client.ui.MainUI;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * @author tj
 * @date 2016年4月15日 上午10:29:56 负责所有label的监听
 */
public class LabelListenerHelper {
	private static int skinNum;
	private static final String styleStr = "-fx-background-color: ";
	private static final String[] enterColors = { "#5d9b78;", "#ff99c7;", "#cad2dd;" };
	private static final String[] baseColors = { "#71af8c;", "#f8aec4;", "#b4b7bb;" };
	
	/**
	 * when change skin style,the label should be reloaded
	 * 
	 * @param skinNum
	 */
	public static void changeSkin(int skinNum) {
		LabelListenerHelper.skinNum = skinNum;
	}
	private static void setImage(Label label, String path) {
		Image image = new Image(MainUI.class.getResourceAsStream("style/" + path));
		label.setGraphic(new ImageView(image));
	}
	/**
	 * 负责那些只变换颜色label的监听
	 * 
	 * @param group
	 */
	public static void addHeadLabelListener(Group group) {
		for (int i = 0; i < group.getChildren().size(); i++) {
			Label label = (Label) group.getChildren().get(i);
			label.setOnMouseEntered(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					label.setStyle(styleStr + enterColors[skinNum]);
				}

			});
			label.setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					label.setStyle(styleStr + enterColors[skinNum]);
				}

			});
			label.setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					label.setStyle(styleStr + baseColors[skinNum]);
				}
			});
		}
	}
	/**
	 * 负责那些变换图片的label
	 * @param label
	 */
	public static void addPicLabelListener(Label label,String[][] path){
		label.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				setImage(label,path[1][skinNum]);
			}
		});
		label.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				setImage(label,path[0][skinNum]);
			}
		});
	}
}
