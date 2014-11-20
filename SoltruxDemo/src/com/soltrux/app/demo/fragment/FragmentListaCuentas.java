package com.soltrux.app.demo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.soltrux.app.demo.entidades.clsCuenta;
import com.soltrux.app.demo.ui.R;
import com.soltrux.app.demo.utilidades.utilidades;
import java.util.ArrayList;
import java.util.List;

public class FragmentListaCuentas extends Fragment {

     private  List<clsCuenta> itens=null;
     private  List<clsCuenta> itensTemp=null;
     private EditText txtFiltro;
     private ListView listContactos;
     private Adaptador adaptador;
    
    @Override
    public void onStart() 
    {
        super.onStart();
        
          
        listContactos = (ListView)getView().findViewById(R.id.listContactos); 
        itens= utilidades.listCuentas(this.getActivity());
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

		View view = inflater.inflate(R.layout.fragment_lista_contactos, container,
				false);
		return view;
	}
        
    public void Buscar(String filtro)
    {    
        itensTemp=new ArrayList<clsCuenta>();
        for(clsCuenta entidad:itens)
            if(entidad.getNombre().toLowerCase().indexOf(filtro.toLowerCase()) != -1 || 
               entidad.getTipo().toLowerCase().indexOf(filtro.toLowerCase()) != -1)
        
        itensTemp.add(entidad);

       
        adaptador = new Adaptador(this.getActivity());
        listContactos.setAdapter(adaptador);
    }
     
      class Adaptador extends ArrayAdapter {
    	
    	Activity context;
    	
    	Adaptador(Activity context) {
    		super(context, R.layout.lista_cuentas_list, itensTemp);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
            final int posicion = position;
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.lista_cuentas_list, null);

            TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText(itensTemp.get(position).getNombre());

            TextView lblTipo = (TextView)item.findViewById(R.id.lblTipo);
            lblTipo.setText(itensTemp.get(position).getTipo());


            return(item);
		}
    }

}
