package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

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
import main.vo.ForksStatisticsVO;

public class ForkController implements Initializable{

	private static ForkController instance;
	private ForksStatisticsVO vo;
	private RepositoryService service;

	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private AnchorPane pane;
	
	private ObservableList<String> ranges = FXCollections.observableArrayList();
	private BarChartGenerator barChartGenerator;

	public static ForkController getInstance() {
		return instance;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		instance = this;
		service = RepositoryServiceImpl.getInstance();
		vo=service.getForksStatistics();
		int[] datas=vo.getNums();
		String[] types=vo.getTypes();
		barChartGenerator = new BarChartGenerator(pane,barChart,xAxis,yAxis);
		ranges.addAll(types);
		xAxis.setCategories(ranges);
		barChartGenerator.setData(ranges,datas,"forks");
		barChart.setCategoryGap(0);
		barChart.setBarGap(0);
	}
}
