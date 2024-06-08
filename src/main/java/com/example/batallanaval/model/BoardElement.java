package com.example.batallanaval.model;

import javafx.scene.layout.Pane;

//Represents a board element, such as a ship, in the game.

public class BoardElement {
    private String name;
    private int quantity;
    private int spaces;
    private Pane barco;
    private int size;
    private int col = 0;
    private int finalCol = 0;
    private int row = 0;
    private int life;

    /**
     * Constructs a new BoardElement with the specified name, quantity, and spaces.
     *
     * @param name The name of the board element.
     * @param quantity The quantity of the board element.
     * @param spaces The number of spaces occupied by the board element.
     */
    public BoardElement(String name, int quantity, int spaces ) {
        this.name = name;
        this.quantity = quantity;
        this.spaces = spaces;
        this.life = spaces;
        selectBoard(name);
    }

    /**
     * Constructs a new BoardElement with the specified name, quantity, spaces, row, and column.
     *
     * @param name The name of the board element.
     * @param quantity The quantity of the board element.
     * @param spaces The number of spaces occupied by the board element.
     * @param row The row index of the board element.
     * @param col The column index of the board element.
     */
    public BoardElement(String name, int quantity, int spaces, int row, int col ) {
        this.name = name;
        this.quantity = quantity;
        this.spaces = spaces;
        this.col = col;
        this.row = row;
        this.life = spaces;
        selectBoard(name);
    }

    /**
     * Selects the board element based on its name.
     *
     * @param name The name of the board element.
     */
    private void selectBoard(String name){
        switch (this.name) {
            case "Portaviones":
                this.barco = new Portaviones().getRoot();
                break;
            case "SubMarinos":
                this.barco = new Submarino().getRoot();
                break;
            case "Destructores":
                this.barco = new Destructor().getRoot();
                break;
            case "Fragatas":
                this.barco = new Fragata().getRoot();
                break;
            default:
                throw new IllegalArgumentException("Unknown board element type: " + this.name);
        }
    }

    /**
     * Returns the quantity of the board element.
     *
     * @return The quantity of the board element.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the board element.
     *
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the remaining life of the board element.
     *
     * @return The remaining life of the board element.
     */
    public int getLife(){
        return life;
    }

    /**
     * Decreases the life of the board element by one.
     */
    public void setLife(){
        this.life -= 1;
    }

    /**
     * Returns the number of spaces occupied by the board element.
     *
     * @return The number of spaces occupied by the board element.
     */
    public int getSpaces() {
        return spaces;
    }

    /**
     * Returns the root pane of the board element.
     *
     * @return The root pane of the board element.
     */
    public Pane getRoot() {
        return barco;
    }

    /**
     * Sets the number of spaces occupied by the board element.
     *
     * @param spaces The number of spaces to set.
     */
    public void setSpaces(int spaces) {
        this.spaces = spaces;
    }

    /**
     * Returns the name of the board element.
     *
     * @return The name of the board element.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the column index of the board element.
     *
     * @return The column index of the board element.
     */
    public int getCol(){
        return col;
    }

    /**
     * Returns the row index of the board element.
     *
     * @return The row index of the board element.
     */
    public int getRow(){
        return row;
    }

    /**
     * Sets the name of the board element.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the size of the board element.
     *
     * @return The size of the board element.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the board element.
     *
     * @param size The size to set.
     */

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Sets the column position of the board element.
     *
     * @param col The column to set.
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Sets the row position of the board element.
     *
     * @param row The row to set.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Gets the final column position of the board element.
     *
     * @return The final column position.
     */
    public int getFinalCol() {
        return finalCol;
    }

    /**
     * Sets the final column position of the board element.
     *
     * @param finalCol The final column to set.
     */
    public void setFinalCol(int finalCol) {
        this.finalCol = finalCol;
    }
}