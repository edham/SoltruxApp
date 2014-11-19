/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soltrux.app.demo.sqlite;



import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.soltrux.app.demo.entidades.clsContacto;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Toditos
 */
public class clsContactoSQL {
    

    public static  List<clsContacto> Listar(Context context)
    {
        List<clsContacto> list=new ArrayList<clsContacto>();
        Cursor fila =context.getContentResolver().query(
            ContactsContract.Data.CONTENT_URI,
            new String[] { ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.TYPE },
            ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
            + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL", null,
            ContactsContract.Data.DISPLAY_NAME + " ASC");
             
            int numRows = fila.getCount();   
            fila.moveToFirst();   
                    for (int i = 0; i < numRows; ++i) 
                    {   
                        int colNumber = fila.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        int colContact = fila.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);                         
                        
                        list.add(new clsContacto(fila.getString(colNumber),fila.getString(colContact)));
                        
                        fila.moveToNext();   
                    } 
        return list;
     }
       
}
