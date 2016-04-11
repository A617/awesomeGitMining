package org.Client.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.UserService;
import org.Client.ui.MainUI;
import org.Client.ui.utility.SkinConfig;
import org.Common.vo.SimpleUserVO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class UserTagController implements Initializable{
	@FXML
	private Label page;
	@FXML
	private ScrollPane scrollPane;
	private static UserTagController instance;
	private UserService service;
	private int pageNum;
	private List<SimpleUserVO> list;
	private int tempPage;
	private String language;
	
	public UserTagController getInstance() {
		if(instance == null)
			instance = new UserTagController();
		return instance;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = UserServiceImpl.getInstance();
	}
	
	public void setText(String text) {
		this.language = text;
		list = service.getUserByLanguage(text, 0);
		pageNum = service.getTagPageNum();
		page.setText("1 / " + pageNum);
		init();
	}
	
	public void init() {
		VBox box = new VBox();
		VBox.setVgrow(scrollPane, Priority.ALWAYS);
		box.setSpacing(4);

		for (int i = 0; i < 10; i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainUI.class.getResource("config/"+SkinConfig.getInstance().getFxmlResoursePath("singleUserView")));
			try {
				Pane single = (Pane) loader.load();
				SingleUserController controller = loader.getController();
				if (i < list.size()) {
					SimpleUserVO vo = list.get(i);
					controller.setVO(vo);

					box.getChildren().add(single);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		scrollPane.setContent(box);
	}
	
	@FXML
	public void handleNextButton() {
		tempPage++;
		list = service.getUserByLanguage(language, tempPage);
		if (list.size() > 0) {
			init();
		} else {
			tempPage--;
		}
		page.setText(tempPage + 1 + " / " + pageNum);
	}

	@FXML
	public void handlePreButton() {
		tempPage--;
		if (tempPage >= 0) {
			list = service.getUserByLanguage(language,tempPage);
			init();
		} else {
			tempPage++;
		}
		page.setText(tempPage + 1 + " / " + pageNum);
	}


}
