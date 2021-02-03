/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mass.jfxpersonal;

/**
 *
 * @author Kentucky
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.TimeZone;

public class ConexionH2 {

    private Connection connection;
    private String username = "kentucky";
    private String password = "1234";
    private String databaseUrl = "jdbc:h2:./jfxPacientes";

    public ConexionH2() throws SQLException {

        //Creamos una conexion
        connection = DriverManager.getConnection(databaseUrl, username, password);

        //confirmacion automatica para conn
        connection.setAutoCommit(true);

    }

    public ResultSet ejecutarConsulta(String H2) throws SQLException {

        Statement statement = this.connection.createStatement();
        return statement.executeQuery(H2);

    }

    public int ejecutarInstruccion(String H2) throws SQLException {

        Statement statement = this.connection.createStatement();
        return statement.executeUpdate(H2);

    }

    public int ultimoID() throws SQLException {
    //Por si a alguien le da por insertan muchas filas al mismo tiempo, LAST_INSERT_ID() devuelve el valor para la primera fila insertada.
        ResultSet rs = this.ejecutarConsulta("SELECT last insert id() as last_id");
        rs.next();
        return rs.getInt("last_id");
    }

    public void cerrarConnection() throws SQLException {
        this.connection.close();
    }
}
