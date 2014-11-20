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
public class clsPreguntaUsuario {
    private int id_pregunta_usuario;
    private String pregunta;
    private int estado;
    private clsUsuario objUsuario;
    private Date fecha_creacion;

    public clsPreguntaUsuario() {
    }

    public clsPreguntaUsuario(String pregunta, clsUsuario objUsuario) {
        this.pregunta = pregunta;
        this.objUsuario = objUsuario;
    }



    public int getId_pregunta_usuario() {
        return id_pregunta_usuario;
    }

    public void setId_pregunta_usuario(int id_pregunta_usuario) {
        this.id_pregunta_usuario = id_pregunta_usuario;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public clsUsuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(clsUsuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    
}
