/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import Entidades.clsUbicacionUsuario;
import Entidades.clsUsuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class clsUbicacionUsuarioDAO {

     public  static int insertar(clsUbicacionUsuario entidad) throws Exception
    {
        int rpta = 0;
        Connection conn =null;
        CallableStatement stmt = null;
        try {
            
            
           String sql="insert into ubicacion_usuario (longitud,latitud,id_usuario) values"
                   + "(?,?,?)";
           
            conn = clsConexion.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(sql);
            stmt.setDouble(1, entidad.getLongitud());
            stmt.setDouble(2, entidad.getLatitud());
            stmt.setInt(3, entidad.getObjUsuario().getId_usuario());

           stmt.executeUpdate();
           
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                rpta=rs.getInt(1);
                    sql="update usuario set gps=?,fecha_actualizacion=now() where id_usuario=?;";
                    PreparedStatement psGPS = conn.prepareCall(sql);
                    psGPS.setBoolean(1,false);            
                    psGPS.setInt(2, entidad.getObjUsuario().getId_usuario());
                    psGPS.execute();
                    psGPS.close();
            }
            rs.close();
            
            
        conn.commit();
        } catch (Exception e) {
             if (conn != null) {
                    conn.rollback();
                }
            throw new Exception("Insertar"+e.getMessage(), e);
        }
        finally{
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
            }
        }
        return rpta;
    }
  
     public static List<clsUbicacionUsuario> lista() throws Exception 
    {
        List<clsUbicacionUsuario>  lista = null;
        
        Connection conn =null;
        CallableStatement stmt = null;
        ResultSet dr = null;
        try {
             String sql="select uu.id_ubicacion_usuario,uu.longitud,uu.latitud,u.id_usuario,"
                     + "u.email from ubicacion_usuario uu inner join usuario u "
                     + "on uu.id_usuario=u.id_usuario";

            conn = clsConexion.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(sql);
            dr = stmt.executeQuery();

            while(dr.next())
            {   //                
                if(lista==null)
                    lista=new ArrayList<clsUbicacionUsuario>();
                
                clsUsuario usuario = new clsUsuario();
                usuario.setId_usuario(dr.getInt(4));
                usuario.setEmail(dr.getString(5));
                
                clsUbicacionUsuario entidad = new clsUbicacionUsuario();
                entidad.setId_ubicacion_usuario(dr.getInt(1));
                entidad.setLongitud(dr.getDouble(2));
                entidad.setLatitud(dr.getDouble(3));
                entidad.setObjUsuario(usuario);
                lista.add(entidad);
            }
            

        conn.commit();
        } catch (Exception e) {
             if (conn != null) {
                    conn.rollback();
                }
            throw new Exception("Insertar"+e.getMessage(), e);
        }
        finally{
            try {
                dr.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
            }
        }
        return lista;
    }
}
