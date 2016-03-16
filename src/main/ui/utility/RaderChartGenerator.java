package main.ui.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RaderChartGenerator {

	private String path = "src/main/ui/style/spider.png";
	private static RaderChartGenerator instance;
	
	public static RaderChartGenerator getInstance() {
		if(instance == null) {
			instance = new RaderChartGenerator();
		}
		return instance;
	}
	
	public void createChart(DefaultCategoryDataset dataset) {
		MySpiderChart spider = new MySpiderChart(dataset);
		JFreeChart jfreechart = new JFreeChart("Score of Repository(total:8)", TextTitle.DEFAULT_FONT, spider, false);
		LegendTitle lt = new LegendTitle(spider);
		lt.setPosition(RectangleEdge.BOTTOM);
		jfreechart.addSubtitle(lt);
		saveAsPng(jfreechart);
	}
	
	private void saveAsPng(JFreeChart chart){
		FileOutputStream out = null;
		try {
			File outFile = new File(path);
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			out = new FileOutputStream(path);
	
			ChartUtilities.writeChartAsPNG(out,chart,300,300);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
