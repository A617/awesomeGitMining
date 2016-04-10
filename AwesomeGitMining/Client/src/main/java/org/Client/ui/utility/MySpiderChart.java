package org.Client.ui.utility;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.math.BigDecimal;

import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;
/**
 * 重写spiderWebPlot可以显示刻度及对应数据
 * @author user
 *
 */
public class MySpiderChart extends SpiderWebPlot{
	
	private static final long serialVersionUID = 1L;
	
	private int maxValue=8;
	
	public MySpiderChart(CategoryDataset categoryDataset) {
		super(categoryDataset);
		this.setSeriesPaint(Color.PINK);
		this.setSeriesOutlinePaint(Color.pink);
		this.setSeriesOutlineStroke(new BasicStroke(2));
		this.setMaxValue(8);
		this.setBackgroundAlpha(0);
		this.setAxisLinePaint(new Color(255, 255,255,0));
	}
	
	@Override
	public void drawOutline(Graphics2D g2, Rectangle2D area) {
	}
	
	
	
	@Override
	protected void drawRadarPoly(Graphics2D g2, Rectangle2D plotArea, Point2D centre, PlotRenderingInfo info,
			int series, int catCount, double headH, double headW) {
		// TODO Auto-generated method stub
		super.drawRadarPoly(g2, plotArea, centre, info, series, catCount, 8,8);
		
		
		
		for (int cat = 0; cat < catCount; cat++) {

            Number dataValue = getPlotValue(series, cat);

            if (dataValue != null) {
                double value = dataValue.doubleValue();

                if (value >= 0) { // draw the polygon series...

                    // Finds our starting angle from the centre for this axis

                    double angle = getStartAngle()
                        + (getDirection().getFactor() * cat * 360 / catCount);

                    // The following angle calc will ensure there isn't a top
                    // vertical axis - this may be useful if you don't want any
                    // given criteria to 'appear' move important than the
                    // others..
                    //  + (getDirection().getFactor()
                    //        * (cat + 0.5) * 360 / catCount);

                    // find the point at the appropriate distance end point
                    // along the axis/angle identified above and add it to the
                    // polygon

                    Point2D point = getWebPoint(plotArea, angle,
                            value / this.maxValue);
                    /*     polygon.addPoint((int) point.getX(), (int) point.getY());

                    // put an elipse at the point being plotted..

                    Paint paint = getSeriesPaint(series);
                    Paint outlinePaint = getSeriesOutlinePaint(series);
                    Stroke outlineStroke = getSeriesOutlineStroke(series);

                    Ellipse2D head = new Ellipse2D.Double(point.getX()
                            - headW / 2, point.getY() - headH / 2, headW,
                            headH);
                    g2.setPaint(paint);
                    g2.fill(head);
                    g2.setStroke(outlineStroke);
                    g2.setPaint(outlinePaint);
                    g2.draw(head);*/
                    
                    BigDecimal bd = new BigDecimal(value);
                    bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
                    g2.setFont(new Font("Arail", Font.ITALIC, 14));
                    g2.setColor(new Color(52,52,52));
                    g2.drawString(bd+"",(float)(point.getX()
                            - headW / 2+6),(float)( point.getY() - headH / 2+12));

                    }

                
            }
        }
	}
	
}
