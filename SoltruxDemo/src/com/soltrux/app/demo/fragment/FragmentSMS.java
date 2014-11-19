package com.soltrux.app.demo.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.telephony.gsm.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.soltrux.app.demo.ui.R;

public class FragmentSMS extends Fragment {

    private String Numero;
    private String Nombre;
    private EditText txtComentario;
    private TextView lblNumero;
    private Button btnRegistrar;
    private Button btnCancelar;

        @Override
        public void onResume() {
            super.onResume();
      
                getActivity().getActionBar().setTitle(Nombre);
       
        }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.sms, container,
				false);
                Nombre=getArguments().getString("Nombre");
                Numero=getArguments().getString("Numero");
                
                txtComentario = (EditText) view.findViewById(R.id.txtComentario);
                txtComentario.setText("");
                
                lblNumero = (TextView) view.findViewById(R.id.lblNumero);
                lblNumero.setText(Numero);
		
                 btnCancelar = (Button) view.findViewById(R.id.btnCancelar);
                    btnCancelar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btnCancelar();
                        }
                    });            
                btnRegistrar = (Button) view.findViewById(R.id.btnRegistrar);
                    btnRegistrar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btnRegistrar();
                        }
            });
		return view;
	}

   public void btnCancelar()
    {
        Fragment Fragment = new FragmentListaContactos();	
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, Fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        
        
    }
   
    public void btnRegistrar()
    {
       if(!txtComentario.getText().toString().equals("") )
       {
            sendSMS(Numero,txtComentario.getText().toString());
       }
        else
            Toast.makeText(this.getActivity(),getString(R.string.str_ingrese_mensaje), Toast.LENGTH_LONG).show();
        
    }
    
     private void sendSMS(String address, String body){
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(address, null, body, null, null);
                        
                        Toast.makeText(this.getActivity(),getString(R.string.str_registro_correcto), Toast.LENGTH_LONG).show();
                         
                        Fragment Fragment = new FragmentListaContactos();	
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.content_frame, Fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        
                        
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
}
