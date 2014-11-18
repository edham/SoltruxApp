package com.soltrux.app.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.soltrux.app.demo.ui.R;

public class FragmentPersonas extends Fragment {

//	ImageView ivIcon;
//	TextView tvItemName;
//
//	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
//	public static final String ITEM_NAME = "itemName";
//
//	public FragmentListaPersonas() {
//
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.persona, container,
				false);

//		ivIcon = (ImageView) view.findViewById(R.id.frag1_icon);
//		tvItemName = (TextView) view.findViewById(R.id.frag1_text);
//
//		tvItemName.setText(getArguments().getString(ITEM_NAME));
//		ivIcon.setImageDrawable(view.getResources().getDrawable(
//				getArguments().getInt(IMAGE_RESOURCE_ID)));
		return view;
	}

        public void dato()
        {
            Fragment fragment = new FragmentListaPersonas();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, fragment);
            transaction.addToBackStack(null);
        }
}
