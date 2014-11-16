/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soltrux.app.demo.http;

import com.soltrux.app.demo.entidades.clsUsuarioMovil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



public class http {

    private static String url="http://app.soltrux.org/servicio.php";
    private static HttpClient client;
    private static HttpResponse responseGet;
    private static HttpEntity resEntityGet;
    
    
    
   
       
      public static String getBuscar(String mail) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url);

    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(3);
        Value.add(new BasicNameValuePair("idServicio", "2"));
        Value.add(new BasicNameValuePair("email", mail));
        httppost.setEntity(new UrlEncodedFormEntity(Value));
        responseGet = client.execute(httppost);
        resEntityGet = responseGet.getEntity();
        if (resEntityGet != null) {
                return  EntityUtils.toString(resEntityGet).trim();
        }
        } catch (ClientProtocolException e) {
            return "-1";
        } catch (IOException e) {
            return "-1";
        }
     return "-1";
}

   
      public static String setGrabarUsuarioMovil(clsUsuarioMovil entidad) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url);

    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(5);
        Value.add(new BasicNameValuePair("idServicio", "1"));
        Value.add(new BasicNameValuePair("nombres",entidad.getStr_nombres())); 
        Value.add(new BasicNameValuePair("apellidos",entidad.getStr_apellidos()));  
        Value.add(new BasicNameValuePair("email",entidad.getStr_email()));   
        Value.add(new BasicNameValuePair("telefono",entidad.getStr_telefono()));
        httppost.setEntity(new UrlEncodedFormEntity(Value));
        responseGet = client.execute(httppost);
        resEntityGet = responseGet.getEntity();
        if (resEntityGet != null) {
                return  EntityUtils.toString(resEntityGet).trim();
        }
        } catch (ClientProtocolException e) {
            return "-1";
        } catch (IOException e) {
            return "-1";
        }
     return "-1";
} 

      
    public static String setActualizaUsuarioMovil(clsUsuarioMovil entidad) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url);

    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(6);
        Value.add(new BasicNameValuePair("IdServicio", "13"));
         Value.add(new BasicNameValuePair("idUsuario",""+entidad.getInt_id_usuario_movil())); 
        Value.add(new BasicNameValuePair("nombres",entidad.getStr_nombres())); 
        Value.add(new BasicNameValuePair("apellidos",entidad.getStr_apellidos()));  
        Value.add(new BasicNameValuePair("email",entidad.getStr_email()));   
        Value.add(new BasicNameValuePair("telefono",entidad.getStr_telefono()));
        httppost.setEntity(new UrlEncodedFormEntity(Value));
        responseGet = client.execute(httppost);
        resEntityGet = responseGet.getEntity();
        if (resEntityGet != null) {
                return  EntityUtils.toString(resEntityGet).trim();
        }
        } catch (ClientProtocolException e) {
            return "-1";
        } catch (IOException e) {
            return "-1";
        }
     return "-1";
} 
   public static String setPregunta(int idUsuario,String pregunta) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url);

    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(3);
        Value.add(new BasicNameValuePair("idServicio", "3"));
        Value.add(new BasicNameValuePair("pregunta", pregunta));
        Value.add(new BasicNameValuePair("idUsuario", ""+idUsuario));
        httppost.setEntity(new UrlEncodedFormEntity(Value));
        responseGet = client.execute(httppost);
        resEntityGet = responseGet.getEntity();
        if (resEntityGet != null) {
                return  EntityUtils.toString(resEntityGet).trim();
        }
        } catch (ClientProtocolException e) {
            return "-1";
        } catch (IOException e) {
            return "-1";
        }
     return "-1";
}

    public static String getPregunta() 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url);

    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(2);
        Value.add(new BasicNameValuePair("idServicio", "4"));
        httppost.setEntity(new UrlEncodedFormEntity(Value));
        responseGet = client.execute(httppost);
        resEntityGet = responseGet.getEntity();
        if (resEntityGet != null) {
                return  EntityUtils.toString(resEntityGet).trim();
        }
        } catch (ClientProtocolException e) {
            return "-1";
        } catch (IOException e) {
            return "-1";
        }
     return "-1";
}


}
