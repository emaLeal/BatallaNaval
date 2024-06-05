package com.example.batallanaval.controller;

import com.example.batallanaval.model.BoardElement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameController {
    // FXML variables
    @FXML
    private Button portaviones;
    @FXML
    private Text cPortaviones;
    @FXML
    private Button submarinos;
    @FXML
    private Text cSubmarinos;
    @FXML
    private Button destructores;
    @FXML
    private Text cDestructores;
    @FXML
    private Button fragatas;
    @FXML
    private Text cFragatas;
    @FXML
    private GridPane tablero1;
    @FXML
    private GridPane tablero2;
    @FXML
    private Button buttonPortaviones;
    @FXML
    private Button buttonSubmarinos;
    @FXML
    private Button buttonDestructores;
    @FXML
    private Button buttonFragatas;

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
        addMouseEvent(tablero1);
        addMouseEvent(tablero2);
    }

    private Pane getCellFromGridPane(GridPane gridPane, int row, int col) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == col) {
                return (Pane) node;
            }
        }
        return null; // No se encontró la celda
    }
    private void addMouseEvent(GridPane grid) {
        for (int row = 0; row < grid.getRowCount(); row++) {
            for (int col = 0; col < grid.getColumnCount(); col++) {
                Pane cell = getCellFromGridPane(grid, row, col);
                if (cell != null) {
                    // Adding mouse enter and exit events
                    cell.setOnMouseEntered(e -> cell.setStyle("-fx-background-color: lightblue; -fx-border-color: black;"));
                    cell.setOnMouseExited(e -> cell.setStyle("-fx-background-color: white; -fx-border-color: white;"));
                }
            }
        }
    }

    public void onHandleElement(ActionEvent event) {
        Button eventButton = (Button) event.getSource();
        System.out.println(eventButton);

     }
}
