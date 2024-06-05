package com.example.batallanaval.controller;

import com.example.batallanaval.model.BoardElement;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameController {
    // FXML variables
    @FXML
    private Text portaviones;
    @FXML
    private Text cPortaviones;
    @FXML
    private Text submarinos;
    @FXML
    private Text cSubmarinos;
    @FXML
    private Text destructores;
    @FXML
    private Text cDestructores;
    @FXML
    private Text fragatas;
    @FXML
    private Text cFragatas;
    @FXML
    private GridPane tablero1;
    @FXML
    private GridPane tablero2;

    // elements
    private BoardElement elPortavion;
    private BoardElement elSubmarino;
    private BoardElement elDestructor;
    private BoardElement elFragatas;


    /**
     *Initializes the game controller.
     * Performs setup tasks such as setting up the timeline and populating the Sudoku board.
     * */
    public void initialize(){
        elPortavion = new BoardElement("Portaviones", 1, 4);
        elSubmarino = new BoardElement("SubMarinos", 2, 3);
        elDestructor = new BoardElement("Destructores", 3, 2);
        elFragatas = new BoardElement("Fragatas", 4, 1);
        cPortaviones.setText(String.valueOf(elPortavion.getQuantity()));
        cSubmarinos.setText(String.valueOf(elSubmarino.getQuantity()));
        cDestructores.setText(String.valueOf(elDestructor.getQuantity()));
        cFragatas.setText(String.valueOf(elFragatas.getQuantity()));
        addMouseEvent();
    }

    private Pane getCellFromGridPane(GridPane gridPane, int row, int col) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            System.out.println(gridPane.getChildren());
            if (GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == col) {
                return (Pane) node;
            }
        }
        return null; // No se encontró la celda
    }
    private void addMouseEvent() {
        for (int row = 0; row < tablero1.getRowCount(); row++) {
            for (int col = 0; col < tablero1.getColumnCount(); col++) {
                Pane cell = getCellFromGridPane(tablero1, row, col);
                if (cell != null) {
                    System.out.println("hola :E");
                    // Adding mouse enter and exit events
                    cell.setOnMouseEntered(e -> cell.setStyle("-fx-background-color: lightblue; -fx-border-color: black;"));
                    cell.setOnMouseExited(e -> cell.setStyle("-fx-background-color: white; -fx-border-color: white;"));
                }
            }
        }
    }
}
