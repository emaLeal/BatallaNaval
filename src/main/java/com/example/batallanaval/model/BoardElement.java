package com.example.batallanaval.model;

import javafx.scene.layout.Pane;

public class BoardElement {
    private String name;
    private int quantity;
    private int spaces;
    private Pane barco;

    private int size;

    public BoardElement(String name, int quantity, int spaces ) {
        this.name = name;
        this.quantity = quantity;
        this.spaces = spaces;

        switch (name) {
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
                throw new IllegalArgumentException("Tipo de barco desconocido: " + name);
        }
        this.size = size;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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
