package com.example.batallanaval.controller;

import com.example.batallanaval.model.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @FXML
    private Label interaction;

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
    private FileCRUD file;
    /**
     * Initializes the game controller.
     * Performs setup tasks such as setting up the timeline and populating the Sudoku board.
     */
    public void initialize(){
        file = new FileCRUD("barcos.txt");
        List<String> files = file.read();

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
        boatsFile(files);
        placeEnemyBoats();
    }


    /**
     * Processes a list of boat data and places the boats on the grid based on the input.
     *
     * @param files List of strings where each string contains boat data in the format: "boatName,row,col".
     */
    public void boatsFile(List<String> files) {
        for (String boat : files) {
            // Regular expression to find text between quotes or text without quotes followed by a comma
            Pattern pattern = Pattern.compile("\"[^\"]*\"|[^,]+");
            Matcher matcher = pattern.matcher(boat);

            // List to store the elements
            List<String> elements = new ArrayList<>();

            // Iterate over matches and add them to the list
            while (matcher.find()) {
                // Get the match text and remove quotes if present
                String match = matcher.group().replaceAll("^\"|\"$", "");
                elements.add(match);
            }
            String[] array = elements.toArray(new String[0]);

            // Select the ship and place it on the grid based on the parsed data
            switch (array[0]) {
                case "Portaviones":
                    selectedShip = elPortavion;
                    shipSize = elPortavion.getSpaces();
                    placeShip(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
                    break;
                case "SubMarinos":
                    selectedShip = elSubmarino;
                    shipSize = elSubmarino.getSpaces();
                    placeShip(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
                    break;
                case "Destructores":
                    selectedShip = elDestructor;
                    shipSize = elDestructor.getSpaces();
                    placeShip(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
                    break;
                case "Fragatas":
                    selectedShip = elFragatas;
                    shipSize = elFragatas.getSpaces();
                    placeShip(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
                    break;
            }
        }
    }

    /**
     * Starts the game by activating the player's board and deactivating the enemy's board.
     */
    public void startGame() {
        activateBoard(tablero1, true);
        activateBoard(tablero2, false);
        gameStart = true;
    }


    /**
     * Handles the enemy's turn by attacking a random cell on the player's grid.
     */
    public void turnEnemy() {
        if (gameStart) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5)); // Simulate enemy thinking time
            Random random = new Random();
            int col = random.nextInt(10) + 1;
            int row = random.nextInt(10) + 1;

            Pane cell = getCellFromGridPane(tablero1, row, col);
            cell.setStyle("-fx-background-color: orange");

            // Check if the attacked cell hits any player's ship
            for (BoardElement barco : barcosPlayer) {
                if (col >= barco.getCol() && col <= barco.getFinalCol() && row == barco.getRow()) {
                    if (barco.getLife() == 1) {
                        // Ship is sunk, mark all cells as dark red
                        for (int i = barco.getCol(); i <= barco.getFinalCol(); i++) {
                            getCellFromGridPane(tablero1, row, i).setStyle("-fx-background-color: darkred");
                        }
                        barcosPlayer.remove(barco);
                        barcosCountPlayer -= 1;
                        verifyWinner();
                    } else {
                        // Ship is hit but not sunk, mark cell as red
                        cell.setStyle("-fx-background-color: red");
                        barco.setLife();
                    }
                    break;
                }
            }

            pause.setOnFinished(event -> activateBoard(tablero2, false)); // Deactivate enemy board after attack
            pause.play();
        }
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
                            placeShip(currentRow, currentCol);
                            verifyWinner();
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
     * @param row The row index of the cell
     * @param col The col index of the cell
     */

    private void placeShip( int row, int col) {
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



                //shipCell.setStyle("-fx-background-color: pink; -fx-border-color: red;");

                Text shipText = new Text(selectedShip.getName());
                shipText.setStyle("-fx-font-size: 8pt;");
                scaleBoats(shipModel);
                tablero1.add(shipModel.getRoot(),col,row);
                shipModel.setCol(col);
                shipModel.setFinalCol(col+shipSize-1);
                shipModel.setRow(row);
                barcosPlayer.add(shipModel);
                file.create(shipModel.getName()+","+shipModel.getRow()+","+shipModel.getCol());
                // Disable click event for this cell
//                shipCell.setOnMouseClicked(e -> cellEnemy(shipModel,shipCell));

                selectedShip.setQuantity(selectedShip.getQuantity() - 1);
                //selectedButton.setText(selectedShip.getName() + " " + selectedShip.getQuantity());
                if (selectedShip.getQuantity() == 0)
                    //selectedButton.setDisable(true);
                shipCell.setDisable(true);
                for (BoardElement b : barcos) {
                    if (b.getQuantity() != 0)
                        return;
                }

                play.setDisable(false);
            }
        }
    }

    /**
     *
     * @param shipModel
     * @param shipCell
     */
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
            verifyWinner();

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
     * Activates or deactivates the given grid.
     * @param grid  The grid to activate or deactivate.
     * @param select  If true, the grid will be activated; otherwise, it will be deactivated.
     */
    private void activateBoard(GridPane grid, boolean select) {
        grid.setDisable(select);
    }

    /**
     *Verifies the current game status to determine if there is a winner.
     * If the player has destroyed all enemy ships, they win.
     */
    private void verifyWinner() {
        System.out.println(barcosCountEnemy + " " + barcosCountPlayer);

        if (barcosCountEnemy == 0) {
            // Player wins the game
            System.out.println("Ganaste");
            activateBoard(tablero1, true);
            activateBoard(tablero2, true);
            interaction.setText("GANASTE!!!");
            interaction.setStyle("-fx-font-weight:BOLD;-fx-text-fill: green");
            gameStart = false;
        }  else if (barcosCountPlayer == 0)  {
            System.out.println("Perdiste");
            activateBoard(tablero1, true);
            activateBoard(tablero2, true);
            interaction.setText("Lo siento, perdiste");
            interaction.setStyle("-fx-font-weight: bold;-fx-text-fill:red");
            gameStart = false;
            }
    }

}
