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
	public BarChart<String,Number> generateHistogram(){
		    prepareData();
	        groupData();
	        Label labelInfo = new Label();
	        labelInfo.setText(
	            "java.version: " + System.getProperty("java.version") + "\n" +
	            "javafx.runtime.version: " + System.getProperty("javafx.runtime.version")
	        );

	        final CategoryAxis xAxis = new CategoryAxis();//横坐标
	        final NumberAxis yAxis = new NumberAxis();//纵坐标
	        final BarChart<String,Number> barChart =//new一个柱状图
	            new BarChart<>(xAxis,yAxis);
	        barChart.setCategoryGap(0);//让种类间距和柱间距都是0
	        barChart.setBarGap(0);

	        xAxis.setLabel("Range");//横纵坐标的名字
	        yAxis.setLabel("Population");

	        XYChart.Series series1 = new XYChart.Series();
	        series1.setName("Histogram");
	        series1.getData().add(new XYChart.Data("0-10", group[0]));
	        series1.getData().add(new XYChart.Data("10-20", group[1]));
	        series1.getData().add(new XYChart.Data("20-30", group[2]));
	        series1.getData().add(new XYChart.Data("30-40", group[3]));
	        series1.getData().add(new XYChart.Data("40-50", group[4]));

	        series1.getData().add(new XYChart.Data("50-60", group[5]));
	        series1.getData().add(new XYChart.Data("60-70", group[6]));
	        series1.getData().add(new XYChart.Data("70-80", group[7]));
	        series1.getData().add(new XYChart.Data("80-90", group[8]));
	        series1.getData().add(new XYChart.Data("90-100", group[9]));

	        barChart.getData().addAll(series1);

	        VBox vBox = new VBox();
	        vBox.getChildren().addAll(labelInfo, barChart);

	        StackPane root = new StackPane();
	        root.getChildren().add(vBox);

		return barChart;

	}



	 //generate dummy random data
    private void prepareData(){//准备了一千个数字

        Random random = new Random();
        for(int i=0; i<DATA_SIZE; i++){
            data[i] = random.nextInt(100);//随机产生一个大于0小于100的整数
        }
    }

    //count data population in groups
    private void groupData(){//统计每根柱子有多高
        for(int i=0; i<10; i++){
            group[i]=0;
        }
        for(int i=0; i<DATA_SIZE; i++){
            if(data[i]<=10){
                group[0]++;
            }else if(data[i]<=20){
                group[1]++;
            }else if(data[i]<=30){
                group[2]++;
            }else if(data[i]<=40){
                group[3]++;
            }else if(data[i]<=50){
                group[4]++;
            }else if(data[i]<=60){
                group[5]++;
            }else if(data[i]<=70){
                group[6]++;
            }else if(data[i]<=80){
                group[7]++;
            }else if(data[i]<=90){
                group[8]++;
            }else if(data[i]<=100){
                group[9]++;
            }
        }
    }
}
