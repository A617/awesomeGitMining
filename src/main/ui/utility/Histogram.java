package main.ui.utility;

import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
//private int[] nums;
//private String[] types;
public class Histogram {
	private static Histogram instance;
	 int DATA_SIZE = 1000;
	    int data[] = new int[DATA_SIZE];
	    int group[] = new int[10];
	public static Histogram getInstance() {
		if(instance==null) {
			instance=new Histogram();
		}
		return instance;
	}
	public BarChart<String,Number> generateHistogram(int[]data,String[]group){
	        Label labelInfo = new Label();
	        labelInfo.setText(
	            "java.version: " + System.getProperty("java.version") + "\n" +
	            "javafx.runtime.version: " + System.getProperty("javafx.runtime.version")
	        );

	        final CategoryAxis xAxis = new CategoryAxis();//横坐标
	        final NumberAxis yAxis = new NumberAxis();//纵坐标
	        final BarChart<String,Number> barChart =new BarChart<>(xAxis,yAxis);
	        barChart.setCategoryGap(0);//让种类间距和柱间距都是0
	        barChart.setBarGap(0);

	        xAxis.setLabel("Range");//横纵坐标的名字
	        yAxis.setLabel("Population");

	        XYChart.Series series1 = new XYChart.Series();
	        series1.setName("Histogram");
	        for(int i=0;i<group.length;i++){
	        	 series1.getData().add(new XYChart.Data(group[i]+"", data[i]));
	        }
	        barChart.getData().addAll(series1);
	        barChart.setMaxSize(1166, 720);
	        barChart.setPrefSize(1166, 720);
	        VBox vBox = new VBox();
	        vBox.getChildren().addAll(labelInfo, barChart);

	        StackPane root = new StackPane();
	        root.getChildren().add(vBox);

		return barChart;

	}

}
