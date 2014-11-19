/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soltrux.app.demo.entidades;

/**
 *
 * @author EdHam
 */
public class clsContacto {
    private String numero;
    private String nombre;

    public clsContacto() {
    }

    public clsContacto(String numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
