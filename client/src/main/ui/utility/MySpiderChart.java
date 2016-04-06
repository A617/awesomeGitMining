package main.ui.utility;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.swing.ImageIcon;

import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.util.TableOrder;
/**
 * 重写spiderWebPlot可以显示刻度及对应数据
 * @author user
 *
 */
public class MySpiderChart extends SpiderWebPlot{
	
	private static final long serialVersionUID = 1L;
	
	private int ticks = DEFAULT_TICKS;
	private static final int DEFAULT_TICKS = 8;
	private NumberFormat format = NumberFormat.getInstance();
	private static final double PERPENDICULAR = 90;
	private static final double TICK_SCALE = 0.015;
	private int valueLabelGap = DEFAULT_GAP;
	private static final int DEFAULT_GAP = 20;
	private static final double THRESHOLD = 15;
	
	private int maxValue=8;
	
	public MySpiderChart(CategoryDataset categoryDataset) {
		super(categoryDataset);
		this.setSeriesPaint(Color.PINK);
		this.setSeriesOutlinePaint(Color.pink);
		this.setSeriesOutlineStroke(new BasicStroke(2));
		this.setMaxValue(8);
		this.setBackgroundAlpha(0);
	//	this.setBackgroundImage(new ImageIcon("src/main/ui/style/raderback.png").getImage());
	//	this.setForegroundAlpha(0);
		this.setAxisLinePaint(new Color(255, 255,255,0));
	}
	
	@Override
	public void drawOutline(Graphics2D g2, Rectangle2D area) {
		// TODO Auto-generated method stub
	//	super.drawOutline(g2, area);
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
	

	
	@Override
	public void drawLabel(final Graphics2D g2, final Rectangle2D plotArea,final double value, 
			final int cat, final double startAngle,final double extent) {
	//		super.drawLabel(g2, plotArea, value, cat, startAngle, extent);
	/*	final FontRenderContext frc = g2.getFontRenderContext();
		final double[] transformed = new double[2];
		final double[] transformer = new double[2];
		final Arc2D arc1 = new Arc2D.Double(plotArea, startAngle, 0, Arc2D.OPEN);
		for (int i = 1; i <= ticks; i++) {
			final Point2D point1 = arc1.getEndPoint();
			final double deltaX = plotArea.getCenterX();
			final double deltaY = plotArea.getCenterY();
			double labelX = point1.getX() - deltaX;
			double labelY = point1.getY() - deltaY;
			final double scale = ((double) i / (double) ticks);
			final AffineTransform tx = AffineTransform.getScaleInstance(scale,
					scale);
			final AffineTransform pointTrans = AffineTransform
					.getScaleInstance(scale + TICK_SCALE, scale + TICK_SCALE);
			transformer[0] = labelX;
			transformer[1] = labelY;
			pointTrans.transform(transformer, 0, transformed, 0, 1);
			final double pointX = transformed[0] + deltaX;
			final double pointY = transformed[1] + deltaY;
			tx.transform(transformer, 0, transformed, 0, 1);
			labelX = transformed[0] + deltaX;
			labelY = transformed[1] + deltaY;
			double rotated = (PERPENDICULAR);
			AffineTransform rotateTrans = AffineTransform.getRotateInstance(
					Math.toRadians(rotated), labelX, labelY);
			transformer[0] = pointX;
			transformer[1] = pointY;
			rotateTrans.transform(transformer, 0, transformed, 0, 1);
			final double x1 = transformed[0];
			final double y1 = transformed[1];
			rotated = (-PERPENDICULAR);
			rotateTrans = AffineTransform.getRotateInstance(
					Math.toRadians(rotated), labelX, labelY);
			rotateTrans.transform(transformer, 0, transformed, 0, 1);
			final Composite saveComposite = g2.getComposite();
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					1.0f));
			g2.draw(new Line2D.Double(transformed[0], transformed[1], x1, y1));
			if (startAngle == this.getStartAngle()) {
				final String label = format
						.format(((double) i / (double) ticks)
								* this.getMaxValue());
				final LineMetrics lm = getLabelFont()
						.getLineMetrics(label, frc);
				final double ascent = lm.getAscent();
				if (Math.abs(labelX - plotArea.getCenterX()) < THRESHOLD) {
					labelX += valueLabelGap;
					labelY += ascent / (float) 2;
				} else if (Math.abs(labelY - plotArea.getCenterY()) < THRESHOLD) {
					labelY += valueLabelGap;
				} else if (labelX >= plotArea.getCenterX()) {
					if (labelY < plotArea.getCenterY()) {
						labelX += valueLabelGap;
						labelY += valueLabelGap;
					} else {
						labelX -= valueLabelGap;
						labelY += valueLabelGap;
					}
				} else {
					if (labelY > plotArea.getCenterY()) {
						labelX -= valueLabelGap;
						labelY -= valueLabelGap;
					} else {
						labelX += valueLabelGap;
						labelY -= valueLabelGap;
					}
				}
				g2.setPaint(getLabelPaint());
				g2.setFont(getLabelFont());
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setFont(new Font("Arail", Font.ITALIC, 10));
				g2.drawString(label, (float) labelX, (float) labelY);
			}
			g2.setComposite(saveComposite);
		}*/
	}
	
	
}
