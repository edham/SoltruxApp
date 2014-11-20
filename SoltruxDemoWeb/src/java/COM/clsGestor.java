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

/**
 *
 * @author EdHam
 */
public class clsGestor {
    //<editor-fold defaultstate="collapsed" desc="clsUsuario">
    
    public static clsUsuario getUsuario(clsUsuario entidad) throws Exception
    {
        return clsUsuarioDAO.get(entidad);
    }
   
// </editor-fold>
    
     //<editor-fold defaultstate="collapsed" desc="clsPreguntaUsuario">
    
    public static int insertarPreguntaUsuario(clsPreguntaUsuario entidad) throws Exception
    {
        return clsPreguntaUsuarioDAO.insertar(entidad);
    }
   
// </editor-fold>
    
    
     //<editor-fold defaultstate="collapsed" desc="clsUbicacionUsuario">
    
    public static int insertarUbicacionUsuario(clsUbicacionUsuario entidad) throws Exception
    {
        return clsUbicacionUsuarioDAO.insertar(entidad);
    }
   
// </editor-fold>

}
