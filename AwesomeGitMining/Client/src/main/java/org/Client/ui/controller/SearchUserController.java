package org.Client.ui.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.UserService;
import org.Client.ui.MainUI;
import org.Client.ui.utility.BackHandler;
import org.Client.ui.utility.BackObject;
import org.Client.ui.utility.BackType;
import org.Client.ui.utility.SkinConfig;
import org.Common.vo.SimpleUserVO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author tj
 * @date 2016年3月15日
 */
public class SearchUserController implements Initializable {
	private static SearchUserController instance;
	private UserService service;
	private List<SimpleUserVO> list;
	private String id;
	private VBox box = new VBox();
	@FXML
	private ScrollPane userPane;
	@FXML
	private Label page;
	@FXML
	private AnchorPane pane;
	private int pageNums;
	private int userPage;
	private final String configPath = "file:src/main/java/org/Client/ui/config/";

	public static SearchUserController getInstance() {
		return instance;
	}

	public SearchUserController() {
		service = UserServiceImpl.getInstance();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		instance = this;
		userPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	public void initPane(List<SimpleUserVO> list) {
		box = new VBox();
		for (int i = 0; i < 10; i++) {
			FXMLLoader loader = new FXMLLoader();
			try {
				loader.setLocation(
						new URL(configPath + (SkinConfig.getInstance().getFxmlResoursePath("singleUserView"))));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Pane single = (Pane) loader.load();
				SingleUserController controller = loader.getController();
				if (i < list.size()) {
					SimpleUserVO vo = list.get(i);
					controller.setVO(vo);
					BackHandler.getInstance().setUserBack(new BackObject(BackType.SEARCH_USER,id,userPage));
					box.getChildren().add(single);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		userPane.setContent(box);
		box = null;
	}
	public void setPage(int num) {
		userPage = num;
		page.setText(userPage + 1 + " / " + pageNums);
	}

	public void setSearchID(String id) {
		this.id = id;
		list = service.searchUser(id, 0);
		pageNums = service.getSearchPageNums(id);
		if (list.size() == 0) {
			Label infoLabel = new Label();
			infoLabel.setLayoutX(200);
			infoLabel.setLayoutY(200);
			infoLabel.setGraphic(
					new ImageView(new Image(MainUI.class.getResourceAsStream("style/404.png"))));
			pane.getChildren().add(infoLabel);
		} else {
			if (pageNums < 1) {
				pageNums = 1;
			}
			page.setText("1 / " + pageNums);
			initPane(list);
		}
	}

	@FXML
	public void handlePre() {
		userPage--;
		if (userPage >= 0) {
			list = service.searchUser(id, userPage);
			initPane(list);
		} else {
			userPage++;
		}
		page.setText(userPage + 1 + " / " + pageNums);
	}

	@FXML
	public void handleNext() {
		userPage++;
		list = service.searchUser(id, userPage);
		if (list.size() > 0) {
			initPane(list);
		} else {
			userPage--;
		}
		page.setText(userPage + 1 + " / " + pageNums);
	}
	public void changeStyle() {
		initPane(list);
	}
}
