/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mass.jfxpersonal;

import java.time.LocalDate;

/**
 *
 * @author Kentucky
 */
public class TablaPacientes {

    private int id;
    private LocalDate anio;

    private String nombre, apellido, datos;
    private String url;
    private boolean h, l, g, t, b, i;
    private String generos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getAnio() {
        return anio;
    }

    public void setAnio(LocalDate anio) {
        this.anio = anio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isH() {
        return h;
    }

    public void setH(boolean h) {
        this.h = h;
    }

    public boolean isL() {
        return l;
    }

    public void setL(boolean l) {
        this.l = l;
    }

    public boolean isG() {
        return g;
    }

    public void setG(boolean g) {
        this.g = g;
    }

    public boolean isT() {
        return t;
    }

    public void setT(boolean t) {
        this.t = t;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public boolean isI() {
        return i;
    }

    public void setI(boolean i) {
        this.i = i;
    }

    public String getGeneros() {
        String generos = "";
        if (h) {
            generos += " Heterosexual ";
        }

        if (l) {
            generos += " Lesbiana ";
        }

        if (g) {
            generos += " Gay ";
        }

        if (t) {
            generos += " Transexual ";
        }

        if (b) {
            generos += " Bisexual ";
        }

        if (i) {
            generos += " Intersexual ";
        }
        return generos;
    }
    

    @Override
    public String toString() {
        return "TablaPacientes{" + "id=" + id + ", anio=" + anio + ", nombre=" + nombre + ", apellido=" + apellido + ", datos=" + datos + ", url=" + url + ", h=" + h + ", l=" + l + ", g=" + g + ", t=" + t + ", b=" + b + ", i=" + i + '}';
    }

}
