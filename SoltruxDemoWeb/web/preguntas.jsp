<%@page import="Entidades.clsPreguntaUsuario"%>
<%@page import="Entidades.clsUbicacionUsuario"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="COM.clsGestor"%>
<%@page import="java.util.List"%>
<div id="tablaPreguntas">
    
<%SimpleDateFormat hora=new SimpleDateFormat("HH:mm a");%>
<%SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");%>
    <table id="listaPreguntas" >
            <thead>
                    <tr>
                            <th>Pregunta</th>
                            <th>Mail</th>
                            <th>Hora</th>
                             <th>Fecha</th>
                             <th>Estado</th>
                    </tr>
            </thead>
            <tbody>
                <% List<clsPreguntaUsuario> listaPregunta=clsGestor.listaPreguntaUsuario();
                if(listaPregunta!=null)
                for(clsPreguntaUsuario entidad : listaPregunta)
                {%>
                    <tr>
                            <td><%=entidad.getPregunta()%></td>
                            <td><%=entidad.getObjUsuario().getEmail()%></td>
                            
                            <td><%=hora.format(entidad.getFecha_creacion())%></td>
                            <td><%=fecha.format(entidad.getFecha_creacion())%></td>
                            
                            <td><%if(entidad.getEstado()==0) out.print("Enviada");
                                    else  out.print("Activada");%></td>
                    </tr>
                <%}%>
            </tbody>

    </table>
<script type="text/javascript">
$(function () { 

   $('#listaPreguntas').dataTable( {
                        "paging":   false,
                        "ordering": false,
                        "info":     false
                    } );
 
});
</script> 
    
</div>