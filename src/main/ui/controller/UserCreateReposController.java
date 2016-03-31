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
import main.business.impl.user.UserServiceImpl;
import main.business.service.UserService;
import main.ui.utility.BarChartGenerator;
import main.vo.UserCreateReposNumVO;

/**
 * @author tj
 * @date 2016年3月26日 下午5:20:46
 */
public class UserCreateReposController implements Initializable {
	private UserService service;
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private AnchorPane pane;
	private UserCreateReposNumVO vo;

	private ObservableList<String> numbers = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = UserServiceImpl.getInstance();
		vo = service.getUserCreateReposNum();
		int[] datas=vo.getNums();
		String[] types=vo.getRanges();
		BarChartGenerator barChartGenerator = new BarChartGenerator(pane, barChart, xAxis, yAxis);
		numbers.addAll(types);
		xAxis.setCategories(numbers);
		barChartGenerator.setData(numbers,datas,"created repositories");
		barChart.setCategoryGap(0);
		barChart.setBarGap(0);
	}

}
