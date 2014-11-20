/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import Entidades.clsPreguntaUsuario;
import Entidades.clsUsuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author virgil
 */
public class clsPreguntaUsuarioDAO {
   
    
     public  static int insertar(clsPreguntaUsuario entidad) throws Exception
    {
        int rpta = 0;
        Connection conn =null;
        CallableStatement stmt = null;
        try {
            
            
           String sql="insert into pregunta_usuario (pregunta,id_usuario,estado,fecha_creacion) values"
                   + "(?,?,0,now())";
           
            conn = clsConexion.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(sql);
            stmt.setString(1, entidad.getPregunta());
            stmt.setInt(2, entidad.getObjUsuario().getId_usuario());

           stmt.executeUpdate();
          
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                rpta=rs.getInt(1);
                 sql="update usuario set fecha_actualizacion=now() where id_usuario=?;";
                    PreparedStatement psGPS = conn.prepareCall(sql);           
                    psGPS.setInt(1, entidad.getObjUsuario().getId_usuario());
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
     
       public static clsPreguntaUsuario getActiva() throws Exception 
    {
        clsPreguntaUsuario  entidad = null;
        
        Connection conn =null;
        CallableStatement stmt = null;
        ResultSet dr = null;
        try {
             String sql="select id_pregunta_usuario,pregunta,fecha_creacion "
                     + "from pregunta_usuario where estado = 1 order by id_pregunta_usuario desc limit 1";

            conn = clsConexion.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(sql);
            dr = stmt.executeQuery();

            if(dr.next())
            {   //                
                entidad = new clsPreguntaUsuario();
                entidad.setId_pregunta_usuario(dr.getInt(1));
                entidad.setPregunta(dr.getString(2));
                entidad.setFecha_creacion(dr.getTimestamp(3));
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
        return entidad;
    }
       
    
    public static List<clsPreguntaUsuario> lista() throws Exception 
    {
        List<clsPreguntaUsuario>  lista = null;
        
        Connection conn =null;
        CallableStatement stmt = null;
        ResultSet dr = null;
        try {
             String sql="SELECT pu.id_pregunta_usuario,pu.pregunta,pu.fecha_creacion,"
                     + "u.id_usuario,u.email FROM  pregunta_usuario pu inner join usuario u "
                     + "on pu.id_usuario=u.id_usuario where pu.estado<2;";

            conn = clsConexion.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(sql);
            dr = stmt.executeQuery();

            while(dr.next())
            {   //                
                if(lista==null)
                    lista=new ArrayList<clsPreguntaUsuario>();
                
                clsUsuario usuario = new clsUsuario();
                usuario.setId_usuario(dr.getInt(4));
                usuario.setEmail(dr.getString(5));
                
                clsPreguntaUsuario entidad = new clsPreguntaUsuario();
                entidad.setId_pregunta_usuario(dr.getInt(1));
                entidad.setPregunta(dr.getString(2));
                entidad.setFecha_creacion(dr.getTimestamp(3));
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
