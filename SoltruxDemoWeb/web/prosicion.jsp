<%@page import="Entidades.clsUbicacionUsuario"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="COM.clsGestor"%>
<%@page import="Entidades.clsUsuario"%>
<div id="tablaPocicion">
    
<%SimpleDateFormat hora=new SimpleDateFormat("HH:mm a");%>
<%SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");%>
    <table id="listaPosicion" >
            <thead>
                    <tr>
                            <th>Longitud</th>
                            <th>Latitud</th>
                            <th>Mail</th>
                    </tr>
            </thead>
            <tbody>
                <% List<clsUbicacionUsuario> listaPregunta=clsGestor.listaUbicacionUsuario();
                if(listaPregunta!=null)
                for(clsUbicacionUsuario entidad : listaPregunta)
                {%>
                    <tr>
                            <td><%=entidad.getLongitud()%></td>
                            <td><%=entidad.getLatitud()%></td>
                            <td><%=entidad.getObjUsuario().getEmail()%></td>
                    </tr>
                <%}%>
            </tbody>

    </table>
<script type="text/javascript">
$(function () { 

   $('#listaPosicion').dataTable( {
                        "paging":   false,
                        "ordering": false,
                        "info":     false
                    } );
 
});
</script> 
    
</div>