package main.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import main.business.impl.repository.RepositoryServiceImpl;
import main.business.service.RepositoryService;
import main.ui.utility.Histogram;
import main.vo.StarsStatisticsVO;

public class StarController implements Initializable{
	private static StarController instance;
	private BarChart<String,Number> barchart;
	private StarsStatisticsVO vo;
	private RepositoryService service;
	private int[] datas1;
	private String[] types1;
	private int[] datas;
	private String[] types;
	int kind;
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
		service = RepositoryServiceImpl.getInstance();
		vo=service.getStarsStatistics();
		datas1=vo.getNums();
		types1=vo.getTypes();
		int p=0;
		int count=0;
		for(int i=0;i<datas1.length;i++){
			if(datas1[i]!=0){
				count++;
			}
		}
		datas=new int[count];
		types=new String[count];

		for(int u=0;u<datas1.length;u++){
			if(datas1[u]!=0){
			datas[p]=datas1[u];
			types[p]=types1[u];
			p++;
			}
		}
		setVO(vo);
	}
	public void setVO(StarsStatisticsVO vo){
		if(vo!=null){

			System.out.println("111");
			barchart=Histogram.getInstance().generateHistogram(datas,types);
			starPane.getChildren().add(barchart);

		}
		else{
			System.out.println("222");
		}


	}
}
