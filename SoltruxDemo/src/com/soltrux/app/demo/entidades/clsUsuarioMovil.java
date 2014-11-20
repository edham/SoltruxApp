/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soltrux.app.demo.entidades;

import java.util.Date;

/**
 *
 * @author Toditos
 */
public class clsUsuarioMovil {
    private int int_id_usuario_movil;
    private String str_email;
    private boolean bool_gps;
    private boolean bool_cerro;
    private Date dat_fecha_creacion;

    
    public clsUsuarioMovil() {
        this.bool_cerro=false;
        this.bool_gps=false;
    }

    public int getInt_id_usuario_movil() {
        return int_id_usuario_movil;
    }

    public void setInt_id_usuario_movil(int int_id_usuario_movil) {
        this.int_id_usuario_movil = int_id_usuario_movil;
    }



    public String getStr_email() {
        return str_email;
    }

    public void setStr_email(String str_email) {
        this.str_email = str_email;
    }

    public boolean isBool_gps() {
        return bool_gps;
    }

    public void setBool_gps(boolean bool_gps) {
        this.bool_gps = bool_gps;
    }

    public Date getDat_fecha_creacion() {
        return dat_fecha_creacion;
    }

    public void setDat_fecha_creacion(Date dat_fecha_creacion) {
        this.dat_fecha_creacion = dat_fecha_creacion;
    }

    public boolean isBool_cerro() {
        return bool_cerro;
    }

    public void setBool_cerro(boolean bool_cerro) {
        this.bool_cerro = bool_cerro;
    }

    

  
    
}
