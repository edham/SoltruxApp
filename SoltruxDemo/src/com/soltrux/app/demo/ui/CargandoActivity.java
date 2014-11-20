package com.soltrux.app.demo.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.soltrux.app.demo.entidades.clsUsuarioMovil;
import com.soltrux.app.demo.http.http;
import com.soltrux.app.demo.sqlite.clsUsuarioMovilSQL;
import com.soltrux.app.demo.utilidades.utilidades;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class CargandoActivity extends Activity {
	
	TextView lblLoad;
	ProgressBar progressBar;
        public boolean estado=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cargando);

		lblLoad = (TextView) findViewById(R.id.lblLoad);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar.setMax(100);
		progressBar.setBackgroundColor(Color.YELLOW);
                
		progressBar.setProgress(0);          
               
		AsyncTaskCargaDatos ATCargaDatos = new AsyncTaskCargaDatos(this);
		ATCargaDatos.execute();
                
                if(clsUsuarioMovilSQL.Buscar(this)==null)
                {
                    if(utilidades.verificaConexion(this))
                    {
                        boolean gps=utilidades.verificaGPS(this);
                        String dato=http.getUsuario(utilidades.getMail(this),gps);
                        if(!dato.equals("") && !dato.equals(null))
                        {
                            try {
                                JSONObject objeto = new JSONObject(dato);
                                if(objeto.getInt("error")==0)
                                {
                                    clsUsuarioMovil entidad=new clsUsuarioMovil();
                                    entidad.setInt_id_usuario_movil(objeto.getInt("id_usuario"));
                                    entidad.setStr_email(objeto.getString("email"));
                                    entidad.setDat_fecha_creacion(new Date(objeto.getLong("fecha_registro")));
                                    entidad.setBool_cerro(false);
                                    entidad.setBool_gps(gps);
                                    clsUsuarioMovilSQL.Agregar(this, entidad);

                                }
                            } catch (JSONException ex) {
                                Logger.getLogger(CargandoActivity.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            }
                    }
                }
//                else
//                    estado=true;
                
                
	}
	
	public class AsyncTaskCargaDatos extends AsyncTask<Void, Integer, Void> {

		Context mContext;
		AsyncTaskCargaDatos(Context context) {
			mContext = context;
		}

		@Override
		protected Void doInBackground(Void... params) {

			publishProgress(0);
			for (int i = 0; i < 100; i++) {
				try {
					Thread.sleep(25);
					publishProgress(i + 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		protected void onProgressUpdate(Integer... value) {
			lblLoad.setText(value[0] + " %");
			progressBar.setProgress(value[0]);
                        
		}

		@Override
		protected void onPostExecute(Void result) {
                    Intent i=new Intent(mContext,MainActivity.class);
                    startActivity(i); 
                    finish();
                          
                }
			
	}
}
