/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Client.ui.utility;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

import java.awt.Font;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bernardo
 */
public class RadarBuilder {

    private Double[] values;
    private int dimensions;
    private double max;
    private Line fakeLine1;
    private Line fakeLine2;//Linhas para consultar a posição x,y dos pontos
    //Baseados em seus valores
    private Point2D location1;
    private Point2D location2;

    public RadarBuilder(Double[] values, int dimensions, int max) {
        this.values = values;
        this.dimensions = dimensions;
        this.max = max;
    }

    public Group build() {
        int degree = (360 / dimensions);
        Group g = new Group();
        List<Double> polygonPosition = new ArrayList<Double>(dimensions * 4);
        boolean keepWalking = true;
        int j = 0;
        int rotationCont;
        for (int i = 0; i < (values.length / dimensions); i++) {
            rotationCont = 0;
            while (keepWalking) {
                double y2 = 0;
                int rotation = degree * rotationCont;
                double y1 = (SizeConstraints.START_Y - SizeConstraints.END_Y)
                        * values[j] / max;
                if ((j + 1) / (i + 1) == dimensions) {
                    y2 = (SizeConstraints.START_Y - SizeConstraints.END_Y)
                            * values[(dimensions) * i] / max;
                    keepWalking = false;
                } else {
                    y2 = (SizeConstraints.START_Y - SizeConstraints.END_Y)
                            * values[j + 1] / max;
                }
                buildFakeLines(y1, rotation, y2, rotation + degree);
                
                
                //paint the point
                Circle c = new Circle(6, javafx.scene.paint.Paint.valueOf("#f6b0b0"));
                c.setCenterX(location1.getX());
                c.setCenterY(location1.getY());
                g.getChildren().add(c);
                
                //write the value
                BigDecimal bd = new BigDecimal(values[j]);
                bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
                Label label = new Label(bd.toString());
                label.setLayoutX(location1.getX());
                label.setFont(new javafx.scene.text.Font("Arail", 17));
                label.setLayoutY(location1.getY());
                g.getChildren().add(label);
                
                polygonPosition.addAll(currentLineValues());
                j++;
                rotationCont++;
                
            }
            Polygon p = new Polygon();
            p.getPoints().addAll(polygonPosition);
            p.setFill(null);
            p.setStroke(javafx.scene.paint.Paint.valueOf("#f6b0b0"));
            p.setStrokeWidth(4.0);
            g.getChildren().add(p);
            p.toBack();
            keepWalking = true;
            
            
            polygonPosition = new ArrayList<Double>();
            
        }
        return g;
    }

    /**
     * Constroi um array com valores X,Y
     * das posições onde o poligono se encontra
     *
     * @return
     */
    private List<Double> currentLineValues() {
        //x1,y1,x2,y2
        List<Double> current = new ArrayList<Double>();
        current.add(location1.getX());
        current.add(location1.getY());
        current.add(location2.getX());
        current.add(location2.getY());
        return current;
    }

    /**
     * Constroi uma linha ficticia com rotação
     * usada para saber o ponto x,y de um det valor Y1 e Y2
     *
     * @param y1
     * @return
     */
    private void buildFakeLines(double y1, double rotation, double y2, double rotation2) {
        this.fakeLine1 = new Line();
        this.fakeLine1.setStartX(SizeConstraints.START_X);
        this.fakeLine1.setEndX(SizeConstraints.END_X);
        this.fakeLine1.setStartY(SizeConstraints.START_Y);
        this.fakeLine1.setEndY(SizeConstraints.START_Y - y1);
        Rotate r = new Rotate(rotation, SizeConstraints.START_X
                , SizeConstraints.START_Y);
        this.fakeLine2 = new Line();
        this.fakeLine2.setStartX(SizeConstraints.START_X);
        this.fakeLine2.setEndX(SizeConstraints.END_X);
        this.fakeLine2.setStartY(SizeConstraints.START_Y);
        this.fakeLine2.setEndY(SizeConstraints.START_Y - y2);
        Rotate r2 = new Rotate(rotation2, SizeConstraints.START_X
                , SizeConstraints.START_Y);
        this.fakeLine1.getTransforms().add(r);
        this.fakeLine2.getTransforms().add(r2);

        this.location1 = this.fakeLine1.localToParent(this.fakeLine1.getLayoutBounds().getMinX()
                , this.fakeLine1.getLayoutBounds().getMinY());
        this.location2 = this.fakeLine2.localToParent(this.fakeLine2.getLayoutBounds().getMinX()
                , this.fakeLine2.getLayoutBounds().getMinY());
        
    }

}
