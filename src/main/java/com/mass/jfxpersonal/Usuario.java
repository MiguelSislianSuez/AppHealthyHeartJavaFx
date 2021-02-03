/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mass.jfxpersonal;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Kentucky
 */
public class Usuario {

    private String usuario;
    private String password;

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public boolean login() throws SQLException {
        //Instanciamos la clase conH2 y hacemos una consulta para verificar que el user existe en la bd
        ConexionH2 conn = new ConexionH2();
        String H2 = "";
        H2 += "SELECT * ";
        H2 += "FROM usuarios ";
        H2 += "WHERE lower(usuario) = '" + usuario.toLowerCase() + "' and password = '" + password + "'";
        
        ResultSet rs = conn.ejecutarConsulta(H2);
        boolean hayUsuarios = rs.next();//devuelve un true si hay un valor despues

        return hayUsuarios;

    }

}
