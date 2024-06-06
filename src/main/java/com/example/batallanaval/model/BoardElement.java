package com.example.batallanaval.model;

public class BoardElement {
    private String name;
    private int quantity;
    private int spaces;

    private int size;

    public BoardElement(String name, int quantity, int spaces) {
        this.name = name;
        this.quantity = quantity;
        this.spaces = spaces;
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
