package com.example.batallanaval.controller;

import com.example.batallanaval.model.BoardElement;
import com.example.batallanaval.model.Destructor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameController {
    // FXML variables
    @FXML
    private GridPane tablero1;
    @FXML
    private GridPane tablero2;

    @FXML
    private Button buttonPortaviones;
    @FXML
    private Button buttonFragatas;
    @FXML
    private Button buttonSubmarinos;
    @FXML
    private Button buttonDestructores;

    // elements
    private BoardElement elPortavion;
    private BoardElement elSubmarino;
    private BoardElement elDestructor;
    private BoardElement elFragatas;

    // Currently selected ship
    private BoardElement selectedShip;
    private int shipSize;
    private Button[] botonesBarcos;
    BoardElement[] barcos;

    /**
     * Initializes the game controller.
     * Performs setup tasks such as setting up the timeline and populating the Sudoku board.
     */
    public void initialize(){

        elPortavion = new BoardElement("Portaviones", 1, 4);
        elSubmarino = new BoardElement("SubMarinos", 2, 3);
        elDestructor = new BoardElement("Destructores", 3, 2);
        elFragatas = new BoardElement("Fragatas", 4, 1);

         barcos = new BoardElement[]{elPortavion,elSubmarino,elDestructor,elFragatas};
         botonesBarcos = new Button[]{buttonPortaviones,buttonSubmarinos,buttonDestructores,buttonFragatas};

        for (int i = 0; i < barcos.length; i++ ) {
            botonesBarcos[i].setText(String.valueOf(barcos[i].getName() +" "+barcos[i].getQuantity()));
            botonesBarcos[i].setGraphic(barcos[i].getRoot());
        }

        addMouseEvent(tablero1);
    }

    private Pane getCellFromGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
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
                    cell.setOnMouseClicked(e ->{
                        placeShip(cell, currentRow, currentCol);

                        Pane edge1 = getCellFromGridPane(tablero1, currentRow-shipSize+1, currentCol);
                        Pane edge2 = getCellFromGridPane(tablero1, currentRow+shipSize-1, currentCol);
                        Pane edge3 = getCellFromGridPane(tablero1, currentRow, currentCol-shipSize+1);
                        Pane edge4 = getCellFromGridPane(tablero1, currentRow, currentCol+shipSize-1);
                        if (edge1 != null || edge2 != null || edge3 != null || edge4 != null) {
                            if (edge1 != null) {
                                edge1.setStyle("-fx-background-color: lightgreen; -fx-border-color: black;");
                                edge1.setOnMouseClicked(event -> {
                                    GridPane.setRowSpan(edge1, shipSize);
//                            for (int i = currentRow-1;i>=currentRow-shipSize+1;i--) {
//                                Pane loopCell = getCellFromGridPane(tablero1, i, currentCol);
//                                placeShip(loopCell, i, currentCol);
//                            }
                                if (edge2 != null) edge2.setStyle("-fx-background-color: white; -fx-border-color: white;");
                                if (edge3 != null) edge3.setStyle("-fx-background-color: white; -fx-border-color: white;");
                                if (edge4 != null) edge4.setStyle("-fx-background-color: white; -fx-border-color: white;");

                                    selectedShip.setQuantity(selectedShip.getQuantity() - 1);
                                    updateShipCounts();
                                });
                            }
                            if (edge2 != null)
                                edge2.setStyle("-fx-background-color: lightgreen; -fx-border-color: black;");
                                edge2.setOnMouseClicked(event -> {
                                GridPane.setRowSpan(cell, shipSize);
//                              for (int i = currentRow+1;i<=currentRow+shipSize-1;i++) {
//                                Pane loopCell = getCellFromGridPane(tablero1, i, currentCol);
//                                placeShip(loopCell, i, currentCol);
//                              }
                                    if (edge1 != null) edge1.setStyle("-fx-background-color: white; -fx-border-color: white;");
                                    if (edge3 != null) edge3.setStyle("-fx-background-color: white; -fx-border-color: white;");
                                    if (edge4 != null) edge4.setStyle("-fx-background-color: white; -fx-border-color: white;");
                                selectedShip.setQuantity(selectedShip.getQuantity() - 1);
                                updateShipCounts();
                            });
                            if (edge3 != null) {
                                edge3.setStyle("-fx-background-color: lightgreen; -fx-border-color: black;");
                                edge3.setOnMouseClicked(event -> {
                                    GridPane.setColumnSpan(edge3, shipSize);
//                            for (int i = currentCol-1;i>=currentCol-shipSize+1;i--) {
//                                Pane loopCell = getCellFromGridPane(tablero1, currentRow, i);
//                                placeShip(loopCell, currentCol, i);
//                            }
                                    if (edge1 != null) edge1.setStyle("-fx-background-color: white; -fx-border-color: white;");
                                    if (edge2 != null) edge2.setStyle("-fx-background-color: white; -fx-border-color: white;");
                                    if (edge4 != null) edge4.setStyle("-fx-background-color: white; -fx-border-color: white;");
                                    selectedShip.setQuantity(selectedShip.getQuantity() - 1);
                                    updateShipCounts();
                                });
                            }
                            if (edge4 != null) {
                                edge4.setStyle("-fx-background-color: lightgreen; -fx-border-color: black;");
                                edge4.setOnMouseClicked(event -> {
                                    GridPane.setColumnSpan(cell, shipSize);
//                              for (int i = currentCol+1;i<=currentCol+shipSize-1;i++) {
//                                Pane loopCell = getCellFromGridPane(tablero1, currentRow, i);
//                                placeShip(loopCell, currentRow, i);
//                              }
                                    if (edge1 != null) edge1.setStyle("-fx-background-color: white; -fx-border-color: white;");
                                    if (edge2 != null) edge2.setStyle("-fx-background-color: white; -fx-border-color: white;");
                                    if (edge3 != null) edge3.setStyle("-fx-background-color: white; -fx-border-color: white;");
                                    selectedShip.setQuantity(selectedShip.getQuantity() - 1);
                                    updateShipCounts();
                                });
                            }
                        }




                    });

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

            }
        }
    }

    private void placeCell() {

    }

    // verifica si una celda está ocupada por algún barco y si lo esta no me deja colocar.
    private boolean isCellOccupied(Pane cell) {
        return !cell.getChildren().isEmpty();
    }

    // esto es pa mermar la cantidad de barcos , osea si ya se selecciono uno actualiza la cantidad de los q me faltan
    private void updateShipCounts() {

        for (int i = 0; i < barcos.length; i++ ) {
            botonesBarcos[i].setText(String.valueOf(barcos[i].getName() +" "+barcos[i].getQuantity()));
        }
    }

    public void onHandleElement(ActionEvent event) {
        Button eventButton = (Button) event.getSource();

        if (eventButton == buttonPortaviones) {
            selectedShip = elPortavion;
            shipSize = elPortavion.getSpaces();
        } else if (eventButton == buttonSubmarinos) {
            selectedShip = elSubmarino;
            shipSize = elSubmarino.getSpaces();
        } else if (eventButton == buttonDestructores) {
            selectedShip = elDestructor;
            shipSize = elDestructor.getSpaces();
        } else if (eventButton == buttonFragatas) {
            selectedShip = elFragatas;
            shipSize = elFragatas.getSpaces();
        }
    }
}
