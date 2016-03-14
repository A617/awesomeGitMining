package main.ui.utility;

import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;

public class MySpiderChart extends SpiderWebPlot{
	//一个返回image（saveas...）
	
	public MySpiderChart(CategoryDataset categoryDataset) {
		super(categoryDataset);
	}
}
