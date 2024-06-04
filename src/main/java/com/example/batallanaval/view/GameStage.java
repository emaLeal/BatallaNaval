package com.example.batallanaval.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {
    /**
     * load the view
     * */
    public GameStage() throws IOException {
        String url = "/com/example/batallanaval/game-view.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        setTitle("Game");
        Scene scene = new Scene(root);
        setScene(scene);

        setResizable(false);
        show();
    }

    /**
     * abstract the view
     * */
    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }

    /**
     * show the view
     * */
    public static GameStage getInstance() throws IOException {
        return GameStage.GameStageHolder.INSTANCE = new GameStage();
    }

    /**
     * close the view
     * */
    public static void deleteInstance() {
        GameStage.GameStageHolder.INSTANCE.close();
        GameStage.GameStageHolder.INSTANCE = null;
    }
}
