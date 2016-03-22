package main.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import main.business.impl.repository.RepositoryServiceImpl;
import main.business.service.RepositoryService;
import main.ui.MainUI;
import main.ui.utility.BackType;
import main.ui.utility.HandleBack;
import main.vo.RepositoryVO;

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

	private List<RepositoryVO> generalList;
	private List<RepositoryVO> starList;
	private List<RepositoryVO> forkList;
	private List<RepositoryVO> contriList;

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
		selectGeneral();
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
			loader.setLocation(MainUI.class.getResource("config/Ui_SingleReposView.fxml"));
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
		}
	}
}
