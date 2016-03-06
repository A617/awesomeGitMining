package main.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import main.business.impl.repository.RepositoryServiceImpl;
import main.business.impl.user.UserServiceImpl;
import main.business.service.RepositoryService;
import main.business.service.UserService;
import main.ui.MainUI;
import main.vo.RepositoryVO;

public class HomeController implements Initializable {

	private static HomeController instance;
	@FXML
	private Tab tab_general;
	@FXML
	private Tab tab_star;
	@FXML
	private Tab tab_fork;
	@FXML
	private Tab tab_contributor;

	private RepositoryService impl;
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
		impl = RepositoryServiceImpl.getInstance();
		UserService us = UserServiceImpl.getInstance();
		MainController.getInstance().initSearchId();
		initialShow();
	}

	// 也许这样初始化的时候会快一些吧 T T
	private void initialShow() {
		if (tab_general.isSelected()) {
			generalList = impl.showRepositories(0);
			initTabPane(tab_general, new ScrollPane(), new VBox(), generalList);
		}
		if (tab_star.isSelected()) {
			starList = impl.showReposByStar(0);
			initTabPane(tab_star, new ScrollPane(), new VBox(), starList);
		}
		if (tab_fork.isSelected()) {
			forkList = impl.showReposByFork(0);
			initTabPane(tab_fork, new ScrollPane(), new VBox(), forkList);
		}
		if (tab_contributor.isSelected()) {
			contriList = impl.showReposByContribute(0);
			initTabPane(tab_contributor, new ScrollPane(), new VBox(), contriList);
		}
	}

	private void initTabPane(Tab tab, ScrollPane sp, VBox vb, List<RepositoryVO> list) {
		// Pane->VBox->ScrollPane->VBox
		VBox box = new VBox();
		box.getChildren().add(sp);
		VBox.setVgrow(sp, Priority.ALWAYS);
		for (int i = 0; i < 10; i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainUI.class.getResource("config/Ui_SingleReposView.fxml"));
			try {
				Pane single = (Pane) loader.load();
				SingleRepositoryController controller = loader.getController();
				if (i < list.size()) {
					RepositoryVO vo = list.get(i);
					controller.setVO(vo);
					vb.getChildren().add(single);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sp.setContent(vb);
		}
		tab.setContent(sp);
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

	// handle the general tab pane
	private void generalNext() {
		if (tab_general.isSelected()) {
			generalPage++;
			generalList = impl.showRepositories(generalPage);
			if (generalList.size() > 0) {
				initTabPane(tab_general, new ScrollPane(), new VBox(), generalList);
			}
		}
	}

	private void generalPre() {
		if (tab_general.isSelected()) {
			generalPage--;
			if (generalPage > 0) {
				generalList = impl.showRepositories(generalPage);
				initTabPane(tab_general, new ScrollPane(), new VBox(), generalList);
			}
		}
	}

	// handle the star tab pane
	private void starNext() {
		if (tab_star.isSelected()) {
			starPage++;
			starList = impl.showReposByStar(starPage);
			if (starList.size() > 0) {
				initTabPane(tab_star, new ScrollPane(), new VBox(), starList);
			}
		}
	}

	private void starPre() {
		if (tab_star.isSelected()) {
			starPage--;
			if (starPage > 0) {
				starList = impl.showReposByStar(starPage);
				initTabPane(tab_star, new ScrollPane(), new VBox(), starList);
			}
		}
	}

	// handle the fork tab pane
	private void forkNext() {
		if (tab_fork.isSelected()) {
			forkPage++;
			forkList = impl.showReposByFork(forkPage);
			if (forkList.size() > 0) {
				initTabPane(tab_fork, new ScrollPane(), new VBox(), forkList);
			}
		}
	}

	private void forkPre() {
		if (tab_fork.isSelected()) {
			forkPage--;
			if (forkPage > 0) {
				forkList = impl.showReposByFork(forkPage);
				initTabPane(tab_fork, new ScrollPane(), new VBox(), forkList);
			}
		}
	}

	// handle the contributor tab pane
	private void contriNext() {
		if (tab_contributor.isSelected()) {
			contriPage++;
			contriList = impl.showReposByContribute(contriPage);
			if (contriList.size() > 0) {
				initTabPane(tab_contributor, new ScrollPane(), new VBox(), contriList);
			}
		}
	}

	private void contriPre() {
		if (tab_contributor.isSelected()) {
			contriPage--;
			if (contriPage > 0) {
				contriList = impl.showReposByContribute(contriPage);
				initTabPane(tab_contributor, new ScrollPane(), new VBox(), contriList);
			}
		}
	}

	private void test() {
		generalList = new ArrayList<RepositoryVO>();
		for (int i = 0; i < 200; i++) {
			RepositoryVO vo = new RepositoryVO();
			vo.setFull_name(i + "awesome");
			generalList.add(vo);
		}
	}

}
