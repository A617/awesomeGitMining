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
import main.business.impl.user.UserServiceImpl;
import main.business.service.UserService;
import main.ui.utility.BarChartGenerator;
import main.vo.UserRegisTimeVO;

public class UserRegisTimeChartController implements Initializable {
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private AnchorPane pane;
	private ObservableList<String> years = FXCollections.observableArrayList();
	private UserService service;
	private int[] nums;
	private UserRegisTimeVO vo;
	private BarChartGenerator barChartGenerator;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = UserServiceImpl.getInstance();
		barChartGenerator = new BarChartGenerator(pane,barChart,xAxis,yAxis);
		vo = service.getRegisTimeStatistics();
		nums = vo.getNums();
		String[] types = vo.getYears();
		years.addAll(types);
		xAxis.setCategories(years);
		barChartGenerator.setData(years,nums,"Year");
		barChart.widthProperty().addListener((obs, b, b1) -> {
			Platform.runLater(() -> barChartGenerator.setMaxBarWidth(40, 10));
		});
	}

}
