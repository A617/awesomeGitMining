package org.Client.ui.controller;
import java.net.URL;
import java.util.ResourceBundle;

import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.UserService;
import org.Client.ui.utility.BarChartGenerator;
import org.Common.vo.UserCompanyVO;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.AnchorPane;

public class UserCompanyChartController implements Initializable{
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private AnchorPane pane;
	private ObservableList<String> companys = FXCollections.observableArrayList();
	private UserService service;
	private int[] nums;
	private UserCompanyVO vo;
	private BarChartGenerator barChartGenerator;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = UserServiceImpl.getInstance();
		barChartGenerator = new BarChartGenerator(pane,barChart,xAxis,yAxis);
		vo = service.getUserCompanyStatistics();
		nums = vo.getNums();
		String[] types = vo.getCompany();
		companys.addAll(types);
		xAxis.setCategories(companys);
		barChartGenerator.setData(companys,nums,"Companys");
		barChart.widthProperty().addListener((obs, b, b1) -> {
			Platform.runLater(() -> barChartGenerator.setMaxBarWidth(40, 10));
		});
	}
}
