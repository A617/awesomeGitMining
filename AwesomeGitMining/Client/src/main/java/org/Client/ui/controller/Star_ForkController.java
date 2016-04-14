package org.Client.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Client.ui.utility.ScatterChartGenerator;
import org.Common.vo.Star_ForkVO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class Star_ForkController implements Initializable{

	@FXML
	private AnchorPane pane;
	
	private RepositoryService service;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = RepositoryServiceImpl.getInstance();
		
		Star_ForkVO vo = service.getstar_forkStatistics();
		int[] stars = vo.getStar();
		int[] forks = vo.getFork();
	
		ScatterChartGenerator generator = new ScatterChartGenerator(pane,"Star and Fork");
		generator.setData(stars,forks);
	}
	
}
