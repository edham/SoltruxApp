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
import com.soltrux.app.demo.servicio.Servicio;
import com.soltrux.app.demo.ui.R;

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
		return view;
	}


   
    public void btnRegistrar()
    {
        btnRegistrar.setBackgroundResource(R.drawable.ic_btn_on);
        Intent svc = new Intent(this.getActivity(), Servicio.class);
        this.getActivity().startService(svc);
    }
    
}
