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
import java.util.Date;



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
        registro.put("str_email",entidad.getStr_email());
        if(entidad.isBool_gps())
            registro.put("bool_gps",1);
        else
            registro.put("bool_gps",0);
        
        if(entidad.isBool_cerro())
            registro.put("bool_cerro",1);
        else
            registro.put("bool_cerro",0);
         
        registro.put("dat_fecha_creacion",entidad.getDat_fecha_creacion().getTime());
        bd.insert(NombreTabla, null, registro);
        bd.close();
    }   
  
     public  static boolean Actualizar(Context context,boolean cerro) {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        if(cerro)
            registro.put("bool_cerro",1);
        else
            registro.put("bool_cerro",0);
        int cant = bd.update(NombreTabla, registro, null, null);
        bd.close();
        if (cant == 1)
            return true;
        
         return false;
    }

       public static  clsUsuarioMovil Buscar(Context context)
     {
        clsUsuarioMovil  entidad=null;
        bdSQLite admin=new bdSQLite(context,null); 
        SQLiteDatabase bd=admin.getWritableDatabase();
          String query="select int_id_usuario_movil,str_email,bool_gps,"
                  + "bool_cerro,dat_fecha_creacion from "+NombreTabla;
        Cursor fila=bd.rawQuery(query,null);

        if (fila.moveToFirst())
        {                    
            
            entidad = new clsUsuarioMovil();
            entidad.setInt_id_usuario_movil(fila.getInt(0));
            entidad.setStr_email(fila.getString(1));
            
            if(fila.getInt(2)==1)
            entidad.setBool_gps(true);
            
            if(fila.getInt(3)==1)
            entidad.setBool_cerro(true);
            
            entidad.setDat_fecha_creacion(new Date(fila.getLong(4)));
           
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
