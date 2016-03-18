package main.ui.utility;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

public class RaderChartGenerator{

	private static RaderChartGenerator instance;
	
	public static RaderChartGenerator getInstance() {
		if(instance == null) {
			instance = new RaderChartGenerator();
		}
		return instance;
	}
	
	public JPanel createPanel(DefaultCategoryDataset dataset) {
		SpiderWebPlot spider = new SpiderWebPlot(dataset);
		//this.setMaxValue(8);
		spider.setBackgroundAlpha(0.0f);
		//spider.setOutlinePaint(null);
		
		JFreeChart jfreechart = new JFreeChart("Score of Repository(total:8)", TextTitle.DEFAULT_FONT, spider, false);
		LegendTitle lt = new LegendTitle(spider);
		lt.setPosition(RectangleEdge.BOTTOM);
		jfreechart.addSubtitle(lt);
		jfreechart.setBackgroundImage(new ImageIcon("src/main/ui/style/raderback.png").getImage());
	
		ChartPanel chart = new ChartPanel(jfreechart);
		return chart;
	}
}
