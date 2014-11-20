package com.soltrux.app.demo.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.soltrux.app.demo.entidades.clsUsuarioMovil;
import com.soltrux.app.demo.http.http;
import com.soltrux.app.demo.sqlite.clsUsuarioMovilSQL;
import com.soltrux.app.demo.ui.CargandoActivity;
import com.soltrux.app.demo.ui.R;
import com.soltrux.app.demo.utilidades.utilidades;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentPregunta extends Fragment {

    private EditText txtComentario;
    private Button btnRegistrar;
    private ProgressDialog pd; 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_pregunta, container,
				false);
                txtComentario = (EditText) view.findViewById(R.id.txtComentario);
                txtComentario.setText("");

		
           
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
         pd = new ProgressDialog(this.getActivity());
                    pd.setMessage(getString(R.string.str_espere));     
                    pd.show();    
       if(!txtComentario.getText().toString().equals("") )
       {
           if(utilidades.verificaConexion(this.getActivity()))
            {
                     
                clsUsuarioMovil objUsuario=clsUsuarioMovilSQL.Buscar(this.getActivity());
                if(objUsuario==null)
                {                    
                    String dato=http.getUsuario(utilidades.getMail(this.getActivity()));
                    if(!dato.equals("") && !dato.equals(null))
                    {
                        try {
                            JSONObject objeto = new JSONObject(dato);
                            if(objeto.getInt("error")==0)
                            {
                                clsUsuarioMovil entidad=new clsUsuarioMovil();
                                entidad.setInt_id_usuario_movil(objeto.getInt("id_usuario"));
                                entidad.setStr_email(objeto.getString("email"));
                                entidad.setDat_fecha_creacion(new Date(objeto.getLong("fecha_registro")));
                                entidad.setBool_cerro(false);
                                entidad.setBool_gps(false);
                                clsUsuarioMovilSQL.Agregar(this.getActivity(), entidad);
                                
                                String id=http.getPregunta(entidad.getInt_id_usuario_movil(), txtComentario.getText().toString());
                                
                                JSONObject idJson = new JSONObject(id);
                                if(idJson.getInt("id")!=0)
                                {
                                    txtComentario.setText("");
                                    Toast.makeText(this.getActivity(),getString(R.string.str_pregunta_registro), Toast.LENGTH_LONG).show();
                                  
                                }
                                else
                                    Toast.makeText(this.getActivity(),getString(R.string.str_pregunta_error), Toast.LENGTH_LONG).show();
                                  
                                    
                                     

                            }
                        } catch (JSONException ex) {}
                    }
                }
                else
                {
                        try {
                            String id=http.getPregunta(objUsuario.getInt_id_usuario_movil(), txtComentario.getText().toString());
                            JSONObject idJson = new JSONObject(id);
                            if(idJson.getInt("id")!=0)
                            {
                                txtComentario.setText("");
                               
                                Toast.makeText(this.getActivity(),getString(R.string.str_pregunta_registro), Toast.LENGTH_LONG).show();
                                
                            }   
                            else
                                Toast.makeText(this.getActivity(),getString(R.string.str_pregunta_error), Toast.LENGTH_LONG).show();
                                  
                        } catch (JSONException ex) {}    
                }
                
            }
            else
               Toast.makeText(this.getActivity(),getString(R.string.str_conexion_internet), Toast.LENGTH_LONG).show();
       }
        else
            Toast.makeText(this.getActivity(),getString(R.string.str_ingrese_mensaje), Toast.LENGTH_LONG).show();
        pd.dismiss();
    }
     
    
}
