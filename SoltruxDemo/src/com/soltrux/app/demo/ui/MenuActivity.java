/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soltrux.app.demo.ui;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.soltrux.app.demo.entidades.clsUsuarioMovil;
import com.soltrux.app.demo.http.http;
import com.soltrux.app.demo.servicio.Servicio;
import com.soltrux.app.demo.sqlite.clsUsuarioMovilSQL;
import com.soltrux.app.demo.utilidades.utilidades;
 
public class MenuActivity extends Activity {
 
    private clsUsuarioMovil entidad=null;
    private ProgressDialog pd; 
    
    private TextView lblMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         
        setContentView(R.layout.menu);
        
        TextView lblMenu = (TextView)findViewById(R.id.lblMenu);
        TextView lblLink = (TextView)findViewById(R.id.lblLink);
        lblLink.setText(Html.fromHtml(getString(R.string.web)));
        Linkify.addLinks(lblLink, Linkify.ALL);
        lblLink.setMovementMethod(LinkMovementMethod.getInstance());
        entidad=clsUsuarioMovilSQL.Buscar(this);
        if(entidad!=null)
          lblMenu.setText(entidad.getStr_apellidos()+" "+entidad.getStr_nombres());
         
         Intent svc = new Intent(this, Servicio.class);
         startService(svc);
        
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
    public void btnSqlite(View v)
    {
     Intent i=new Intent(this,ListaPersonasActivity.class);
            startActivity(i); 
    }
    public void btnMapas(View v)
    {
     Intent i=new Intent(this,SeleccionMapaActivity.class);
            startActivity(i); 

    }
      public void btnCodigos(View v)
    {
     Intent i=new Intent(this,CodigoQRActivity.class);
            startActivity(i); 

    }
        public void btnPreguntas(View v)
    {

         if(utilidades.verificaConexion(this))
        {
        
        if(entidad!=null)
        {
            final Dialog dialog = new Dialog(this);

            dialog.setContentView(R.layout.realizar_pregunta);
            dialog.setTitle(getString(R.string.str_agregar_pregunta));
            final EditText txtComentario = (EditText) dialog.findViewById(R.id.txtComentario);                

            //
            Button btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
            btnAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            if(!txtComentario.getText().toString().equals("") && !txtComentario.getText().toString().equals(null))
                            {
                            dialog.dismiss();
                             EnviarPregunta(txtComentario.getText().toString());
                            }
                            else
                                Toast.makeText(MenuActivity.this,getString(R.string.str_ingresar_pregunta), Toast.LENGTH_LONG).show();
                    }
            });
            Button btnCancelar = (Button) dialog.findViewById(R.id.btnCancelar);
            btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            dialog.dismiss();                              
                    }
            });
            dialog.show();
            }
            else
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle(getString(R.string.str_desea_registrarse));
                alert.setMessage(getString(R.string.str_desea_pregunta));
                alert.setPositiveButton(getString(R.string.btn_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {  
                        Intent i=new Intent(MenuActivity.this,LoginActivity.class);
                         startActivity(i); 
                        
                        }});
                alert.setNegativeButton(getString(R.string.btn_cancelar), new DialogInterface.OnClickListener() {  
                       public void onClick(DialogInterface dialog, int whichButton) {    
                    }});
                       alert.show();

            }
       

                    }
                     else
                        Toast.makeText(this,getString(R.string.str_conexion_internet), Toast.LENGTH_LONG).show();

    }
        @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
   
    
    return false;

    } 
    
    public void EnviarPregunta(String pregunta)
    {
        final String pre=pregunta;
        
         pd = new ProgressDialog(this);
                    pd.setMessage(getString(R.string.str_espere));     
                    pd.show();        

                    new Thread() { 
                        public void run() { 
                            
                            http.setPregunta(entidad.getInt_id_usuario_movil(), pre);
                        
                              handler.sendEmptyMessage(0);                
                        } 
                    }.start();
    }
    
      final Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //update UI here depending on what message is received.
            super.handleMessage(msg);
            pd.dismiss();       
       
              Toast.makeText(MenuActivity.this,getString(R.string.str_registro_correcto), Toast.LENGTH_LONG).show();

        }
    };
      
      
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);   
            return true;
       
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         AlertDialog.Builder alert = new AlertDialog.Builder(this);
        switch (item.getItemId()) {
        case R.id.MnuOpc1:  
                alert.setTitle(getString(R.string.str_cerrar_sesion));
                alert.setPositiveButton(getString(R.string.btn_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {  
                            clsUsuarioMovilSQL.Borrar(MenuActivity.this);
                            Intent svc = new Intent(MenuActivity.this, Servicio.class);
                            stopService(svc); 
                           Intent i=new Intent(MenuActivity.this,LoginActivity.class);
                         startActivity(i); 
                        }});
                alert.setNegativeButton(getString(R.string.btn_cancelar), new DialogInterface.OnClickListener() {  
                       public void onClick(DialogInterface dialog, int whichButton) {    
                    }});
                       alert.show();
            return true;
   
        default:
            return super.onOptionsItemSelected(item);
        }
    }
  @Override
public boolean onPrepareOptionsMenu(Menu menu)
{

   if(entidad==null)
    menu.getItem(0).setVisible(false);
   else
      menu.getItem(0).setVisible(true); 
  return super.onPrepareOptionsMenu(menu);
}   
     
}
