package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import main.ui.utility.Histogram;
import main.vo.StarsStatisticsVO;

public class StarController implements Initializable{
	private static StarController instance;
	private BarChart<String,Number> barchart;
	@FXML
	private AnchorPane starPane;


	public static StarController getInstance() {
		if (instance == null) {
			instance = new StarController();
		}
		return instance;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		instance = this;
	}
	public void setVO(StarsStatisticsVO vo){
		if(vo!=null){
			System.out.println("111");
			barchart=Histogram.getInstance().generateHistogram();
			starPane.getChildren().add(barchart);

		}
		else{
			System.out.println("222");
		}


	}
}
