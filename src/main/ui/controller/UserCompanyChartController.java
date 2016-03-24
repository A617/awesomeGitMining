package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.util.Duration;
import main.business.impl.user.UserServiceImpl;
import main.business.service.UserService;
import main.vo.UserCompanyVO;

public class UserCompanyChartController implements Initializable{
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	
	private ObservableList<String> companys = FXCollections.observableArrayList();
	private UserService service;
	private int[] nums;
	private XYChart.Series<String, Integer> series;
	private UserCompanyVO vo;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = UserServiceImpl.getInstance();
		vo = service.getCompanyStatistics();
		nums = vo.getNums();
		String[] types = vo.getCompanys();
		companys.addAll(types);
		xAxis.setCategories(companys);
		setData();
		barChart.widthProperty().addListener((obs, b, b1) -> {
			Platform.runLater(() -> setMaxBarWidth(40, 10));
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setData() {
		series = new XYChart.Series();
		for (int i = 0; i < nums.length; i++) {
			XYChart.Data<String, Integer> data = new XYChart.Data(companys.get(i), 0);
			series.getData().add(data);
		}
		barChart.getData().add(series);
		series.setName("Company");
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				int i = 0;
				for (Data<String, Integer> data : series.getData()) {
					data.setYValue(nums[i]);
					i++;
				}
			}
		}));
		yAxis.setAnimated(false);
		tl.play();
	}
	
	private void setMaxBarWidth(double maxBarWidth, double minCategoryGap) {
		double barWidth = 0;
		do {
			double catSpace = xAxis.getCategorySpacing();
			double avilableBarSpace = catSpace - (barChart.getCategoryGap() + barChart.getBarGap());
			barWidth = (avilableBarSpace / barChart.getData().size()) - barChart.getBarGap();
			if (barWidth > maxBarWidth) {
				avilableBarSpace = (maxBarWidth + barChart.getBarGap()) * barChart.getData().size();
				barChart.setCategoryGap(catSpace - avilableBarSpace - barChart.getBarGap());
			}
		} while (barWidth > maxBarWidth);

		do {
			double catSpace = xAxis.getCategorySpacing();
			double avilableBarSpace = catSpace - (minCategoryGap + barChart.getBarGap());
			barWidth = Math.min(maxBarWidth, (avilableBarSpace / barChart.getData().size()) - barChart.getBarGap());
			avilableBarSpace = (barWidth + barChart.getBarGap()) * barChart.getData().size();
			barChart.setCategoryGap(catSpace - avilableBarSpace - barChart.getBarGap());
		} while (barWidth < maxBarWidth && barChart.getCategoryGap() > minCategoryGap);
	}

}
