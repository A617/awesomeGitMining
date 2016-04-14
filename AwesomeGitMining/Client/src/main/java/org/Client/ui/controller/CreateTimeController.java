package org.Client.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Client.ui.utility.LineChartGenerator;
import org.Common.vo.CreatedTimeStatisticsVO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class CreateTimeController implements Initializable {
	@FXML
	private AnchorPane pane;
	
	private RepositoryService service;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = RepositoryServiceImpl.getInstance();
		
		CreatedTimeStatisticsVO vo = service.getCreatedTimeStatistics();
		int[] nums = vo.getNum();
		String[] year = vo.getTimes();
		
		LineChartGenerator generator = new LineChartGenerator(pane,"Create Time");
		generator.setData(year,nums);
	}
}
