package com.example.batallanaval.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;


//Represents a portaviones ship in the game.
public class Portaviones {
    private Pane root;

    /**
     * Constructs a new Portaviones ship and initializes its components.
     */
    public Portaviones(){
        root = new Pane();

        // Create the main body of the ship
        Polygon cuerpo = new Polygon();
        cuerpo.getPoints().addAll(
                0.0, 20.0,
                80.0, 20.0,
                100.0, 10.0,
                80.0, 30.0,
                5.0, 30.0);
        cuerpo.setFill(Color.GREY);
        cuerpo.setStroke(Color.BLACK);

        // Create the tower of the ship
        Polyline torre = new Polyline();
        torre.getPoints().addAll(
                12.0, 20.0,
                12.0, 0.0,
                27.0, 0.0,
                27.0, 20.0);
        torre.setFill(Color.GRAY);
        torre.setStroke(Color.BLACK);

        // Create the first turret of the ship
        Polyline torre2 = new Polyline();
        torre2.setStroke(Color.BLACK);
        torre2.getPoints().addAll(
                37.0, 20.0,
                37.0, 15.0,
                47.0, 15.0,
                47.0, 20.0);
        torre2.setFill(Color.ORANGERED);

        // Create the second turret of the ship
        Polyline torre3 = new Polyline();
        torre3.setStroke(Color.BLACK);
        torre3.getPoints().addAll(
                57.0, 20.0,
                57.0, 15.0,
                67.0, 15.0,
                67.0, 20.0);
        torre3.setFill(Color.ORANGERED);

        // Add all shapes to the root pane
        root.getChildren().addAll(cuerpo, torre, torre2, torre3);
    }

    /**
     * Returns the root pane of the Portaviones ship.
     *
     * @return The root pane of the Portaviones ship.
     */
    public Pane getRoot() {
        return root;
    }
}
