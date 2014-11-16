/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soltrux.app.demo.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.soltrux.app.demo.entidades.clsPersona;
import com.soltrux.app.demo.sqlite.clsPersonaSQL;
import com.soltrux.app.demo.utilidades.utilidades;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Babsy Gamboa
 */
public class ListaPersonasActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
//    
     private  List<clsPersona> itens=null;
     private  List<clsPersona> itensTemp=null;
      private EditText txtFiltro;
     private ListView listPersonas;
     private AdaptadorTitulares adaptador;
     private String[] listSexo;
     
    
     
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.lista_personas);
        listSexo=getResources().getStringArray(R.array.array_sexo);
        listPersonas = (ListView)findViewById(R.id.listPersonas); 
        itens= clsPersonaSQL.Listar(this);
        txtFiltro = (EditText) findViewById(R.id.txtFiltro);
        txtFiltro.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,int count) {                                
                            Buscar(s.toString().trim()); 
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
                
                Buscar("");    
    }
   public void Buscar(String filtro)
  {    
       
        itensTemp=new ArrayList<clsPersona>();
        for(clsPersona entidad:itens)
         if(entidad.getStr_nombres().toLowerCase().indexOf(filtro.toLowerCase()) != -1 || 
               entidad.getStr_apellidos().toLowerCase().indexOf(filtro.toLowerCase()) != -1)
         itensTemp.add(entidad);

       
       adaptador = new AdaptadorTitulares(this);
       listPersonas.setAdapter(adaptador);
      if(itensTemp==null && itensTemp.size()==0)
         Toast.makeText(this,"No se encontraron Itinerarios", Toast.LENGTH_SHORT).show(); 
  }
     
      class AdaptadorTitulares extends ArrayAdapter {
    	
    	Activity context;
    	
    	AdaptadorTitulares(Activity context) {
    		super(context, R.layout.lista_personas_list, itensTemp);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
            final int posicion = position;
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.lista_personas_list, null);

            TextView lblNombresPersona = (TextView)item.findViewById(R.id.lblNombresPersona);
            lblNombresPersona.setText(itensTemp.get(position).getStr_nombres());

            TextView lblApellidosPersona = (TextView)item.findViewById(R.id.lblApellidosPersona);
            lblApellidosPersona.setText(itensTemp.get(position).getStr_apellidos());
            
            TextView lblSexoPersona = (TextView)item.findViewById(R.id.lblSexoPersona);
            if(itensTemp.get(position).isBol_sexo())
            lblSexoPersona.setText(listSexo[0]);
            else
                lblSexoPersona.setText(listSexo[1]);

            TextView lblIdPersona = (TextView)item.findViewById(R.id.lblIdPersona);
            lblIdPersona.setText(getString(R.string.str_codigo_persona)+" "+itensTemp.get(position).getInt_id_persona());

            TextView lblEdadPersona = (TextView)item.findViewById(R.id.lblEdadPersona);
            lblEdadPersona.setText(""+utilidades.getEdad(itensTemp.get(position).getDat_fecha_nacimiento()));


            Button btnInformacion = (Button)item.findViewById(R.id.btnAgregar);

            btnInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
               AlertDialog.Builder alert = new AlertDialog.Builder(ListaPersonasActivity.this);
                alert.setTitle(getString(R.string.str_desea_actualizar));
                alert.setPositiveButton(getString(R.string.btn_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {  
                       Intent i=new Intent(ListaPersonasActivity.this,PersonaActivity.class);
                            i.putExtra("IdPersona",""+itensTemp.get(posicion).getInt_id_persona());
                            startActivity(i); 
                        }});
                alert.setNegativeButton(getString(R.string.btn_cancelar), new DialogInterface.OnClickListener() {  
                       public void onClick(DialogInterface dialog, int whichButton) {    
                    }});
                       alert.show();

              
            }
            });

            Button btnSiguiente = (Button)item.findViewById(R.id.btnEliminar);
            btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                AlertDialog.Builder alert = new AlertDialog.Builder(ListaPersonasActivity.this);
                alert.setTitle(getString(R.string.str_desea_eliminar));
                alert.setPositiveButton(getString(R.string.btn_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {  
                        clsPersonaSQL.BorrarId(context, itensTemp.get(posicion).getInt_id_persona());
                        itens= clsPersonaSQL.Listar(ListaPersonasActivity.this);
                                        Buscar("");    

                        }});
                alert.setNegativeButton(getString(R.string.btn_cancelar), new DialogInterface.OnClickListener() {  
                       public void onClick(DialogInterface dialog, int whichButton) {    
                    }});
                       alert.show();

            }
            });

            return(item);
		}
    }
        
         

             
     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_persona, menu);   
            return true;
       
    }
       @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
        case R.id.MnuOpc1:          
                    Intent i=new Intent(this,PersonaActivity.class);
                     i.putExtra("IdPersona","0");
                    startActivity(i);
                

                return true;
           default:
            return super.onOptionsItemSelected(item);
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
    
}
