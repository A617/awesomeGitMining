package main.ui.utility;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
//private int[] nums;
//private String[] types;


//①集中和记录数据,求出其最大值和最小值.数据的数量应在100个以上,在数量不多的情况下,至少也应在50个以上.
//②将数据分成若干组,并做好记号.分组的数量在5－12之间较为适宜.
//③计算组距的宽度.用组数去除最大值和最小值之差,求出组距的宽度.
//④计算各组的界限位.各组的界限位可以从第一组开始依次计算,第一组的下界为最小值减去组距的一半,第一组的上界为其下界值加上组距.第二组的下界限位为第一组的上界限值,第二组的下界限值加上组距,就是第二组的上界限位,依此类推.
//⑤统计各组数据出现频数,作频数分布表.
//⑥作直方图.以组距为底长,以频数为高,作各组的矩形图.


//此直方图分为12组，采取余数项数据加在最后一项的方式

public class Histogram {
	private static Histogram instance;
	int groupNum=12;//定义组数为12
	int cataDatas[]=new int[groupNum];//分组后的每组数据
	String cataTypes[]=new String[groupNum];//分组后的每组范围段
	public static Histogram getInstance() {
		if(instance==null) {
			instance=new Histogram();
		}
		return instance;
	}
	public BarChart<String,Number> generateHistogram(int[]data,String[]group){
		int originGroupNum=data.length;
		String last="";
	        int size=originGroupNum/groupNum;//size是2
	        for(int j=0;j<groupNum;j++){
	        	cataDatas[j]=0;//初始化
	        }
	        if(originGroupNum%groupNum==0){
	        	for(int i=0;i<groupNum;i++){
		        	for(int k=size*i;k<size*i+size;k++){
		        		cataDatas[i]+=data[k];
		        	}
	        	}
		        	cataTypes[0]=group[0]+"~"+group[size-1];
		        	last=group[size-1];
		        	for(int y=1;y<groupNum;y++){
		        		cataTypes[y]=last+"~"+group[size*(y+1)-1];
		        		last=group[size*(y+1)-1];
		        	}
	        }
	        else{
	        	for(int i=0;i<groupNum;i++){
		        	for(int k=size*i;k<size*i+size;k++){
		        		cataDatas[i]+=data[k];
		        		//cataTypes[i]=type[size*i]+"~"+type[k];
		        	}//剩下的数据是从下标为size*group开始的
		        }
	        	for(int h=groupNum*size;h<originGroupNum;h++){
	        		cataDatas[groupNum-1]+=data[h];
	        	}
	        	//cataTypes[groupNum-1]=type[(groupNum-1)*size]+"~"+type[originGroupNum-1];
	        	cataTypes[0]=group[0]+"~"+group[size-1];
	        	last=group[size-1];
	        	for(int y=1;y<groupNum-1;y++){
	        		cataTypes[y]=last+"~"+group[size*(y+1)-1];
	        		last=group[size*(y+1)-1];
	        	}
	        	cataTypes[groupNum-1]=last+"~"+group[originGroupNum-1];
	        }
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
	        yAxis.setLabel("Number");

	        XYChart.Series series1 = new XYChart.Series();
	        series1.setName("Histogram");
	        for(int i=0;i<cataDatas.length;i++){
	        	 series1.getData().add(new XYChart.Data(cataTypes[i]+"", cataDatas[i]));
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
