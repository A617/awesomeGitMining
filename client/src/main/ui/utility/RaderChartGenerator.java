package main.ui.utility;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

public class RaderChartGenerator {

	private static RaderChartGenerator instance;

	public static RaderChartGenerator getInstance() {
		if (instance == null) {
			instance = new RaderChartGenerator();
		}
		return instance;
	}

	public JPanel createPanel(DefaultCategoryDataset dataset) {
		MySpiderChart spider = new MySpiderChart(dataset);
		// this.setMaxValue(8);
		// spider.setBackgroundAlpha(0.0f);
		// spider.setOutlinePaint(null);

		JFreeChart jfreechart = new JFreeChart(spider);
		// JFreeChart jfreechart = new JFreeChart("Score of
		// Repository(total:8)", TextTitle.DEFAULT_FONT, spider, false);
		// LegendTitle lt = new LegendTitle(spider);
		// lt.setPosition(RectangleEdge.BOTTOM);
		// jfreechart.addSubtitle(lt);
	//	jfreechart.setBorderVisible(false);
		jfreechart.setTextAntiAlias(true);
		jfreechart.setNotify(false);
		// jfreechart.setPadding(null);
		jfreechart.setAntiAlias(true);
		// jfreechart.setPadding(null);
		jfreechart.clearSubtitles();
		jfreechart.setBackgroundPaint(new Color(247,242,229));
		jfreechart.setBackgroundImage(new ImageIcon("src/main/ui/style/raderback.png").getImage());

		ChartPanel chart = new ChartPanel(jfreechart);
		// chart.setBackground(new Color(0, 0, 0, 0));
		return chart;
	}
}
