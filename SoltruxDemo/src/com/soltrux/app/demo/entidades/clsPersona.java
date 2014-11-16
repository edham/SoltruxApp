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
public class clsPersona {
    private int int_id_persona;
    private String str_nombres;
    private String str_apellidos;
    private Date dat_fecha_nacimiento;
    private boolean bol_sexo;

    public clsPersona() {
        this.bol_sexo = false;
    }

    

    
    public int getInt_id_persona() {
        return int_id_persona;
    }

    public void setInt_id_persona(int int_id_persona) {
        this.int_id_persona = int_id_persona;
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

    public Date getDat_fecha_nacimiento() {
        return dat_fecha_nacimiento;
    }

    public void setDat_fecha_nacimiento(Date dat_fecha_nacimiento) {
        this.dat_fecha_nacimiento = dat_fecha_nacimiento;
    }

    public boolean isBol_sexo() {
        return bol_sexo;
    }

    public void setBol_sexo(boolean bol_sexo) {
        this.bol_sexo = bol_sexo;
    }
    
    

    
}
