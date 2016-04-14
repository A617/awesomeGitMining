package org.Client.ui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.Client.business.impl.user.UserServiceImpl;
import org.Client.business.service.UserService;
import org.Client.ui.utility.ScatterChartGenerator;
import org.Common.vo.FollowersVO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class FollowerController implements Initializable {
	@FXML
	private AnchorPane pane;

	private UserService service;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = UserServiceImpl.getInstance();

		FollowersVO vo = service.getFollowerStatistics();
		List<Integer> follower = vo.getFollowers();
		List<Double> stars = vo.getRepoAvgStars();

		ScatterChartGenerator generator = new ScatterChartGenerator(pane, "Star and Fork");
		generator.setData(follower, stars);
	
	}

}
