/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soltrux.app.demo.entidades;

/**
 *
 * @author Toditos
 */
public class clsUsuarioMovil {
    private int int_id_usuario_movil;
    private String str_nombres;
    private String str_apellidos;
    private String str_email;
    private String str_telefono;
   

    public clsUsuarioMovil(String cadena) {
          String [] entidad = cadena.split("\\<+parametro+>");          
            this.int_id_usuario_movil = Integer.parseInt(entidad[0].trim());
            this.str_nombres = entidad[1].trim();
            this.str_apellidos = entidad[2].trim();            
            this.str_telefono = entidad[3].trim();
            this.str_email = entidad[4].trim();
          
    }

    
    public clsUsuarioMovil() {
    }

    public int getInt_id_usuario_movil() {
        return int_id_usuario_movil;
    }

    public void setInt_id_usuario_movil(int int_id_usuario_movil) {
        this.int_id_usuario_movil = int_id_usuario_movil;
    }

    public String getStr_nombres() {
        return str_nombres;
    }

    public void setStr_nombres(String str_nombres) {
        this.str_nombres = str_nombres;
    }

    public String getStr_apellidos() {
        return str_apellidos;
    }

    public void setStr_apellidos(String str_apellidos) {
        this.str_apellidos = str_apellidos;
    }

    public String getStr_email() {
        return str_email;
    }

    public void setStr_email(String str_email) {
        this.str_email = str_email;
    }

    public String getStr_telefono() {
        return str_telefono;
    }

    public void setStr_telefono(String str_telefono) {
        this.str_telefono = str_telefono;
    }

  
    
}
