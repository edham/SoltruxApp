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

/**
 *
 * @author virgil
 */
public class clsUsuarioDAO {
     public static clsUsuario get(clsUsuario entidad) throws Exception 
    {
        clsUsuario  objUsuario = new clsUsuario();
        
        Connection conn =null;
        CallableStatement stmt = null;
        ResultSet dr = null;
        try {
             String sql="select id_usuario,email,gps,cerro,fecha_actualizacion,fecha_registro from usuario where email='"+entidad.getEmail()+"'";

            conn = clsConexion.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(sql);
            dr = stmt.executeQuery();

            if(dr.next())
            {   //                
               
                objUsuario.setId_usuario(dr.getInt(1));
                objUsuario.setEmail(dr.getString(2));
                objUsuario.setGps(dr.getBoolean(3));
                objUsuario.setCerro(dr.getBoolean(4));
                objUsuario.setFecha_actualizacion(dr.getTimestamp(5));
                objUsuario.setFecha_registro(dr.getTimestamp(6)); 
            }
            else
            {
                sql="insert into usuario (email,gps,cerro,fecha_actualizacion,fecha_registro)values(?,?,?,now(),now());";

                CallableStatement stmtInsert = conn.prepareCall(sql);
                stmtInsert.setString(1, entidad.getEmail());
                stmtInsert.setBoolean(2, entidad.isGps());
                stmtInsert.setBoolean(3, entidad.isCerro());
                entidad.setFecha_actualizacion(new Date());
                entidad.setFecha_registro(new Date()); 

                stmtInsert.executeUpdate();

                ResultSet rs = stmtInsert.getGeneratedKeys();
                if (rs.next()){
                    entidad.setId_usuario(rs.getInt(1));
                    objUsuario=entidad;
                  
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
    
    
//    public static List<clsUsuario> listarXUsuario(int IdUsuario) throws Exception 
//    {
//        List<clsUsuario> lista = null;
//        
//        Connection conn =null;
//        CallableStatement stmt = null;
//        ResultSet dr = null;
//        try {
//             String sql="SELECT i.id_incidente,i.latitud,i.longuitud,i.detalle,i.fecha_registro,i.estado,"
//                     + "i.foto,ti.id_tipo_incidente,ti.nombre,i.rapides,i.conformidad FROM incidente i inner join tipo_incidente ti "
//                     + "on i.id_tipo_incidente=ti.id_tipo_incidente where i.id_usuario="+IdUsuario;
//
//            conn = clsConexion.getConnection();
//            stmt = conn.prepareCall(sql);
//            dr = stmt.executeQuery();
//
//            while(dr.next())
//            {   
//                if(lista==null)
//                    lista = new ArrayList<clsUsuario>();
//                
//                clsTipoIncidente objTipoIncidente = new clsTipoIncidente();
//                objTipoIncidente.setInt_id_tipo_incidente(dr.getInt(8));
//                objTipoIncidente.setStr_nombre(dr.getString(9));
//                
//                clsUsuario objclsUsuario = new clsUsuario();
//                objclsUsuario.setInt_id_incidente(dr.getInt(1));
//                objclsUsuario.setDou_latitud(dr.getDouble(2));
//                objclsUsuario.setDou_longitud(dr.getDouble(3));
//                objclsUsuario.setStr_detalle(dr.getString(4));
//                objclsUsuario.setDat_fecha_registro(dr.getTimestamp(5));      
//                objclsUsuario.setInt_estado(dr.getInt(6)); 
//                objclsUsuario.setByte_foto(dr.getBytes(7));
//                objclsUsuario.setObjTipoIncidente(objTipoIncidente);
//                objclsUsuario.setInt_rapides(dr.getInt(10));
//                objclsUsuario.setInt_conformidad(dr.getInt(10));
//                
//                lista.add(objclsUsuario);                
//            }
//
//       } catch (Exception e) {
//    
//                throw new Exception("Listar Cargos "+e.getMessage(), e);
//        
//        }
//        finally{
//            try {
//                dr.close();
//                stmt.close();
//                conn.close();
//            } catch (Exception e) {
//            }
//        }
//        return lista;
//    }
    
  
//     public  static clsUsuario insertar(clsUsuario entidad) throws Exception
//    {
//        clsUsuario objUsuario = null;
//        Connection conn =null;
//        CallableStatement stmt = null;
//        try {
//            
//            
//           String sql="insert into incidente (id_tipo_incidente,id_usuario,latitud,"
//                   + "longuitud,detalle,foto,fecha_registro,estado,rapides,conformidad) values"
//                   + "(?,?,?,?,?,?,now(),0,0,0)";
//           
//            conn = clsConexion.getConnection();
//            stmt = conn.prepareCall(sql);
////            stmt.setInt(1, entidad.getObjTipoIncidente().getInt_id_tipo_incidente());
////            stmt.setInt(2, entidad.getObjUsuario().getInt_id_usuario());
////            stmt.setDouble(3, entidad.getDou_latitud());
////            stmt.setDouble(4, entidad.getDou_longitud());
////            stmt.setString(5, entidad.getStr_detalle());
////            stmt.setBytes(6,entidad.getByte_foto());
//
//           stmt.executeUpdate();
//           
//            ResultSet rs = stmt.getGeneratedKeys();
//            if (rs.next()){
//                rpta=rs.getInt(1);
//            }
//            rs.close();
//            
//            
//        } catch (Exception e) {
//            throw new Exception("Insertar"+e.getMessage(), e);
//        }
//        finally{
//            try {
//                stmt.close();
//                conn.close();
//            } catch (Exception e) {
//            }
//        }
//        return rpta;
//    }
//    
//    public static boolean calificar(clsUsuario entidad) throws Exception
//    {
//        boolean rpta = false;
//        Connection conn =null;
//        CallableStatement stmt = null;
//        try {
//             String sql="UPDATE incidente SET rapides = ?,conformidad = ? WHERE id_incidente = ? and id_usuario = ?";
//            conn = clsConexion.getConnection();
//            conn.setAutoCommit(false);
//            stmt = conn.prepareCall(sql);
//            stmt.setInt(1, entidad.getInt_rapides());
//            stmt.setInt(2, entidad.getInt_conformidad());
//            stmt.setInt(3, entidad.getInt_id_incidente());
//            stmt.setInt(4, entidad.getObjUsuario().getInt_id_usuario());
//            rpta = stmt.executeUpdate() == 1;          
//            conn.commit();
//            
//        } catch (Exception e) {
//             if (conn != null) {
//                    conn.rollback();
//                }
//            throw new Exception("Insertar"+e.getMessage(), e);
//        }
//        finally{
//            try {
//                stmt.close();
//                conn.close();
//            } catch (SQLException e) {
//            }
//        }
//        return rpta;
//    }
     
     
}
