package com.soltrux.app.demo.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.soltrux.app.demo.entidades.clsUsuarioMovil;
import com.soltrux.app.demo.http.http;
import com.soltrux.app.demo.sqlite.clsUsuarioMovilSQL;
import com.soltrux.app.demo.utilidades.utilidades;

public class LoginActivity extends Activity
{
    private EditText txtNombres;
    private EditText txtApellidos;
    private EditText txtCelular;
    private TextView lblMail;
    private ProgressDialog pd; 
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        txtApellidos = (EditText)findViewById(R.id.txtApellidos);
        txtNombres = (EditText)findViewById(R.id.txtNombres); 
        txtCelular = (EditText)findViewById(R.id.txtNumero);
        lblMail = (TextView)findViewById(R.id.lblMail);
        
        TextView lblLink = (TextView)findViewById(R.id.lblLink);
        lblLink.setText(Html.fromHtml(getString(R.string.web)));
        Linkify.addLinks(lblLink, Linkify.ALL);
        lblLink.setMovementMethod(LinkMovementMethod.getInstance());
        lblMail.setText(utilidades.getMail(this));
        
        if(clsUsuarioMovilSQL.Buscar(this)!=null)
        {
            Intent i=new Intent(LoginActivity.this,MenuActivity.class);
                                startActivity(i); 
        }
        
    }
    
    public void btnShare(View v)
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.str_compartir_sms));
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.str_compartir_via)));
        startActivity(Intent.createChooser(sharingIntent,getString(R.string.str_compartir_con)));
        
    }
     public void btnRegistrar(View v)
    {
        if(!txtNombres.getText().toString().equals("") && !txtNombres.getText().toString().equals(null))
        {
            if(!txtApellidos.getText().equals("") && !txtNombres.getText().toString().equals(null))
            {
                if(txtCelular.getText().length()==9 &&! txtNombres.getText().toString().equals(null))
                {
                    if(utilidades.verificaConexion(this))
                    {
                    pd = new ProgressDialog(this);
                    pd.setMessage(getString(R.string.str_espere));     
                    pd.show();        

                    new Thread() { 
                        public void run() { 
                            
                             String dato=http.getBuscar(utilidades.getMail(LoginActivity.this));
                              if(!dato.equals("") && !dato.equals(null))
                              {
                                    clsUsuarioMovilSQL.Agregar(LoginActivity.this, new clsUsuarioMovil(dato));

                              }else
                              {
                            
                                clsUsuarioMovil entidad = new clsUsuarioMovil();
                                entidad.setStr_email(lblMail.getText().toString());
                                entidad.setStr_nombres(txtNombres.getText().toString());
                                entidad.setStr_apellidos(txtApellidos.getText().toString());
                                entidad.setStr_telefono(txtCelular.getText().toString());
                                String data=http.setGrabarUsuarioMovil(entidad);
                                int id=Integer.parseInt(data.trim());
                                if(id>0)
                                {
                                    entidad.setInt_id_usuario_movil(id);
                                    clsUsuarioMovilSQL.Agregar(LoginActivity.this, entidad);                              
                                }
                            
                              }
                            
                        
                              handler.sendEmptyMessage(0);                
                        } 
                    }.start(); 
                    
                    }
                     else
                        Toast.makeText(this,getString(R.string.str_conexion_internet), Toast.LENGTH_LONG).show();
                    
      
            
                }
                else
                    Toast.makeText(this,getString(R.string.str_registro_numero_valido), Toast.LENGTH_LONG).show();

            }
             else
                Toast.makeText(this,getString(R.string.str_ingresar_apellido), Toast.LENGTH_LONG).show();
        }
        else
             Toast.makeText(this,getString(R.string.str_ingresar_nombre), Toast.LENGTH_LONG).show();


    }
     
            final Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //update UI here depending on what message is received.
            super.handleMessage(msg);
            pd.dismiss();       
       
              Toast.makeText(LoginActivity.this,getString(R.string.str_registro_correcto), Toast.LENGTH_LONG).show();
                                         Intent i=new Intent(LoginActivity.this,MenuActivity.class);
                                        startActivity(i); 
        }
    };
        
      
      public void btnOmitir(View v)
    {
        Intent i=new Intent(LoginActivity.this,MenuActivity.class);
                startActivity(i); 
    }
    
 
}
