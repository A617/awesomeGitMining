package org.Client.ui.controller;
import java.net.URL;
import java.util.ResourceBundle;

import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.UserService;
import org.Client.ui.utility.BarChartGenerator;
import org.Common.vo.UserCollaReposNumVO;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/** 
* @author  tj 
* @date 2016年3月31日 上午9:04:20 
*/
public class UserCollaReposController implements Initializable{
	private UserService service;
	private UserCollaReposNumVO vo;
	private static UserCollaReposController instance;
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
	public static UserCollaReposController getInstance() {
		return instance;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = UserServiceImpl.getInstance();
		vo = service.getUserCollaReposNum();
		instance = this;
		int[] nums = vo.getNums();
		String[] types = vo.getRanges();
		barChartGenerator = new BarChartGenerator(pane,barChart,xAxis,yAxis);
		ranges.addAll(types);
		xAxis.setCategories(ranges);
		barChartGenerator.setData(ranges,nums,"collaboration");
		barChart.setCategoryGap(0);
		barChart.setBarGap(0);
	}
}
