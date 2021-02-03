/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mass.jfxpersonal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Kentucky
 */
public class TablaPacientesDao {

    public static final String URL_CONN = "jdbc:h2:./jfxPacientes";
    public static final String URL_BD = "";
    public static final String CONTR_BD = "";

    public TablaPacientesDao() {

        crearTablaSiNoExiste();

    }

    public void crearTablaSiNoExiste() {

        try (Connection conexionDatabase
                = DriverManager.getConnection(URL_CONN, URL_BD, CONTR_BD)) {
            Statement statement = conexionDatabase.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS pacientes"
                    + "(id INTEGER auto_increment, "
                    + " nombre VARCHAR (255), "
                    + " apellido VARCHAR (255), "
                    + " anio DATE, "
                    + " url VARCHAR (10000),"
                    + " datos VARCHAR (255),"
                    + " h BOOLEAN,"
                    + " l BOOLEAN,"
                    + " g BOOLEAN,"
                    + " t BOOLEAN,"
                    + " b BOOLEAN,"
                    + " i BOOLEAN)";

            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void guardar(TablaPacientes paciente) {
        LocalDate ld = paciente.getAnio();
        java.sql.Date sqlDate = java.sql.Date.valueOf(ld);
        try (Connection conexionDatabase = DriverManager.getConnection(URL_CONN, URL_BD, CONTR_BD)) {
            Statement statement = conexionDatabase.createStatement();
            String sql = "INSERT INTO pacientes (nombre, apellido, anio, url, datos, h, l ,g, t, b, i)"
                    + "VALUES ('" + paciente.getNombre() + "', '" + paciente.getApellido() + "', '" + sqlDate 
                    + "', '" + paciente.getUrl() + "', '" + paciente.getDatos() + "', " + paciente.isH() + " , " 
                    + paciente.isL() + " , " + paciente.isG() + " , " + paciente.isT() + " , " + paciente.isB() + " , " 
                    + paciente.isI() + ")";

            //ejecutamos la consulta
            statement.executeUpdate(sql);
            System.out.println("Información guardada en la base de datos H2");

        } catch (Exception e) {
            throw new RuntimeException("Ocurrido un error al guardar información: " + e.getMessage());
        }

    }

    public void actualizar(TablaPacientes paciente) {
        LocalDate ld = paciente.getAnio();

        java.sql.Date sqlDate = java.sql.Date.valueOf(ld);

        try (Connection conexionDatabase
                = DriverManager.getConnection(URL_CONN, URL_BD, CONTR_BD)) {
            Statement statement = conexionDatabase.createStatement();
            String sql = "UPDATE pacientes SET nombre='" + paciente.getNombre() + "', apellido='" + paciente.getApellido() + "', anio='" 
                    + sqlDate + "', datos='" + paciente.getDatos() + "', h=" + paciente.isH() + ", l=" + paciente.isL() + ", g=" + paciente.isG() 
                    + ", t=" + paciente.isT() + ", b=" + paciente.isB() + ", i=" + paciente.isI() + " WHERE id=" + paciente.getId();
            statement.executeUpdate(sql);

        } catch (Exception e) {
            throw new RuntimeException("Ocurrido un error al guardar información: " + e.getMessage());
        }

    }

    public void eliminar(TablaPacientes paciente) {
        try (Connection conexionDatabase = DriverManager.getConnection(URL_CONN, URL_BD, CONTR_BD)) {
            Statement statement = conexionDatabase.createStatement();
            String sql = "DELETE FROM pacientes WHERE id = " + paciente.getId();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrido un error al guardar información: " + e.getMessage());
        }

    }

    public void guardarOactualizar(TablaPacientes paciente) {
        if (paciente.getId() == 0) {
            guardar(paciente);

        } else {
            actualizar(paciente);

        }
    }

    public List<TablaPacientes> cargarPacientesDB() {
        List<TablaPacientes> pacientes = new ArrayList<>();
        try (Connection conexionDatabase = DriverManager.getConnection(URL_CONN, URL_BD, CONTR_BD)) {
            Statement statement = conexionDatabase.createStatement();
            String sql = "SELECT * FROM pacientes ORDER BY id";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                //creamos nuestro objeto vacio departamento al que le damos unos valores
                //y luego a nuestra lista pacientes le pasamos  que invocará los valores
                TablaPacientes paciente = new TablaPacientes();
                paciente.setId(resultSet.getInt("id"));
                paciente.setNombre(resultSet.getString("nombre"));
                paciente.setApellido(resultSet.getString("apellido"));
                paciente.setAnio((resultSet.getDate("anio")).toLocalDate());
                paciente.setUrl(resultSet.getString("url"));
                paciente.setDatos(resultSet.getString("datos"));

                paciente.setH(resultSet.getBoolean("h"));
                paciente.setL(resultSet.getBoolean("l"));
                paciente.setG(resultSet.getBoolean("g"));
                paciente.setT(resultSet.getBoolean("t"));
                paciente.setB(resultSet.getBoolean("b"));
                paciente.setI(resultSet.getBoolean("i"));

                //System.out.println(paciente.getDatos());
                pacientes.add(paciente);
                //System.out.println(paciente);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(pacientes.toString());
        return pacientes;

    }

}
