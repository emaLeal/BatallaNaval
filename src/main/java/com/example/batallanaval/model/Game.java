package com.example.batallanaval.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents the game logic and management.
 */
public class Game {
    private List<BoardElement> boatgame;

    /**
     * Constructs a new Game and initializes the list of board elements.
     */
    public Game(){
        boatgame = new ArrayList<>();
        orderEnemyShips();
    }

    /**
     * Orders the enemy ships and adds them to the list of board elements.
     */
    public void orderEnemyShips() {
        // Create destructor ships
        for (int i = 1; i < 4; i++) {
            BoardElement destructor = new BoardElement("Destructores", 3, 2, 2*i, 1+i);
            boatgame.add(destructor);
        }

        // Create submarine ships
        for (int i = 1; i < 3 ; i++) {
            BoardElement submarino = new BoardElement("SubMarinos", 2, 3, 5*i, 4+i);
            boatgame.add(submarino);
        }

        // Create frigate ships
        for (int i = 2; i < 10 ; i+=2) {
            BoardElement fragatas = new BoardElement("Fragatas", 4, 1, 7, i);
            boatgame.add(fragatas);
        }

        // Create aircraft carrier ship
        BoardElement Portavion = new BoardElement("Portaviones", 1, 4, 3, 6);
        boatgame.add(Portavion);
    }

    /**
     * Returns the list of board elements representing enemy ships.
     *
     * @return The list of board elements representing enemy ships.
     */
    public List<BoardElement> getBoatgame(){
        return boatgame;
    }
}
