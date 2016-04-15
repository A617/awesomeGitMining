package org.Client.ui.controller;
import java.io.IOException;
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

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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
	private Group group;
	@FXML
	private Group Language;
	@FXML
	private Group Company;
	@FXML
	private AnchorPane mainpane;
	private UserService userImpl;
	private static UserPageController instance;
	private List<SimpleUserVO> userVO;
	private int pageNums;
	
	private String[] tagBackColors = { "-fx-background-color:#5d9b78;", "-fx-background-color:#ff99c7;","-fx-background-color:#cad2dd;" };
	private int skinNum;

	public static UserPageController getInstance() {
		return instance;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		instance = this;
		userImpl = UserServiceImpl.getInstance();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		userPage = 0;
		pageNums = userImpl.getPageNums();
		page.setText(1 + " / " + pageNums);

		addTagController();
		setSkinNum(SkinConfig.getInstance().getSkinNum());
	}
	
	public void setSkin(int skinNum) {
		this.skinNum = skinNum;
	}
	
	public void setSkinNum(int skinNum) {
		this.skinNum = skinNum;
		userVO = userImpl.showUsers(userPage);
		initUser(userVO);
	}
	
	public void initUser(List<SimpleUserVO> userVO) {
		VBox box = new VBox();
		VBox.setVgrow(scrollPane, Priority.ALWAYS);
		box.setSpacing(4);

		for (int i = 0; i < 10; i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainUI.class.getResource("config/"+SkinConfig.getInstance().getFxmlResoursePath("singleUserView")));
			try {
				Pane single = (Pane) loader.load();
				SingleUserController controller = loader.getController();
				if (i < userVO.size()) {
					SimpleUserVO vo = userVO.get(i);
					BackHandler.getInstance().setUserBack(new BackObject(BackType.HOME_USER,vo.getLogin(),userPage));
					controller.setVO(vo);

					box.getChildren().add(single);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		scrollPane.setContent(box);
	}
	public void setPage(int num) {
		userPage = num;
		page.setText(userPage+1 + " / " + pageNums);
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

	public void addTagController() {
		for (int j = 0; j < group.getChildren().size(); j++) {
			Group cg = (Group) group.getChildren().get(j);
			for (int i = 0; i < cg.getChildren().size(); i++) {
				String methodName = "getUserBy" + cg.getId();
				Label label = (Label) cg.getChildren().get(i);
				label.setOnMouseReleased(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						resetTag();
						String text = label.getText();

						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(MainUI.class.getResource("config/Ui_UserTagPane.fxml"));
						AnchorPane result = null;
						try {
							result = (AnchorPane) loader.load();
						} catch (IOException e) {
							System.out.println("Ui_UserTagPane加载失败");
						}
						UserTagController controller = loader.getController();
						controller.setText(text, methodName);
						mainpane.getChildren().clear();
						mainpane.getChildren().add(result);
						label.setStyle(tagBackColors[skinNum]);
					}

				});
			}
		}
	}

	private void resetTag() {
		for (int j = 0; j < group.getChildren().size(); j++) {
			Group cg = (Group) group.getChildren().get(j);
			for (int i = 0; i < cg.getChildren().size(); i++) {
				Label label = (Label) cg.getChildren().get(i);
				if (!label.getStyle().isEmpty()) {
					label.setStyle("-fx-background-color:transparent;");
				}
			}
		}
	}

}
