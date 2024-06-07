package com.example.batallanaval.controller;

import com.example.batallanaval.model.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    @FXML
    private Button play;
    @FXML
    private Pane idPanelControllerTwo;
    @FXML
    private Pane idPanelControllerOne;

    // elements
    private BoardElement elPortavion;
    private BoardElement elSubmarino;
    private BoardElement elDestructor;
    private BoardElement elFragatas;

    // Currently selected ship
    private BoardElement selectedShip;
    private Button selectedButton;
    private int shipSize;
    private Button[] botonesBarcos;
    BoardElement[] barcos;
    List<BoardElement> barcosGameEnemy;
    List<BoardElement> barcosPlayer;
    private int barcosCountEnemy =10;
    private int barcosCountPlayer =10;
    private boolean gameStart = false;
    /**
     * Initializes the game controller.
     * Performs setup tasks such as setting up the timeline and populating the Sudoku board.
     */
    public void initialize(){
        Game game = new Game();
        barcosGameEnemy = game.getBoatgame();
        barcosPlayer = new ArrayList<>();

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
        addMouseEventEnemy(tablero2);
        placeEnemyBoats();
    }

    public void startGame() {
        activateBoard(tablero1,true);
        activateBoard(tablero2,false);

    }

    public  void turnEnemy(){
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        int col, row;

        Random random = new Random();
        col = random.nextInt((10 - 1) + 1) + 1;
        row = random.nextInt((10 - 1) + 1) + 1;
        System.out.println(col + " " + row);

        Pane cell = getCellFromGridPane(tablero1, row, col);
        //cell.f
        //cell.getOnMouseClicked();
        cell.setStyle("-fx-background-color: orange");
        //cell.setOnMouseClicked(null);
        for (BoardElement barco : barcosPlayer) {
            if (col >= barco.getCol() && col <= barco.getFinalCol() && row == barco.getRow()) {
                if (barco.getLife() == 1) {
                    for (int i = 01; i > barco.getSpaces(); ){

                    }
                    cell.setStyle("-fx-background-color: darkred");
                    barcosPlayer.remove(barco);
                    barcosCountPlayer -=1;
                    break;
                }
                cell.setStyle("-fx-background-color: red");
                barco.setLife();
                break;
            }
        }

        int randomNumber = random.nextInt(10); // 10 es exclusivo, así que genera números del 0 al 9

        pause.setOnFinished(event -> {

            System.out.println("desactivar");
            activateBoard(tablero2,false);

        });
        // Iniciar la pausa
        pause.play();
    }


    /**
     *
     * @param gridPane The gridpane to search for
     * @param row the row index of the desired cells
     * @param col the column index of the desired cell
     * @return the panel at the specified position , or null if not found
     */
    private Pane getCellFromGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == col) {
                return (Pane) node;
            }
        }
        return null; // cell not found
    }

    /**
     * Adds mouse events to each cell in the GridPane and Highlights
     * cells on mouse enter/exit and calls `placeShip` on click.
     * @param grid  The GridPane to which mouse events will be added.
     */
    private void addMouseEvent(GridPane grid) {
        for (int row = 0; row < grid.getRowCount(); row++) {
            for (int col = 0; col < grid.getColumnCount(); col++) {
                Pane cell = getCellFromGridPane(grid, row, col);
                if (cell != null) {
                    // Hacer que la fila y la columna sean efectivamente definitivas mediante la creación de variables locales finales
                    final int currentRow = row;
                    final int currentCol = col;
                    // Adding mouse enter and exit events
                    if (!cell.isDisabled()) {
                        cell.setOnMouseEntered(e -> cell.setStyle("-fx-background-color: lightblue; -fx-border-color: black;"));
                        cell.setOnMouseExited(e -> cell.setStyle("-fx-background-color: white; -fx-border-color: white;"));
                    }
                    // Calls placeShip when the cell is clicked
                    cell.setOnMouseClicked(e ->{
                        if(!gameStart){
                            placeShip(cell, currentRow, currentCol);
                        }else{
                            cell.setStyle("-fx-background-color: lightblue; -fx-border-color: black;");
                        }

                    });

                }
            }
        }
    }

    /**
     *Adds mouse events to each cell in the enemy GridPane.
     * @param grid The GridPane to which mouse events will be added
     */
    private void addMouseEventEnemy(GridPane grid) {
        for (int row = 0; row < grid.getRowCount(); row++) {
            for (int col = 0; col < grid.getColumnCount(); col++) {
                Pane cell = getCellFromGridPane(grid, row, col);
                if (cell != null) {

                    // Adding mouse enter and exit events
                    cell.setOnMouseEntered(e -> cell.setStyle("-fx-background-color: lightblue; -fx-border-color: black;"));
                    cell.setOnMouseExited(e -> cell.setStyle("-fx-background-color: white; -fx-border-color: white;"));
                    cell.setOnMouseClicked(e -> {
                        cell.setOnMouseExited(null);
                        cell.setStyle("-fx-background-color: blue; -fx-border-color: black;");
                        cell.setDisable(true);
                        activateBoard(tablero2,true);
                        turnEnemy();
                    });

                }
            }
        }
    }

    /**
     * Places a ship on the player´s grid at the specified cell
     *
     * @param cell The cell on which to place the ship
     * @param row The row index of the cell
     * @param col The col index of the cell
     */

    private void placeShip(Pane cell, int row, int col) {
        if (selectedShip == null || selectedShip.getQuantity() == 0) {
            return;
        }

        // Check if the ship can be placed within grid boundaries
        if (col + shipSize - 1 < tablero1.getColumnCount()) {
            Pane shipCell = getCellFromGridPane(tablero1, row, col);
            BoardElement shipModel = new BoardElement(selectedShip.getName(),selectedShip.getQuantity(),selectedShip.getSpaces());


            if (shipCell != null && !isCellOccupied(shipCell)) {
                if (shipSize > 1) {
                    for (int i = 1;i<shipSize;i++) {
                        Pane neighbourdCell = getCellFromGridPane(tablero1, row, col+i);
                        neighbourdCell.setDisable(true);
                    }
                }



                shipCell.setStyle("-fx-background-color: pink; -fx-border-color: red;");

                Text shipText = new Text(selectedShip.getName());
                shipText.setStyle("-fx-font-size: 8pt;");
                scaleBoats(shipModel);
                tablero1.add(shipModel.getRoot(),col,row);
                shipModel.setCol(col);
                shipModel.setFinalCol(col+shipSize-1);
                shipModel.setRow(row);
                barcosPlayer.add(shipModel);

                // Disable click event for this cell
//                shipCell.setOnMouseClicked(e -> cellEnemy(shipModel,shipCell));

                selectedShip.setQuantity(selectedShip.getQuantity() - 1);
                selectedButton.setText(selectedShip.getName() + " " + selectedShip.getQuantity());
                if (selectedShip.getQuantity() == 0)
                    selectedButton.setDisable(true);

                shipCell.setDisable(true);
                for (BoardElement b : barcos) {
                    if (b.getQuantity() != 0)
                        return;
                }

                play.setDisable(false);
            }
        }
    }

    private void cellEnemy(Pane shipModel,Pane shipCell) {
        shipCell.setStyle("-fx-background-color: pink; -fx-border-color: red;");
    }

    /**
     *Checks if a cell is occupied by a ship
     * @param cell The cell to check
     * @return True if the cell is occupied, false atherwise
     */

    private boolean isCellOccupied(Pane cell) {
        return !cell.getChildren().isEmpty();
    }

    /**
     *  Updates the text of ship buttons to reflect the remaining quantity of each ship.
     */

    private void updateShipCounts() {

        for (int i = 0; i < barcos.length; i++ ) {
            botonesBarcos[i].setText(String.valueOf(barcos[i].getName() +" "+barcos[i].getQuantity()));
        }
    }

    /**
     * Handles the selection of a ship type when a button is clicked.
     * @param event The ActionEvent triggered by clicking a ship button.
     */
    public void onHandleElement(ActionEvent event) {
        Button eventButton = (Button) event.getSource();

        if (eventButton == buttonPortaviones) {
            selectedShip = elPortavion;
            shipSize = elPortavion.getSpaces();
            selectedButton = eventButton;
        } else if (eventButton == buttonSubmarinos) {
            selectedShip = elSubmarino;
            shipSize = elSubmarino.getSpaces();
            selectedButton = eventButton;
        } else if (eventButton == buttonDestructores) {
            selectedShip = elDestructor;
            shipSize = elDestructor.getSpaces();
            selectedButton = eventButton;
        } else if (eventButton == buttonFragatas) {
            selectedShip = elFragatas;
            shipSize = elFragatas.getSpaces();
            selectedButton = eventButton;
        }
    }

    /**
     * Scales the size of the ship based on its name.
     *
     * @param boat The BoardElement representing the ship to scale.
     */
    public void scaleBoats(BoardElement boat) {
        switch (boat.getName()){
            case "Portaviones":
    boat.getRoot().setScaleX(0.8);
                boat.getRoot().setScaleY(0.5);
                break;
            case "SubMarinos":
                boat.getRoot().setScaleX(0.7);
                boat.getRoot().setScaleY(0.5);
                break;
            case "Destructores":
                boat.getRoot().setScaleX(0.4);
                boat.getRoot().setScaleY(0.5);
                break;
            case "Fragatas":
                boat.getRoot().setScaleX(0.2);
                boat.getRoot().setScaleY(0.2);
                break;
        }
    }

    /**
     * Places enemy boats on the grid.
     */
   public void placeEnemyBoats(){


        for(BoardElement boat:barcosGameEnemy){
            scaleBoats(boat);
            int col= boat.getCol();
            for (int i = 0; i < boat.getSpaces(); i++) {
                Pane cell = getCellFromGridPane(tablero2, boat.getRow() , col);
                col++;
                if(cell != null){
                    cell.setOnMouseClicked(e ->OnClickCellEnemy(cell,boat));
                }
            }
            boat.getRoot().setVisible(false);
            tablero2.add(boat.getRoot(),boat.getCol(),boat.getRow());
        }

    }

    /**
     *Handles the click event on an enemy cell.
     *
     * @param cell The cell that was clicked
     * @param boat The enemy boat associated with the cell
     */
    private void OnClickCellEnemy(Pane cell, BoardElement boat){
        activateBoard(tablero2,true);
        turnEnemy();
        cell.setOnMouseExited(null);
        cell.setStyle("-fx-background-color: pink; -fx-border-color: red;");
        cell.setDisable(true);

        if(boat.getLife() == 1 ){
            boat.getRoot().setVisible(true);
            barcosCountEnemy-=1;
        }else {
            boat.setLife();
        }
    }

    /**
     *Shows or hides the enemy board based on the selected radio button.
     * @param event The ActionEvent triggered by selecting a radio button.
     */
    public void showBoard(ActionEvent event ){
           RadioButton source = (RadioButton) event.getSource();


        if (source.isSelected()) {
            for(BoardElement boat:barcosGameEnemy){
                boat.getRoot().setVisible(true);

            }
            activateBoard(tablero1,true);

        } else {
            for(BoardElement boat:barcosGameEnemy){
                if(boat.getLife() != 0){
                    boat.getRoot().setVisible(false);
                }
            }
            activateBoard(tablero1,false);


        }
    }

    /**
     *
     * @param grid
     * @param select
     */
    private void activateBoard(GridPane grid, boolean select) {
        grid.setDisable(select);

    }


}


