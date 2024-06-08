package com.example.batallanaval.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polyline;

/**
 * Represents a submarino ship in the game.
 */
public class Submarino {
    private Pane root;

    /**
     * Constructs a new Submarino ship and initializes its components.
     */
    public Submarino(){
        root = new Pane();

        // Create the body of the submarine
        Ellipse cuerpo = new Ellipse(40.0, 20, 40, 10);
        cuerpo.setFill(Color.GREY);
        cuerpo.setStroke(Color.BLACK);

        // Create the tower of the submarine
        Polyline torre = new Polyline();
        torre.getPoints().addAll(
                42.0, 10.0,
                42.0, 0.0,
                57.0, 0.0,
                57.0, 10.0);
        torre.setFill(Color.GRAY);
        torre.setStroke(Color.BLACK);

        // Create windows for the submarine
        Circle ventana1 = new Circle(20,20,4);
        Circle ventana2 = new Circle(35,20,4);
        Circle ventana3 = new Circle(50,20,4);
        Circle ventana4 = new Circle(65,20,4);
        ventana1.setFill(Color.BLUE);
        ventana1.setStroke(Color.BLACK);
        ventana2.setFill(Color.BLUE);
        ventana2.setStroke(Color.BLACK);
        ventana3.setFill(Color.BLUE);
        ventana3.setStroke(Color.BLACK);
        ventana4.setFill(Color.BLUE);
        ventana4.setStroke(Color.BLACK);

        // Add all shapes to the root pane
        root.getChildren().addAll(cuerpo, torre, ventana1, ventana2, ventana3, ventana4);
    }

    /**
     * Returns the root pane of the Submarino ship.
     *
     * @return The root pane of the Submarino ship.
     */
    public Pane getRoot() {
        return root;
    }
}
