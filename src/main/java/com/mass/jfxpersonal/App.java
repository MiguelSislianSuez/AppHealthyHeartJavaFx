package com.mass.jfxpersonal;

import com.sun.tools.javac.Main;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.Logger;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javax.swing.JFrame;

/**
 * JavaFX App
 */
public class App extends Application {

    public void start(Stage primaryStage) throws IOException {
                final JFrame jFrame = new JFrame();

       
        /*Parent container = new FXMLLoader().load(getClass().getResource("primary.fxml"));

        Scene scene = new Scene(container, 900, 500);
        scene.getStylesheets().addAll(getClass().getResource("../../../styles/CSS.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pacientes");
        primaryStage.getIcons().add(new Image("/img/heart.png"));
        //primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("../../../img/heart.png")));

        primaryStage.show();
    }*/
       
        try {

            Parent container = new FXMLLoader().load(getClass().getResource("LoginController.fxml"));

            Scene scene = new Scene(container, 250, 400);
            scene.getStylesheets().addAll(getClass().getResource("../../../styles/CSS.css").toExternalForm());
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image(getClass().getResource("../../../img/report.png").toExternalForm()));
            primaryStage.setTitle("Login");

            // Muestro la ventana
            primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
