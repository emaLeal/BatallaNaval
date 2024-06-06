package com.example.batallanaval.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;

public class Portaviones {
    Pane root;
    public Portaviones(){
        root = new Pane();

        Polygon cuerpo = new Polygon();
        cuerpo.getPoints().addAll(
                0.0, 20.0,
                80.0, 20.0,
                100.0,10.0,
                80.0, 30.0,
                5.0, 30.0);
        cuerpo.setFill(Color.GREY);
        cuerpo.setStroke(Color.BLACK);

        Polyline torre = new Polyline();
        torre.getPoints().addAll(
                12.0, 20.0,
                12.0, 0.0,
                27.0, 0.0,
                27.0, 20.0);
        torre.setFill(Color.GRAY);
        torre.setStroke(Color.BLACK);

        Polyline torre2 = new Polyline();

        torre2.setStroke(Color.BLACK);
        torre2.getPoints().addAll(
                37.0, 20.0,
                37.0, 15.0,
                47.0, 15.0,
                47.0, 20.0);
        torre2.setFill(Color.ORANGERED);
        torre2.setStroke(Color.BLACK);

        Polyline torre3 = new Polyline();

        torre3.setStroke(Color.BLACK);
        torre3.getPoints().addAll(
                57.0, 20.0,
                57.0, 15.0,
                67.0, 15.0,
                67.0, 20.0);
        torre3.setFill(Color.ORANGERED);
        torre3.setStroke(Color.BLACK);

        root.getChildren().addAll(cuerpo,torre,torre2,torre3);

    }

    public Pane getRoot() {
        return root;
    }
}
