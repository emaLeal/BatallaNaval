package com.example.batallanaval.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeStage extends Stage {

    /**
     * Constructs a new WelcomeStage and initializes its components.
     *
     * @throws IOException If an I/O error occurs while loading the FXML file.
     */
    public WelcomeStage() throws IOException {
        // Load the FXML file for the welcome view
        String url = "/com/example/batallanaval/welcome-view.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();
        //Set the title, scene, and other properties
        setTitle("Batalla Naval");
        Scene scene = new Scene(root);
        setScene(scene);
        setResizable(false);
        show();
    }

    /**
     * abstract the view
     * */
    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }

    /**
     * show the view
     * */
    public static WelcomeStage getInstance() throws IOException {
        return WelcomeStageHolder.INSTANCE = new WelcomeStage();
    }

    /**
     * close the view
     * */
    public static void deleteInstance() {
        WelcomeStageHolder.INSTANCE.close();
        WelcomeStageHolder.INSTANCE = null;
    }
}
