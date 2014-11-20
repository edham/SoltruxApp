<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="COM.clsGestor"%>
<%@page import="Entidades.clsUsuario"%>
<%@page import="Entidades.clsUsuario"%>
<div id="tablaUsuarios">
    
<%SimpleDateFormat hora=new SimpleDateFormat("HH:mm a");%>
<%SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");%>
    <table id="listaUsuario" >
            <thead>
                    <tr>
                            <th>id</th>
                            <th>Mail</th>
                            <th>Actualizacion</th>
                            <th>Registro</th>
                    </tr>
            </thead>
            <tbody>
                   <% List<clsUsuario> listaUsuario=clsGestor.listaUsuario();
                   if(listaUsuario!=null)
                   for(clsUsuario entidad : listaUsuario)
                   {%>
                    <tr>
                            <td><%=entidad.getId_usuario()%></td>
                            <td><%=entidad.getEmail()%></td>
                            <td><%=hora.format(entidad.getFecha_actualizacion())%></td>
                            <td><%=fecha.format(entidad.getFecha_registro())%></td>
                    </tr>
                   <%}%>
            </tbody>

    </table>
<script type="text/javascript">
$(function () { 

   $('#listaUsuario').dataTable( {
                        "paging":   false,
                        "ordering": false,
                        "info":     false
                    } );
 
});
</script> 
    
</div>