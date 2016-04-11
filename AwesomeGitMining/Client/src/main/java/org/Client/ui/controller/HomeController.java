package org.Client.ui.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Client.ui.MainUI;
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
	private Group group_language;
	@FXML
	private Group group_year;
	@FXML
	private AnchorPane listPane;
	private VBox box;

	private String styleStr = "-fx-background-color: ";
	private String enterColor = "#5d9b78;";
	private String baseColor = "#71af8c;";

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
	private List<RepositoryVO> generalList;
	private List<RepositoryVO> starList;
	private List<RepositoryVO> forkList;
	private List<RepositoryVO> contriList;
	private final String configPath = "file:src/main/java/org/Client/ui/config/";

	public static HomeController getInstance() {
		if (instance == null) {
			instance = new HomeController();
		}
		return instance;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		repositoryImpl = RepositoryServiceImpl.getInstance();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		pageNum = repositoryImpl.getPageNums();
		selectGeneral();
		addTagListener();
		addTagListener2();
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
		generalList = repositoryImpl.showRepositories(0);
		initTabPane(generalList);
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
		starList = repositoryImpl.showReposByStar(0);
		initTabPane(starList);
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
		forkList = repositoryImpl.showReposByFork(0);
		initTabPane(forkList);
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
		contriList = repositoryImpl.showReposByContribute(0);
		initTabPane(contriList);
	}

	private void initTabPane(List<RepositoryVO> list) {
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
			generalList = repositoryImpl.showRepositories(generalPage);
			if (generalList.size() > 0) {
				initTabPane(generalList);
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
				generalList = repositoryImpl.showRepositories(generalPage);
				initTabPane(generalList);
			} else {
				generalPage++;
			}
			page.setText(generalPage + 1 + " / " + pageNum);
		}
	}

	private void starNext() {
		if (selectStar) {
			starPage++;
			starList = repositoryImpl.showReposByStar(starPage);
			if (starList.size() > 0) {
				initTabPane(starList);
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
				starList = repositoryImpl.showReposByStar(starPage);
				initTabPane(starList);
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
			forkList = repositoryImpl.showReposByFork(forkPage);
			if (forkList.size() > 0) {
				initTabPane(forkList);
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
				forkList = repositoryImpl.showReposByFork(forkPage);
				initTabPane(forkList);
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
			contriList = repositoryImpl.showReposByContribute(contriPage);
			if (contriList.size() > 0) {
				initTabPane(contriList);
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
				contriList = repositoryImpl.showReposByContribute(contriPage);
				initTabPane(contriList);
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
		for (int i = 0; i < group_language.getChildren().size(); i++) {
			Label label = (Label) group_language.getChildren().get(i);
			label.setOnMouseReleased(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// 把其他tag变回原来的颜色
					for (int i = 0; i < group_language.getChildren().size(); i++) {
						Label label = (Label) group_language.getChildren().get(i);
						label.setStyle("-fx-background-color:transparent;");
					}
					String text = label.getText();
					// 如果是all，返回初始界面
					if (text.equals("All")) {
						MainController.getInstance().setPanel("main.fxml");
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
					controller.setText(text);
					listPane.getChildren().clear();
					listPane.getChildren().add(result);
					label.setStyle("-fx-background-color:#5d9b78;");
				}

			});
		}
	}

	private void addTagListener2() {
		for (int i = 0; i < group_year.getChildren().size(); i++) {
			Label label = (Label) group_year.getChildren().get(i);
			label.setOnMouseReleased(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					for (int i = 0; i < group_year.getChildren().size(); i++) {
						Label label = (Label) group_year.getChildren().get(i);
						label.setStyle("-fx-background-color:transparent;");
					}
					// String text = label.getText();
					// FXMLLoader loader = new FXMLLoader();
					// loader.setLocation(MainUI.class.getResource("config/Ui_ReposTagPane.fxml"));
					// AnchorPane result = null;
					// try {
					// result = (AnchorPane) loader.load();
					// } catch (IOException e) {
					// System.out.println("Ui_ReposTagPane加载失败");
					// }
					// ReposTagPaneController controller =
					// loader.getController();
					// controller.setText(text);
					listPane.getChildren().clear();
					// listPane.getChildren().add(result);
					label.setStyle("-fx-background-color:#5d9b78;");
				}

			});
		}

	}

}
