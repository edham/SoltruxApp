/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soltrux.app.demo.sqlite;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.soltrux.app.demo.entidades.clsPersona;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 *
 * @author Toditos
 */
public class clsPersonaSQL {
    
    private static String NombreTabla="PERSONA";
    
     public static void Agregar(Context context,clsPersona entidad)
    {
         bdSQLite admin=new bdSQLite(context,null); 
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        
        registro.put("str_nombres",entidad.getStr_nombres());
        registro.put("str_apellidos",entidad.getStr_apellidos());        
        if(entidad.isBol_sexo())
            registro.put("bool_sexo",1);
        else
            registro.put("bool_sexo",0);
        registro.put("dat_fecha_nacimiento",entidad.getDat_fecha_nacimiento().getTime());
        
        bd.insert(NombreTabla, null, registro);
        bd.close();
        
       
    }   

     public  static void Actualizar(Context context,clsPersona entidad) {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        
         registro.put("str_nombres",entidad.getStr_nombres());
        registro.put("str_apellidos",entidad.getStr_apellidos());        
        if(entidad.isBol_sexo())
            registro.put("bool_sexo",1);
        else
            registro.put("bool_sexo",0);
        registro.put("dat_fecha_nacimiento",entidad.getDat_fecha_nacimiento().getTime());
        
        bd.update(NombreTabla, registro, "int_id_persona="+entidad.getInt_id_persona(), null);
        bd.close();

    }
      

       public static  clsPersona Buscar(Context context, int id)
     {
        clsPersona  entidad=null;
        bdSQLite admin=new bdSQLite(context,null); 
        SQLiteDatabase bd=admin.getWritableDatabase();
          String query="select int_id_persona,str_nombres,str_apellidos,"
                  + "dat_fecha_nacimiento,bool_sexo from "+NombreTabla+" where int_id_persona="+id;
        Cursor fila=bd.rawQuery(query,null);

        if (fila.moveToFirst())
        {                    
            
            entidad = new clsPersona();
            entidad.setInt_id_persona(fila.getInt(0));
            entidad.setStr_nombres(fila.getString(1));
            entidad.setStr_apellidos(fila.getString(2));
            entidad.setDat_fecha_nacimiento(new Date(fila.getLong(3)));
            if(fila.getInt(4)==1)
            entidad.setBol_sexo(true);
           
        }
        bd.close();   
        return entidad;
     }
     
        public static  List<clsPersona> Listar(Context context)
     {
        List<clsPersona> list=new ArrayList<clsPersona>();
        bdSQLite admin=new bdSQLite(context,null); 
        SQLiteDatabase bd=admin.getWritableDatabase();
        String query="select int_id_persona,str_nombres,str_apellidos,bool_sexo,dat_fecha_nacimiento from "+NombreTabla;

        Cursor fila=bd.rawQuery(query,null);
        int numRows = fila.getCount();   
        fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsPersona entidad = new clsPersona();
                    entidad.setInt_id_persona(fila.getInt(0));
                    entidad.setStr_nombres(fila.getString(1));
                    entidad.setStr_apellidos(fila.getString(2));                    
                    if(fila.getInt(3)==1)
                    entidad.setBol_sexo(true);
                    entidad.setDat_fecha_nacimiento(new Date(fila.getLong(4)));
                    
                    list.add(entidad);
                       
                    fila.moveToNext();   
                }   

        bd.close();   
        return list;
     }
       
       
       
       
       
    public static void BorrarId(Context context,int id) {
     bdSQLite admin=new bdSQLite(context,null);
     SQLiteDatabase bd=admin.getWritableDatabase();
     bd.delete(NombreTabla, "int_id_persona=" + id , null);
     bd.close();
   }
    public static void Borrar(Context context) {
     bdSQLite admin=new bdSQLite(context,null);
     SQLiteDatabase bd=admin.getWritableDatabase();
     bd.delete(NombreTabla, null, null);
     bd.close();
    }
   
}
