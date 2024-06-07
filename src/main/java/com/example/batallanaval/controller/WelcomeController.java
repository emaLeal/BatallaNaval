package com.example.batallanaval.controller;

import com.example.batallanaval.view.GameStage;
import com.example.batallanaval.view.WelcomeStage;

import java.io.IOException;

/**
 * Controller class for the welcome screen.
 * Handles initialization tasks and starting the game.
 */
public class WelcomeController {
    //Initializes the controller.
    public void initialize(){
        System.out.println("controlador");
    }

    /**
     * Starts the game by transitioning to the game stage.
     * Deletes the welcome stage
     *
     * @throws IOException If an I/O error occurs.
     */
    public void startGame() throws IOException {
        WelcomeStage.deleteInstance();
        GameStage.getInstance();
    }
}
