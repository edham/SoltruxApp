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

public class CargandoActivity extends Activity {
	
	TextView lblLoad;
	ProgressBar progressBar;
        public boolean estado=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cargando);

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
                        String dato=http.getBuscar(utilidades.getMail(this));
                        if(!dato.equals("") && !dato.equals(null))
                        {
                            clsUsuarioMovilSQL.Agregar(this, new clsUsuarioMovil(dato));
                           estado=true;
                        }
                    }
                }
                else
                    estado=true;
                
                
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
                        if(estado)
                        {
                            Intent i=new Intent(mContext,MenuActivity.class);
                            startActivity(i); 
                            finish();
                        }
                        else
                        {
                            Intent i=new Intent(mContext,LoginActivity.class);
                            startActivity(i); 
                            finish();
                        }
                          
                }
			
	}
}
