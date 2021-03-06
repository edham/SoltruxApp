package com.soltrux.app.demo.fragment;

import android.content.Intent;
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
import com.soltrux.app.demo.servicio.Servicio;
import com.soltrux.app.demo.sqlite.clsUsuarioMovilSQL;
import com.soltrux.app.demo.ui.R;
import com.soltrux.app.demo.utilidades.utilidades;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentServicio extends Fragment {

    private Button btnRegistrar;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_servicio, container,
				false);
 

                btnRegistrar = (Button) view.findViewById(R.id.btnRegistrar);
                    btnRegistrar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btnRegistrar();
                        }
                });
                if(clsUsuarioMovilSQL.Buscar(this.getActivity())==null)
                    if(utilidades.verificaConexion(this.getActivity()))
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

                                    }
                                } catch (JSONException ex) {}
                                }
                        }
                     if(clsUsuarioMovilSQL.Buscar(this.getActivity()).isBool_cerro())
                         btnRegistrar.setBackgroundResource(R.drawable.ic_btn_on);
                    
		return view;
	}


   
    public void btnRegistrar()
    {
        
        if(!clsUsuarioMovilSQL.Buscar(this.getActivity()).isBool_cerro())
        {
            clsUsuarioMovilSQL.Actualizar(this.getActivity(), true);
            btnRegistrar.setBackgroundResource(R.drawable.ic_btn_on);
            Intent svc = new Intent(this.getActivity(), Servicio.class);
            this.getActivity().startService(svc);
        }
        else
        {
            clsUsuarioMovilSQL.Actualizar(this.getActivity(), false);
            btnRegistrar.setBackgroundResource(R.drawable.ic_btn_off);
            Intent svc = new Intent(this.getActivity(), Servicio.class);
            this.getActivity().stopService(svc);
        }
       
    }
    
}
