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

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class UserPageController implements Initializable {

	private int userPage;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private Button btn_previous;
	@FXML
	private Button btn_next;
	@FXML
	private Label page;
	@FXML
	private Group language_group;
	@FXML
	private Group company_group;
	@FXML
	private AnchorPane mainpane;
	
	private UserService userImpl;
	private static UserPageController instance;
	private List<SimpleUserVO> userVO;
	private int pageNums;

	public static UserPageController getInstance() {
		if (instance == null) {
			instance = new UserPageController();
		}
		return instance;
	}

	public void initUser(List<SimpleUserVO> user) {
		VBox box = new VBox();
		VBox.setVgrow(scrollPane, Priority.ALWAYS);
		box.setSpacing(4);

		for (int i = 0; i < 10; i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainUI.class.getResource("config/"+SkinConfig.getInstance().getFxmlResoursePath("singleUserView")));
			try {
				Pane single = (Pane) loader.load();
				SingleUserController controller = loader.getController();
				if (i < user.size()) {
					SimpleUserVO vo = user.get(i);
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
	public void handleUserPre() {
		userPage--;
		if (userPage >= 0) {
			userVO = userImpl.showUsers(userPage);
			initUser(userVO);
		} else {
			userPage++;
		}
		page.setText(userPage+1 + " / " + pageNums);
	}

	@FXML
	public void handleUserNext() {
		userPage++;
		userVO = userImpl.showUsers(userPage);
		if (userVO.size() > 0) {
			initUser(userVO);
		} else {
			userPage--;
		}
		page.setText(userPage+1 + " / " + pageNums);
	}
	
	public void lanTagController() {
		for (int i = 0; i < language_group.getChildren().size(); i++) {
			Label label = (Label) language_group.getChildren().get(i);
			label.setOnMouseReleased(new EventHandler<MouseEvent>() {
				
				@Override
				public void handle(MouseEvent arg0) {
					for (int i = 0; i < language_group.getChildren().size(); i++) {
						Label label = (Label) language_group.getChildren().get(i);
						label.setStyle("-fx-background-color:transparent;");
					}
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainUI.class.getResource("config/Ui_UserTagPane.fxml"));
					AnchorPane result = null;
					try {
						result = (AnchorPane) loader.load();
					} catch (IOException e) {
						System.out.println("Ui_UserTagPane加载失败");
					}
					UserTagController controller = loader.getController();
					controller.setLanguages(label.getText());
					mainpane.getChildren().clear();
					mainpane.getChildren().add(result);
					label.setStyle("-fx-background-color:#5d9b78;");
				}

			});
		}
	}
	
	public void comTagController() {
		for (int i = 0; i < company_group.getChildren().size(); i++) {
			Label label = (Label) company_group.getChildren().get(i);
			label.setOnMouseReleased(new EventHandler<MouseEvent>() {
				
				@Override
				public void handle(MouseEvent arg0) {
					for (int i = 0; i < company_group.getChildren().size(); i++) {
						Label label = (Label) company_group.getChildren().get(i);
						label.setStyle("-fx-background-color:transparent;");
					}
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainUI.class.getResource("config/Ui_UserTagPane.fxml"));
					AnchorPane result = null;
					try {
						result = (AnchorPane) loader.load();
					} catch (IOException e) {
						System.out.println("Ui_UserTagPane加载失败");
					}
					UserTagController controller = loader.getController();
					controller.setCompanys(label.getText());
					mainpane.getChildren().clear();
					mainpane.getChildren().add(result);
					label.setStyle("-fx-background-color:#5d9b78;");
				}

			});
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		userImpl = UserServiceImpl.getInstance();
		userPage = 0;
		userVO = userImpl.showUsers(userPage);
		initUser(userVO);
		pageNums = userImpl.getPageNums();
		page.setText("1 / " + pageNums);
		
		lanTagController();
		comTagController();
	}

}
