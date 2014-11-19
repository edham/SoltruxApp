package com.soltrux.app.demo.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.soltrux.app.demo.entidades.clsPersona;
import com.soltrux.app.demo.sqlite.clsPersonaSQL;
import com.soltrux.app.demo.ui.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FragmentPersonas extends Fragment  {

    
    private Spinner ComboSexo;
    private String[] listSexo;
    private TextView lblFecha;
    
    
    private EditText txtNombres;
    private EditText txtApellidos;
    private int IdPersona=0;
    private int mYear;    
    private int mMonth;    
    private int mDay;   
    private Calendar calendar = Calendar.getInstance();
    private  clsPersona entidad;
    private clsPersona clsPersona;
    
    private Button btnRegistrar;
    private Button btnCancelar;
    private Button btnFecha;
    
        @Override
        public void onResume() {
            super.onResume();
            // Set title
            if(IdPersona==0)
                getActivity().getActionBar().setTitle(R.string.str_registro_usario);
            else
            {
                clsPersona=clsPersonaSQL.Buscar(this.getActivity(), IdPersona);
                txtApellidos.setText(clsPersona.getStr_apellidos());                
                txtNombres.setText(clsPersona.getStr_nombres());
                if(!clsPersona.isBol_sexo())
                ComboSexo.setSelection(1);
                SimpleDateFormat  fecha=new SimpleDateFormat("dd/MM/yyyy");
                lblFecha.setText(fecha.format(clsPersona.getDat_fecha_nacimiento()));
                getActivity().getActionBar().setTitle(getString(R.string.str_codigo_persona)+" "+clsPersona.getInt_id_persona());
            }
        }

          @Override
    public void onStart() 
    {
        super.onStart();
       
        
        
        btnCancelar = (Button) getView().findViewById(R.id.btnCancelar);
            btnCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnCancelar();
                }
            });            
        btnFecha = (Button) getView().findViewById(R.id.btnFecha);
            btnFecha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnFecha();
                }
            });
        btnRegistrar = (Button) getView().findViewById(R.id.btnRegistrar);
            btnRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnRegistrar();
                }
            });
        ComboSexo = (Spinner) getView().findViewById(R.id.ComboSexo);
        lblFecha = (TextView) getView().findViewById(R.id.lblFecha);
        txtApellidos = (EditText) getView().findViewById(R.id.txtApellidos);
        txtNombres = (EditText) getView().findViewById(R.id.txtNombres);
        
        
        listSexo=getResources().getStringArray(R.array.array_sexo);
        ArrayAdapter<String> adapterSexo = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,listSexo);       
        adapterSexo.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ComboSexo.setAdapter(adapterSexo); 
        ComboSexo.setSelection(0);
         
        final Calendar c = Calendar.getInstance();        
    	mYear = c.get(Calendar.YEAR);        
    	mMonth = c.get(Calendar.MONTH);        
    	mDay = c.get(Calendar.DAY_OF_MONTH);  
        updateDisplay();  
        txtApellidos.setText("");                
        txtNombres.setText("");
        
       

       
    }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.persona, container,
				false);
                
                IdPersona=getArguments().getInt("IdPersona");
		return view;
	}

         private void updateDisplay() {   

         calendar.set(mYear, mMonth, mDay);
    	 lblFecha.setText(new StringBuilder().append(mDay).append("/") .append(mMonth+ 1).append("/").append(mYear));


        } 
         
         
             public void btnRegistrar()
    {
        if(!txtApellidos.getText().toString().equals("") )
        {
            if(!txtNombres.getText().toString().equals("") )
          {
            entidad = new clsPersona();
            entidad.setInt_id_persona(IdPersona);
            entidad.setStr_nombres(txtNombres.getText().toString());
            entidad.setStr_apellidos(txtApellidos.getText().toString());
            entidad.setDat_fecha_nacimiento(calendar.getTime());
            if(ComboSexo.getSelectedItemPosition()==0)
                entidad.setBol_sexo(true);
            
                if(IdPersona==0)
                clsPersonaSQL.Agregar(this.getActivity(), entidad);
                else
                    clsPersonaSQL.Actualizar(this.getActivity(), entidad);
//                
               Toast.makeText(this.getActivity(),getString(R.string.str_registro_correcto), Toast.LENGTH_LONG).show();
            
            Fragment InicioFragment = new FragmentListaPersonas();	
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, InicioFragment);
            transaction.addToBackStack(null);
            transaction.commit(); 
          }
            else
                 Toast.makeText(this.getActivity(),getString(R.string.str_ingresar_nombre), Toast.LENGTH_LONG).show();
        } 
        else
            Toast.makeText(this.getActivity(),getString(R.string.str_ingresar_apellido), Toast.LENGTH_LONG).show();
        
    }
      public void btnCancelar()
    {
        Fragment InicioFragment = new FragmentListaPersonas();	
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, InicioFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        
        
    }
      public void btnFecha ()
      {
           DialogFragment newFragment = new SelectDateFragment();
           newFragment.show(getFragmentManager(), "DatePicker");
      }
      
      

    public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    	@Override
    	public Dialog onCreateDialog(Bundle savedInstanceState) {
			int yy = calendar.get(Calendar.YEAR);
			int mm = calendar.get(Calendar.MONTH);
			int dd = calendar.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    	}
    	
    	public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            mYear = yy;                    
            mMonth = mm;                    
            mDay = dd;   
            updateDisplay();        
    	}
    }
       

}
