package com.example.batallanaval.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<BoardElement> barcosGame;

    public Game(){
        barcosGame = new ArrayList<>();
        orderEnemyShips();
    }

    public void orderEnemyShips() {

        for (int i = 1; i < 4; i++) {
            BoardElement destructor = new BoardElement("Destructores", 3, 2,2*i, 1+i);
            barcosGame.add(destructor);
        }

        for (int i = 1; i <3 ; i++) {
            BoardElement submarino = new BoardElement("SubMarinos", 2, 3, 5*i,4+i);
            barcosGame.add(submarino);
        }

        for (int i = 1; i <10 ; i+=2) {
            BoardElement fragatas = new BoardElement("Fragatas", 4, 1,7,i );
            barcosGame.add(fragatas);
        }

        BoardElement Portavion = new BoardElement("Portaviones", 1, 4,3,6);
        barcosGame.add(Portavion);


    }
public List<BoardElement> getBarcosGame(){
        return barcosGame;
}

}
