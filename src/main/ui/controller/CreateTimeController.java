package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import main.business.impl.repository.RepositoryServiceImpl;
import main.business.service.RepositoryService;
import main.vo.CreatedTimeStatisticsVO;

public class CreateTimeController implements Initializable {
	@FXML
	private LineChart<String, Integer> lineChart;
	@FXML
	private RepositoryService service;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		series.setName("time");
		service = RepositoryServiceImpl.getInstance();
		CreatedTimeStatisticsVO vo = service.getCreatedTimeStatistics();
		int[] nums = vo.getNum();
		String[] year = vo.getTimes();
		for (int i = 0; i < nums.length; i++) {
			series.getData().add(new XYChart.Data<String, Integer>(year[i], nums[i]));
		}
		lineChart.getData().add(series);
	}
}
