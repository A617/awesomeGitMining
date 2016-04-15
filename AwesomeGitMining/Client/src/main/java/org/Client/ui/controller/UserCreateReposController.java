package org.Client.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.UserService;
import org.Client.ui.utility.PieChartGenerator;
import org.Common.vo.UserCreateReposNumVO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

/**
 * @author tj
 * @date 2016年3月26日 下午5:20:46
 */
public class UserCreateReposController implements Initializable {
	private UserService service;
	private UserCreateReposNumVO vo;
	@FXML
	private PieChart pieChart;
	@FXML
	private AnchorPane pane;
	private ObservableList<PieChart.Data> pieChartData;
	private int[] data;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = UserServiceImpl.getInstance();
		vo = service.getUserCreateReposNum();
		data = vo.getNums();
		String[] types = vo.getRanges();
		pieChartData = FXCollections.observableArrayList();
		// set data
		for (int i = 0; i < data.length; i++) {
			pieChartData.add(new PieChart.Data(types[i], data[i]));
		}
		PieChartGenerator generator = new PieChartGenerator(pane,pieChart,pieChartData);
		generator.setData();
	}

}
