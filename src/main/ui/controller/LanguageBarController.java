package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.AnchorPane;
import main.business.impl.repository.RepositoryServiceImpl;
import main.business.service.RepositoryService;
import main.ui.utility.BarChartGenerator;
import main.vo.LanguageStatisticsVO;

public class LanguageBarController implements Initializable {
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private AnchorPane pane;
	
	private ObservableList<String> languages = FXCollections.observableArrayList();
	private RepositoryService service;
	private LanguageStatisticsVO vo;
	private int[] nums;
	private BarChartGenerator barChartGenerator;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = RepositoryServiceImpl.getInstance();
		barChartGenerator = new BarChartGenerator(pane,barChart,xAxis,yAxis);
		vo = service.getLanguageStatistics();
		nums = vo.getLanguageNum();
		String[] types = vo.getLanguageType();
		languages.addAll(types);
		xAxis.setCategories(languages);
		barChartGenerator.setData(languages,nums,"Languages");
		barChart.widthProperty().addListener((obs, b, b1) -> {
			Platform.runLater(() -> barChartGenerator.setMaxBarWidth(40, 10));
		});
	}
	
}
