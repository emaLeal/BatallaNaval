package com.example.batallanaval.model;

public class Board {
    private int[][] boardMatrix;
    private boolean isPlayer;

    public Board(boolean isPlayer) {
        this.isPlayer = isPlayer;
        boardMatrix = new int[10][10];
    }

    private void fillBoardMachine() {
        System.out.println("LLenar tablero de maquina");
    }
}
