package org.Client.ui.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Client.ui.MainUI;
import org.Client.ui.utility.BackHandler;
import org.Client.ui.utility.BackObject;
import org.Client.ui.utility.BackType;
import org.Client.ui.utility.SkinConfig;
import org.Common.vo.RepositoryVO;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {

	private static HomeController instance;
	@FXML
	private Label tab_star;
	@FXML
	private Label tab_fork;
	@FXML
	private Label tab_general;
	@FXML
	private Label tab_contributor;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private Label page;
	@FXML
	private Group group;
	@FXML
	private Group Language;
	@FXML
	private Group Year;
	@FXML
	private Group Key;
	@FXML
	private AnchorPane listPane;
	@FXML
	private Label innerBar;
	private VBox box;

	private RepositoryService repositoryImpl;
	// record the pages currently,count from 0
	private int currentPage;
	private int pageNum;

	private List<RepositoryVO> list;
	private final String configPath = "file:src/main/java/org/Client/ui/config/";
	private String[] tagBackColors = { "-fx-background-color:#5d9b78;", "-fx-background-color:#ff99c7;",
			"-fx-background-color:#cad2dd;" };
	private int skinNum;
	private String currentLabel;

	public static HomeController getInstance() {
		if (instance == null) {
			instance = new HomeController();
		}
		return instance;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;
		repositoryImpl = RepositoryServiceImpl.getInstance();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		pageNum = repositoryImpl.getPageNums();
		addTagListener();
		selectTab("showRepositories");
		addLabelListener();
	}

	public void addLabelListener() {
		tab_star.setOnMouseReleased((e) -> {
			selectTab("showReposByStar");
			scrollPane.setVvalue(0);
		});
		tab_fork.setOnMouseReleased((e) -> {
			selectTab("showReposByFork");
			scrollPane.setVvalue(0);
		});
		tab_general.setOnMouseReleased((e) -> {
			selectTab("showRepositories");
			scrollPane.setVvalue(0);
		});
		tab_contributor.setOnMouseReleased((e) -> {
			selectTab("showReposByContribute");
			scrollPane.setVvalue(0);
		});
	}

	public void selectTab(String tabName) {
		currentLabel = tabName;
		page.setText("1 / " + pageNum);
		loadList();
		initTabPane();
	}

	@SuppressWarnings("unchecked")
	public void loadList() {
		try {
			Method method = repositoryImpl.getClass().getMethod(currentLabel, int.class);
			list = (List<RepositoryVO>) method.invoke(repositoryImpl, currentPage);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void initTabPane() {
		box = new VBox();
		VBox.setVgrow(scrollPane, Priority.ALWAYS);
		box.setSpacing(4);
		for (int i = 0; i < 10; i++) {
			FXMLLoader loader = new FXMLLoader();
			try {
				loader.setLocation(
						new URL(configPath + (SkinConfig.getInstance().getFxmlResoursePath("singleReposView"))));
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			try {
				AnchorPane single = (AnchorPane) loader.load();
				SingleRepositoryController controller = loader.getController();
				if (i < list.size()) {
					RepositoryVO vo = list.get(i);
					controller.setVO(vo);
					BackHandler.getInstance()
							.setRepoBack(new BackObject(BackType.HOME_REPO, vo.getFull_name(), pageNum));

					box.getChildren().add(single);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		scrollPane.setContent(box);
		box = null;
	}

	@FXML
	public void handleNextButton() {
		currentPage++;
		loadList();
		if (list.size() > 0) {
			initTabPane();
		} else {
			currentPage--;
		}
		page.setText(currentPage + 1 + " / " + pageNum);
		scrollPane.setVvalue(0);
	}

	@FXML
	public void handlePreButton() {
		currentPage--;
		if (currentPage >= 0) {
			loadList();
			initTabPane();
		} else {
			currentPage++;
		}
		page.setText(currentPage + 1 + " / " + pageNum);
		scrollPane.setVvalue(0);
	}


	/**
	 * jump to another page
	 */
	private void addTagListener() {
		for (int j = 0; j < group.getChildren().size(); j++) {
			Group cg = (Group) group.getChildren().get(j);
			for (int i = 0; i < cg.getChildren().size(); i++) {
				String methodName = "getReposBy" + cg.getId();
				Label label = (Label) cg.getChildren().get(i);
				label.setOnMouseReleased(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						resetTag();
						String text = label.getText();
						// 如果是all，返回初始界面
						if (text.equals("All")) {
							MainController.getInstance().setPanel("repositoryPage.fxml");
							return;
						}
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(MainUI.class.getResource("config/Ui_ReposTagPane.fxml"));
						AnchorPane result = null;
						try {
							result = (AnchorPane) loader.load();
						} catch (IOException e) {
							System.out.println("Ui_ReposTagPane加载失败");
						}
						ReposTagPaneController controller = loader.getController();
						controller.setText(text, methodName);
						listPane.getChildren().clear();
						listPane.getChildren().add(result);
						label.setStyle(tagBackColors[skinNum]);
					}
				});
			}
		}
	}

	/**
	 * 每当选中一个tag时，之前被选中的tag还原到原来的颜色
	 */
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
