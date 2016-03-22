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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import main.business.impl.repository.RepositoryServiceImpl;
import main.business.service.RepositoryService;
import main.vo.LanguageStatisticsVO;

public class LanguageBarController implements Initializable {
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private AnchorPane pane;
	private ObservableList<String> languages = FXCollections.observableArrayList();
	private RepositoryService service;
	private LanguageStatisticsVO vo;
	private int typeNum;
	private int[] nums;
	private XYChart.Series<String, Integer> series;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = RepositoryServiceImpl.getInstance();
		vo = service.getLanguageStatistics();
		nums = vo.getLanguageNum();
		String[] types = vo.getLanguageType();
		typeNum = types.length;
		languages.addAll(types);
		xAxis.setCategories(languages);
		setData();
		barChart.widthProperty().addListener((obs, b, b1) -> {
			Platform.runLater(() -> setMaxBarWidth(40, 10));
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setData() {
		series = new XYChart.Series();
		for (int i = 0; i < typeNum; i++) {
			XYChart.Data<String, Integer> data = new XYChart.Data(languages.get(i), 0);
			series.getData().add(data);
		}
		barChart.getData().add(series);
		series.setName("Language");
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
