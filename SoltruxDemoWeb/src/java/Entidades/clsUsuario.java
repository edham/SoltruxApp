/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

import java.util.Date;


/**
 *
 * @author EdHam
 */
public class clsUsuario {
    private int id_usuario;
    private String email;
    private boolean gps;
    private boolean cerro;
    private boolean telefono;
    private String numero;
    private Date fecha_actualizacion;
    private Date fecha_registro;

    public clsUsuario(String email) {
        this.email = email;
    }

    public clsUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    
    public clsUsuario() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGps() {
        return gps;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    public boolean isCerro() {
        return cerro;
    }

    public void setCerro(boolean cerro) {
        this.cerro = cerro;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public boolean isTelefono() {
        return telefono;
    }

    public void setTelefono(boolean telefono) {
        this.telefono = telefono;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}
