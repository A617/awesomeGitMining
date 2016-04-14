package org.Client.ui.utility;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class RaderChartGenerator {

	private static RaderChartGenerator instance;

	public static RaderChartGenerator getInstance() {
		if (instance == null) {
			instance = new RaderChartGenerator();
		}
		return instance;
	}

	public JPanel createPanel(DefaultCategoryDataset dataset,String background) {
		MySpiderChart spider = new MySpiderChart(dataset);

		JFreeChart jfreechart = new JFreeChart(spider);
		jfreechart.setTextAntiAlias(true);
		jfreechart.setNotify(false);
		jfreechart.setAntiAlias(true);
		jfreechart.clearSubtitles();
		jfreechart.setBackgroundPaint(new Color(247, 242, 229));
		jfreechart.setBackgroundImage(new ImageIcon(background).getImage());

		ChartPanel chart = new ChartPanel(jfreechart);

		return chart;
	}
}
