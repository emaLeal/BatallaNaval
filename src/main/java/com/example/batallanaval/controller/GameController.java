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

    // Currently selected ship
    private BoardElement selectedShip;
    private int shipSize;

    /**
     * Initializes the game controller.
     * Performs setup tasks such as setting up the timeline and populating the Sudoku board.
     */
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
                    // Hacer que la fila y la columna sean efectivamente definitivas mediante la creación de variables locales finales
                    final int currentRow = row;
                    final int currentCol = col;
                    // Adding mouse enter and exit events
                    cell.setOnMouseEntered(e -> cell.setStyle("-fx-background-color: lightblue; -fx-border-color: black;"));
                    cell.setOnMouseExited(e -> cell.setStyle("-fx-background-color: white; -fx-border-color: white;"));
                    //Esto es pa que la función placeShip sea llamada cuando el usuario hace clic en una celda específica del tablero
                    cell.setOnMouseClicked(e -> placeShip(cell, currentRow, currentCol));
                }
            }
        }
    }

    //esto es pa colocar un barco en una celda específica del tablero y coloca el nombre del barco que se selecciono
    private void placeShip(Pane cell, int row, int col) {
        if (selectedShip == null || selectedShip.getQuantity() == 0) {
            return;
        }

        // esto es pa Compruebar si el barco se puede colocar dentro de los límites de la cuadrícula.
        //entonces solo se  aplica  para un tablero q es el del jugador
        if (col + shipSize - 1 < tablero1.getColumnCount()) {
            Pane shipCell = getCellFromGridPane(tablero1, row, col);
            if (shipCell != null && !isCellOccupied(shipCell)) {
                shipCell.setStyle("-fx-background-color: pink; -fx-border-color: red;");
                Text shipText = new Text(selectedShip.getName());
                shipText.setStyle("-fx-font-size: 8pt;");
                shipCell.getChildren().add(shipText);

                // Disable click event for this cell
                shipCell.setOnMouseClicked(null);

                selectedShip.setQuantity(selectedShip.getQuantity() - 1);
                updateShipCounts();
            }
        }
    }
    // verifica si una celda está ocupada por algún barco y si lo esta no me deja colocar.
    private boolean isCellOccupied(Pane cell) {
        return !cell.getChildren().isEmpty();
    }

    // esto es pa mermar la cantidad de barcos , osea si ya se selecciono uno actualiza la cantidad de los q me faltan
    private void updateShipCounts() {
        cPortaviones.setText(String.valueOf(elPortavion.getQuantity()));
        cSubmarinos.setText(String.valueOf(elSubmarino.getQuantity()));
        cDestructores.setText(String.valueOf(elDestructor.getQuantity()));
        cFragatas.setText(String.valueOf(elFragatas.getQuantity()));
    }

    public void onHandleElement(ActionEvent event) {
        Button eventButton = (Button) event.getSource();
        if (eventButton == buttonPortaviones) {
            selectedShip = elPortavion;
            shipSize = elPortavion.getSize();
        } else if (eventButton == buttonSubmarinos) {
            selectedShip = elSubmarino;
            shipSize = elSubmarino.getSize();
        } else if (eventButton == buttonDestructores) {
            selectedShip = elDestructor;
            shipSize = elDestructor.getSize();
        } else if (eventButton == buttonFragatas) {
            selectedShip = elFragatas;
            shipSize = elFragatas.getSize();
        }
    }
}
