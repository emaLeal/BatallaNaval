package com.example.batallanaval.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;

//Represents a fragata ship in the game.
public class Fragata {
    private Pane root;

    /**
     * Constructs a new Fragata ship and initializes its components.
     */
    public Fragata(){
        root = new Pane();

        // Create the main body of the ship
        Polygon cuerpo = new Polygon();
        cuerpo.getPoints().addAll(
                0.0, 15.0,
                100.0, 15.0,
                70.0, 30.0,
                20.0, 30.0);
        cuerpo.setFill(Color.GREY);
        cuerpo.setStroke(Color.BLACK);

        // Create the tower of the ship
        Polyline torre = new Polyline();
        torre.getPoints().addAll(
                20.0, 15.0,
                35.0, 0.0,
                55.0, 0.0,
                70.0, 15.0);
        torre.setFill(Color.GRAY);
        torre.setStroke(Color.BLACK);

        // Create a window for the ship
        Circle ventana = new Circle(45,10,4);
        ventana.setFill(Color.LIGHTBLUE);
        ventana.setStroke(Color.BLACK);

        // Create a decorative line for the ship
        Polyline raya = new Polyline();
        raya.getPoints().addAll(
                9.0, 22.0,
                84.0, 22.0,
                78.0, 25.0,
                13.0, 25.0);
        raya.setFill(Color.RED);

        // Add all shapes to the root pane
        root.getChildren().addAll(cuerpo, torre, ventana, raya);
    }

    /**
     * Returns the root pane of the Fragata ship.
     *
     * @return The root pane of the Fragata ship.
     */
    public Pane getRoot() {
        return root;
    }
}