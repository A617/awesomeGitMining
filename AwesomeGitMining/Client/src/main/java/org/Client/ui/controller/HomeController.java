package org.Client.ui.controller;

import java.io.IOException;
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
	private Label tab_general;
	@FXML
	private Label tab_star;
	@FXML
	private Label tab_fork;
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

	private String styleStr = "-fx-background-color: ";
	private String enterColor;
	private String baseColor;
	private boolean selectGeneral;
	private boolean selectStar;
	private boolean selectFork;
	private boolean selectContri;

	private RepositoryService repositoryImpl;
	// record the pages currently,count from 0
	private int generalPage = 0;
	private int starPage = 0;
	private int forkPage = 0;
	private int contriPage = 0;
	private int pageNum;

	private List<RepositoryVO> list;
	private final String configPath = "file:src/main/java/org/Client/ui/config/";
	private String[] enterColors = { "#5d9b78;", "#bdc9e7;", "#c9cacc;" };
	private String[] baseColors = { "#71af8c;", "#d4dfff;", "#d5d8dd;" };
	private String[] tagBackColors = { "-fx-background-color:#5d9b78;", "-fx-background-color:#ff99c7;",
			"-fx-background-color:#cad2dd;" };
	private int skinNum;

	public static HomeController getInstance() {
		if (instance == null) {
			instance = new HomeController();
		}
		return instance;
	}

	public void setSkinNum(int skinNum) {
		this.skinNum = skinNum;
		this.enterColor = enterColors[skinNum];
		this.baseColor = baseColors[skinNum];
		tab_general.setStyle(styleStr + baseColor);
		tab_star.setStyle(styleStr + baseColor);
		tab_contributor.setStyle(styleStr + baseColor);
		tab_fork.setStyle(styleStr + baseColor);
		initTabPane();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;
		repositoryImpl = RepositoryServiceImpl.getInstance();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		pageNum = repositoryImpl.getPageNums();
		addTagListener();
		selectGeneral();
		setSkinNum(SkinConfig.getInstance().getSkinNum());
	}

	@FXML
	public void enterGeneral() {
		tab_general.setStyle(styleStr + enterColor);
	}

	@FXML
	public void enterFork() {
		tab_fork.setStyle(styleStr + enterColor);
	}

	@FXML
	public void enterStar() {
		tab_star.setStyle(styleStr + enterColor);
	}

	@FXML
	public void enterContributor() {
		tab_contributor.setStyle(styleStr + enterColor);
	}

	@FXML
	public void selectGeneral() {
		page.setText("1 / " + pageNum);
		selectStar = false;
		selectFork = false;
		selectContri = false;
		selectGeneral = true;
		enterGeneral();
		tab_fork.setStyle(styleStr + baseColor);
		tab_star.setStyle(styleStr + baseColor);
		tab_contributor.setStyle(styleStr + baseColor);
		list = repositoryImpl.showRepositories(0);
		initTabPane();
	}

	@FXML
	public void selectStar() {
		page.setText("1 / " + pageNum);
		selectStar = true;
		selectFork = false;
		selectContri = false;
		selectGeneral = false;
		enterStar();
		tab_fork.setStyle(styleStr + baseColor);
		tab_general.setStyle(styleStr + baseColor);
		tab_contributor.setStyle(styleStr + baseColor);
		list = repositoryImpl.showReposByStar(0);
		initTabPane();
	}

	@FXML
	public void selectFork() {
		page.setText("1 / " + pageNum);
		selectStar = false;
		selectFork = true;
		selectContri = false;
		selectGeneral = false;
		enterFork();
		tab_general.setStyle(styleStr + baseColor);
		tab_star.setStyle(styleStr + baseColor);
		tab_contributor.setStyle(styleStr + baseColor);
		list = repositoryImpl.showReposByFork(0);
		initTabPane();
	}

	@FXML
	public void selectContributor() {
		page.setText("1 / " + pageNum);
		selectStar = false;
		selectFork = false;
		selectContri = true;
		selectGeneral = false;
		enterContributor();
		tab_fork.setStyle(styleStr + baseColor);
		tab_star.setStyle(styleStr + baseColor);
		tab_general.setStyle(styleStr + baseColor);
		list = repositoryImpl.showReposByContribute(0);
		initTabPane();
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				AnchorPane single = (AnchorPane) loader.load();
				SingleRepositoryController controller = loader.getController();
				if (i < list.size()) {
					RepositoryVO vo = list.get(i);
					controller.setVO(vo);
					BackHandler.getInstance()
							.setRepoBack(new BackObject(BackType.HOME_REPO, vo.getFull_name(), generalPage));

					box.getChildren().add(single);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		scrollPane.setContent(box);
		box = null;
	}

	public void setPage(int num) {
		generalPage = num;
		page.setText(generalPage + 1 + " / " + pageNum);
	}

	@FXML
	public void exitGeneral() {
		if (!selectGeneral) {
			tab_general.setStyle(styleStr + baseColor);
		}
	}

	@FXML
	public void exitFork() {
		if (!selectFork) {
			tab_fork.setStyle(styleStr + baseColor);
		}
	}

	@FXML
	public void exitStar() {
		if (!selectStar) {
			tab_star.setStyle(styleStr + baseColor);
		}
	}

	@FXML
	public void exitContri() {
		if (!selectContri) {
			tab_contributor.setStyle(styleStr + baseColor);
		}
	}

	@FXML
	public void handleNextButton() {
		generalNext();
		starNext();
		forkNext();
		contriNext();
	}

	@FXML
	public void handlePreButton() {
		generalPre();
		starPre();
		forkPre();
		contriPre();
	}

	private void generalNext() {
		if (selectGeneral) {
			generalPage++;
			list = repositoryImpl.showRepositories(generalPage);
			if (list.size() > 0) {
				initTabPane();
			} else {
				generalPage--;
			}
			page.setText(generalPage + 1 + " / " + pageNum);
		}
	}

	private void generalPre() {
		if (selectGeneral) {
			generalPage--;
			if (generalPage >= 0) {
				list = repositoryImpl.showRepositories(generalPage);
				initTabPane();
			} else {
				generalPage++;
			}
			page.setText(generalPage + 1 + " / " + pageNum);
		}
	}

	private void starNext() {
		if (selectStar) {
			starPage++;
			list = repositoryImpl.showReposByStar(starPage);
			if (list.size() > 0) {
				initTabPane();
			} else {
				starPage--;
			}
			page.setText(starPage + 1 + " / " + pageNum);
		}
	}

	private void starPre() {
		if (selectStar) {
			starPage--;
			if (starPage >= 0) {
				list = repositoryImpl.showReposByStar(starPage);
				initTabPane();
			} else {
				starPage++;
			}
			page.setText(starPage + 1 + " / " + pageNum);
		}
	}

	// handle the fork tab pane
	private void forkNext() {
		if (selectFork) {
			forkPage++;
			list = repositoryImpl.showReposByFork(forkPage);
			if (list.size() > 0) {
				initTabPane();
			} else {
				forkPage--;
			}
			page.setText(forkPage + 1 + " / " + pageNum);
		}
	}

	private void forkPre() {
		if (selectFork) {
			forkPage--;
			if (forkPage >= 0) {
				list = repositoryImpl.showReposByFork(forkPage);
				initTabPane();
			} else {
				forkPage++;
			}
			page.setText(forkPage + 1 + " / " + pageNum);
		}
	}

	// handle the contributor tab pane
	private void contriNext() {
		if (selectContri) {
			contriPage++;
			list = repositoryImpl.showReposByContribute(contriPage);
			if (list.size() > 0) {
				initTabPane();
			} else {
				contriPage--;
			}
			page.setText(contriPage + 1 + " / " + pageNum);
		}
	}

	private void contriPre() {
		if (selectContri) {
			contriPage--;
			if (contriPage >= 0) {
				list = repositoryImpl.showReposByContribute(contriPage);
				initTabPane();
			} else {
				contriPage++;
			}
			page.setText(contriPage + 1 + " / " + pageNum);
		}
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
