package main.ui.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import main.business.impl.repository.RepositoryServiceImpl;
import main.business.service.RepositoryService;
import main.ui.MainUI;
import main.ui.utility.SkinConfig;
import main.vo.RepositoryVO;

/**
 * @author tj
 * @date 2016年4月5日 下午11:17:41
 */
public class ReposTagPaneController implements Initializable {
	private static ReposTagPaneController instance;
	private RepositoryService service;
	private int pageNum;
	@FXML
	private Label page;
	@FXML
	private ScrollPane scrollPane;
	private List<RepositoryVO> list;
	private int tempPage;
	private String language;

	public static ReposTagPaneController getInstance() {
		return instance;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = RepositoryServiceImpl.getInstance();
		instance = this;

	}

	public void setText(String text) {
		this.language = text;
		list = service.getReposByLanguage(text, 0);
		pageNum = service.getTagPageNum();
		page.setText("1 / " + pageNum);
		initPane();
	}

	private void initPane() {
		VBox box = new VBox();
		VBox.setVgrow(scrollPane, Priority.ALWAYS);
		box.setSpacing(4);
		for (int i = 0; i < 10; i++) {
			FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainUI.class.getResource("config/"+SkinConfig.getInstance().getFxmlResoursePath("singleReposView")));
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
	}

	@FXML
	public void handleNextButton() {
		tempPage++;
		list = service.getReposByLanguage(language, tempPage);
		if (list.size() > 0) {
			initPane();
		} else {
			tempPage--;
		}
		page.setText(tempPage + 1 + " / " + pageNum);
	}

	@FXML
	public void handlePreButton() {
		tempPage--;
		if (tempPage >= 0) {
			list = service.showRepositories(tempPage);
			initPane();
		} else {
			tempPage++;
		}
		page.setText(tempPage + 1 + " / " + pageNum);
	}

}
