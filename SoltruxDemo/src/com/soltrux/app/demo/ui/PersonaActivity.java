package com.soltrux.app.demo.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.soltrux.app.demo.entidades.clsPersona;
import com.soltrux.app.demo.sqlite.clsPersonaSQL;
import com.soltrux.app.demo.sqlite.clsUsuarioMovilSQL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PersonaActivity extends Activity
{
    private Spinner ComboSexo;
    private String[] listSexo;
    private TextView lblFecha;
    private TextView lblIdPersona;
    
    
    private EditText txtNombres;
    private EditText txtApellidos;
    private int IdPersona=0;
    private int mYear1;    
    private int mMonth1;    
    private int mDay1;   
    static final int DATE_DIALOG_ID = 1;    
    private Calendar calendar1 = Calendar.getInstance();
    private  clsPersona entidad;
    private clsPersona clsPersona;
    
    /** Called when the activity is first created. */
    
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persona);
        Bundle bundle=getIntent().getExtras();
        IdPersona=Integer.valueOf(bundle.getString("IdPersona"));
        
        ComboSexo = (Spinner)findViewById(R.id.ComboSexo);
        lblFecha = (TextView)findViewById(R.id.lblFecha);
        lblIdPersona = (TextView)findViewById(R.id.lblIdPersona);
        txtApellidos = (EditText)findViewById(R.id.txtApellidos);
        txtNombres = (EditText)findViewById(R.id.txtNombres);
        
        
        TextView lblLink = (TextView)findViewById(R.id.lblLink);
        lblLink.setText(Html.fromHtml(getString(R.string.web)));
        Linkify.addLinks(lblLink, Linkify.ALL);
        lblLink.setMovementMethod(LinkMovementMethod.getInstance());
        listSexo=getResources().getStringArray(R.array.array_sexo);
        ArrayAdapter<String> adapterSexo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listSexo);       
        adapterSexo.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ComboSexo.setAdapter(adapterSexo); 
         ComboSexo.setSelection(0);
         
         final Calendar c = Calendar.getInstance();        
    	mYear1 = c.get(Calendar.YEAR);        
    	mMonth1 = c.get(Calendar.MONTH);        
    	mDay1 = c.get(Calendar.DAY_OF_MONTH);  
        updateDisplay();  
        txtApellidos.setText("");                
        txtNombres.setText("");
        if(IdPersona>0)
        {
        clsPersona=clsPersonaSQL.Buscar(this, IdPersona);
        txtApellidos.setText(clsPersona.getStr_apellidos());                
        txtNombres.setText(clsPersona.getStr_nombres());
        if(!clsPersona.isBol_sexo())
        ComboSexo.setSelection(1);
        SimpleDateFormat  fecha=new SimpleDateFormat("dd/MM/yyyy");
        lblFecha.setText(fecha.format(clsPersona.getDat_fecha_nacimiento()));
        lblIdPersona.setText(getString(R.string.str_codigo_persona)+" "+clsPersona.getInt_id_persona());
        }
        
    }
    
      private void updateDisplay() {   

         calendar1.set(mYear1, mMonth1, mDay1);
    	 lblFecha.setText(new StringBuilder().append(mDay1).append("/") .append(mMonth1+ 1).append("/").append(mYear1));


    } 
          @Override
    protected Dialog onCreateDialog(int id) {    
    	switch (id) {   
    		case DATE_DIALOG_ID:        
    			return new DatePickerDialog(this,                    
    					mDateSetListener,                    
    					mYear1, mMonth1, mDay1);      
                
    			}    
    	return null;
    	} 
     
        private DatePickerDialog.OnDateSetListener mDateSetListener =            
    new DatePickerDialog.OnDateSetListener() {     
         
      
            
    public void onDateSet(DatePicker view, int year,                                       
    			int monthOfYear, int dayOfMonth) {     
                        
                        mYear1 = year;                    
                        mMonth1 = monthOfYear;                    
                        mDay1 = dayOfMonth;   
                     
                            
    		updateDisplay();                
    		}            
    	};

    
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
        if(!txtApellidos.getText().toString().equals("") )
        {
            if(!txtNombres.getText().toString().equals("") )
          {
            entidad = new clsPersona();
            entidad.setInt_id_persona(IdPersona);
            entidad.setStr_nombres(txtNombres.getText().toString());
            entidad.setStr_apellidos(txtApellidos.getText().toString());
            entidad.setDat_fecha_nacimiento(calendar1.getTime());
            if(ComboSexo.getSelectedItemPosition()==0)
                entidad.setBol_sexo(true);
            
                if(IdPersona==0)
                clsPersonaSQL.Agregar(this, entidad);
                else
                    clsPersonaSQL.Actualizar(this, entidad);
                
               Toast.makeText(this,getString(R.string.str_registro_correcto), Toast.LENGTH_LONG).show();
               Intent i=new Intent(this,ListaPersonasActivity.class);
                
                startActivity(i); 
          }
            else
                 Toast.makeText(this,getString(R.string.str_ingresar_nombre), Toast.LENGTH_LONG).show();
        } 
        else
            Toast.makeText(this,getString(R.string.str_ingresar_apellido), Toast.LENGTH_LONG).show();
        
    }
      public void btnCancelar(View v)
    {
         Intent i=new Intent(this,ListaPersonasActivity.class);
                startActivity(i); 
        
        
    }
      public void btnFecha (View v)
      {
          showDialog(DATE_DIALOG_ID); 
      }
      
        @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
   
    
    return false;

    } 

 
}
