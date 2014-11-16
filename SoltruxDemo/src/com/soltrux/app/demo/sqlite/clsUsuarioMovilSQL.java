/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soltrux.app.demo.sqlite;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.soltrux.app.demo.entidades.clsUsuarioMovil;



/**
 *
 * @author Toditos
 */
public class clsUsuarioMovilSQL {
    
    private static String NombreTabla="USUARIO";
    
     public static void Agregar(Context context,clsUsuarioMovil entidad)
    {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_usuario_movil",entidad.getInt_id_usuario_movil());
        registro.put("str_nombres",entidad.getStr_nombres());
        registro.put("str_apellidos",entidad.getStr_apellidos());;
        registro.put("str_email",entidad.getStr_email());
        registro.put("str_telefono",entidad.getStr_telefono());
        bd.insert(NombreTabla, null, registro);
        bd.close();
    }   
  

       public static  clsUsuarioMovil Buscar(Context context)
     {
        clsUsuarioMovil  entidad=null;
        bdSQLite admin=new bdSQLite(context,null); 
        SQLiteDatabase bd=admin.getWritableDatabase();
          String query="select int_id_usuario_movil,str_nombres,str_apellidos,"
                  + "str_email,str_telefono from "+NombreTabla;
        Cursor fila=bd.rawQuery(query,null);

        if (fila.moveToFirst())
        {                    
            
            entidad = new clsUsuarioMovil();
            entidad.setInt_id_usuario_movil(fila.getInt(0));
            entidad.setStr_nombres(fila.getString(1));
            entidad.setStr_apellidos(fila.getString(2));
            entidad.setStr_email(fila.getString(4));
            entidad.setStr_telefono(fila.getString(4));
           
        }
        bd.close();   
        return entidad;
     }
     
    public static void Borrar(Context context) {
     bdSQLite admin=new bdSQLite(context,null);
     SQLiteDatabase bd=admin.getWritableDatabase();
     bd.delete(NombreTabla, null, null);
     bd.close();
    }
   
}
