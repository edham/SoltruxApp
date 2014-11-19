package com.soltrux.app.demo.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
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
import com.soltrux.app.demo.ui.R;
import com.soltrux.app.demo.utilidades.utilidades;
import java.util.ArrayList;
import java.util.List;

public class FragmentListaPersonas extends Fragment {

     private  List<clsPersona> itens=null;
     private  List<clsPersona> itensTemp=null;
     private EditText txtFiltro;
     private ListView listPersonas;
     private Adaptador adaptador;
     private String[] listSexo;
     private Button btnNuevo;
    
    @Override
    public void onStart() 
    {
        super.onStart();
        
            btnNuevo = (Button) getView().findViewById(R.id.btnNuevo);
            btnNuevo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn_Nuevo();
                }
            });
        listSexo=getResources().getStringArray(R.array.array_sexo);
        listPersonas = (ListView)getView().findViewById(R.id.listPersonas); 
        itens= clsPersonaSQL.Listar(this.getActivity());
        txtFiltro = (EditText) getView().findViewById(R.id.txtFiltro);
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
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_lista_personas, container,
				false);
		return view;
	}
        
    public void Buscar(String filtro)
    {    
        itensTemp=new ArrayList<clsPersona>();
        for(clsPersona entidad:itens)
            if(entidad.getStr_nombres().toLowerCase().indexOf(filtro.toLowerCase()) != -1 || 
               entidad.getStr_apellidos().toLowerCase().indexOf(filtro.toLowerCase()) != -1)
        
        itensTemp.add(entidad);

       
        adaptador = new Adaptador(this.getActivity());
        listPersonas.setAdapter(adaptador);
        if(itensTemp==null && itensTemp.size()==0)
            Toast.makeText(this.getActivity(),"No se encontraron Itinerarios", Toast.LENGTH_SHORT).show(); 
    }
     
      class Adaptador extends ArrayAdapter {
    	
    	Activity context;
    	
    	Adaptador(Activity context) {
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
                
               AlertDialog.Builder alert = new AlertDialog.Builder(FragmentListaPersonas.this.getActivity());
                alert.setTitle(getString(R.string.str_desea_actualizar));
                alert.setPositiveButton(getString(R.string.btn_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {  
                        
                            Bundle args = new Bundle();
                            args.putInt("IdPersona",itensTemp.get(posicion).getInt_id_persona());
                            Fragment InicioFragment = new FragmentPersonas();
                            InicioFragment.setArguments(args);	
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.content_frame, InicioFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
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
                
                AlertDialog.Builder alert = new AlertDialog.Builder(FragmentListaPersonas.this.getActivity());
                alert.setTitle(getString(R.string.str_desea_eliminar));
                alert.setPositiveButton(getString(R.string.btn_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {  
                        clsPersonaSQL.BorrarId(context, itensTemp.get(posicion).getInt_id_persona());
                        itens= clsPersonaSQL.Listar(FragmentListaPersonas.this.getActivity());
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

        public void btn_Nuevo()
        {
//             Toast.makeText(this.getActivity(),"No se encontraron Itinerarios", Toast.LENGTH_SHORT).show(); 
            Bundle args = new Bundle();
            args.putInt("IdPersona",0);
            Fragment InicioFragment = new FragmentPersonas();
            InicioFragment.setArguments(args);	
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, InicioFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
}
