package com.soltrux.app.demo.servicio;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import static android.content.Context.NOTIFICATION_SERVICE;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;
import com.soltrux.app.demo.entidades.clsUsuarioMovil;
import com.soltrux.app.demo.http.http;
import com.soltrux.app.demo.sqlite.clsUsuarioMovilSQL;
import com.soltrux.app.demo.ui.MainActivity;
import com.soltrux.app.demo.ui.R;
import com.soltrux.app.demo.utilidades.utilidades;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Servicio extends Service {

	private Timer timer = new Timer();
	private static final long UPDATE_INTERVAL = 1000;// timer avanza cada hora
	
	private int count = 0;

        private clsUsuarioMovil entidad=null;
	

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
            super.onCreate();            
            _startService();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();               
		_shutdownService();           
	}
	
	private void _startService() {
            final Context contexto = this.getApplicationContext();
		timer.scheduleAtFixedRate(
			new TimerTask() {
				public void run() {
					
                                      if(count%20==0)
                                      {
                                          
                                        if(EstadoServicio())
                                        {
//                                            String dato=http.getPregunta();
//                                            if(!dato.equals("") && !dato.equals(null))
//                                            Notificacion(dato.trim());
//                                            else
//                                                borrarNotificacion();
                                        }

                                      }                                   
                                      
                                      
                                      count++;
				}
			},
			0,
			UPDATE_INTERVAL);         
                

	}
	
	private void _shutdownService() {            
		if (timer != null) timer.cancel();
                borrarNotificacion();
	}
	
        
 public boolean EstadoServicio()
 {
     boolean estado=false;
     if(entidad==null)
     {
         entidad=clsUsuarioMovilSQL.Buscar(this.getApplicationContext());
     }
     if(entidad!=null)
     {
        estado =utilidades.verificaConexion(this.getApplicationContext());
     }
                                         
     
     return estado;
 }
public void borrarNotificacion()
 {
     String ns = Context.NOTIFICATION_SERVICE;
				NotificationManager notManager = 
				(NotificationManager) getSystemService(ns);
                                notManager.cancelAll();
 }

public void Notificacion(String Motivo){
    	
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.ic_launcher, getString(R.string.str_pregunte_actual), System.currentTimeMillis());
        
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification);
        contentView.setImageViewResource(R.id.img_notification, R.drawable.ic_launcher);
        contentView.setTextViewText(R.id.txt_notification, Motivo);
        
        notification.contentView = contentView;
        
        Intent notificationIntent = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, notificationIntent, 0);
        notification.contentIntent = contentIntent;
        
        notificationManager.notify(0, notification);
    }

   
}
