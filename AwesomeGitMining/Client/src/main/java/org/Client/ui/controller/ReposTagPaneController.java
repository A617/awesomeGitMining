package org.Client.ui.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Client.ui.MainUI;
import org.Client.ui.utility.SkinConfig;
import org.Common.vo.RepositoryVO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * @author tj
 * @date 2016年4月5日 下午11:17:41
 */
public class ReposTagPaneController implements Initializable {
	private static ReposTagPaneController instance;
	private RepositoryService service;
	private int pageNum = 0;
	@FXML
	private Label page;
	@FXML
	private ScrollPane scrollPane;
	private List<RepositoryVO> list;
	private int tempPage;
	private String text;
	private String methodName;

	public static ReposTagPaneController getInstance() {
		if (instance == null)
			instance = new ReposTagPaneController();
		return instance;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = RepositoryServiceImpl.getInstance();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	/**
	 * 
	 * @param text
	 *            on the tag label
	 * @param methodName
	 *            such as"getReposByLanguage"
	 */
	public void setText(String text, String methodName) {
		this.text = text;
		this.methodName = methodName;
		this.pageNum = 0;
		refreshList();
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
			loader.setLocation(MainUI.class
					.getResource("config/" + SkinConfig.getInstance().getFxmlResoursePath("singleReposView")));
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
		refreshList();
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
			refreshList();
			initPane();
		} else {
			tempPage++;
		}
		page.setText(tempPage + 1 + " / " + pageNum);
	}

	/**
	 * 通过反射更新list
	 */
	@SuppressWarnings("unchecked")
	private void refreshList() {
		try {
			Method method = service.getClass().getMethod(methodName, String.class,int.class);
			this.list = (List<RepositoryVO>) method.invoke(service, text, tempPage);
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
}
