package com.soltrux.app.demo.utilidades;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import static android.content.Context.LOCATION_SERVICE;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import com.soltrux.app.demo.entidades.clsCuenta;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class utilidades {

 
  public static String getMail(Context context) {
      String possibleEmail=null;
         
           try{
                   Account[] accounts = AccountManager.get(context).getAccountsByType("com.google");
                    
                   for (Account account : accounts) {
                      
                     possibleEmail =account.name;
                      
                   }
              }
              catch(Exception e)
              {
                   Log.i("Exception", "Exception:"+e) ; 
              }
      return possibleEmail;
  }
  
   public static List<clsCuenta> listCuentas(Context context) {
      
       List<clsCuenta> list=new ArrayList<clsCuenta>();
         
           try{
               Account[] accounts = AccountManager.get(context).getAccounts();
                   for (Account account : accounts) 
                      list.add(new clsCuenta(account.type,account.name));
              }
              catch(Exception e)
              {
                   Log.i("Exception", "Exception:"+e) ; 
              }
            
      return list;
  }
  
  public static int getEdad(Date pNacio){

      Calendar fecha = new GregorianCalendar();
      fecha.setTime(pNacio);
      int diaNacio = fecha.get(Calendar.DATE);
       int mesNacio = fecha.get(Calendar.MONTH)+1;
      int anoNacio = fecha.get(Calendar.YEAR);
      fecha.setTime(new Date());

      int anoFecha = fecha.get(Calendar.YEAR);
      int mesFecha = fecha.get(Calendar.MONTH)+1;
      int diaFecha = fecha.get(Calendar.DATE);

      int difAno = anoFecha - anoNacio;
      int difMes = mesFecha - mesNacio;
      int difDia = diaFecha - diaNacio;

      if(difMes <0){

         difAno-=1;

      }else if(difMes == 0){

           if(difDia < 0){
             difAno-=1;
      }  

      }
      return difAno;
      }


public static boolean verificaConexion(Context context) {
    boolean bConectado = false;
    ConnectivityManager connec = (ConnectivityManager) context
            .getSystemService(Context.CONNECTIVITY_SERVICE);
    // No sólo wifi, también GPRS
    NetworkInfo[] redes = connec.getAllNetworkInfo();
    // este bucle debería no ser tan ñapa
   for (int i = 0; i < redes.length; i++) {
        // ¿Tenemos conexión? ponemos a true
        if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
            bConectado = true;
        }
    }
    return bConectado;
}


public static boolean verificaGPS(Context context) {
    LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);    
    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) 
         return true;
    return false;
}





     public static void setLocale(Context context,String lang) { 
        Locale myLocale = new Locale(lang); 
        Resources res = context.getResources(); 
        DisplayMetrics dm = res.getDisplayMetrics(); 
        Configuration conf = res.getConfiguration(); 
        conf.locale = myLocale; 
        res.updateConfiguration(conf, dm); 
       
        } 

}