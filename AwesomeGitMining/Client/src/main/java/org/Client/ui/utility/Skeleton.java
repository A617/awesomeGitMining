package org.Client.ui.utility;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;

public class Skeleton {

    public static Group buildSkeleton(int dimensions, String[] labels) {
        Group g = buildSkeletonLines(dimensions);
        Double[] polygonValues = new Double[dimensions * 2];
        int cont = 0;
        for (int i = 0; i < dimensions; i++) {
            Line l = (Line) g.getChildren().get(i);
            Point2D pt = l.localToParent(l.getLayoutBounds().getMinX()
                    , l.getLayoutBounds().getMinY());
            polygonValues[cont] = pt.getX();
            polygonValues[cont + 1] = pt.getY();
            g.getChildren().add(addLabel(pt, labels[i]));
            cont = cont + 2;
        }
        g.getChildren().add(0, buildSkeletonPolygon(polygonValues));
        return g;
    }

    public static Label addLabel(Point2D pt, String lbText) {
        Label lb = new Label(lbText);
        lb.setTextFill(javafx.scene.paint.Paint.valueOf("#726d6d"));
        lb.setFont(new Font("Arail", 16));
        if (pt.getX() < SizeConstraints.END_X-20) {
            lb.setLayoutX(pt.getX() - 40);
        } else if(pt.getX() < SizeConstraints.END_X){
        	lb.setLayoutX(pt.getX() - 15);
        }else {
            lb.setLayoutX(pt.getX());
        }
        /*if(pt.getY() > SizeConstraints.START_Y){
            lb.setLayoutY(pt.getY() + 30);
        }else{*/
        
        if (pt.getY() < SizeConstraints.END_Y+100) {
            lb.setLayoutY(pt.getY() - 25);
        } else {
            lb.setLayoutY(pt.getY()+5);
        }
      //  lb.setLayoutY(pt.getY());
        //}

        return lb;
    }

    /**
     *
     * @param coordinates
     * @return
     */
    private static Polygon buildSkeletonPolygon(Double[] coordinates) {
        Polygon p = new Polygon();
        p.setFill(null);
        p.setStroke(javafx.scene.paint.Paint.valueOf("8f8f8f"));
        p.getPoints().addAll(coordinates);
        return p;
    }

    /**
     * Retorna um grupo de linhas que compõe
     * o esqueleto do gráfico
     *
     * @param dimensions Quantidade de linhas irá criar
     * @return
     */
    private static Group buildSkeletonLines(int dimensions) {
        Group g = new Group();
        double rotation = 360 / dimensions;
        double nextRotation = 0;
        int quantity = 2;
        for (int j = 1; j <= quantity; j++) {
            for (int i = 0; i < dimensions; i++) {
                Line l = gimmePopulatedLine(quantity);
                l.setStroke(javafx.scene.paint.Paint.valueOf("8f8f8f"));
                Rotate r = new Rotate(nextRotation, SizeConstraints.START_X
                        , SizeConstraints.START_Y);
                l.getTransforms().add(r);
                g.getChildren().add(l);
                nextRotation = nextRotation + rotation;
            }
        }
        return g;
    }

    public static Line gimmePopulatedLine(int qt) {
        Line l = new Line();
        l.setStartX(SizeConstraints.START_X);
        l.setEndX(SizeConstraints.END_X);
        l.setStartY(SizeConstraints.START_Y);
        l.setEndY(SizeConstraints.END_Y);
        return l;
    }

}
