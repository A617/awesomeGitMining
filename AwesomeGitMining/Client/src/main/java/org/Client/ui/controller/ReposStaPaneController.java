package org.Client.ui.controller;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Client.ui.MainUI;
import org.Client.ui.utility.SkinConfig;
import org.Common.vo.CreatedTimeStatisticsVO;
import org.Common.vo.ForksStatisticsVO;
import org.Common.vo.LanguageVO;
import org.Common.vo.StarsStatisticsVO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ReposStaPaneController implements Initializable {
	@FXML
	private Label language;
	@FXML
	private Label createTime;
	@FXML
	private Label star;
	@FXML
	private Label fork;
	@FXML
	private Label language_size;
	@FXML
	private Label star_fork;
	@FXML
	private Label blank;
	@FXML
	private AnchorPane center;
	private List<LanguageVO> languageList;
	private List<CreatedTimeStatisticsVO> createList;
	private List<StarsStatisticsVO> starList;
	private List<ForksStatisticsVO> forkList;
	private String styleStr = "-fx-background-color: ";
	private RepositoryService repositoryImpl;
	private static String enterColor;
	private static String baseColor;
	private boolean selectLanguage;
	private boolean selectFork;
	private boolean selectStar;
	private boolean selectCreateTime;
	private boolean selectStar_Fork;
	private boolean selectLanguage_Size;
	private int skinNum;
	private final String configPath = "file:src/main/java/org/Client/ui/config/";
	private String[] enterColors = { "#5d9b78;", "#bdc9e7;", "#c9cacc;" };
	private String[] baseColors = { "#71af8c;", "#d4dfff;", "#d5d8dd;" };

	public void setSkinNum(int skinNum) {
		this.skinNum = skinNum;
		this.enterColor = enterColors[skinNum];
		this.baseColor = baseColors[skinNum];
		language.setStyle(styleStr + baseColor);
		createTime.setStyle(styleStr + baseColor);
		star.setStyle(styleStr + baseColor);
		fork.setStyle(styleStr + baseColor);
//		add.setStyle(styleStr+baseColor);
		language_size.setStyle(styleStr+baseColor);
		star_fork.setStyle(styleStr+baseColor);
		blank.setStyle(styleStr + baseColor);
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setSkinNum(SkinConfig.getInstance().getSkinNum());
		repositoryImpl = RepositoryServiceImpl.getInstance();
		blank.setStyle(styleStr + baseColor);
		selectLanguage();
	}



	private void setChart(String name) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainUI.class.getResource("config/" + name));
		AnchorPane chart = null;
		try {
			chart = (AnchorPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		center.getChildren().clear();
		if (chart != null)
			center.getChildren().add(chart);
	}

	@FXML
	public void enterLanguage(){
		language.setStyle(styleStr + enterColor);
	}
	@FXML
	public void exitLanguage(){
		if (!selectLanguage) {
			language.setStyle(styleStr + baseColor);
		}
	}

	@FXML
	public void selectLanguage() {
		selectLanguage = true;
		selectFork = false;
		selectCreateTime = false;
		selectStar = false;
		selectStar_Fork=false;
		selectLanguage_Size=false;
		enterLanguage();
		star.setStyle(styleStr + baseColor);
		fork.setStyle(styleStr + baseColor);
		createTime.setStyle(styleStr + baseColor);
		star_fork.setStyle(styleStr+baseColor);
		language_size.setStyle(styleStr+baseColor);
		setChart("Ui_ReposSta.fxml");

	}

	@FXML
	public void enterFork(){
		fork.setStyle(styleStr + enterColor);
	}
	@FXML
	public void exitFork(){
		if (!selectFork) {
			fork.setStyle(styleStr + baseColor);
		}
	}
	@FXML
	public void selectFork() {
		selectLanguage = false;
		selectFork = true;
		selectCreateTime = false;
		selectStar = false;
		selectStar_Fork=false;
		selectLanguage_Size=false;
		enterFork();
		language.setStyle(styleStr + baseColor);
		star.setStyle(styleStr + baseColor);
		createTime.setStyle(styleStr + baseColor);
		star_fork.setStyle(styleStr+baseColor);
		language_size.setStyle(styleStr+baseColor);
		setChart("Ui_ForkStatistics.fxml");
	}

	@FXML
	public void enterStar(){
		star.setStyle(styleStr + enterColor);
	}
	@FXML
	public void exitStar(){
		if (!selectStar) {
			star.setStyle(styleStr + baseColor);
		}
	}

	@FXML
	public void selectStar() {
		selectLanguage = false;
		selectFork = false;
		selectCreateTime = false;
		selectStar = true;
		selectStar_Fork=false;
		selectLanguage_Size=false;
		enterStar();
		language.setStyle(styleStr + baseColor);
		fork.setStyle(styleStr + baseColor);
		createTime.setStyle(styleStr + baseColor);
		star_fork.setStyle(styleStr+baseColor);
		language_size.setStyle(styleStr+baseColor);
		setChart("Ui_StarStatistics.fxml");
	}

	@FXML
	public void enterCreateTime(){
		createTime.setStyle(styleStr + enterColor);
	}
	@FXML
	public void exitCreateTime(){
		if (!selectCreateTime) {
			createTime.setStyle(styleStr + baseColor);
		}
	}
	@FXML
	public void selectCreateTime() {
		selectLanguage = false;
		selectFork = false;
		selectCreateTime = true;
		selectStar = false;
		selectStar_Fork=false;
		selectLanguage_Size=false;
		enterCreateTime();
		language.setStyle(styleStr + baseColor);
		fork.setStyle(styleStr + baseColor);
		star.setStyle(styleStr + baseColor);
		star_fork.setStyle(styleStr+baseColor);
		language_size.setStyle(styleStr+baseColor);
		setChart("Ui_CreateTimeChar.fxml");
	}

	@FXML
	public void enterStar_Fork(){
		star_fork.setStyle(styleStr + enterColor);
	}
	
	@FXML
	public void exitStar_Fork(){
		if (!selectStar_Fork) {
			star_fork.setStyle(styleStr + baseColor);
		}
	}
	
	@FXML
	public void selectStar_Fork() {
		selectLanguage = false;
		selectFork = false;
		selectCreateTime = false;
		selectStar = false;
		selectStar_Fork=true;
		selectLanguage_Size=false;
		enterStar_Fork();
		language.setStyle(styleStr + baseColor);
		fork.setStyle(styleStr + baseColor);
		star.setStyle(styleStr + baseColor);
		createTime.setStyle(styleStr + baseColor);
		language_size.setStyle(styleStr + baseColor);
		setChart("Ui_star_fork.fxml");
	}
	
	@FXML
	public void enterLanguage_Size(){
		language_size.setStyle(styleStr + enterColor);
	}
	
	@FXML
	public void exitLanguage_Size(){
		if (!selectLanguage_Size) {
			language_size.setStyle(styleStr + baseColor);
		}
	}
	
	@FXML
	public void selectLanguage_Size() {
		selectLanguage = false;
		selectFork = false;
		selectCreateTime = false;
		selectStar = false;
		selectStar_Fork=false;
		selectLanguage_Size=true;
		enterLanguage_Size();
		language.setStyle(styleStr + baseColor);
		fork.setStyle(styleStr + baseColor);
		star.setStyle(styleStr + baseColor);
		createTime.setStyle(styleStr + baseColor);
		star_fork.setStyle(styleStr + baseColor);
		setChart("Ui_language_size.fxml");
	}
}
