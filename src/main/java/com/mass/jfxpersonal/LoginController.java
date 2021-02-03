/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mass.jfxpersonal;

import static com.mass.jfxpersonal.TablaPacientesDao.CONTR_BD;
import static com.mass.jfxpersonal.TablaPacientesDao.URL_BD;
import static com.mass.jfxpersonal.TablaPacientesDao.URL_CONN;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kentucky
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtPass;
    @FXML
    private Button btnLogin;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ESTO SE LO HE METIDO PARA PROBAR para APAÑAR LO DL LOGIN
        try (Connection conexionDatabase
                = DriverManager.getConnection(URL_CONN, URL_BD, CONTR_BD)) {
            Statement statement = conexionDatabase.createStatement();

            String sql1 = "CREATE TABLE IF NOT EXISTS usuarios"
                    + " (usuario VARCHAR (255), "
                    + " password INTEGER (100)) ";
            statement.executeUpdate(sql1);

            conexionDatabase.commit();

            String sql2 = "INSERT INTO usuarios (usuario, password) VALUES ('kentucky', '1234')";
            statement.execute(sql2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void comprobarLog(ActionEvent event) throws SQLException, IOException {

        String usuario = this.txtUsuario.getText();
        String password = this.txtPass.getText();

        Usuario user = new Usuario(usuario, password);

        if (user.login()) {
            //Alert es propio de jfx podriamos usar el joptionpane
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Información");
            alert.setContentText("Login correcto");
            alert.showAndWait();

            //Si es cierto abrimos siguiente ventana
            Parent container = new FXMLLoader().load(getClass().getResource("primary.fxml"));

            Scene scene = new Scene(container, 900, 500);
            scene.getStylesheets().addAll(getClass().getResource("../../../styles/CSS.css").toExternalForm());

            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResource("../../../img/report.png").toExternalForm()));
            stage.setScene(scene);
            stage.setTitle("Pacientes");
            stage.show();

            Stage myStage = (Stage) this.btnLogin.getScene().getWindow();
            myStage.close();
            /*
            scene.getStylesheets().addAll(getClass().getResource("../../../styles/CSS.css").toExternalForm());
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("/img/heart.png"));
            primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("../../../img/heart.png")));
            
            primaryStage.show();*/
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Login incorrecto");
            alert.showAndWait();
        }
    }

}
