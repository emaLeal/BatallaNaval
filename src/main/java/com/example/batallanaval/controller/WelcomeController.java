package com.example.batallanaval.controller;

import com.example.batallanaval.view.GameStage;
import com.example.batallanaval.view.WelcomeStage;

import java.io.IOException;

public class WelcomeController {
    public void initialize(){
        System.out.println("controlador");
    }

    public void startGame() throws IOException {
        WelcomeStage.deleteInstance();
        GameStage.getInstance();
    }
}
