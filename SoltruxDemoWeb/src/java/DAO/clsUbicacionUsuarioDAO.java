/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;


import Entidades.clsUbicacionUsuario;
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
public class clsUbicacionUsuarioDAO {
   
    
    
//    public static List<clsUbicacionUsuario> listarXUsuario(int IdUsuario) throws Exception 
//    {
//        List<clsUbicacionUsuario> lista = null;
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
//                    lista = new ArrayList<clsUbicacionUsuario>();
//                
//                clsTipoIncidente objTipoIncidente = new clsTipoIncidente();
//                objTipoIncidente.setInt_id_tipo_incidente(dr.getInt(8));
//                objTipoIncidente.setStr_nombre(dr.getString(9));
//                
//                clsUbicacionUsuario objclsUbicacionUsuario = new clsUbicacionUsuario();
//                objclsUbicacionUsuario.setInt_id_incidente(dr.getInt(1));
//                objclsUbicacionUsuario.setDou_latitud(dr.getDouble(2));
//                objclsUbicacionUsuario.setDou_longitud(dr.getDouble(3));
//                objclsUbicacionUsuario.setStr_detalle(dr.getString(4));
//                objclsUbicacionUsuario.setDat_fecha_registro(dr.getTimestamp(5));      
//                objclsUbicacionUsuario.setInt_estado(dr.getInt(6)); 
//                objclsUbicacionUsuario.setByte_foto(dr.getBytes(7));
//                objclsUbicacionUsuario.setObjTipoIncidente(objTipoIncidente);
//                objclsUbicacionUsuario.setInt_rapides(dr.getInt(10));
//                objclsUbicacionUsuario.setInt_conformidad(dr.getInt(10));
//                
//                lista.add(objclsUbicacionUsuario);                
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
    
  
     public  static int insertar(clsUbicacionUsuario entidad) throws Exception
    {
        int rpta = 0;
        Connection conn =null;
        CallableStatement stmt = null;
        try {
            
            
           String sql="insert into ubicacion_usuario (longitud,latitud,id_usuario) values"
                   + "(?,?,?)";
           
            conn = clsConexion.getConnection();
            stmt = conn.prepareCall(sql);
            stmt.setDouble(1, entidad.getLongitud());
            stmt.setDouble(2, entidad.getLatitud());
            stmt.setInt(3, entidad.getObjUsuario().getId_usuario());

           stmt.executeUpdate();
           
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                rpta=rs.getInt(1);
            }
            rs.close();
            
            
        } catch (Exception e) {
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
//    
//    public static boolean calificar(clsUbicacionUsuario entidad) throws Exception
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
