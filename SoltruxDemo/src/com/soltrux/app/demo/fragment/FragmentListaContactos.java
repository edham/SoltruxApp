package com.soltrux.app.demo.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.soltrux.app.demo.entidades.clsContacto;
import com.soltrux.app.demo.sqlite.clsContactoSQL;
import com.soltrux.app.demo.ui.R;
import java.util.ArrayList;
import java.util.List;

public class FragmentListaContactos extends Fragment {

     private  List<clsContacto> itens=null;
     private  List<clsContacto> itensTemp=null;
     private EditText txtFiltro;
     private ListView listContactos;
     private Adaptador adaptador;
    
    @Override
    public void onStart() 
    {
        super.onStart();
        
          
        listContactos = (ListView)getView().findViewById(R.id.listContactos); 
        itens= clsContactoSQL.Listar(this.getActivity());
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

		View view = inflater.inflate(R.layout.lista_contactos, container,
				false);
		return view;
	}
        
    public void Buscar(String filtro)
    {    
        itensTemp=new ArrayList<clsContacto>();
        for(clsContacto entidad:itens)
            if(entidad.getNombre().toLowerCase().indexOf(filtro.toLowerCase()) != -1 || 
               entidad.getNumero().toLowerCase().indexOf(filtro.toLowerCase()) != -1)
        
        itensTemp.add(entidad);

       
        adaptador = new Adaptador(this.getActivity());
        listContactos.setAdapter(adaptador);
    }
     
      class Adaptador extends ArrayAdapter {
    	
    	Activity context;
    	
    	Adaptador(Activity context) {
    		super(context, R.layout.lista_contactos_list, itensTemp);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
            final int posicion = position;
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.lista_contactos_list, null);

            TextView lblNombresPersona = (TextView)item.findViewById(R.id.lblNombresPersona);
            lblNombresPersona.setText(itensTemp.get(position).getNombre());

            TextView lblNumero = (TextView)item.findViewById(R.id.lblNumero);
            lblNumero.setText(itensTemp.get(position).getNumero());

//
            Button btnLlamar = (Button)item.findViewById(R.id.btnLlamar);

            btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
               AlertDialog.Builder alert = new AlertDialog.Builder(FragmentListaContactos.this.getActivity());
                alert.setTitle(getString(R.string.str_desea_sms));
                alert.setPositiveButton(getString(R.string.btn_agregar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {  
                        
                           getLlamar(itensTemp.get(posicion).getNumero());
                        }});
                alert.setNegativeButton(getString(R.string.btn_cancelar), new DialogInterface.OnClickListener() {  
                       public void onClick(DialogInterface dialog, int whichButton) {    
                    }});
                       alert.show();

              
            }
            });

            Button btnSMS = (Button)item.findViewById(R.id.btnSMS);
            btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                AlertDialog.Builder alert = new AlertDialog.Builder(FragmentListaContactos.this.getActivity());
                alert.setTitle(getString(R.string.str_desea_sms));
                alert.setPositiveButton(getString(R.string.btn_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {  
                            Bundle args = new Bundle();
                            args.putString("Nombre",itensTemp.get(posicion).getNombre());
                            args.putString("Numero",itensTemp.get(posicion).getNumero());
                            Fragment Fragment = new FragmentSMS();
                            Fragment.setArguments(args);	
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.content_frame, Fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                       

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

    public void getLlamar(String Numero)
    {
         Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:"+Numero));
                            startActivity(intent);

    }
    
    
       
}
