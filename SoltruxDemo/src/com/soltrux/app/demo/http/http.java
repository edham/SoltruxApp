/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soltrux.app.demo.http;


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

    private static String url="http://192.168.1.38:8084/";
//    private static String url="http://www.serviciostecnologicosintegrales.com/soltrux/";
    private static HttpClient client;
    private static HttpResponse responseGet;
    private static HttpEntity resEntityGet;
    
    
    public static String getUsuario(String mail) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url+"registro");

        try {
            List<NameValuePair> Value = new ArrayList<NameValuePair>(1);
            Value.add(new BasicNameValuePair("mail",mail));
            httppost.setEntity(new UrlEncodedFormEntity(Value));
            responseGet = client.execute(httppost);
            resEntityGet = responseGet.getEntity();
            if (resEntityGet != null) {
                    return  EntityUtils.toString(resEntityGet).trim();
            }
            } catch (ClientProtocolException e) {
                return "{\"error\":3}";
            } catch (IOException e) {
                return "{\"error\":3} ";
            }
         return "{\"error\":3} ";
    }
    
    
    public static String getPregunta(int id,String pregunta) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url+"pregunta");

        try {
            List<NameValuePair> Value = new ArrayList<NameValuePair>(2);
            Value.add(new BasicNameValuePair("id",""+id));
            Value.add(new BasicNameValuePair("pregunta",pregunta));
            httppost.setEntity(new UrlEncodedFormEntity(Value));
            responseGet = client.execute(httppost);
            resEntityGet = responseGet.getEntity();
            if (resEntityGet != null) {
                    return  EntityUtils.toString(resEntityGet).trim();
            }
            } catch (ClientProtocolException e) {
                return "{\"error\":0}";
            } catch (IOException e) {
                return "{\"error\":0} ";
            }
         return "{\"error\":0} ";
    }
    
    
    public static String getUbicacion(int id,double longitud,double latitud) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url+"ubicacion");

        try {
            List<NameValuePair> Value = new ArrayList<NameValuePair>(3);
            Value.add(new BasicNameValuePair("id",""+id));
            Value.add(new BasicNameValuePair("longitud",""+longitud));
            Value.add(new BasicNameValuePair("latitud",""+latitud));
            httppost.setEntity(new UrlEncodedFormEntity(Value));
            responseGet = client.execute(httppost);
            resEntityGet = responseGet.getEntity();
            if (resEntityGet != null) {
                    return  EntityUtils.toString(resEntityGet).trim();
            }
            } catch (ClientProtocolException e) {
                return "{\"error\":0}";
            } catch (IOException e) {
                return "{\"error\":0} ";
            }
         return "{\"error\":0} ";
    }
    
   
     public static String getServicio(int id) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url+"servicio");

        try {
            List<NameValuePair> Value = new ArrayList<NameValuePair>(1);
            Value.add(new BasicNameValuePair("id",""+id));
            httppost.setEntity(new UrlEncodedFormEntity(Value));
            responseGet = client.execute(httppost);
            resEntityGet = responseGet.getEntity();
            if (resEntityGet != null) {
                    return  EntityUtils.toString(resEntityGet).trim();
            }
            } catch (ClientProtocolException e) {
                return "{\"error\":3}";
            } catch (IOException e) {
                return "{\"error\":3} ";
            }
         return "{\"error\":3} ";
    }
     
     
     
       public static String setTelefono(int id) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(url+"telefono");

        try {
            List<NameValuePair> Value = new ArrayList<NameValuePair>(1);
            Value.add(new BasicNameValuePair("id",""+id));
            httppost.setEntity(new UrlEncodedFormEntity(Value));
            responseGet = client.execute(httppost);
            resEntityGet = responseGet.getEntity();
            if (resEntityGet != null) {
                    return  EntityUtils.toString(resEntityGet).trim();
            }
            } catch (ClientProtocolException e) {
                return "{\"id\":false}";
            } catch (IOException e) {
                return "{\"id\":false} ";
            }
         return "{\"id\":false} ";
    }


}
