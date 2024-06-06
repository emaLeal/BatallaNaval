package com.example.batallanaval.model;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Destructor{
    Pane root;

    public Destructor(){
        root = new Pane();
        //root.setPrefSize(100.0,50.0);
       // root.setStyle("-fx-background-color: red");

        Polygon triangle1 = new Polygon();
        triangle1.getPoints().addAll(
                0.0, 10.0,   // Punto superior izquierdo
                100.0, 10.0,  // Punto superior derecho
                80.0, 30.0, // Punto inferior derecho
                20.0, 30.0);  // Punto inferior izquierdo);

        triangle1.setFill(Color.GREY);
        triangle1.setStroke(Color.BLACK);

        Circle ventana1 = new Circle(30,20,4);
        Circle ventana2 = new Circle(70,20,4);
        ventana1.setFill(Color.LIGHTBLUE);
        ventana1.setStroke(Color.BLACK);
        ventana2.setFill(Color.LIGHTBLUE);
        ventana2.setStroke(Color.BLACK);

        Polyline torre = new Polyline();

        torre.getPoints().addAll(
                42.0, 10.0,
                42.0, 0.0,
                57.0, 0.0,
                57.0, 10.0);
        torre.setFill(Color.GRAY);
        torre.setStroke(Color.BLACK);
        Polyline torreta1 = new Polyline();
        torreta1.getPoints().addAll(
                25.0, 10.0,
                25.0, 5.0,
                30.0, 5.0,
                30.0, 10.0);
        torreta1.setFill(Color.ORANGERED);
        torreta1.setStroke(Color.BLACK);

        Polyline torreta2 = new Polyline();
        torreta2.getPoints().addAll(
                65.0, 10.0,
                65.0, 5.0,
                70.0, 5.0,
                70.0, 10.0);
        torreta2.setFill(Color.ORANGERED);
        torreta1.setStroke(Color.BLACK);
        // Agregar todas las formas al panel
        root.getChildren().addAll(triangle1,ventana1,ventana2,torre,torreta1,torreta2);
    }

    public Pane getRoot() {
        return root;
    }



}
