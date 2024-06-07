package com.example.batallanaval.model;

import javafx.scene.layout.Pane;

public class BoardElement {
    private String name;
    private int quantity;
    private int spaces;
    private Pane barco;
    private int size;
    private int col = 0;
    private int row = 0;
    private int life;

    public BoardElement(String name, int quantity, int spaces ) {
        this.name = name;
        this.quantity = quantity;
        this.spaces = spaces;
        this.size = size;
        selectBoard(name);
    }
    public BoardElement(String name, int quantity, int spaces,int row,int col ) {
        this.name = name;
        this.quantity = quantity;
        this.spaces = spaces;
        this.size = size;
        this.col = col;
        this.row = row;
        this.life = spaces;
        selectBoard(name);
    }
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
                throw new IllegalArgumentException("Tipo de barco desconocido: " + this.name);
        }
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLife(){return life;}
    public void setLife(){ this.life -= 1;}

    public int getSpaces() {
        return spaces;
    }

    public Pane getRoot() {
        return barco;
    }

    public void setSpaces(int spaces) {
        this.spaces = spaces;
    }

    public String getName() {
        return name;
    }

    public int getCol(){return col;}

    public int getRow(){return row;}

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
