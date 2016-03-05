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
import main.business.service.RepositoryService;
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
		if(instance == null) {
			instance = new HomeController();
		}
		return instance;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		impl = RepositoryServiceImpl.getInstance();
		test();
		generalList = impl.showRepositories();
		starList = impl.showReposByStar();
		forkList = impl.showReposByFork();
		contriList = impl.showReposByContribute();
		initTabPane(tab_general,new ScrollPane(),new VBox(), generalList, 0);
		initTabPane(tab_star,new ScrollPane(), new VBox(), starList, 0);
		initTabPane(tab_fork,new ScrollPane(), new VBox(), forkList, 0);
		initTabPane(tab_contributor,new ScrollPane(), new VBox(), contriList, 0);
	}

	private void initTabPane(Tab tab,ScrollPane sp, VBox vb, List<RepositoryVO> list, int startIndex) {
		// Pane->VBox->ScrollPane->VBox
		VBox box = new VBox();
		box.getChildren().add(sp);
		VBox.setVgrow(sp, Priority.ALWAYS);
		for (int i = startIndex; i < 10+startIndex; i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainUI.class.getResource("config/Ui_SingleReposView.fxml"));
			try {
				Pane single = (Pane) loader.load();
				SingleRepositoryController controller = loader.getController();
				if (!list.isEmpty()) {
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
			if (generalPage * 10 < generalList.size()) {
				initTabPane(tab_general,new ScrollPane(), new VBox(), generalList, generalPage*10);
			}
			System.out.println(generalPage);
		}
	}

	private void generalPre() {
		if (tab_general.isSelected()) {
			if (generalPage > 0) {
				initTabPane(tab_general,new ScrollPane(), new VBox(), generalList, (--generalPage)*10);
			}
		}
	}
	// handle the star tab pane
		private void starNext() {
			if (tab_star.isSelected()) {
				starPage++;
				if (starPage * 10 < starList.size()) {
					initTabPane(tab_star,new ScrollPane(), new VBox(), starList, starPage*10);
				}
			}
		}

		private void starPre() {
			if (tab_star.isSelected()) {
				if (starPage > 0) {
					initTabPane(tab_star,new ScrollPane(), new VBox(), starList, (--starPage)*10);
				}
			}
		}
		// handle the fork tab pane
		private void forkNext() {
			if (tab_fork.isSelected()) {
				forkPage++;
				if (forkPage * 10 < forkList.size()) {
					initTabPane(tab_fork,new ScrollPane(), new VBox(), forkList, forkPage*10);
				}
			}
		}

		private void forkPre() {
			if (tab_fork.isSelected()) {
				if (forkPage > 0) {
					initTabPane(tab_fork,new ScrollPane(), new VBox(), forkList, (--forkPage)*10);
				}
			}
		}
		// handle the contributor tab pane
		private void contriNext() {
			if (tab_contributor.isSelected()) {
				contriPage++;
				if (contriPage * 10 < contriList.size()) {
					initTabPane(tab_contributor,new ScrollPane(), new VBox(), contriList, contriPage*10);
				}
			}
		}

		private void contriPre() {
			if (tab_contributor.isSelected()) {
				if (contriPage > 0) {
					initTabPane(tab_contributor,new ScrollPane(), new VBox(), contriList, (--contriPage)*10);
				}
			}
		}
		
	private void test(){
		generalList = new ArrayList<RepositoryVO>();
		for(int i = 0;i<200;i++){
			RepositoryVO vo = new RepositoryVO();
			vo.setName(i+"awesome");
			generalList.add(vo);
		}
	}

}
