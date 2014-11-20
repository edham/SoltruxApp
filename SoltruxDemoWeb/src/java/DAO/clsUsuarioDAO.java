/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import Entidades.clsUsuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class clsUsuarioDAO {
    
    
      public static List<clsUsuario> lista() throws Exception 
    {
        List<clsUsuario>  lista = null;
        
        Connection conn =null;
        CallableStatement stmt = null;
        ResultSet dr = null;
        try {
             String sql="select id_usuario,email,gps,cerro,fecha_actualizacion,fecha_registro,telefono,numero from usuario";

            conn = clsConexion.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(sql);
            dr = stmt.executeQuery();

            while(dr.next())
            {   //       
                if(lista==null)
                    lista=new ArrayList<clsUsuario>();
                
                clsUsuario objUsuario = new clsUsuario();
                objUsuario.setId_usuario(dr.getInt(1));
                objUsuario.setEmail(dr.getString(2));
                objUsuario.setGps(dr.getBoolean(3));
                objUsuario.setCerro(dr.getBoolean(4));
                objUsuario.setFecha_actualizacion(dr.getTimestamp(5));
                objUsuario.setFecha_registro(dr.getTimestamp(6)); 
                objUsuario.setTelefono(dr.getBoolean(7));
                objUsuario.setNumero(dr.getString(8));
                lista.add(objUsuario);
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
    
     public static clsUsuario get(String Mail) throws Exception 
    {
        clsUsuario  objUsuario = new clsUsuario();
        
        Connection conn =null;
        CallableStatement stmt = null;
        ResultSet dr = null;
        try {
             String sql="select id_usuario,email,fecha_registro from usuario where email='"+Mail+"'";

            conn = clsConexion.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(sql);
            dr = stmt.executeQuery();

            if(dr.next())
            {   //                
               
                objUsuario.setId_usuario(dr.getInt(1));
                objUsuario.setEmail(dr.getString(2));
                objUsuario.setFecha_registro(dr.getTimestamp(3)); 
            }
            else
            {
                sql="insert into usuario (email,gps,cerro,telefono,numero,fecha_actualizacion,fecha_registro)"
                        + "values(?,0,0,0,'',now(),now());";

                CallableStatement stmtInsert = conn.prepareCall(sql);
                stmtInsert.setString(1, Mail);
//               

                stmtInsert.executeUpdate();

                ResultSet rs = stmtInsert.getGeneratedKeys();
                if (rs.next()){
                    objUsuario.setId_usuario(rs.getInt(1));
                    objUsuario.setEmail(Mail);
                    objUsuario.setFecha_registro(new Date()); 
                  
                }
                rs.close();
                stmtInsert.close();
                
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
        return objUsuario;
    }
     
     
      public static clsUsuario getId(int id) throws Exception 
    {
        clsUsuario  objUsuario = null;
        
        Connection conn =null;
        CallableStatement stmt = null;
        ResultSet dr = null;
        try {
             String sql="select id_usuario,email,gps,cerro,fecha_actualizacion,fecha_registro,telefono,numero from usuario where id_usuario="+id;

            conn = clsConexion.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(sql);
            dr = stmt.executeQuery();

            if(dr.next())
            {   //                
                objUsuario = new clsUsuario();
                objUsuario.setId_usuario(dr.getInt(1));
                objUsuario.setEmail(dr.getString(2));
                objUsuario.setGps(dr.getBoolean(3));
                objUsuario.setCerro(dr.getBoolean(4));
                objUsuario.setFecha_actualizacion(dr.getTimestamp(5));
                objUsuario.setFecha_registro(dr.getTimestamp(6)); 
                objUsuario.setTelefono(dr.getBoolean(7));
                objUsuario.setNumero(dr.getString(8));
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
        return objUsuario;
    }
    
      
    public static boolean setTelefono(int Id) throws Exception
    {
        boolean rpta = false;
        Connection conn =null;
        CallableStatement stmt = null;
        try {
             String sql="update usuario set telefono=0,numero='',fecha_actualizacion=now() where id_usuario=?;";
            conn = clsConexion.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(sql);
            stmt.setInt(1, Id);
            rpta = stmt.executeUpdate() == 1;          
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
            } catch (SQLException e) {
            }
        }
        return rpta;
    }
 
}
