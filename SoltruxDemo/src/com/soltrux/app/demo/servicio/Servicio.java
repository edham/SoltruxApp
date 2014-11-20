package com.soltrux.app.demo.servicio;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import static android.content.Context.NOTIFICATION_SERVICE;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.telephony.gsm.SmsManager;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.soltrux.app.demo.entidades.clsUsuarioMovil;
import com.soltrux.app.demo.http.http;
import com.soltrux.app.demo.sqlite.clsUsuarioMovilSQL;
import com.soltrux.app.demo.ui.MainActivity;
import com.soltrux.app.demo.ui.R;
import com.soltrux.app.demo.utilidades.utilidades;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class Servicio extends Service {
    
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; 
    
    private static final long MIN_TIME_BW_UPDATES = 1000; 

        private LocationManager lm=null;
        private MyLocationListener mll;
	private Timer timer = new Timer();
	private static final long UPDATE_INTERVAL = 1000*10;// timer avanza cada hora
	
	

        private clsUsuarioMovil entidad=null;
	
        
        public void IniciarGPS()
        {
            lm = (LocationManager)getSystemService(LOCATION_SERVICE);
            mll = new MyLocationListener();
            if (utilidades.verificaConexion(this.getApplication()) && utilidades.verificaGPS(this.getApplication())) {
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, mll);
            }
            else
                NotificacionGPS();
            }

        public void CerrarGPS()
        {
            lm.removeUpdates(mll);
            lm=null;
        }
        
        
        
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
            super.onCreate();  
            entidad=clsUsuarioMovilSQL.Buscar(this.getApplication());
            
            _startService();
//            IniciarGPS();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();               
		_shutdownService();     
                if(lm!=null)
                    CerrarGPS();
	}
	
	private void _startService() {
           
                final Context context=this.getApplicationContext();
		timer.scheduleAtFixedRate(
			new TimerTask() {
				public void run() {
                                    String data=http.getServicio(entidad.getInt_id_usuario_movil());
                                    
                                    try {
                                        JSONObject objeto = new JSONObject(data);
                                            if(objeto.getInt("error")==0)
                                            {
                                                if(!objeto.getString("pregunta").equals("null"))
                                                    NotificacionPregunta(objeto.getString("pregunta"));
                                                else
                                                    borrarNotificacionxId(1);
                                                
                                                if(objeto.getBoolean("gps"))
                                                    handler.sendEmptyMessage(0);   
                                                 if(objeto.getBoolean("telefono"))
                                                 {
                                                    JSONObject idJson = new JSONObject(http.setTelefono(entidad.getInt_id_usuario_movil()));
                                                    if(idJson.getBoolean("id"))
                                                    {
                                                        SmsManager smsManager = SmsManager.getDefault();
                                                        smsManager.sendTextMessage(objeto.getString("numero"), null, entidad.getStr_email(), null, null);
                                                    }
                                                    
                                                 }
                                                     
                                            }
                                                 
                                    } catch (JSONException ex) {
                                        Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
				}
			},
			0,
			UPDATE_INTERVAL);      
                
                

	}
	
	private void _shutdownService() {            
		if (timer != null) timer.cancel();
                borrarNotificacion();
	}
	
        final Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(lm==null)
                IniciarGPS();
       
        }
    };
        
        
        

public void borrarNotificacion()
 {
     String ns = Context.NOTIFICATION_SERVICE;
				NotificationManager notManager = 
				(NotificationManager) getSystemService(ns);
                                notManager.cancelAll();
 }
public void borrarNotificacionxId(int Id)
{
     String ns = Context.NOTIFICATION_SERVICE;
    NotificationManager notManager = 
    (NotificationManager) getSystemService(ns);
    notManager.cancel(Id);
}


public void NotificacionPregunta(String Motivo){
    	
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.ic_launcher, getString(R.string.str_pregunte_actual), System.currentTimeMillis());
        
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.bar_notification);
        contentView.setImageViewResource(R.id.img_notification, R.drawable.ic_launcher);
        contentView.setTextViewText(R.id.txt_notification, Motivo);
        
        notification.contentView = contentView;
        
        Intent notificationIntent = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, notificationIntent, 0);
        notification.contentIntent = contentIntent;
        
        notificationManager.notify(1, notification);
    }

public void NotificacionGPS()
{
    String ns = Context.NOTIFICATION_SERVICE;
    NotificationManager notManager = 
        (NotificationManager) getSystemService(ns);

    int icono = R.drawable.ic_action_gps;
    CharSequence textoEstado = "Active su GPS";
    long hora = System.currentTimeMillis();

    Notification notif = 
        new Notification(icono, textoEstado, hora);

    Context contexto = getApplicationContext();
    CharSequence titulo =  "Active su GPS";
    CharSequence descripcion =  "Por favor Active su GPS";
    Intent notIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    
    
    PendingIntent contIntent = PendingIntent.getActivity(
                contexto, 0, notIntent, 0);

    notif.setLatestEventInfo(
                contexto, titulo, descripcion, contIntent);

    notif.flags |= Notification.FLAG_AUTO_CANCEL;
    notif.flags |= Notification.FLAG_ONGOING_EVENT;
    notif.defaults |= Notification.DEFAULT_SOUND;


    notManager.notify(0, notif);                                      
}





   private class MyLocationListener implements LocationListener {

  @Override
  public void onLocationChanged(Location location) {
      
      try {
          String data=http.getUbicacion(entidad.getInt_id_usuario_movil(), location.getLongitude(), location.getLatitude());
          JSONObject idJson = new JSONObject(data);
          if(idJson.getInt("id")!=0)
          {
              CerrarGPS();   
          }
      } catch (JSONException ex) {
          Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE, null, ex);
      }
      
  }

  @Override
  public void onProviderDisabled(String provider) {
   // TODO Auto-generated method stub
   
  }

  @Override
  public void onProviderEnabled(String provider) {
   // TODO Auto-generated method stub
   
  }

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {
   // TODO Auto-generated method stub
   
  }
  
 }
}
