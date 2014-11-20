/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package COM;

import DAO.clsPreguntaUsuarioDAO;
import DAO.clsUbicacionUsuarioDAO;
import DAO.clsUsuarioDAO;
import Entidades.clsPreguntaUsuario;
import Entidades.clsUbicacionUsuario;
import Entidades.clsUsuario;
import java.util.List;

/**
 *
 * @author EdHam
 */
public class clsGestor {
    //<editor-fold defaultstate="collapsed" desc="clsUsuario">
    
    public static clsUsuario getUsuario(String Mail) throws Exception
    {
        return clsUsuarioDAO.get(Mail);
    }
    public static clsUsuario getIdUsuario(int id) throws Exception 
    {
        return clsUsuarioDAO.getId(id);
    }
    public static boolean setTelefonoUsuario(int Id) throws Exception
    {
        return clsUsuarioDAO.setTelefono(Id);
    }
      public static List<clsUsuario> listaUsuario() throws Exception 
    { 
        return clsUsuarioDAO.lista();
    }
// </editor-fold>
    
     //<editor-fold defaultstate="collapsed" desc="clsPreguntaUsuario">
    
    public static int insertarPreguntaUsuario(clsPreguntaUsuario entidad) throws Exception
    {
        return clsPreguntaUsuarioDAO.insertar(entidad);
    }
     public static clsPreguntaUsuario getPreguntaUsuario() throws Exception
    {
        return clsPreguntaUsuarioDAO.getActiva();
    }
     public static List<clsPreguntaUsuario> listaPreguntaUsuario() throws Exception
    {
        return clsPreguntaUsuarioDAO.lista();
    }
   
// </editor-fold>
    
    
     //<editor-fold defaultstate="collapsed" desc="clsUbicacionUsuario">
    
    public static int insertarUbicacionUsuario(clsUbicacionUsuario entidad) throws Exception
    {
        return clsUbicacionUsuarioDAO.insertar(entidad);
    }
    public static List<clsUbicacionUsuario> listaUbicacionUsuario() throws Exception 
     {
        return clsUbicacionUsuarioDAO.lista();
    }
// </editor-fold>

}
