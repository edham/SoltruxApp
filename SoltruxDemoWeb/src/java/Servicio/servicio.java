/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicio;

import COM.clsGestor;
import Entidades.clsPreguntaUsuario;
import Entidades.clsUbicacionUsuario;
import Entidades.clsUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author EdHam
 */
@WebServlet(name = "servicio", urlPatterns = {"/servicio"})
public class servicio extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             JSONObject obj=new JSONObject();
           if(request.getParameter("id") != null && request.getParameter("id") != "")
            {
                
                clsUsuario objUsuario=clsGestor.getIdUsuario(Integer.parseInt(request.getParameter("id")));                
                if(objUsuario!=null)
                { 
                    obj.put("gps",objUsuario.isGps());  
                    obj.put("error",0); 
                    obj.put("telefono",objUsuario.isTelefono());  
                    if(!objUsuario.getNumero().equals(""))
                    obj.put("numero",objUsuario.getNumero()); 
                    else
                         obj.put("numero",null); 
                    
                    clsPreguntaUsuario objPreguntaUsuario=clsGestor.getPreguntaUsuario();
                     if(objPreguntaUsuario!=null)
                         obj.put("pregunta",objPreguntaUsuario.getPregunta()); 
                     else
                         obj.put("pregunta",null); 
                         
                }
                else
                     obj.put("error",1);
                   
            }
           else
               obj.put("error",2);
              
           out.println(obj);
        } catch (Exception ex) {
            Logger.getLogger(servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
