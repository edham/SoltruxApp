package com.soltrux.app.demo.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.soltrux.app.demo.ui.R;

public class FragmentOne extends Fragment {

	ImageView ivIcon;
	TextView tvItemName;

	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_layout_one, container,
				false);

		ivIcon = (ImageView) view.findViewById(R.id.frag1_icon);
		tvItemName = (TextView) view.findViewById(R.id.frag1_text);

		tvItemName.setText(getArguments().getString(ITEM_NAME));
		ivIcon.setImageDrawable(view.getResources().getDrawable(
				getArguments().getInt(IMAGE_RESOURCE_ID)));
//                contactos();
		return view;
	}

        public void contactos()
        {
            Cursor mCursor =this.getActivity().getContentResolver().query(
            ContactsContract.Data.CONTENT_URI,
            new String[] { ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.TYPE },
            ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
            + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL", null,
            ContactsContract.Data.DISPLAY_NAME + " ASC");
             
            int numRows = mCursor.getCount();   
            mCursor.moveToFirst();   
                    for (int i = 0; i < numRows; ++i) 
                    {   
                        int colNumber = mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        int colContact = mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);                         
                        String number = mCursor.getString(colNumber);
                        String contact = mCursor.getString(colContact);
                        Toast.makeText(this.getActivity(), number+" - "+contact, Toast.LENGTH_SHORT).show();
                        mCursor.moveToNext();   
                    }   
    
        }
}
