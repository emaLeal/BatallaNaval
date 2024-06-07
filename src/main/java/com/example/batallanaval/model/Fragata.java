package com.example.batallanaval.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;

public class Fragata {
    Pane root;

    public Fragata(){
        root = new Pane();

        Polygon cuerpo = new Polygon();
        cuerpo.getPoints().addAll(
                0.0, 15.0,
                100.0, 15.0,
                70.0, 30.0,
                20.0, 30.0);
        cuerpo.setFill(Color.GREY);
        cuerpo.setStroke(Color.BLACK);

        Polyline torre = new Polyline();
        torre.getPoints().addAll(
                20.0, 15.0,
                35.0, 0.0,
                55.0, 0.0,
                70.0, 15.0);
        torre.setFill(Color.GRAY);
        torre.setStroke(Color.BLACK);

        Circle ventana = new Circle(45,10,4);
        ventana.setFill(Color.LIGHTBLUE);
        ventana.setStroke(Color.BLACK);

        Polyline raya = new Polyline();
        raya.getPoints().addAll(
                9.0, 22.0,
                84.0, 22.0,
                78.0, 25.0,
                13.0, 25.0);
        raya.setFill(Color.RED);


        root.getChildren().addAll(cuerpo,torre,ventana,raya);
    }

public Pane getRoot() {
    return root;
}
}
