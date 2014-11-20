/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

/**
 *
 * @author EdHam
 */
public class clsUbicacionUsuario {
    private int id_ubicacion_usuario;
    private double longitud;
    private double latitud;

    private clsUsuario objUsuario;

    public clsUbicacionUsuario(double longitud, double latitud, clsUsuario objUsuario) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.objUsuario = objUsuario;
    }
    

    public clsUbicacionUsuario() {
    }

    public int getId_ubicacion_usuario() {
        return id_ubicacion_usuario;
    }

    public void setId_ubicacion_usuario(int id_ubicacion_usuario) {
        this.id_ubicacion_usuario = id_ubicacion_usuario;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public clsUsuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(clsUsuario objUsuario) {
        this.objUsuario = objUsuario;
    }

   
}
